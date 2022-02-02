package com.goodshop.demo.order;

import com.goodshop.demo.domain.order.Order;
import com.goodshop.demo.domain.order.OrderItem;
import com.goodshop.demo.domain.product.Product;
import com.goodshop.demo.domain.user.User;
import com.goodshop.demo.repository.OrderItemRepository;
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
    private final OrderItemRepository orderItemRepository;

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
    public String order_Post(OrderForm orderForm,
                             RedirectAttributes redirectAttributes){

        Long order_code = orderService.order(orderForm.getUser_id(),
                orderForm.getPdct_code(),
                orderForm.getQuantity());

        redirectAttributes.addFlashAttribute("pdct_code", orderForm.getPdct_code());
        redirectAttributes.addFlashAttribute("order_info", orderRepository.findOne(order_code));
        redirectAttributes.addFlashAttribute("quantity", orderForm.getQuantity());


        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orders(OrderForm orderForm, Model model){


        Long pdct_code = (Long) model.asMap().get("pdct_code");
        Order order = (Order) model.asMap().get("order_info");

        int quantity = (int) model.asMap().get("quantity");

        OrderItem orderItem =  orderItemRepository.findByOd_code(order.getId());

        model.addAttribute("product", productRepository.findOne(pdct_code));
        model.addAttribute("quantity", quantity);
        model.addAttribute("orderItem", orderItem);
        model.addAttribute("order", orderRepository.findOne(order.getId()));

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

    @GetMapping("/orderList/{user_id}")
    public String orderList(@PathVariable String user_id, Model model){

        List<OrderItem> orderList = orderService.orderList(user_id);
        model.addAttribute("orderList", orderItemRepository.orderItems(user_id));

        return "order/orderList";
    }


}
