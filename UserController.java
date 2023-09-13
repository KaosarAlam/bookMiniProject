package com.miniProject.miniProjectTask.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miniProject.miniProjectTask.entity.Book;
import com.miniProject.miniProjectTask.model.UserDto;
import com.miniProject.miniProjectTask.repo.UserRepo;
import com.miniProject.miniProjectTask.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    // @Autowired
   // private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody UserDto userDto) {
        try {
            UserDto createdUser = userService.createUser(userDto);
            String accessToken = JWTUtils.generateToken(createdUser.getEmail());

            Map<String, Object> response = new HashMap<>();
            response.put("user", createdUser);
            response.put(AppConstants.HEADER_STRING, AppConstants.TOKEN_PREFIX + accessToken);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public void login(@RequestBody UserLoginDto userLoginRequestModel, HttpServletResponse response) throws IOException {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginRequestModel.getEmail(), userLoginRequestModel.getPassword()));

        if (authentication.isAuthenticated()) {
            UserDto userDto = userService.getUser(userLoginRequestModel.getEmail());
            String accessToken = JWTUtils.generateToken(userDto.getEmail());

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put(AppConstants.HEADER_STRING, AppConstants.TOKEN_PREFIX + accessToken);

            response.setContentType("application/json");
            response.getWriter().write(new ObjectMapper().writeValueAsString(responseBody));
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserDetails(@PathVariable String userId) throws Exception {
        UserDto user = userService.getUserByUserId(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{userId}/books")
    public ResponseEntity<List<Book>> getAllBorrowedBooksInfo(){
        List<Book> book = userService.getBorrowedBook();
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/{userId}/borrowed-books")
    public ResponseEntity<List<Book>> getAllCurrentBorrowedBooksInfo(){
        List<Book> book = userService.getCurrentBorrowedBook();
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
}
