package lk.nibm.ead2.web.controller;

import lk.nibm.ead2.web.model.Basket;
import lk.nibm.ead2.web.model.BasketItemDTO;
import lk.nibm.ead2.web.service.IBasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public  class mmBasketRestController {

    @Autowired
    IBasketService basketService;

    @PostMapping("/basket")
    public Basket createBasket(@RequestBody List<BasketItemDTO> cartItems) {
        return basketService.saveAll(cartItems);
    }

    @RequestMapping(value = "/basket/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public boolean deleteBasket(@PathVariable Long id) {
        return basketService.delete(id);
    }

}
