package com.yuwb.service.impl;

import com.yuwb.domain.Commit;
import com.yuwb.domain.ResearchDetail;
import com.yuwb.enums.ReceiveType;
import com.yuwb.repository.CommitRepository;
import com.yuwb.service.CommitService;
import com.yuwb.service.OrderRecordService;
import com.yuwb.service.ResearchService;
import com.yuwb.domain.Research;
import com.yuwb.repository.ResearchRepository;
import com.yuwb.service.WeUserService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Service Implementation for managing Research.
 */
@Service
@Transactional
public class ResearchServiceImpl implements ResearchService {

    private final Logger log = LoggerFactory.getLogger(ResearchServiceImpl.class);

    private final ResearchRepository researchRepository;

    private final CommitService commitService;

    private final WeUserService weUserService;

    private final OrderRecordService orderRecordService;

    public ResearchServiceImpl(ResearchRepository researchRepository,CommitService commitService,WeUserService weUserService,OrderRecordService orderRecordService) {
        this.researchRepository = researchRepository;
        this.commitService = commitService;
        this.weUserService = weUserService;
        this.orderRecordService = orderRecordService;
    }

    /**
     * Save a research.
     *
     * @param research the entity to save
     * @return the persisted entity
     */
    @Override
    public Research save(Research research) {
        log.debug("Request to save Research : {}", research);
        return researchRepository.save(research);
    }

    /**
     * Get all the research.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Research> findAll(Pageable pageable) {
        log.debug("Request to get all Research");
        return researchRepository.findAll(pageable);
    }


    @Override
    public List<ResearchDetail> queryEnabledResearchs(String name, String openId) {
        Date today = new Date();
        List<Research> researchList ;
        List<ResearchDetail> researchs = new ArrayList<>();

        if(StringUtils.isNotEmpty(name)){

            researchList = researchRepository.findByStartTimeBeforeAndEndTimeAfterAndReceiveEqualsAndNameLike(today,today, ReceiveType.PUBLISH.getCode(),"%"+name+"%");
        }else{
            researchList = researchRepository.findByStartTimeBeforeAndEndTimeAfterAndReceiveEquals(today,today,ReceiveType.PUBLISH.getCode());
        }
        for (Research research:researchList) {
            //统计回答人数
            ResearchDetail researchDetail = new ResearchDetail(research,commitService.isUserCommit(research.getId(),openId),commitService.getCommitNoByResearchId(research.getId()));


            researchs.add(researchDetail);
        }



        return researchs;

    }

    /**
     * Get one research by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Research findOne(Long id) {
        log.debug("Request to get Research : {}", id);
        return researchRepository.findOne(id);
    }

    /**
     * Delete the research by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Research : {}", id);
        researchRepository.delete(id);
    }

    @Override
    public List<ResearchDetail> queryMyResearchs(String openId) {
        long userId = weUserService.findUserIdByOpenId(openId);
        List<Commit> commitList = commitService.findCommitByUserId(userId);
        List<ResearchDetail> researchList = new ArrayList<>();
        for (Commit commit:commitList) {
            Research research =findOne(commit.getResearchId());
            research.setCreateTime(commit.getCreateTime());
            ResearchDetail researchDetail = new ResearchDetail(research, orderRecordService.findUserOrder(commit.getResearchId(),userId).getId());
            researchList.add(researchDetail);
        }

        return researchList;
    }
}
