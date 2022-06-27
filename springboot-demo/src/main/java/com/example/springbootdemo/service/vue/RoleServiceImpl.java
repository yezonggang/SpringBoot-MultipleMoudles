package com.example.springbootdemo.service.vue;

import com.example.springbootdemo.model.entitiy.user.Role;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl {
    public Role getRoleFromToken(String token){

        List<String> roleList =new ArrayList<String>();
        roleList.add("admin");
        String introductions="I am a super administrator";
        String avatar="https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif";
        String name="Super Admin";
        return  new Role(name,roleList,introductions,avatar);
    }
}
