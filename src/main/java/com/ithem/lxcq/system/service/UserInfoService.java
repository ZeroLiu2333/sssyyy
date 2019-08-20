package com.ithem.lxcq.system.service;

import com.ithem.lxcq.system.dao.UserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ithem.lxcq.system.domain.userinformation;
@Service
public class UserInfoService {
    @Autowired
    UserInfoDao userInfoDao;
    public userinformation getInfo(String username){
        return userInfoDao.getInfo(username);

    }

}
