package com.yuwb.service;

import com.yuwb.domain.Question;
import com.yuwb.domain.QuestionDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service Interface for managing Question.
 */
public interface QuestionService {

    /**
     * Save a question.
     *
     * @param question the entity to save
     * @return the persisted entity
     */
    Question save(Question question);

    /**
     * Get all the questions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Question> findAll(Pageable pageable);


    List<Question> getResearchQuestionList(long id);

    /**
     * Get the "id" question.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Question findOne(Long id);

    /**
     * Delete the "id" question.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
