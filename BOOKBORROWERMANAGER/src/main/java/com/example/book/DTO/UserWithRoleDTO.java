package com.example.book.DTO;

import com.example.book.model.User;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithRoleDTO {

        private Long userId;
        private String userName;
        private String roleName;
}
