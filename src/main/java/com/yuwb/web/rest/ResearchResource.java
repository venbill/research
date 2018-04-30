package com.yuwb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yuwb.domain.Research;
import com.yuwb.domain.ResearchDetail;
import com.yuwb.domain.RtData;
import com.yuwb.enums.EnumModel;
import com.yuwb.enums.EnumUtil;
import com.yuwb.enums.ReceiveType;
import com.yuwb.security.SecurityUtils;
import com.yuwb.service.CommitService;
import com.yuwb.service.PrizeService;
import com.yuwb.service.ResearchService;
import com.yuwb.util.ResponseBuilder;
import com.yuwb.web.rest.errors.BadRequestAlertException;
import com.yuwb.web.rest.util.HeaderUtil;
import com.yuwb.web.rest.util.PaginationUtil;
import com.yuwb.web.rest.vm.ResearchVM;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Research.
 */
@RestController
@RequestMapping("/api")
public class ResearchResource {

    private final Logger log = LoggerFactory.getLogger(ResearchResource.class);

    private static final String ENTITY_NAME = "research";

    private final ResearchService researchService;

    private final CommitService commitService;

    private final PrizeService prizeService;

    public ResearchResource(ResearchService researchService,CommitService commitService,PrizeService prizeService) {
        this.researchService = researchService;
        this.commitService = commitService;
        this.prizeService = prizeService;
    }

    /**
     * POST  /research : Create a new research.
     *
     * @param research the research to create
     * @return the ResponseEntity with status 201 (Created) and with body the new research, or with status 400 (Bad Request) if the research has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/research")
    @Timed
    public ResponseEntity<Research> createResearch(@RequestBody Research research) throws URISyntaxException {
        log.debug("REST request to save Research : {}", research);
        if (research.getId() != null) {
            throw new BadRequestAlertException("A new research cannot already have an ID", ENTITY_NAME, "idexists");
        }
        research.setCreateTime(new Date());
        research.setPublisher(SecurityUtils.getCurrentUserId());
        Research result = researchService.save(research);
        return ResponseEntity.created(new URI("/api/research/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /research : Updates an existing research.
     *
     * @param research the research to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated research,
     * or with status 400 (Bad Request) if the research is not valid,
     * or with status 500 (Internal Server Error) if the research couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/research")
    @Timed
    public ResponseEntity<Research> updateResearch(@RequestBody Research research) throws URISyntaxException {
        log.debug("REST request to update Research : {}", research);
        if (research.getId() == null) {
            return createResearch(research);
        }
        research.setUpdateTime(new Date());
        Research result = researchService.save(research);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, research.getId().toString()))
            .body(result);
    }

    /**
     * GET  /research : get all the research.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of research in body
     */
    @GetMapping("/research")
    @Timed
    public ResponseEntity<List<Research>> getAllResearch(Pageable pageable) {
        log.debug("REST request to get a page of Research");
        Page<Research> page = researchService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/research");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @PostMapping("/researchs")
    @Timed
    public RtData getAllResearch(@RequestBody ResearchVM researchVM) {
        log.debug("REST request to get a page of Research");
        return  ResponseBuilder.ok(researchService.queryEnabledResearchs(researchVM.getName(),researchVM.getOpenId()));
    }


    /**
     * GET  /research/:id : get the "id" research.
     *
     * @param id the id of the research to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the research, or with status 404 (Not Found)
     */
    @GetMapping("/research/{id}")
    @Timed
    public ResponseEntity<Research> getResearch(@PathVariable Long id) {
        log.debug("REST request to get Research : {}", id);
        Research research = researchService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(research));
    }

    @GetMapping("/research/{id}/{openId}")
    @Timed
    public ResponseEntity<Research> getResearch(@PathVariable Long id, @PathVariable String openId) {
        log.debug("REST request to get Research : {}", id);
        Research research = researchService.findOne(id);
        ResearchDetail researchDetail = new ResearchDetail(research,commitService.isUserCommit(id,openId),commitService.getCommitNoByResearchId(research.getId()));
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(researchDetail));
    }

    /**
     * DELETE  /research/:id : delete the "id" research.
     *
     * @param id the id of the research to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/research/{id}")
    @Timed
    public ResponseEntity<Void> deleteResearch(@PathVariable Long id) {
        log.debug("REST request to delete Research : {}", id);
        researchService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }





    @GetMapping("/research/receive/types")
    @Timed
    public RtData getReceiveTypes()  {
        log.debug("REST request to get a page of Research");

        return ResponseBuilder.ok(EnumUtil.getReceiveTypeList());

    }



    @PostMapping("/researchs/my")
    @Timed
    public RtData getMyResearch(@RequestBody ResearchVM researchVM) {
        log.debug("REST request to get a page of Research");
        return  ResponseBuilder.ok(researchService.queryMyResearchs(researchVM.getOpenId()));
    }


    @GetMapping("/research/prize/{id}")
    @Timed
    public RtData getMyResearch(@PathVariable Long id) {
        return ResponseBuilder.ok(prizeService.findOne(researchService.findOne(id).getPrizeId()));
    }





}
