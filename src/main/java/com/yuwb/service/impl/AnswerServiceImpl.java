package com.yuwb.service.impl;

import com.yuwb.domain.*;
import com.yuwb.enums.ErrorCode;
import com.yuwb.enums.IsAnswer;
import com.yuwb.service.AnswerService;
import com.yuwb.repository.AnswerRepository;
import com.yuwb.service.CommitService;
import com.yuwb.service.QuestionService;
import com.yuwb.util.ResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * Service Implementation for managing Answer.
 */
@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {

    private final Logger log = LoggerFactory.getLogger(AnswerServiceImpl.class);

    private final AnswerRepository answerRepository;

    private final CommitService commitService;

    private final QuestionService questionService;

    public AnswerServiceImpl(AnswerRepository answerRepository,CommitService commitService,QuestionService questionService) {
        this.answerRepository = answerRepository;
        this.commitService = commitService;
        this.questionService = questionService;
    }

    /**
     * Save a answer.
     *
     * @param answer the entity to save
     * @return the persisted entity
     */
    @Override
    public Answer save(Answer answer) {
        log.debug("Request to save Answer : {}", answer);
        return answerRepository.save(answer);
    }

    /**
     * Get all the answers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Answer> findAll(Pageable pageable) {
        log.debug("Request to get all AnswersVM");
        return answerRepository.findAll(pageable);
    }

    /**
     * Get one answer by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Answer findOne(Long id) {
        log.debug("Request to get Answer : {}", id);
        return answerRepository.findOne(id);
    }

    /**
     * Delete the answer by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Answer : {}", id);
        answerRepository.delete(id);
    }

    @Override
    public RtData getResearchAnswers(long researchId, String openId){
        if(!commitService.isUserCommit(researchId,openId)){
            return ResponseBuilder.build(ErrorCode.NOT_COMMIT);
        }else{
            Commit commit=commitService.findByResearchIdAndOpenId(researchId,openId);
            List<Answer> answerList = answerRepository.findByCommitIdEquals(commit.getId());
            List<Question> questionList = questionService.getResearchQuestionList(researchId);
            List<Question > questions = new ArrayList<>();
            for (Question q:questionList) {
                List<SelectOption > selectOptionList = q.getOptionList();
                List<SelectOption> selectOptions = new ArrayList<>();
                long answerId = getAnswerIdFromAnswerList(q.getId(),answerList);
                for (SelectOption o:selectOptionList) {
                    SelectOption selectOption = new SelectOption();
                    selectOption.setId(o.getId());
                    selectOption.setIsAnswer(o.getIsAnswer());
                    selectOption.setOrderNo(o.getOrderNo());
                    selectOption.setQuestionId(o.getQuestionId());
                    selectOption.setContent(o.getContent());

                    if(selectOption.getId().equals(answerId)){
                        selectOption.setIsAnswer(IsAnswer.SELECTED.getCode());
                    }else{
                        selectOption.setIsAnswer(IsAnswer.NOT_SELECTED.getCode());
                    }
                    selectOptions.add(selectOption);
                }
                q.setOptionList(selectOptions);
                questions.add(q);
            }

            return ResponseBuilder.ok(questions);

        }
    }



    private long getAnswerIdFromAnswerList(long questionId,List<Answer> answerList){
        long answerId =0;
        for (Answer answer:answerList) {
            if(answer.getQuestionId().equals(questionId)){
                answerId = answer.getAnswerId();
                break;
            }
        }
        return answerId;
    }
}
