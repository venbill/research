package com.yuwb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.google.gson.Gson;
import com.yuwb.config.Constants;
import com.yuwb.domain.RtData;
import com.yuwb.domain.WeUser;
import com.yuwb.enums.ErrorCode;
import com.yuwb.service.WeUserService;
import com.yuwb.util.ResponseBuilder;
import com.yuwb.web.rest.errors.BadRequestAlertException;
import com.yuwb.web.rest.util.HeaderUtil;
import com.yuwb.web.rest.util.PaginationUtil;
import com.yuwb.web.rest.vm.OpenVm;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * REST controller for managing WeUser.
 */
@RestController
@RequestMapping("/api")
public class WeUserResource {

    private final Logger log = LoggerFactory.getLogger(WeUserResource.class);

    private static final String ENTITY_NAME = "weUser";

    private final WeUserService weUserService;

    public WeUserResource(WeUserService weUserService) {
        this.weUserService = weUserService;
    }

    /**
     * POST  /we-users : Create a new weUser.
     *
     * @param weUser the weUser to create
     * @return the ResponseEntity with status 201 (Created) and with body the new weUser, or with status 400 (Bad Request) if the weUser has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/we-users")
    @Timed
    public ResponseEntity<WeUser> createWeUser(@RequestBody WeUser weUser) throws URISyntaxException {
        log.debug("REST request to save WeUser : {}", weUser);
        if (weUser.getId() != null) {
            throw new BadRequestAlertException("A new weUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WeUser result = weUserService.save(weUser);
        return ResponseEntity.created(new URI("/api/we-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /we-users : Updates an existing weUser.
     *
     *
     * 个人信息保存接口
     * @param weUser the weUser to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated weUser,
     * or with status 400 (Bad Request) if the weUser is not valid,
     * or with status 500 (Internal Server Error) if the weUser couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/we-users")
    @Timed
    public ResponseEntity<WeUser> updateWeUser(@RequestBody WeUser weUser) throws URISyntaxException {
        log.debug("REST request to update WeUser : {}", weUser);
        if (weUser.getOpenId() == null) {
//            return createWeUser(weUser);
            throw new BadRequestAlertException("error param", ENTITY_NAME, "idexists");
        }
        WeUser result = weUserService.save(weUser);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, weUser.getId().toString()))
            .body(result);
    }

    /**
     * GET  /we-users : get all the weUsers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of weUsers in body
     */
    @GetMapping("/we-users")
    @Timed
    public ResponseEntity<List<WeUser>> getAllWeUsers(Pageable pageable) {
        log.debug("REST request to get a page of WeUsers");
        Page<WeUser> page = weUserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/we-users");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /we-users/:id : get the "id" weUser.
     *
     * @param id the id of the weUser to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the weUser, or with status 404 (Not Found)
     */
    @GetMapping("/we-users/{id}")
    @Timed
    public ResponseEntity<WeUser> getWeUser(@PathVariable Long id) {
        log.debug("REST request to get WeUser : {}", id);
        WeUser weUser = weUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(weUser));
    }

    /**
     * DELETE  /we-users/:id : delete the "id" weUser.
     *
     * @param id the id of the weUser to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/we-users/{id}")
    @Timed
    public ResponseEntity<Void> deleteWeUser(@PathVariable Long id) {
        log.debug("REST request to delete WeUser : {}", id);
        weUserService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }






    /**
     * 微信接口获取openId sessionkey
     * @param code
     * @return
     */
    @GetMapping("/wechat/login/{code}")
    @ResponseBody
    public RtData getCode(@PathVariable String code){
        System.out.println("==>code"+code);
        String urlNameString = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
        urlNameString=urlNameString.replace("APPID", Constants.APPID);
        urlNameString=urlNameString.replace("SECRET",Constants.SECRET);
        urlNameString=urlNameString.replace("JSCODE",code);
        System.out.println("urlNameStirng:"+urlNameString);
        String result="";
        OpenVm openVm = new OpenVm();
        WeUser weUser = new WeUser();

        try {
            // 根据地址获取请求
            HttpGet request = new HttpGet(urlNameString);//这里发送get请求
            // 获取当前客户端对象
            HttpClient httpClient = new DefaultHttpClient();
            // 通过请求对象获取响应对象
            HttpResponse response = httpClient.execute(request);

            // 判断网络连接状态码是否正常(0--200都数正常)
            System.out.println("===>response");
            if (response.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
                result= EntityUtils.toString(response.getEntity(),"utf-8");
                System.out.println("200:"+result);
            }else{
                result= EntityUtils.toString(response.getEntity(),"utf-8");
                System.out.println(response.getStatusLine().getStatusCode()+result);
            }
            openVm =new Gson().fromJson(result,OpenVm.class);
            weUser=weUserService.login(openVm.getOpenid());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ResponseBuilder.ok(weUser);
    }



}
