package com.example.Basic_Project.Service;

import com.example.Basic_Project.Model.Blog;
import com.example.Basic_Project.Model.UserModel;
import com.example.Basic_Project.Repository.BlogRepository;
import com.example.Basic_Project.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class BlogService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogRepository blogRepository;

    public void save(String username, Blog blog) {

        Optional<UserModel> userModel = userRepository.findByUsername(username);

        if (userModel.isPresent()) {
            blog.setUserModel(userModel.get());
            blogRepository.save(blog);

        }


    }

    public List<Blog> fetch() {

        return blogRepository.findAll();
    }

    public String delete(UUID uuid) {

        Optional<Blog> blog = blogRepository.findById(uuid);
        if (blog.isPresent()) {
            blogRepository.deleteById(uuid);
            return "Successful";
        }

         return "Failed to delete a post";
    }
}
