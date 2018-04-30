package com.yuwb.service;

import com.yuwb.domain.Commit;
import com.yuwb.domain.RtData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service Interface for managing Commit.
 */
public interface CommitService {

    /**
     * Save a commit.
     *
     * @param commit the entity to save
     * @return the persisted entity
     */
    Commit save(Commit commit);

    /**
     * Get all the commits.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Commit> findAll(Pageable pageable);

    /**
     * Get the "id" commit.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Commit findOne(Long id);

    /**
     * Delete the "id" commit.
     *
     * @param id the id of the entity
     */
    void delete(Long id);


    Long getCommitNoByResearchId(Long researchId);


    List<Commit> findCommitByUserId(Long userId);


    boolean isUserCommit(long researchId,String openId);


    RtData commitAnswer(long researchId,String openId,List<String> answers);


    Commit findByResearchIdAndOpenId(long researchId,String openId);
}
