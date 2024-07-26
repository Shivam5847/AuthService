package com.example.authservice.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AuthRequestDTO {
    private String username;
    private String password;
}
