package com.example.book.controller;

import com.example.book.model.*;

import com.example.book.respository.BookRepository;
import com.example.book.respository.UserRepository;
import com.example.book.service.UserRoleService;
import com.example.book.service.UserService;
import com.example.book.notfound.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;


// Rest of the code
import java.util.ArrayList;
import java.util.List;



@RestController
        @RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final UserRoleService userRoleService;
    private  final BookRepository bookRepository;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRoleService userRoleService, BookRepository bookRepository, UserRepository userRepository) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        if (!isValidEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Invalid email format");
        }
        if (!iValidPhone(user.getPhoneNumber())) {
            return ResponseEntity.badRequest().body("Invalid phone format");
        }
        User savedUser = userService.saveUser(user);

        UserRole tuserRole = new UserRole();
        tuserRole.setUser(savedUser);

        Role userRole = new Role();
        userRole.setRoleId(3L); // RoleId của User là 3

        tuserRole.setRole(userRole);

        userRoleService.saveUserRole(tuserRole);

        return ResponseEntity.ok("User registered successfully");
    }

    private boolean iValidPhone(String phoneNumber){
        String regex = "(84|0[3|5|7|8|9])+([0-9]{8})";
        return phoneNumber.matches(regex);
    }
    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-].+$";
        return email.matches(regex);
    }


    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
        return userService.loginUser(username, password);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        // Gọi phương thức thêm người dùng trong UserService
        return userService.addUser(user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        // Gọi phương thức xóa người dùng trong UserService
        userService.deleteUser(userId);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {

        return userService.updateUser(user);
    }

    @GetMapping("/list")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }
 /*   @GetMapping("/usersrole")
    public ResponseEntity<List<UserWithRoleDTO>> getAllUsersWithRole() {
        List<UserWithRoleDTO> usersWithRole = userService.getAllUsersWithRole();
        return ResponseEntity.ok(usersWithRole);
    }*/

    // Endpoint: GET /users/{userId}/books
    @GetMapping("/{userId}/books")
    public List<Book> getBorrowedBooks(@PathVariable("userId") Long userId) {
        List<Book> borrowedBooks = userService.getBorrowedBooks(userId);
        return borrowedBooks;
    }

    // Endpoint: POST /users/{userId}/books/{bookId}/borrow
    @PostMapping("/{userId}/books/{bookId}/borrow/{quantity}")
    public ResponseEntity<String> borrowBook(@PathVariable("userId") Long userId, @PathVariable("bookId") Long bookId,@PathVariable("quantity")Integer quantity) {
        try {
            userService.borrowBook(userId, bookId,quantity);
            return ResponseEntity.ok("Book borrowed successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to borrow book: " + e.getMessage());
        }
    }
    @GetMapping("/{userId}/roles")
    public List<String> getUserRoles(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        List<UserRole> userRoles = user.getUserRoles();
        if (userRoles.isEmpty()) {
            throw new NotFoundException("User has no roles assigned");
        }

        List<String> roles = new ArrayList<>();
        for (UserRole userRole : userRoles) {
            Role role = userRole.getRole();
            roles.add(role.getRoleName());
        }

        return roles;
    }
   /* // Endpoint: POST /users/{userId}/books/{bookId}/return
        @PostMapping("/{userId}/books/return")
        public ResponseEntity<ReturnBookResponse> returnBooks(@PathVariable("userId") Long userId) {
            try {
                ReturnBookResponse response = userService.returnBook(userId);
                return ResponseEntity.ok(response);
            } catch (NotFoundException e) {
                return ResponseEntity.notFound().build();
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        }*/
}

