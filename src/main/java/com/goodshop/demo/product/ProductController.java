package com.goodshop.demo.product;

import com.goodshop.demo.config.SessionConst;
import com.goodshop.demo.domain.product.UploadFile;
import com.goodshop.demo.domain.product.Product;
import com.goodshop.demo.domain.product.childEntity.FileStore;
import com.goodshop.demo.domain.user.User;
import com.goodshop.demo.repository.ProductRepository;
import com.goodshop.demo.service.ProductService;
import com.goodshop.demo.service.UserService;
import com.goodshop.demo.user.LoginForm;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.logging.annotations.Log;
import org.apache.catalina.session.StandardSession;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;

@RequiredArgsConstructor
@Controller
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final FileStore fileStore;

    @GetMapping("/item")
    public String item(Model model){


        model.addAttribute("items",productRepository.findAll());

        return "menu/item";
    }

    @GetMapping("/item/new")
    public String newItem(Model model){

        model.addAttribute("productForm", new ProductForm());

        return "menu/product/newitem";
    }

    @PostMapping("/item/new")
    public String newItem(@Valid ProductForm productForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {
        if(bindingResult.hasErrors()){
            return "menu/product/newitem";
        }

        UploadFile attachFile = fileStore.storeFile(productForm.getPdct_image());
        UploadFile attachFile2 = fileStore.storeFile(productForm.getDetail_image());




        System.out.println(productForm.getPdct_image());
        System.out.println(productForm.getDetail_image());

        Product product = new Product();
        product.setPdct_name(productForm.getPdct_name());
        product.setPdct_price(productForm.getPdct_price());
        product.setPdct_quantity(productForm.getPdct_quantity());
        product.setPdct_detail(productForm.getPdct_detail());
        product.setPdct_sell(productForm.getPdct_sell());

        product.setPdct_image(attachFile.getStoreFileName());
        product.setDetail_image(attachFile2.getStoreFileName());

        productService.saveProduct(product);


        redirectAttributes.addAttribute("pdct_code", product.getPdct_code());

        return "redirect:/item";
    }

    @GetMapping("/item/{pdct_code}")
    public String items(@PathVariable Long pdct_code, Model model){

        Product product = productRepository.findOne(pdct_code);
        model.addAttribute("product",product);
        System.out.println();
        return "menu/product/product_detail";
    }



    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
       return new UrlResource("file:" + fileStore.getFullPath(filename));
    }


}
