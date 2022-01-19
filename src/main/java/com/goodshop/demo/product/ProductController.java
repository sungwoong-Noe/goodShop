package com.goodshop.demo.product;

import com.goodshop.demo.domain.product.UploadFile;
import com.goodshop.demo.domain.product.Product;
import com.goodshop.demo.domain.product.childEntity.FileStore;
import com.goodshop.demo.repository.ProductRepository;
import com.goodshop.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;

@RequiredArgsConstructor
@Controller
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;
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

    @GetMapping("/item/{pdct_code}/edit")
    public String updateProductForm(@PathVariable("pdct_code") Long pdct_code ,Model model){

        Product product = (Product) productService.findOne(pdct_code);

        ProductForm form = new ProductForm();
        form.setPdct_code(pdct_code);
        form.setPdct_name(product.getPdct_name());
        form.setPdct_price(product.getPdct_price());
        form.setPdct_quantity(product.getPdct_quantity());
        form.setPdct_detail(product.getPdct_detail());


        model.addAttribute("image" ,product);
        model.addAttribute("product", form);
        return "menu/product/updateForm";
    }

    @PostMapping("/item/{pdct_code}/edit")
    public String updateProduct(@PathVariable Long pdct_code, @ModelAttribute("form") ProductForm form, BindingResult bindingResult) throws IOException {
        if(bindingResult.hasErrors()){
            return "redirect:/item/{pdct_code}/edit";
        }

        UploadFile updateImage = fileStore.storeFile(form.getPdct_image());
        UploadFile updateImage2 = fileStore.storeFile(form.getDetail_image());

        Product product = new Product();
        product.setPdct_code(form.getPdct_code());
        product.setPdct_name(form.getPdct_name());
        product.setPdct_price(form.getPdct_price());
        product.setPdct_quantity(form.getPdct_quantity());
        product.setPdct_detail(form.getPdct_detail());

        product.setPdct_image(updateImage.getStoreFileName());
        product.setDetail_image(updateImage2.getStoreFileName());

        productService.saveProduct(product);

        return "redirect:/item/{pdct_code}";
    }


}
