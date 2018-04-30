package com.yuwb.repository;

import com.yuwb.domain.Commit;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the Commit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommitRepository extends JpaRepository<Commit, Long> {
//    @Query(" select count(t) from FollowerInfo t where investUserId = :invUserId")
//    Integer findFollowerNumberByInvUserId(@Param("invUserId") Long invUserId);


    Long countByResearchId(long researchId);


    Long countByResearchIdAndUserId(long researchId,long userId);

    List<Commit> findByResearchIdAndUserId(long researchId,long userId);


    List<Commit>findByUserIdOrderByCreateTimeDesc(long userId);
}
