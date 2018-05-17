package com.ls.brand.controller;

import javax.jws.soap.SOAPBinding.Use;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ls.brand.domain.User;
import com.ls.brand.dto.JSONResult;
import com.ls.brand.dto.request.UserRequest;
import com.ls.brand.service.UserService;

/**
 * Home redirection to swagger api documentation 
 */
@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
    @GetMapping(value = "/")
    public String index() {
        return "redirect:swagger-ui.html";
    }
    
    @GetMapping(value = "/index")
    public String index2(Model model) {
    	User user = new User();
    	user.setId(1111L);
    	user.setUsername("tom");
    	model.addAttribute("user", user);
        return "index";
    }
    
    @GetMapping(value = "/list")
    public String page(Model model) {
    	model.addAttribute("list", userService.page(1,100,new UserRequest()).getList());
        return "list";
    }
    
    
    @GetMapping(value = "/add")
    public String add() {
        return "add";
    }
    
    @PostMapping(value = "/add")
    public String add(UserRequest userRequest) {
    	userService.add(userRequest);
        return "redirect:list";
    }
    
    @GetMapping(value = "/update/{id}")
    public String update(@PathVariable(value="id") Long id,Model model) {
    	model.addAttribute("user",userService.findOne(id));
        return "update";
    }
    
    
    @PostMapping(value = "/update")
    public String update(UserRequest userRequest) {
    	userService.update(userRequest);
        return "redirect:list";
    }
}
