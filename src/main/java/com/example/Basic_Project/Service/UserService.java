package com.example.Basic_Project.Service;

import com.example.Basic_Project.Model.UserDto;
import com.example.Basic_Project.Model.UserModel;
import com.example.Basic_Project.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;


    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public UserModel get_user(String username) throws Exception{
      Optional<UserModel> userModel = userRepository.findByUsername(username);
      if(userModel.isPresent()){
          return userModel.get();
      }

      else throw new UsernameNotFoundException("User not found");
    }


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    public UserModel register(UserModel userModel){
        userModel.setPassword(encoder.encode(userModel.getPassword()));
        userRepository.save(userModel);
        return userModel;
    }


    public String verify(UserDto userDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(),userDto.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(userDto.getUsername());
        }

        return "Fail";
    }

    public String delete(String username) {
        Optional<UserModel> userModel = userRepository.findByUsername(username);

        if(userModel.isPresent()){
            userRepository.delete(userModel.get());
            return "User Deleted Successfully";
        }

        return  "No such user exists";
    }

    public String update(String username , UserDto userDto) {
        Optional<UserModel> userModel = userRepository.findByUsername(username);

        if(userModel.isPresent()){
            UserModel userModel1 = userModel.get();
           userModel1.setUsername(username);
           String password1 = encoder.encode(userDto.getPassword());
           userModel1.setPassword(password1);
           userRepository.save(userModel1);
           return "Updation successful";
        }

        return "Updation could not be done successfully";

    }
}
