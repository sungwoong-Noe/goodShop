package com.goodshop.demo.order;

import com.goodshop.demo.domain.product.Product;
import com.goodshop.demo.domain.user.User;
import com.goodshop.demo.service.OrderService;
import com.goodshop.demo.service.ProductService;
import com.goodshop.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final ProductService productService;


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
    public String order(@RequestParam("user_id") String user_id,
                        @RequestParam("pdct_code") Long pdct_code,
                        @RequestParam("quantity") int pdct_quantity){
        orderService.order(user_id, pdct_code, pdct_quantity);
        return "redirect:/orders";
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
