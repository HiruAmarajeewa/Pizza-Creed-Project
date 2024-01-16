package lk.nibm.ead2.web.controller;

import lk.nibm.ead2.web.model.Product;
import lk.nibm.ead2.web.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductRestController {
    @Autowired
    IProductService service;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> getProducts() {
        return service.findAll();
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    @ResponseBody
    public Product createProduct(@RequestBody Product product) {
        return service.save(product);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Product UpdateProduct(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        return service.save(product);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Product findProduct(@PathVariable Long id) {
        return service.find(id);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public boolean deleteProduct(@PathVariable Long id) {
        return service.delete(id);
    }


}
