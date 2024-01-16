package lk.nibm.ead2.web.controller;

import lk.nibm.ead2.web.model.Product;
import lk.nibm.ead2.web.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("home")
    public String showHome() {
        return "home";
    }

    @GetMapping("/product")
    public String showProductPage(Model model) {
        List<Product> listProduct = productService.listAll();
        model.addAttribute("listProduct", listProduct);
        System.out.println("Get / ");
        return "product";
    }

    @GetMapping("/new_product")
    public String add(Model model) {
        model.addAttribute("product", new Product());
        return "new_product";
    }

    @RequestMapping(value = "/saves", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") Product std) {
        productService.saveProducts(std);
        return "redirect:product";
    }

    @RequestMapping("/edits/{id}")
    public ModelAndView showEditPage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("new_product");
        Product std = productService.get(id);
        mav.addObject("product", std);
        return mav;
    }

    @RequestMapping("/deletes/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id) {
        productService.deleteProducts(id);
        return "redirect:/product";
    }

    @RequestMapping("/product")
    public String showProductPage() {
        // Logic to retrieve and add products to the model if needed
        return "product";
    }

}
