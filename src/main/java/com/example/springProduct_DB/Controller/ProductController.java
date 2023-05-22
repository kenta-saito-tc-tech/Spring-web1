package com.example.springProduct_DB.Controller;

import com.example.springProduct_DB.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/product-list")
    public String productList(Model model){
        var list = productService.findAll();
        model.addAttribute("products", list);
        return "Product-list";
    }

    @GetMapping("/products/{id}")
    public String productInfo(@PathVariable("id") int id, Model model){
        var pr = productService.findById(id);
        model.addAttribute("product", pr);
        return "Product-info";
    }

}
