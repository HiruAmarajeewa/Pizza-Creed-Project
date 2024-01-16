package lk.nibm.ead2.web.controller;

import lk.nibm.ead2.web.model.User;
import lk.nibm.ead2.web.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserRegistrationController {

    @Autowired
    IUserService service;

    @GetMapping("/Registration")
    public String showRegistration() {
        return "Registration";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute User user) {
        service.save(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

}
