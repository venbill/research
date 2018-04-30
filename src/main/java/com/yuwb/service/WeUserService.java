package com.yuwb.service;

import com.yuwb.domain.WeUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing WeUser.
 */
public interface WeUserService {

    /**
     * Save a weUser.
     *
     * @param weUser the entity to save
     * @return the persisted entity
     */
    WeUser save(WeUser weUser);

    /**
     * Get all the weUsers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<WeUser> findAll(Pageable pageable);

    /**
     * Get the "id" weUser.
     *
     * @param id the id of the entity
     * @return the entity
     */
    WeUser findOne(Long id);

    /**
     * Delete the "id" weUser.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    WeUser findByOpenId(String openId);

    long findUserIdByOpenId(String openId);


    WeUser login(String openId);
}
