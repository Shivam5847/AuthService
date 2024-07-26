package com.example.authservice.Controller;

import com.example.authservice.DTO.UserInfoDto;
import com.example.authservice.Models.RefreshToken;
import com.example.authservice.Response.JwtResponseDTO;
import com.example.authservice.Service.JwtService;
import com.example.authservice.Service.RefreshTokenService;
import com.example.authservice.Service.UserDetailsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {


    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final UserDetailsServiceImpl userDetailsService;

    public AuthController(JwtService jwtService, RefreshTokenService refreshTokenService, UserDetailsServiceImpl userDetailsService) {
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.userDetailsService = userDetailsService;
    }

    boolean x=true;

    @PostMapping("auth/v1/signup")
    public ResponseEntity SignUp(@RequestBody UserInfoDto userInfoDto){
        try{
            x=true;
           // x=userDetailsService.signupUser(userInfoDto);
            Boolean isSignUped = userDetailsService.signupUser(userInfoDto);
            x=false;
            if(Boolean.FALSE.equals(isSignUped)){
                return new ResponseEntity<>("Already Exist", HttpStatus.BAD_REQUEST);
            }
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userInfoDto.getUsername());
            x=true;
            String jwtToken = jwtService.GenerateToken(userInfoDto.getUsername());
            return new ResponseEntity<>(JwtResponseDTO.builder().accessToken(jwtToken).
                    token(refreshToken.getToken()).build(), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>("Exception in User Service  "+x, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
