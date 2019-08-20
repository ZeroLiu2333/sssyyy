package com.ithem.lxcq.system.service;
import com.ithem.lxcq.system.dao.UserDao;
import com.ithem.lxcq.system.domain.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public void UpdatePassword(user user){
        userDao.UpdatePassword(user);
    }
    public  user getByName(String username,String password){
        return userDao.getByName(username,password);
    }
}
