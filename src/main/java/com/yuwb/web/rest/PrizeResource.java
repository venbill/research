package com.yuwb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yuwb.domain.Prize;
import com.yuwb.domain.RtData;
import com.yuwb.security.SecurityUtils;
import com.yuwb.service.PrizeService;
import com.yuwb.util.ResponseBuilder;
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

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Prize.
 */
@RestController
@RequestMapping("/api")
public class PrizeResource {

    private final Logger log = LoggerFactory.getLogger(PrizeResource.class);

    private static final String ENTITY_NAME = "prize";

    private final PrizeService prizeService;

    public PrizeResource(PrizeService prizeService) {
        this.prizeService = prizeService;
    }

    /**
     * POST  /prizes : Create a new prize.
     *
     * @param prize the prize to create
     * @return the ResponseEntity with status 201 (Created) and with body the new prize, or with status 400 (Bad Request) if the prize has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/prizes")
    @Timed
    public ResponseEntity<Prize> createPrize(@RequestBody Prize prize) throws URISyntaxException {
        log.debug("REST request to save Prize : {}", prize);
        if (prize.getId() != null) {
            throw new BadRequestAlertException("A new prize cannot already have an ID", ENTITY_NAME, "idexists");
        }
        prize.setCreateTime(new Date());
        prize.setCreateUser(SecurityUtils.getCurrentUserId());
        Prize result = prizeService.save(prize);
        return ResponseEntity.created(new URI("/api/prizes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /prizes : Updates an existing prize.
     *
     * @param prize the prize to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated prize,
     * or with status 400 (Bad Request) if the prize is not valid,
     * or with status 500 (Internal Server Error) if the prize couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/prizes")
    @Timed
    public ResponseEntity<Prize> updatePrize(@RequestBody Prize prize) throws URISyntaxException {
        log.debug("REST request to update Prize : {}", prize);
        if (prize.getId() == null) {
            return createPrize(prize);
        }
        prize.setUpdateTime(new Date());
        prize.setUpdateUser(SecurityUtils.getCurrentUserId());
        Prize result = prizeService.save(prize);

        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, prize.getId().toString()))
            .body(result);
    }

    /**
     * GET  /prizes : get all the prizes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of prizes in body
     */
    @GetMapping("/prizes")
    @Timed
    public ResponseEntity<List<Prize>> getAllPrizes(Pageable pageable) {
        log.debug("REST request to get a page of Prizes");
        Page<Prize> page = prizeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/prizes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /prizes/:id : get the "id" prize.
     *
     * @param id the id of the prize to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the prize, or with status 404 (Not Found)
     */
    @GetMapping("/prizes/{id}")
    @Timed
    public ResponseEntity<Prize> getPrize(@PathVariable Long id) {
        log.debug("REST request to get Prize : {}", id);
        Prize prize = prizeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(prize));
    }

    /**
     * DELETE  /prizes/:id : delete the "id" prize.
     *
     * @param id the id of the prize to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/prizes/{id}")
    @Timed
    public ResponseEntity<Void> deletePrize(@PathVariable Long id) {
        log.debug("REST request to delete Prize : {}", id);
        prizeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }




    @GetMapping("/prizes/all")
    @Timed
    public RtData getReceiveTypes()  {
        log.debug("REST request to get all  prize");
        return ResponseBuilder.ok(prizeService.getPrizeList());

    }
}
