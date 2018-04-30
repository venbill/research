package com.yuwb.service.impl;

import com.yuwb.domain.SelectOption;
import com.yuwb.repository.SelectOptionRepository;
import com.yuwb.service.QuestionService;
import com.yuwb.domain.Question;
import com.yuwb.repository.QuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * Service Implementation for managing Question.
 */
@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    private final Logger log = LoggerFactory.getLogger(QuestionServiceImpl.class);

    private final QuestionRepository questionRepository;

    private final SelectOptionRepository selectOptionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository,SelectOptionRepository selectOptionRepository) {
        this.questionRepository = questionRepository;
        this.selectOptionRepository = selectOptionRepository;
    }

    /**
     * Save a question.
     *
     * @param question the entity to save
     * @return the persisted entity
     */
    @Override
    public Question save(Question question) {
        log.debug("Request to save Question : {}", question);
        List<SelectOption> options = question.getOptionList();
        question.setOptionList(null);
        Question q= questionRepository.save(question);
        if(options!=null&&options.size()>0){
            List<SelectOption> optionList = new ArrayList<>();
            //添加选项
            for (int i=0;i<options.size();i++) {
                SelectOption option = options.get(i);
                option.setOrderNo(i+1);
                option.setQuestionId(q.getId());
                optionList.add(selectOptionRepository.save(option));
            }

            if(question.getId()!=null&&question.getId()>0){
                //查找之前的optionList
                List<SelectOption> allOptions = selectOptionRepository.findByQuestionIdOrderByOrderNo(q.getId());
                if(allOptions.size()>optionList.size()){
                    for (SelectOption o:allOptions) {
                        boolean exist = false;
                        for (SelectOption so:optionList) {
                            if(o.getId().equals(so.getId())){
                                exist = true;
                                break;
                            }
                        }
                        if(!exist){
                            selectOptionRepository.delete(o.getId());
                        }

                    }
                }

            }

            q.setOptionList(optionList);
        }
        return q;
    }

    /**
     * Get all the questions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Question> findAll(Pageable pageable) {
        log.debug("Request to get all Questions");
        return questionRepository.findAll(pageable);
    }


    /**
     * 获取所有调查的问题列表
     * @param id
     * @return
     */

    @Override
    @Transactional(readOnly = true)
    public List<Question> getResearchQuestionList(long id) {
        log.debug("Request to get all research Questions");
         List<Question> questions = questionRepository.findByResearchIdOrderByOrderNo(id);
         List<Question> questionList = new ArrayList<>();
        for (Question question: questions) {
            question.setOptionList(selectOptionRepository.findByQuestionIdOrderByOrderNo(question.getId()));
            questionList.add(question);

        }
        return questionList;
    }





    /**
     * Get one question by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Question findOne(Long id) {
        log.debug("Request to get Question : {}", id);
        return questionRepository.findOne(id);
    }

    /**
     * Delete the question by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Question : {}", id);
        questionRepository.delete(id);
    }
}
