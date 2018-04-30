package com.yuwb.repository;

import com.yuwb.domain.OrderInfo;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the OrderInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long> {

}
