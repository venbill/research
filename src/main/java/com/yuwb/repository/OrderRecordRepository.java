package com.yuwb.repository;

import com.yuwb.domain.OrderRecord;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the OrderRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderRecordRepository extends JpaRepository<OrderRecord, Long> {
    List<OrderRecord> findByResearchIdAndUserId(long researchId,long userId);


    List<OrderRecord> findByUserId(long userId);
}
