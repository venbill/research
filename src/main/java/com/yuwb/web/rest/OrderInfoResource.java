package com.yuwb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yuwb.domain.OrderInfo;
import com.yuwb.service.OrderInfoService;
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
 * REST controller for managing OrderInfo.
 */
@RestController
@RequestMapping("/api")
public class OrderInfoResource {

    private final Logger log = LoggerFactory.getLogger(OrderInfoResource.class);

    private static final String ENTITY_NAME = "orderInfo";

    private final OrderInfoService orderInfoService;

    public OrderInfoResource(OrderInfoService orderInfoService) {
        this.orderInfoService = orderInfoService;
    }

    /**
     * POST  /order-infos : Create a new orderInfo.
     *
     * @param orderInfo the orderInfo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new orderInfo, or with status 400 (Bad Request) if the orderInfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/order-infos")
    @Timed
    public ResponseEntity<OrderInfo> createOrderInfo(@RequestBody OrderInfo orderInfo) throws URISyntaxException {
        log.debug("REST request to save OrderInfo : {}", orderInfo);
        if (orderInfo.getId() != null) {
            throw new BadRequestAlertException("A new orderInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderInfo result = orderInfoService.save(orderInfo);
        return ResponseEntity.created(new URI("/api/order-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /order-infos : Updates an existing orderInfo.
     *
     * @param orderInfo the orderInfo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated orderInfo,
     * or with status 400 (Bad Request) if the orderInfo is not valid,
     * or with status 500 (Internal Server Error) if the orderInfo couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/order-infos")
    @Timed
    public ResponseEntity<OrderInfo> updateOrderInfo(@RequestBody OrderInfo orderInfo) throws URISyntaxException {
        log.debug("REST request to update OrderInfo : {}", orderInfo);
        if (orderInfo.getId() == null) {
            return createOrderInfo(orderInfo);
        }
        OrderInfo result = orderInfoService.save(orderInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orderInfo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /order-infos : get all the orderInfos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of orderInfos in body
     */
    @GetMapping("/order-infos")
    @Timed
    public ResponseEntity<List<OrderInfo>> getAllOrderInfos(Pageable pageable) {
        log.debug("REST request to get a page of OrderInfos");
        Page<OrderInfo> page = orderInfoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/order-infos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /order-infos/:id : get the "id" orderInfo.
     *
     * @param id the id of the orderInfo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the orderInfo, or with status 404 (Not Found)
     */
    @GetMapping("/order-infos/{id}")
    @Timed
    public ResponseEntity<OrderInfo> getOrderInfo(@PathVariable Long id) {
        log.debug("REST request to get OrderInfo : {}", id);
        OrderInfo orderInfo = orderInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(orderInfo));
    }

    /**
     * DELETE  /order-infos/:id : delete the "id" orderInfo.
     *
     * @param id the id of the orderInfo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/order-infos/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrderInfo(@PathVariable Long id) {
        log.debug("REST request to delete OrderInfo : {}", id);
        orderInfoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
