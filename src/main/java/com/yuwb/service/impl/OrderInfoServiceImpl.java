package com.yuwb.service.impl;

import com.yuwb.service.OrderInfoService;
import com.yuwb.domain.OrderInfo;
import com.yuwb.repository.OrderInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing OrderInfo.
 */
@Service
@Transactional
public class OrderInfoServiceImpl implements OrderInfoService {

    private final Logger log = LoggerFactory.getLogger(OrderInfoServiceImpl.class);

    private final OrderInfoRepository orderInfoRepository;

    public OrderInfoServiceImpl(OrderInfoRepository orderInfoRepository) {
        this.orderInfoRepository = orderInfoRepository;
    }

    /**
     * Save a orderInfo.
     *
     * @param orderInfo the entity to save
     * @return the persisted entity
     */
    @Override
    public OrderInfo save(OrderInfo orderInfo) {
        log.debug("Request to save OrderInfo : {}", orderInfo);
        return orderInfoRepository.save(orderInfo);
    }

    /**
     * Get all the orderInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderInfo> findAll(Pageable pageable) {
        log.debug("Request to get all OrderInfos");
        return orderInfoRepository.findAll(pageable);
    }

    /**
     * Get one orderInfo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public OrderInfo findOne(Long id) {
        log.debug("Request to get OrderInfo : {}", id);
        return orderInfoRepository.findOne(id);
    }

    /**
     * Delete the orderInfo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderInfo : {}", id);
        orderInfoRepository.delete(id);
    }
}
