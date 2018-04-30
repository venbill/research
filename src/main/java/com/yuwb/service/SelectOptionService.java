package com.yuwb.service;

import com.yuwb.domain.SelectOption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing SelectOption.
 */
public interface SelectOptionService {

    /**
     * Save a selectOption.
     *
     * @param selectOption the entity to save
     * @return the persisted entity
     */
    SelectOption save(SelectOption selectOption);

    /**
     * Get all the selectOptions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SelectOption> findAll(Pageable pageable);

    /**
     * Get the "id" selectOption.
     *
     * @param id the id of the entity
     * @return the entity
     */
    SelectOption findOne(Long id);

    /**
     * Delete the "id" selectOption.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
