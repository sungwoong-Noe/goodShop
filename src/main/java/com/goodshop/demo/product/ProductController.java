package com.goodshop.demo.product;

import com.goodshop.demo.domain.product.Product;
import com.goodshop.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class ProductController {

    private final ProductService productService;

    @GetMapping("/men")
    public String men(){
        return "/menu/men";
    }

    @GetMapping("/men/vanta")
    public String productDetail(){
        return "/menu/product/productEx";
    }

    @GetMapping("/tech")
    public String tech(){
        return "menu/tech";
    }

    @GetMapping("/item/new")
    public String newItem(Model model){
        model.addAttribute("productForm", new ProductForm());
        return "menu/product/newitem";
    }

    @PostMapping("/item/new")
    public String newItem(@Valid ProductForm productForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "menu/product/newitem";
        }

        Product product = new Product();
        product.setPdct_name(productForm.getPdct_name());
        product.setPdct_price(productForm.getPdct_price());
        product.setPdct_quantity(productForm.getPdct_quantity());
        product.setPdct_image(productForm.getDetail_image());
        product.setDetail_image(productForm.getDetail_image());
        product.setPdct_detail(productForm.getPdct_detail());
        product.setPdct_sell(productForm.getPdct_sell());

        productService.saveProduct(product);
        return "redirect:/";
    }




}
