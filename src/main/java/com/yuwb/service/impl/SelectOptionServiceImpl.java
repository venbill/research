package com.yuwb.service.impl;

import com.yuwb.service.SelectOptionService;
import com.yuwb.domain.SelectOption;
import com.yuwb.repository.SelectOptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing SelectOption.
 */
@Service
@Transactional
public class SelectOptionServiceImpl implements SelectOptionService {

    private final Logger log = LoggerFactory.getLogger(SelectOptionServiceImpl.class);

    private final SelectOptionRepository selectOptionRepository;

    public SelectOptionServiceImpl(SelectOptionRepository selectOptionRepository) {
        this.selectOptionRepository = selectOptionRepository;
    }

    /**
     * Save a selectOption.
     *
     * @param selectOption the entity to save
     * @return the persisted entity
     */
    @Override
    public SelectOption save(SelectOption selectOption) {
        log.debug("Request to save SelectOption : {}", selectOption);
        return selectOptionRepository.save(selectOption);
    }

    /**
     * Get all the selectOptions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SelectOption> findAll(Pageable pageable) {
        log.debug("Request to get all SelectOptions");
        return selectOptionRepository.findAll(pageable);
    }

    /**
     * Get one selectOption by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public SelectOption findOne(Long id) {
        log.debug("Request to get SelectOption : {}", id);
        return selectOptionRepository.findOne(id);
    }

    /**
     * Delete the selectOption by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SelectOption : {}", id);
        selectOptionRepository.delete(id);
    }
}
