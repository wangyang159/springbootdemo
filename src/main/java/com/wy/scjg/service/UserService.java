package com.wy.scjg.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wy.scjg.bean.User;

public interface UserService extends IService<User>{

    IPage<User> getUserList(Page page, User user);

}
