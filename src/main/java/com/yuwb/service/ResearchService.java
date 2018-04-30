package com.yuwb.service;

import com.yuwb.domain.Research;
import com.yuwb.domain.ResearchDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service Interface for managing Research.
 */
public interface ResearchService {

    /**
     * Save a research.
     *
     * @param research the entity to save
     * @return the persisted entity
     */
    Research save(Research research);

    /**
     * Get all the research.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Research> findAll(Pageable pageable);

    List<ResearchDetail> queryEnabledResearchs(String name, String openId);

    /**
     * Get the "id" research.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Research findOne(Long id);

    /**
     * Delete the "id" research.
     *
     * @param id the id of the entity
     */
    void delete(Long id);


    List<ResearchDetail> queryMyResearchs(String openId);
}
