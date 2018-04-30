package com.yuwb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yuwb.domain.OrderRecord;
import com.yuwb.domain.RtData;
import com.yuwb.service.OrderRecordService;
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

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing OrderRecord.
 */
@RestController
@RequestMapping("/api")
public class OrderRecordResource {

    private final Logger log = LoggerFactory.getLogger(OrderRecordResource.class);

    private static final String ENTITY_NAME = "orderRecord";

    private final OrderRecordService orderRecordService;

    public OrderRecordResource(OrderRecordService orderRecordService) {
        this.orderRecordService = orderRecordService;
    }

    /**
     * POST  /order-records : Create a new orderRecord.
     *
     * @param orderRecord the orderRecord to create
     * @return the ResponseEntity with status 201 (Created) and with body the new orderRecord, or with status 400 (Bad Request) if the orderRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/order-records")
    @Timed
    public ResponseEntity<OrderRecord> createOrderRecord(@RequestBody OrderRecord orderRecord) throws URISyntaxException {
        log.debug("REST request to save OrderRecord : {}", orderRecord);
        if (orderRecord.getId() != null) {
            throw new BadRequestAlertException("A new orderRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderRecord result = orderRecordService.save(orderRecord);
        return ResponseEntity.created(new URI("/api/order-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /order-records : Updates an existing orderRecord.
     *
     * @param orderRecord the orderRecord to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated orderRecord,
     * or with status 400 (Bad Request) if the orderRecord is not valid,
     * or with status 500 (Internal Server Error) if the orderRecord couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/order-records")
    @Timed
    public ResponseEntity<OrderRecord> updateOrderRecord(@RequestBody OrderRecord orderRecord) throws URISyntaxException {
        log.debug("REST request to update OrderRecord : {}", orderRecord);
        if (orderRecord.getId() == null) {
            return createOrderRecord(orderRecord);
        }
        OrderRecord result = orderRecordService.save(orderRecord);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, orderRecord.getId().toString()))
            .body(result);
    }

    /**
     * GET  /order-records : get all the orderRecords.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of orderRecords in body
     */
    @GetMapping("/order-records")
    @Timed
    public ResponseEntity<List<OrderRecord>> getAllOrderRecords(Pageable pageable) {
        log.debug("REST request to get a page of OrderRecords");
        Page<OrderRecord> page = orderRecordService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/order-records");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /order-records/:id : get the "id" orderRecord.
     *
     * @param id the id of the orderRecord to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the orderRecord, or with status 404 (Not Found)
     */
    @GetMapping("/order-records/{id}")
    @Timed
    public ResponseEntity<OrderRecord> getOrderRecord(@PathVariable Long id) {
        log.debug("REST request to get OrderRecord : {}", id);
        OrderRecord orderRecord = orderRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(orderRecord));
    }

    /**
     * DELETE  /order-records/:id : delete the "id" orderRecord.
     *
     * @param id the id of the orderRecord to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/order-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrderRecord(@PathVariable Long id) {
        log.debug("REST request to delete OrderRecord : {}", id);
        orderRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }



    @GetMapping("/order-records/my/{openId}")
    @Timed
    public RtData getOrderRecord(@PathVariable String openId) {
        return ResponseBuilder.ok(orderRecordService.findMyOrderList(openId));
    }
}
