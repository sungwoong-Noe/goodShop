package com.goodshop.demo.order;

import com.goodshop.demo.domain.order.Order;
import com.goodshop.demo.domain.order.OrderItem;
import com.goodshop.demo.domain.product.Product;
import com.goodshop.demo.repository.order.OrderItemRepository;
import com.goodshop.demo.repository.order.OrderRepository;
import com.goodshop.demo.repository.product.ProductRepository;
import com.goodshop.demo.service.OrderService;
import com.goodshop.demo.service.ProductService;
import com.goodshop.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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
    public String order_Post(@Valid @ModelAttribute OrderForm orderForm,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            return "/item/" + orderForm.getPdct_code();
        }

        Long order_code = orderService.order(orderForm.getUser_id(),
                orderForm.getPdct_code(),
                orderForm.getQuantity());


        redirectAttributes.addFlashAttribute("pdct_code", orderForm.getPdct_code());
        redirectAttributes.addFlashAttribute("order_info", orderRepository.findOne(order_code));
        redirectAttributes.addFlashAttribute("quantity", orderForm.getQuantity());


        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orders(Model model){


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

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId){
        Order order = orderRepository.findOne(orderId);
        String user_id = order.getUser().getUser_id();
        orderService.cancelOrder(orderId);
        return "redirect:/orderList/" + user_id;
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
