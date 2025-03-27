package com.example.Basic_Project.Controller;

import com.example.Basic_Project.Model.Blog;
import com.example.Basic_Project.Model.UserModel;
import com.example.Basic_Project.Repository.UserRepository;
import com.example.Basic_Project.Service.BlogService;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/post/{username}")
    public String post(@PathVariable String username , @RequestBody Blog blog){
        Optional<UserModel> userModel = userRepository.findByUsername(username);

        if(userModel.isPresent()) {
            blogService.save(username, blog);
            return "Successful";
        }

        return "Failed";
    }

    @GetMapping("/get_posts/{username}")
    public List<Blog> get_all_Posts(@PathVariable String username){
        Optional<UserModel> userModel = userRepository.findByUsername(username);

        if(userModel.isPresent()){
            return blogService.fetch();
        }

        else return null;
    }

    @DeleteMapping("/delete/{username}/{blogId}")
    public String  delete_post(@PathVariable UUID blogId , @PathVariable String username){

        Optional<UserModel> userModel = userRepository.findByUsername(username);

        if (userModel.isPresent()) {
            blogService.delete(blogId);
            return "Blog deleted successfully!";
        }

        return "User not found!";

    }

}
