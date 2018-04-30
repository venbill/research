package com.yuwb.service.impl;

import com.yuwb.domain.Prize;
import com.yuwb.domain.PrizeDetail;
import com.yuwb.repository.PrizeRepository;
import com.yuwb.service.OrderRecordService;
import com.yuwb.domain.OrderRecord;
import com.yuwb.repository.OrderRecordRepository;
import com.yuwb.service.UserService;
import com.yuwb.service.WeUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * Service Implementation for managing OrderRecord.
 */
@Service
@Transactional
public class OrderRecordServiceImpl implements OrderRecordService {

    private final Logger log = LoggerFactory.getLogger(OrderRecordServiceImpl.class);

    private final OrderRecordRepository orderRecordRepository;

    private final WeUserService weUserService;

    private final PrizeRepository prizeRepository;

    public OrderRecordServiceImpl(OrderRecordRepository orderRecordRepository, WeUserService weUserService, PrizeRepository prizeRepository) {
        this.orderRecordRepository = orderRecordRepository;
        this.weUserService = weUserService;
        this.prizeRepository = prizeRepository;
    }

    /**
     * Save a orderRecord.
     *
     * @param orderRecord the entity to save
     * @return the persisted entity
     */
    @Override
    public OrderRecord save(OrderRecord orderRecord) {
        log.debug("Request to save OrderRecord : {}", orderRecord);
        return orderRecordRepository.save(orderRecord);
    }

    /**
     * Get all the orderRecords.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderRecord> findAll(Pageable pageable) {
        log.debug("Request to get all OrderRecords");
        return orderRecordRepository.findAll(pageable);
    }

    /**
     * Get one orderRecord by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OrderRecord findOne(Long id) {
        log.debug("Request to get OrderRecord : {}", id);
        return orderRecordRepository.findOne(id);
    }

    /**
     * Delete the orderRecord by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderRecord : {}", id);
        orderRecordRepository.delete(id);
    }


    @Override
    public OrderRecord findUserOrder(long researchId, long userId) {
        List<OrderRecord> orderRecords = orderRecordRepository.findByResearchIdAndUserId(researchId,userId);
        if(orderRecords!=null&&orderRecords.size()>0){
            System.out.println("====>");
            System.out.println(orderRecords.get(0));
            return orderRecords.get(0);
        }else{
            return new OrderRecord();
        }
    }

    @Override
    public List<PrizeDetail> findMyOrderList(String openId) {
        long userId = weUserService.findUserIdByOpenId(openId);
        List<OrderRecord> orderRecordList = orderRecordRepository.findByUserId(userId);
        List<PrizeDetail> prizeDetailList = new ArrayList<>();


        for (OrderRecord order:orderRecordList) {
            Prize prize = prizeRepository.findOne(order.getPrizeId());
            PrizeDetail prizeDetail = new PrizeDetail();
            prizeDetail.setPrizeName(prize.getName());
            prizeDetail.setCreateTime(order.getCreateTime());
            prizeDetailList.add(prizeDetail);
        }

        return prizeDetailList;
    }
}
