package com.yuwb.service.impl;

import com.yuwb.service.PrizeService;
import com.yuwb.domain.Prize;
import com.yuwb.repository.PrizeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Service Implementation for managing Prize.
 */
@Service
@Transactional
public class PrizeServiceImpl implements PrizeService {

    private final Logger log = LoggerFactory.getLogger(PrizeServiceImpl.class);

    private final PrizeRepository prizeRepository;

    public PrizeServiceImpl(PrizeRepository prizeRepository) {
        this.prizeRepository = prizeRepository;
    }

    /**
     * Save a prize.
     *
     * @param prize the entity to save
     * @return the persisted entity
     */
    @Override
    public Prize save(Prize prize) {
        log.debug("Request to save Prize : {}", prize);
        return prizeRepository.save(prize);
    }

    /**
     * Get all the prizes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Prize> findAll(Pageable pageable) {
        log.debug("Request to get all Prizes");
        return prizeRepository.findAll(pageable);
    }

    /**
     * Get one prize by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Prize findOne(Long id) {
        log.debug("Request to get Prize : {}", id);
        return prizeRepository.findOne(id);
    }

    /**
     * Delete the prize by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Prize : {}", id);
        prizeRepository.delete(id);
    }

    @Override
    public List<Prize> getPrizeList() {
        return prizeRepository.findAll();
    }
}
