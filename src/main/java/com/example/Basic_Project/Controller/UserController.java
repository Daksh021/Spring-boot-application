package com.example.Basic_Project.Controller;


import com.example.Basic_Project.Model.UserDto;
import com.example.Basic_Project.Model.UserModel;
import com.example.Basic_Project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to the API!";
    }

    @GetMapping("/get/{username}")
    public Object get_user(@PathVariable String username) throws Exception{
        return userService.get_user(username);
    }

    @PostMapping("/register")
    public UserModel register(@RequestBody UserModel userModel){
        return userService.register(userModel);
    }

   @PostMapping("/login")
   public String login(@RequestBody UserDto userDto) {

       return userService.verify(userDto);
   }


   @PutMapping("/update/{username}")
    public String update(@PathVariable String username, @RequestBody UserDto userDto){
        return userService.update(username,userDto);
   }

   @DeleteMapping("/delete/{username}")
    public String delete(@PathVariable String username){
        return userService.delete(username);
   }

}
