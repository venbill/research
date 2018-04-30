package com.yuwb.repository;

import com.yuwb.domain.Prize;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Prize entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrizeRepository extends JpaRepository<Prize, Long> {

}
