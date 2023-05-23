package com.example.springProduct_DB.Controller;

import com.example.springProduct_DB.Entity.ProductRecord;
import com.example.springProduct_DB.Form.ProductForm;
import com.example.springProduct_DB.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/product-list")
    public String productList(Model model){
        var list = productService.findAll();
        model.addAttribute("products", list);
        return "/Product-list";
    }

    @GetMapping("/products/{id}")
    public String productInfo(@ModelAttribute("productForm") ProductForm productForm,
                              @PathVariable("id") int id, Model model){
        var pr = productService.findById(id);
        productForm.setId(pr.id());
        productForm.setName(pr.name());
        productForm.setPrice(pr.price());
        return "/Product-info";
    }

    @GetMapping("/insertProduct")
    public String insertView(@ModelAttribute("productForm") ProductForm productForm){
       return "/ProductInsert";
    }

    @PostMapping("/insert")
    public String insert(@Validated @ModelAttribute("productForm") ProductForm productForm,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/ProductInsert";
        }
        int count = productService.insert(ProductRecord.changeToDto(productForm));
        if(count == 1){
            return "redirect:product-list";
        }else{
            return "/ProductInsert";
        }
    }

    @RequestMapping(value = "/update", params = "updatePr", method = RequestMethod.POST)
    public String update(@Validated @ModelAttribute("productForm") ProductForm productForm,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/Product-info";
        }
        int count = productService.update(ProductRecord.changeToDto(productForm));
        if(count == 1){
            return "redirect:product-list";
        }else{
            return "/Product-info";
        }
    }

    @RequestMapping(value = "/update", params = "deletePr", method = RequestMethod.POST)
    public String delete(@ModelAttribute("productForm") ProductForm productForm){
        int count = productService.delete(ProductRecord.changeToDto(productForm).id());
        if(count == 1){
            return "redirect:product-list";
        }else{
            return "redirect:Product-info";
        }
    }

}
