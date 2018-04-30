package com.yuwb.service.impl;

import com.yuwb.service.WeUserService;
import com.yuwb.domain.WeUser;
import com.yuwb.repository.WeUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


/**
 * Service Implementation for managing WeUser.
 */
@Service
@Transactional
public class WeUserServiceImpl implements WeUserService {

    private final Logger log = LoggerFactory.getLogger(WeUserServiceImpl.class);

    private final WeUserRepository weUserRepository;

    public WeUserServiceImpl(WeUserRepository weUserRepository) {
        this.weUserRepository = weUserRepository;
    }

    /**
     * Save a weUser.
     *
     * @param weUser the entity to save
     * @return the persisted entity
     */
    @Override
    public WeUser save(WeUser weUser) {
        log.debug("Request to save WeUser : {}", weUser);
        if(weUser.getId()==null){
            WeUser user =weUserRepository.findByOpenId(weUser.getOpenId());
            if(user!=null){
                weUser.setId(user.getId());
            }

        }
        return weUserRepository.save(weUser);
    }

    /**
     * Get all the weUsers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<WeUser> findAll(Pageable pageable) {
        log.debug("Request to get all WeUsers");
        return weUserRepository.findAll(pageable);
    }

    /**
     * Get one weUser by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public WeUser findOne(Long id) {
        log.debug("Request to get WeUser : {}", id);
        return weUserRepository.findOne(id);
    }

    /**
     * Delete the weUser by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete WeUser : {}", id);
        weUserRepository.delete(id);
    }


    @Override
    public WeUser findByOpenId(String openId) {
        return weUserRepository.findByOpenId(openId);
    }

    @Override
    public long findUserIdByOpenId(String openId) {
        return weUserRepository.findByOpenId(openId).getId();
    }

    @Override
    public WeUser login(String openId) {
        WeUser weUser =  weUserRepository.findByOpenId(openId);
        if(weUser==null){
            weUser = new WeUser();
            weUser.setOpenId(openId);
            weUser.setCreateTime(new Date());
            weUser = weUserRepository.save(weUser);
        }
        return weUser;
    }
}
