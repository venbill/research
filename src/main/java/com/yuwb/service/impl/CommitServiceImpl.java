package com.yuwb.service.impl;

import com.yuwb.domain.Answer;
import com.yuwb.domain.RtData;
import com.yuwb.enums.ErrorCode;
import com.yuwb.repository.AnswerRepository;
import com.yuwb.repository.WeUserRepository;
import com.yuwb.service.CommitService;
import com.yuwb.domain.Commit;
import com.yuwb.repository.CommitRepository;
import com.yuwb.util.ResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * Service Implementation for managing Commit.
 */
@Service
@Transactional
public class CommitServiceImpl implements CommitService {

    private final Logger log = LoggerFactory.getLogger(CommitServiceImpl.class);

    private final CommitRepository commitRepository;

    private final WeUserRepository weUserRepository;

    private final AnswerRepository answerRepository;

    public CommitServiceImpl(CommitRepository commitRepository,WeUserRepository weUserRepository,AnswerRepository answerRepository) {
        this.commitRepository = commitRepository;
        this.weUserRepository = weUserRepository;
        this.answerRepository = answerRepository;
    }

    /**
     * Save a commit.
     *
     * @param commit the entity to save
     * @return the persisted entity
     */
    @Override
    public Commit save(Commit commit) {
        log.debug("Request to save Commit : {}", commit);
        return commitRepository.save(commit);
    }

    /**
     * Get all the commits.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Commit> findAll(Pageable pageable) {
        log.debug("Request to get all Commits");
        return commitRepository.findAll(pageable);
    }

    /**
     * Get one commit by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Commit findOne(Long id) {
        log.debug("Request to get Commit : {}", id);
        return commitRepository.findOne(id);
    }

    /**
     * Delete the commit by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Commit : {}", id);
        commitRepository.delete(id);
    }


    @Override
    public Long getCommitNoByResearchId(Long researchId) {
        return commitRepository.countByResearchId(researchId);
    }


    @Override
    public List<Commit> findCommitByUserId(Long userId) {
        return commitRepository.findByUserIdOrderByCreateTimeDesc(userId);
    }

    @Override
    public boolean isUserCommit(long researchId, String openId) {
        return commitRepository.countByResearchIdAndUserId(researchId,weUserRepository.findByOpenId(openId).getId())>0;

    }

    @Transactional
    @Override
    public RtData commitAnswer(long researchId, String openId, List<String> answers) {
        if(isUserCommit(researchId,openId)){
            //已经做过这份问卷
            return ResponseBuilder.build(ErrorCode.REPEAT_COMMIT);
        }else{
            //录入答案
            try{
                //问卷是否在有效期内 todo


                //题目是否答完

                long userId = weUserRepository.findByOpenId(openId).getId();
                Commit commit = new Commit();
                commit.setResearchId(researchId);
                commit.setUserId(userId);
                commit.setCreateTime(new Date());
                commit = commitRepository.save(commit);
                for (String str:answers) {
                    if(!str.contains(",")){
//                        throw new Exception();
                    }else{
                        String [] kv = str.split(",");
                        Answer answer = new Answer();
                        answer.setCommitId(commit.getId());
                        answer.setCreateTime(new Date());
                        answer.setOptionId(userId);
                        answer.setAnswerId(Long.decode(kv[1]));
                        answer.setQuestionId(Long.decode(kv[0]));
                        answerRepository.save(answer);
                    }
                }

                return ResponseBuilder.ok(commit);


            }catch (Exception e){
                return ResponseBuilder.build(ErrorCode.NOT_SUCCESS);
            }

        }



    }

    @Override
    public Commit findByResearchIdAndOpenId(long researchId, String openId) {
        long userId = weUserRepository.findByOpenId(openId).getId();
        List<Commit> commitList = commitRepository.findByResearchIdAndUserId(researchId,userId);
        if(commitList==null||commitList.size()==0){
            return new Commit();
        }else{
            return commitList.get(0);
        }


    }
}
