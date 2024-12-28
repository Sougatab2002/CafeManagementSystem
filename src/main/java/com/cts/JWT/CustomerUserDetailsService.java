package com.cts.JWT;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.Dao.UserDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;
    //it specifies that we are creating a bean of User of com.inn.cafw.POJO type
    private com.cts.POJO.User userDatails;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Inside loadUserByUsername {}", username);
        userDatails = userDao.findByEmailId(username);
        if (!Objects.isNull(userDatails)) {
            return new User(userDatails.getEmail(), userDatails.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    //If we need complete userdetails in case
    public com.cts.POJO.User getUserDatails() {
        return userDatails;
    }
}
