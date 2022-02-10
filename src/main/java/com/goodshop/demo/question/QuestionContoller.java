package com.goodshop.demo.question;

import com.goodshop.demo.domain.product.Product;
import com.goodshop.demo.domain.question.Question;
import com.goodshop.demo.repository.product.ProductRepository;
import com.goodshop.demo.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuestionContoller {

    private final ProductRepository productRepository;
    private final QuestionService questionService;

    @GetMapping("/item/qna")
    public String questionForm(Model model) {

        List<Product> products = productRepository.findAll();

        model.addAttribute("products", products);
        model.addAttribute("qForm", new QuestionForm());

        return "question/qForm";
    }

    @PostMapping("/item/qna")
    public String question_do(@ModelAttribute @Valid  QuestionForm questionForm, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        questionService.saveQuestion(questionForm);

        return "redirect:/item/qnaList/" + questionForm.getUser_id();
    }

    @GetMapping("/item/qnaList/{user_id}")
    public String q_List(@PathVariable(name = "user_id") String user_id, Model model){

        List<Question> q_list = questionService.findList(user_id);

        model.addAttribute("qList", q_list);

        return "question/qnaList";
    }

    @GetMapping("/item/qna/{q_code}")
    public String question(@PathVariable(name = "q_code") Long q_code, Model model){

        Question question = questionService.findOne(q_code);

        model.addAttribute("question", question);

        return "question/question";
    }
}
