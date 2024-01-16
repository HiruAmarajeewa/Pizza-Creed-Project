package lk.nibm.ead2.web.service;

import lk.nibm.ead2.web.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    Product save(Product product);

    Product find(Long id);

    boolean delete(Long id);
}
