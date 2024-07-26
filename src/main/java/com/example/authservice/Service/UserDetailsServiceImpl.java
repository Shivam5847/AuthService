package com.example.authservice.Service;

import com.example.authservice.DTO.UserInfoDto;
import com.example.authservice.Models.UserInfo;
import com.example.authservice.Repository.UserRepository;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Data
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private  UserRepository userRepository;
    private  PasswordEncoder passwordEncoder;

    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    public UserDetailsServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {

        log.debug("Entering in loadUserByUsername Method...");
        UserInfo user = userRepository.findByUsername(username).get();
        if(user == null){
            log.error("Username not found: {}", username);
            throw new UsernameNotFoundException("could not found user..!!");
        }
        log.info("User Authenticated Successfully..!!!");
        return new CustomUserDetails(user);
    }

    public UserInfo checkIfUserAlreadyExist(UserInfoDto userInfoDto){
        return userRepository.findByUsername(userInfoDto.getUsername()).get();
    }
    public Boolean signupUser(UserInfoDto userInfoDto){
        //return true;
        //        ValidationUtil.validateUserAttributes(userInfoDto);
        //userInfoDto.setPassword(passwordEncoder.encode(userInfoDto.getPassword()));
//        if(Objects.nonNull(checkIfUserAlreadyExist(userInfoDto))){
//            return false;
//        }
//        String userId = UUID.randomUUID().toString();
//        UserInfo userInfo=new UserInfo();
//        userInfo.setRoles(new ArrayList<>());
//        userInfo.setUsername(userInfoDto.getUsername());
//        userInfo.setPassword(passwordEncoder.encode(userInfoDto.getPassword()));
//        userInfo.setUserId(userId);
//        userRepository.save(userInfo);
        //userRepository.save(new UserInfo(userId,userInfoDto.getUsername(),userInfoDto.getPassword(), new ArrayList<>()));
       //  pushEventToQueue
        return true;
    }
}
