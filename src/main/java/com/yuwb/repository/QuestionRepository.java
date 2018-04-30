package com.yuwb.repository;

import com.yuwb.domain.Question;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the Question entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByResearchIdOrderByOrderNo(long id);

}
