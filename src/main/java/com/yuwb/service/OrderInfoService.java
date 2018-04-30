package com.yuwb.service;

import com.yuwb.domain.OrderInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing OrderInfo.
 */
public interface OrderInfoService {

    /**
     * Save a orderInfo.
     *
     * @param orderInfo the entity to save
     * @return the persisted entity
     */
    OrderInfo save(OrderInfo orderInfo);

    /**
     * Get all the orderInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderInfo> findAll(Pageable pageable);

    /**
     * Get the "id" orderInfo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    OrderInfo findOne(Long id);

    /**
     * Delete the "id" orderInfo.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
