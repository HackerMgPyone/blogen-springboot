package com.example.blogenspringboot.service;

import com.example.blogenspringboot.dao.CategoryDao;
import com.example.blogenspringboot.dao.PostDao;
import com.example.blogenspringboot.dao.UserDao;
import com.example.blogenspringboot.entity.Category;
import com.example.blogenspringboot.entity.Post;
import com.example.blogenspringboot.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final CategoryDao categoryDao;
    private final PostDao postDao;
    private final UserDao userDao;

    public List<Category> listAllCategories(){
        return categoryDao.findAll();
    }

    public List<User> listAllUsers(){
        return userDao.findAll();
    }

    public void createCategory(Category category){
        categoryDao.save(category);
    }

    public void createUser(User user){
        userDao.save(user);
    }

    @Transactional
    public void createPost(Post post) {
        Category category = categoryDao.findById(post.getCategory().getId())
                .orElseThrow();
        User user = userDao.findById(post.getUser().getId())
                .orElseThrow();
        category.addPost(post);
        user.addPost(post);
        postDao.save(post);
    }

    public List<Post> listAllPost(){
        return postDao.findAll();
    }
}
