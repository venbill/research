package com.yuwb.repository;

import com.yuwb.domain.Answer;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the Answer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByCommitIdEquals(long commitId);
}
