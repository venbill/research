package com.yuwb.service;

import com.yuwb.domain.OrderRecord;
import com.yuwb.domain.PrizeDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing OrderRecord.
 */
public interface OrderRecordService {

    /**
     * Save a orderRecord.
     *
     * @param orderRecord the entity to save
     * @return the persisted entity
     */
    OrderRecord save(OrderRecord orderRecord);

    /**
     * Get all the orderRecords.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrderRecord> findAll(Pageable pageable);

    /**
     * Get the "id" orderRecord.
     *
     * @param id the id of the entity
     * @return the entity
     */
    OrderRecord findOne(Long id);

    /**
     * Delete the "id" orderRecord.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    OrderRecord findUserOrder(long researchId,long userId);


    List<PrizeDetail> findMyOrderList(String openId);
}
