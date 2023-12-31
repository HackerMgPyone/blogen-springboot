package com.example.blogenspringboot.controller;

import com.example.blogenspringboot.entity.Category;
import com.example.blogenspringboot.entity.Post;
import com.example.blogenspringboot.entity.User;
import com.example.blogenspringboot.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BlogService blogService;


    @RequestMapping({"/","/home"})
    public String welcome(Model model){
        model.addAttribute("category",new Category());
        model.addAttribute("user",new User());
        model.addAttribute("post",new Post());
        model.addAttribute("posts",blogService.listAllPost());
        model.addAttribute("users",blogService.listAllUsers());
        model.addAttribute("categories",blogService.listAllCategories());
        return "index";
    }
    @PostMapping("/save-post")
    public String savePost(Post post,BindingResult result){
        if (result.hasErrors()){
            return "index";
        }
        blogService.createPost(post);
        return "redirect:/";
    }
    @GetMapping("/list-posts")
    public String listAllPost(Model model){
        model.addAttribute("posts",blogService.listAllPost());
        return "post";
    }

    @PostMapping("/save-user")
    public String saveUser(User user,BindingResult result){
        if (result.hasErrors()){
            return "index";
        }
        blogService.createUser(user);
        return "redirect:/";
    }
    @GetMapping("/list-users")
    public String listAllUser(Model model){
        model.addAttribute("users",blogService.listAllUsers());
        return "user";
    }
    @PostMapping("/save-category")
    public String saveCategory(Category category, BindingResult result){
        if (result.hasErrors()){
            return "index";
        }
        blogService.createCategory(category);
        return "redirect:/";
    }
    @GetMapping("/list-categories")
    public String listAllCategory(Model model){
        model.addAttribute("categories",blogService.listAllCategories());

        return "categories";
    }
}
