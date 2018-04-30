package com.yuwb.repository;

import com.yuwb.domain.WeUser;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the WeUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WeUserRepository extends JpaRepository<WeUser, Long> {
    WeUser findByOpenId(String openId);
}
