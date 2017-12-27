package com.anlu.secrity.service.impl;

import com.anlu.secrity.dao.UserProfileDao;
import com.anlu.secrity.model.User;
import com.anlu.secrity.model.UserProfile;
import com.anlu.secrity.service.UserProfileService;
import com.anlu.secrity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService,UserDetailsService {

    @Autowired
   private UserProfileDao userProfileDao;

    @Autowired
    private UserService userService;

    public List<UserProfile> findAll() {
        return userProfileDao.findAll();
    }

    public UserProfile findByType(String type) {
        return userProfileDao.findByType(type);
    }

    public UserProfile findById(int id) {
        return userProfileDao.findById(id);
    }

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String ssoId)
            throws UsernameNotFoundException {
        User user = userService.findBySso(ssoId);
        System.out.println("User : "+user);
        if(user==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getSsoId(),user.getPassword(),user.getState().equals("Active"),
                true,true,true,getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for(UserProfile userProfile : user.getUserProfiles()){
            System.out.println("UserProfile : "+userProfile);
            authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getType()));
        }
        System.out.print("authorities :"+authorities);
        return authorities;
    }
}
