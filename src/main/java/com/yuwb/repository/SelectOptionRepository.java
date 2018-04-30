package com.yuwb.repository;

import com.yuwb.domain.SelectOption;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the SelectOption entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SelectOptionRepository extends JpaRepository<SelectOption, Long> {

    List<SelectOption> findByQuestionIdOrderByOrderNo(long id);
}
