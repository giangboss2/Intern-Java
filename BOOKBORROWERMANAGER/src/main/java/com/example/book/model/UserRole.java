package com.example.book.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Fetch;
import lombok.*;

@Entity
@Table(name = "USER_ROLE")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

    public class UserRole {
        @Id
        @Column(name = "usr_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long userRoleId;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "role_id")
        private Role role;



}
