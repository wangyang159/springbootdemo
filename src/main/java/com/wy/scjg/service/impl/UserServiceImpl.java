package com.wy.scjg.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wy.scjg.bean.User;
import com.wy.scjg.mapper.UserMapper;
import com.wy.scjg.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 实现类固定继承ServiceImpl类，目的是直接注入Dao层以及操作的实例类，并实现自定义的service接口
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public IPage<User> getUserList(Page page, User user) {
        return baseMapper.getUserList(page,user);
    }

}
