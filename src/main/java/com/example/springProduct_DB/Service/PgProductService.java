package com.example.springProduct_DB.Service;

import com.example.springProduct_DB.Dao.ProductDao;
import com.example.springProduct_DB.Entity.ProductRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PgProductService implements ProductService{
    @Autowired
    private ProductDao productDao;
    @Override
    public List<ProductRecord> findAll() {
        return productDao.findAll();
    }

    @Override
    public ProductRecord findById(int id) {
        return productDao.findById(id);
    }
}
