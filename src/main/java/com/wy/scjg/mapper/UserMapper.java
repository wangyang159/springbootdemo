package com.wy.scjg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wy.scjg.bean.User;

public interface UserMapper extends BaseMapper<User> {

    /**
     * 获取列表
     * @param page
     * @param user
     * @return
     */
    IPage<User> getUserList(Page page,User user);

}
