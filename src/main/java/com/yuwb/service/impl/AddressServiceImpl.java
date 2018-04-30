package com.yuwb.service.impl;

import com.yuwb.domain.*;
import com.yuwb.enums.ErrorCode;
import com.yuwb.repository.OrderRecordRepository;
import com.yuwb.repository.PrizeRepository;
import com.yuwb.repository.ResearchRepository;
import com.yuwb.service.AddressService;
import com.yuwb.repository.AddressRepository;
import com.yuwb.service.CommitService;
import com.yuwb.service.WeUserService;
import com.yuwb.util.ResponseBuilder;
import com.yuwb.web.rest.vm.AddressVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


/**
 * Service Implementation for managing Address.
 */
@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    private final Logger log = LoggerFactory.getLogger(AddressServiceImpl.class);

    private final AddressRepository addressRepository;

    private final CommitService commitService;

    private final WeUserService weUserService;

    private final ResearchRepository researchRepository;

    private final PrizeRepository prizeRepository;

    private final OrderRecordRepository orderRecordRepository;


    public AddressServiceImpl(AddressRepository addressRepository, CommitService commitService, WeUserService weUserService, ResearchRepository researchRepository, PrizeRepository prizeRepository, OrderRecordRepository orderRecordRepository) {
        this.addressRepository = addressRepository;
        this.commitService = commitService;
        this.weUserService = weUserService;
        this.researchRepository = researchRepository;
        this.prizeRepository = prizeRepository;
        this.orderRecordRepository = orderRecordRepository;
    }



    /**
     * Save a address.
     *
     * @param address the entity to save
     * @return the persisted entity
     */
    @Override
    public Address save(Address address) {
        log.debug("Request to save Address : {}", address);
        return addressRepository.save(address);
    }

    /**
     * Get all the addresses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Address> findAll(Pageable pageable) {
        log.debug("Request to get all Addresses");
        return addressRepository.findAll(pageable);
    }

    /**
     * Get one address by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Address findOne(Long id) {
        log.debug("Request to get Address : {}", id);
        return addressRepository.findOne(id);
    }

    /**
     * Delete the address by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Address : {}", id);
        addressRepository.delete(id);
    }


    @Override
    @Transactional
    public RtData add(AddressVM addressVM) {
        Commit commit = commitService.findByResearchIdAndOpenId(addressVM.getResearchId(),addressVM.getOpenId());
        if(commit==null||commit.getId()==null||commit.getId()==0){
            return ResponseBuilder.fail(ErrorCode.CREATE_ERROR,"请先回答问卷，再领取奖品！");
        }else if(commit.getOrderId()!=null&&commit.getOrderId()!=0){
            return ResponseBuilder.fail(ErrorCode.CREATE_ERROR,"已经领取过该奖品，无法创建订单！");
        }else{
            long userId = weUserService.findUserIdByOpenId(addressVM.getOpenId());
            //创建地址
            Address address = new Address();
            address.setCreateTime(new Date());
            address.setPersonName(addressVM.getPersonName());
            address.setTel(addressVM.getTel());
            address.setAddress(addressVM.getAddress());
            address.setUserId(userId);

            address = save(address);


            //获取奖品内容
            Research research = researchRepository.findOne(addressVM.getResearchId());




            //创建订单
            OrderRecord orderRecord = new OrderRecord();
            orderRecord.setAddressId(address.getId());
            orderRecord.setCreateTime(new Date());
            orderRecord.setPrizeId(research.getPrizeId());
            orderRecord.setResearchId(research.getId());
            orderRecord.setUserId(userId);

            orderRecord = orderRecordRepository.save(orderRecord);


            //修改commit
           commit.setOrderId(orderRecord.getId());
           commitService.save(commit);
           return ResponseBuilder.ok("订单创建成功");


        }



    }
}
