package com.yuwb.service;

import com.yuwb.domain.Answer;
import com.yuwb.domain.RtData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Answer.
 */
public interface AnswerService {

    /**
     * Save a answer.
     *
     * @param answer the entity to save
     * @return the persisted entity
     */
    Answer save(Answer answer);

    /**
     * Get all the answers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Answer> findAll(Pageable pageable);

    /**
     * Get the "id" answer.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Answer findOne(Long id);

    /**
     * Delete the "id" answer.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    RtData getResearchAnswers(long researchId,String openId);
}
