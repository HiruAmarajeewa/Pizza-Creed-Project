package lk.nibm.ead2.web.service.impl;

import lk.nibm.ead2.web.model.Product;
import lk.nibm.ead2.web.repository.ProductRepository;
import lk.nibm.ead2.web.service.IProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        Product dto = null;
        if (product.getId() == null) {
            dto = new Product();
        } else {
            Optional<Product> optionalProduct = productRepository.findById(product.getId());
            if (optionalProduct.isPresent()) {
                dto = optionalProduct.get();
            }
        }
        BeanUtils.copyProperties(product, dto);
        dto = productRepository.save(dto);
        product.setId(dto.getId());
        return product;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(s -> {
            Product dto = new Product();
            BeanUtils.copyProperties(s, dto);
            return dto;
        }).collect(Collectors.toList());
    }


    @Override
    public Product find(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product dto = new Product();
            BeanUtils.copyProperties(optionalProduct.get(), dto);
            return dto;
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.delete(optionalProduct.get());
        } else {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public List<Product> listAll() {
        return productRepository.findAll();
    }

    public Product get(long id) {
        return productRepository.findById(id).get();
    }

    public void deleteProducts(long id) {
        productRepository.deleteById(id);
    }

    public void saveProducts(Product std) {
        productRepository.save(std);
    }
}
