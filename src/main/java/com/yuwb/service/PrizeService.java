package com.yuwb.service;

import com.yuwb.domain.Prize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service Interface for managing Prize.
 */
public interface PrizeService {

    /**
     * Save a prize.
     *
     * @param prize the entity to save
     * @return the persisted entity
     */
    Prize save(Prize prize);

    /**
     * Get all the prizes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Prize> findAll(Pageable pageable);

    /**
     * Get the "id" prize.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Prize findOne(Long id);

    /**
     * Delete the "id" prize.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    List<Prize> getPrizeList();
}
