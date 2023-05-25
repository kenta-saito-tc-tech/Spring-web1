package com.example.springProduct_DB.Controller;

import com.example.springProduct_DB.Entity.ProductRecord;
import com.example.springProduct_DB.Form.ProductForm;
import com.example.springProduct_DB.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ProductRestController {
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public List<ProductRecord> productList(Model model){
        try {
            var list = productService.findAll();
            return list; //ステータスコード200番
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //ステータスコード400番
        }
    }

    @GetMapping("/product")
    public ProductRecord productInfo(@RequestParam(name = "searchId") int id){
        try {
            var pr = productService.findById(id);
            return pr; //ステータスコード200番
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //ステータスコード400番
        }
    }

    @PostMapping("/product")
    public ResponseEntity<String> insertView(@RequestBody ProductRecord productRecord){
        try {
            int count = productService.insert(productRecord);
            if (count == 1) {
                return new ResponseEntity<>("POST request processed", HttpStatus.OK);
            }else {
                return new ResponseEntity<>("POST request failed", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //ステータスコード400番
        }
    }

    @PutMapping("/product")
    public ResponseEntity<String> update(@RequestBody ProductRecord productRecord){
        try {
            int count = productService.update(productRecord);
            if (count == 1) {
                return new ResponseEntity<>("PUT request processed", HttpStatus.OK);
            }else {
                return new ResponseEntity<>("PUT request failed", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //ステータスコード400番
        }
    }

    @DeleteMapping("/product")
    public ResponseEntity<String> delete(@RequestBody ProductRecord productRecord){
        try {
            int count = productService.delete(productRecord.id());
            if (count == 1) {
                return new ResponseEntity<>("DELETE request processed", HttpStatus.OK);
            }else {
                return new ResponseEntity<>("DELETE request failed", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //ステータスコード400番
        }
    }
}
