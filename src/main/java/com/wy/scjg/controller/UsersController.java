package com.wy.scjg.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wy.scjg.bean.Dept;
import com.wy.scjg.bean.User;
import com.wy.scjg.service.DeptService;
import com.wy.scjg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*",allowCredentials = "true")
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private DeptService deptService;

    @RequestMapping("/add")
    public Boolean add(@RequestBody User user){
        return userService.save(user);
    }

    /**
     * 出列表
     * @param request
     * @param current
     * @param size
     * @param user
     * @return
     */
    @RequestMapping("/list")
    public IPage<User> list(HttpServletRequest request,HttpServletResponse response, @RequestParam(defaultValue = "1") long current, @RequestParam(defaultValue = "3") long size, User user){
        //创建分页对象
        Page<User> page =new Page<>(current,size);
        IPage<User> pageInfo = userService.getUserList(page, user);

        System.out.println(user);

        //判断最后一页是否有数据
        if(current>pageInfo.getPages() && pageInfo.getRecords().size()==0){
            page.setCurrent(page.getCurrent()-1);
            pageInfo = userService.getUserList(page,user);
        }
        return pageInfo;
    }

    /**
     * 删除用户
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public boolean delete(Integer ids[]){
        System.out.println(ids);
        return userService.removeByIds(Arrays.asList(ids));
    }

    @RequestMapping("/deptAll")
    public List<Dept> getDeptAll(){
        return deptService.list();
    }
}
