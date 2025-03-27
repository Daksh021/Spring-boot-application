package com.example.Basic_Project.Service;


import com.example.Basic_Project.Model.UserModel;
import com.example.Basic_Project.Model.UserPrincipal;
import com.example.Basic_Project.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserModel> userModelOptional = userRepository.findByUsername(username);

        if(userModelOptional.isEmpty()){
            System.out.println("User is not found");
            throw new UsernameNotFoundException("User Not found");
        }

        return new UserPrincipal(userModelOptional.get());
    }
}
