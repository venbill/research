package com.yuwb.repository;

import com.yuwb.domain.Research;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.Date;
import java.util.List;


/**
 * Spring Data JPA repository for the Research entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResearchRepository extends JpaRepository<Research, Long> {
    List<Research> findByStartTimeBeforeAndEndTimeAfterAndReceiveEqualsAndNameLike(Date startTime,Date endTime,Integer receive,String name);

    List<Research> findByStartTimeBeforeAndEndTimeAfterAndReceiveEquals(Date startTime,Date endTime, Integer receive);
}
