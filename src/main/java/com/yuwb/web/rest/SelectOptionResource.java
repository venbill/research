package com.yuwb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yuwb.domain.SelectOption;
import com.yuwb.service.SelectOptionService;
import com.yuwb.web.rest.errors.BadRequestAlertException;
import com.yuwb.web.rest.util.HeaderUtil;
import com.yuwb.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing SelectOption.
 */
@RestController
@RequestMapping("/api")
public class SelectOptionResource {

    private final Logger log = LoggerFactory.getLogger(SelectOptionResource.class);

    private static final String ENTITY_NAME = "selectOption";

    private final SelectOptionService selectOptionService;

    public SelectOptionResource(SelectOptionService selectOptionService) {
        this.selectOptionService = selectOptionService;
    }

    /**
     * POST  /select-options : Create a new selectOption.
     *
     * @param selectOption the selectOption to create
     * @return the ResponseEntity with status 201 (Created) and with body the new selectOption, or with status 400 (Bad Request) if the selectOption has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/select-options")
    @Timed
    public ResponseEntity<SelectOption> createSelectOption(@RequestBody SelectOption selectOption) throws URISyntaxException {
        log.debug("REST request to save SelectOption : {}", selectOption);
        if (selectOption.getId() != null) {
            throw new BadRequestAlertException("A new selectOption cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SelectOption result = selectOptionService.save(selectOption);
        return ResponseEntity.created(new URI("/api/select-options/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /select-options : Updates an existing selectOption.
     *
     * @param selectOption the selectOption to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated selectOption,
     * or with status 400 (Bad Request) if the selectOption is not valid,
     * or with status 500 (Internal Server Error) if the selectOption couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/select-options")
    @Timed
    public ResponseEntity<SelectOption> updateSelectOption(@RequestBody SelectOption selectOption) throws URISyntaxException {
        log.debug("REST request to update SelectOption : {}", selectOption);
        if (selectOption.getId() == null) {
            return createSelectOption(selectOption);
        }
        SelectOption result = selectOptionService.save(selectOption);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, selectOption.getId().toString()))
            .body(result);
    }

    /**
     * GET  /select-options : get all the selectOptions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of selectOptions in body
     */
    @GetMapping("/select-options")
    @Timed
    public ResponseEntity<List<SelectOption>> getAllSelectOptions(Pageable pageable) {
        log.debug("REST request to get a page of SelectOptions");
        Page<SelectOption> page = selectOptionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/select-options");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /select-options/:id : get the "id" selectOption.
     *
     * @param id the id of the selectOption to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the selectOption, or with status 404 (Not Found)
     */
    @GetMapping("/select-options/{id}")
    @Timed
    public ResponseEntity<SelectOption> getSelectOption(@PathVariable Long id) {
        log.debug("REST request to get SelectOption : {}", id);
        SelectOption selectOption = selectOptionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(selectOption));
    }

    /**
     * DELETE  /select-options/:id : delete the "id" selectOption.
     *
     * @param id the id of the selectOption to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/select-options/{id}")
    @Timed
    public ResponseEntity<Void> deleteSelectOption(@PathVariable Long id) {
        log.debug("REST request to delete SelectOption : {}", id);
        selectOptionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
