package com.goodshop.demo.service;


import com.goodshop.demo.domain.product.Product;
import com.goodshop.demo.domain.question.Question;
import com.goodshop.demo.domain.user.User;
import com.goodshop.demo.question.QuestionForm;
import com.goodshop.demo.repository.ProductRepository;
import com.goodshop.demo.repository.QuestionRepository;
import com.goodshop.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void saveQuestion(QuestionForm questionForm){

        User user = userRepository.findOne(questionForm.getUser_id());
        Product product = productRepository.findOne(questionForm.getPdct_code());

        Question question = Question.createQuestion(user, product, questionForm);

        questionRepository.save(question);

    }

    public List<Question> findList(String user_id){
        return questionRepository.findList(user_id);
    }




    public Question findOne(Long q_code){
        return questionRepository.findOne(q_code);

    }


}
