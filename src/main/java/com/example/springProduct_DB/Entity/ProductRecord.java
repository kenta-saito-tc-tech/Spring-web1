package com.example.springProduct_DB.Entity;

import com.example.springProduct_DB.Form.ProductForm;

public record ProductRecord(int id, String name, int price) {
    public static ProductRecord changeToDto(ProductForm productForm){
        return new ProductRecord(productForm.getId(), productForm.getName(), productForm.getPrice());
    }
}
