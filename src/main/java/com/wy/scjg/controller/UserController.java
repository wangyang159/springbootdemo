package com.wy.scjg.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wy.scjg.bean.Dept;
import com.wy.scjg.bean.User;
import com.wy.scjg.repository.UserRepository;
import com.wy.scjg.service.DeptService;
import com.wy.scjg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DeptService deptService;

    /**
     * 预登陆
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "user_login";
    }

    /**
     * 正式登录
     * @param request
     * @param name
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request,String name){
        request.getSession().setAttribute("user",name);
        return "redirect:/user/list";
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
    public String list(HttpServletRequest request, @RequestParam(defaultValue = "1") long current, @RequestParam(defaultValue = "3") long size, User user){
        //创建分页对象
        Page<User> page =new Page<>(current,size);
        IPage<User> pageInfo = userService.getUserList(page, user);
        //单独存储总页数，最所以怎么做是因为现在市场上用的JDK和mybatis-plus版本有点不符合，总页数无法封装到前台
        request.setAttribute("pages",pageInfo.getPages());
        //存储到request中
        request.setAttribute("pageInfo",pageInfo);
        //封装查询条件
        request.setAttribute("user",user);
        return "/user_list";
    }

    /**
     * 删除用户
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public boolean delete(Integer ids[]){
        return userService.removeByIds(Arrays.asList(ids));
    }

    /**
     * 预添加
     * @param request
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(HttpServletRequest request){
        //查出所有的部门
        List<Dept> depts = deptService.list();
        request.setAttribute("depts",depts);
        return "/user_add";
    }

    /**
     * 正式添加方法
     * @param user
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public boolean add(User user,MultipartFile file,MultipartFile[] files){
        try {
            //证件照的处理
            if (file != null) {
                //判断文件不为空
                if (!file.isEmpty()) {
                    //获取文件名称
                    String originalFilename = file.getOriginalFilename();
                    //新文件名称
                    String fileName = UUID.randomUUID() + "_" + originalFilename;
                    //创建新文件对象
                    File destFile = new File("D://imgs", fileName);
                    //判断目标文件存在否
                    if (!destFile.getParentFile().exists()) {
                        //创建目录
                        destFile.mkdirs();
                    }
                    //执行拷贝过程
                    file.transferTo(destFile);
                    //保存文件的绝对路径
                    user.setZp("http://localhost:91/imgs/"+fileName);
                }
            }

            //任意张个人照的处理
            if(files!=null ){
                //准备一个字符串缓冲区，保存任意张个人照路径
                StringBuffer grzPaths = new StringBuffer();
                for(MultipartFile t:files){
                    if(!t.isEmpty()){
                        //获取文件名称
                        String originalFilename = t.getOriginalFilename();
                        //新文件名称
                        String fileName = UUID.randomUUID() + "_" + originalFilename;
                        //创建新文件对象
                        File destFile = new File("D://imgs", fileName);
                        //判断目标文件存在否
                        if (!destFile.getParentFile().exists()) {
                            //创建目录
                            destFile.mkdirs();
                        }
                        //执行拷贝过程
                        t.transferTo(destFile);
                        //拼接文件的绝对路径,用逗号隔开
                        grzPaths.append("http://localhost:91/imgs/"+fileName+",");
                    }
                }
                //保存所有个人照路径
                user.setGrz(grzPaths.delete(grzPaths.length()-1,grzPaths.length()).toString());
                grzPaths.delete(0,grzPaths.length());
            }
            user.setEmail(user.getEmail().replaceAll("%40","@"));
        }catch (IOException io){
            io.getMessage();
        }
        return userService.save(user);
    }

    /**
     * 预修改
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/toEdit")
    public String toEdit(HttpServletRequest request,Long id){
        //查询要编辑用户信息
        User user = userService.getById(id);
        //存储
        request.setAttribute("user",user);
        //查询楚所有部门
        List<Dept> depts = deptService.list();
        request.setAttribute("depts",depts);
        return "user_edit";
    }

    /**
     * 修改方法
     * @param user
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public boolean edit(User user){
        return userService.updateById(user);
    }

    /**
     * 详情
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/detail")
    public String detail(HttpServletRequest request,Long id){
        //查询要编辑用户信息
        User user = userService.getById(id);
        //判断外键是否为空
        if(user.getDid()!=null){
            //根据id查询
            Dept dept = deptService.getById(user.getDid());
            //设置部门名称
            user.setDname(dept.getName());
        }

        //存储
        request.setAttribute("user",user);
        return "/user_detail";
    }
}
