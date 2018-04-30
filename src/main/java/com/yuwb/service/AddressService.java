package com.yuwb.service;

import com.yuwb.domain.Address;
import com.yuwb.domain.RtData;
import com.yuwb.web.rest.vm.AddressVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Address.
 */
public interface AddressService {

    /**
     * Save a address.
     *
     * @param address the entity to save
     * @return the persisted entity
     */
    Address save(Address address);

    RtData add(AddressVM addressVM);

    /**
     * Get all the addresses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Address> findAll(Pageable pageable);

    /**
     * Get the "id" address.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Address findOne(Long id);

    /**
     * Delete the "id" address.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
