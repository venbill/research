package com.yuwb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yuwb.domain.Question;
import com.yuwb.domain.RtData;
import com.yuwb.service.AnswerService;
import com.yuwb.service.CommitService;
import com.yuwb.service.QuestionService;
import com.yuwb.util.ResponseBuilder;
import com.yuwb.web.rest.errors.BadRequestAlertException;
import com.yuwb.web.rest.util.HeaderUtil;
import com.yuwb.web.rest.util.PaginationUtil;
import com.yuwb.web.rest.vm.ResearchVM;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
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
 * REST controller for managing Question.
 */
@RestController
@RequestMapping("/api")
public class QuestionResource {

    private final Logger log = LoggerFactory.getLogger(QuestionResource.class);

    private static final String ENTITY_NAME = "question";

    private final QuestionService questionService;

    private final CommitService commitService;

    private final AnswerService answerService;

    public QuestionResource(QuestionService questionService,CommitService commitService,AnswerService answerService) {
        this.questionService = questionService;
        this.commitService = commitService;
        this.answerService = answerService;
    }

    /**
     * POST  /questions : Create a new question.
     *
     * @param question the question to create
     * @return the ResponseEntity with status 201 (Created) and with body the new question, or with status 400 (Bad Request) if the question has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/questions")
    @Timed
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) throws URISyntaxException {
        log.debug("REST request to save Question : {}", question);
        if (question.getId() != null) {
            throw new BadRequestAlertException("A new question cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Question result = questionService.save(question);
        return ResponseEntity.created(new URI("/api/questions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }




    /**
     * PUT  /questions : Updates an existing question.
     *
     * @param question the question to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated question,
     * or with status 400 (Bad Request) if the question is not valid,
     * or with status 500 (Internal Server Error) if the question couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/questions")
    @Timed
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question) throws URISyntaxException {
        log.debug("REST request to update Question : {}", question);
        if (question.getId() == null) {
            return createQuestion(question);
        }
        Question result = questionService.save(question);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, question.getId().toString()))
            .body(result);
    }

    /**
     * GET  /questions : get all the questions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of questions in body
     */
    @GetMapping("/questions")
    @Timed
    public ResponseEntity<List<Question>> getAllQuestions(Pageable pageable) {
        log.debug("REST request to get a page of Questions");
        Page<Question> page = questionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/questions");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * 根据问卷id获取所有问题及选项
     * @param id
     * @return
     */
    @GetMapping("/questions/research/{id}")
    @Timed
    public RtData getResearchQuestions(@PathVariable Long id) {
        log.debug("REST request to get research Questions");

        return ResponseBuilder.ok(questionService.getResearchQuestionList(id));
    }



    @PostMapping("/research/questions")
    @Timed
    public RtData getResearchQuestions(@RequestBody ResearchVM researchVM) {

        if(StringUtils.isEmpty(researchVM.getOpenId())||!commitService.isUserCommit(researchVM.getResearchId(),researchVM.getOpenId())){
            return ResponseBuilder.ok(questionService.getResearchQuestionList(researchVM.getResearchId()));

        }else{
            return answerService.getResearchAnswers(researchVM.getResearchId(),researchVM.getOpenId());
        }

    }







    /**
     * GET  /questions/:id : get the "id" question.
     *
     * @param id the id of the question to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the question, or with status 404 (Not Found)
     */
    @GetMapping("/questions/{id}")
    @Timed
    public ResponseEntity<Question> getQuestion(@PathVariable Long id) {
        log.debug("REST request to get Question : {}", id);
        Question question = questionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(question));
    }

    /**
     * DELETE  /questions/:id : delete the "id" question.
     *
     * @param id the id of the question to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/questions/{id}")
    @Timed
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        log.debug("REST request to delete Question : {}", id);
        questionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
