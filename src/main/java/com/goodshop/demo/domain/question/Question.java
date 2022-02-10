package com.goodshop.demo.domain.question;

import com.goodshop.demo.domain.product.Product;
import com.goodshop.demo.domain.user.User;
import com.goodshop.demo.question.QuestionForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "question")
@Getter @Setter
public class Question {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "q_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pdct_code")
    private Product product;

    private String q_title;

    private String q_content;

    private LocalDateTime q_date;

    public static Question createQuestion(User user, Product product, QuestionForm questionForm){
        Question question = new Question();
        question.setProduct(product);
        question.setUser(user);
        question.setQ_title(questionForm.getQ_title());
        question.setQ_content(questionForm.getQ_content());

        return question;

    }

}
