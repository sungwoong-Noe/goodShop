package com.goodshop.demo.order;

import com.goodshop.demo.domain.product.Product;
import com.goodshop.demo.domain.user.User;
import com.goodshop.demo.repository.OrderRepository;
import com.goodshop.demo.repository.ProductRepository;
import com.goodshop.demo.service.OrderService;
import com.goodshop.demo.service.ProductService;
import com.goodshop.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


//    @GetMapping("/order")
//    public String createForm(Model model){
//        List<User> users = userService.findMember();
//        List<Product> products = productService.findProducts();
//
//        model.addAttribute("users", users);
//        model.addAttribute("products", products);
//
//        return "order/order";
//
//    }

    @PostMapping("/order")
    public String order(@RequestParam("userId") String user_id,
                        @RequestParam("productCode") Long pdct_code,
                        @RequestParam("quantity") int pdct_quantity,
                        RedirectAttributes redirectAttributes){

        Long order_code = orderService.order(user_id, pdct_code, pdct_quantity);

        redirectAttributes.addAttribute("pdct_code", pdct_code);
        redirectAttributes.addAttribute("order_info", order_code);
        redirectAttributes.addAttribute("quantity", pdct_quantity);

        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orders(@RequestParam(value = "pdct_code") Long pdct_code,
                         @RequestParam(value = "order_code") Long order_code,
                         @RequestParam(value="quantity") int quantity,
                         Model model){

        model.addAttribute("product",productRepository.findOne(pdct_code));
        model.addAttribute("order_info", orderRepository.findOne(order_code));
        model.addAttribute("quantity", quantity);

        return "order/orders";
    }

    @GetMapping("/orderPage")
    public String order(@RequestParam int qa, @RequestParam Long code, Model model) {

        Product order_product = productService.findOne(code);

        int orderPrice = qa * order_product.getPdct_price();

        model.addAttribute("quantity", qa);
        model.addAttribute("product", order_product );
        model.addAttribute("orderPrice", orderPrice);
        return "order/orderPage";
    }

}
