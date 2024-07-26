package com.example.authservice.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class UserInfo {
    @Id
    @Column(name = "user_id")
    private String userId;
    private String username;
    private String password;
    @ManyToMany
    private ArrayList<UserRole> roles;
}
