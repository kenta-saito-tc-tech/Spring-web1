package com.example.springProduct_DB.Dao;

import com.example.springProduct_DB.Entity.ProductRecord;

import java.util.List;

public interface ProductDao {
    public List<ProductRecord> findAll();
    public ProductRecord findById(int id);
    public int insert(ProductRecord productRecord);

    public int update(ProductRecord productRecord);
    public int delete(int id);
}
