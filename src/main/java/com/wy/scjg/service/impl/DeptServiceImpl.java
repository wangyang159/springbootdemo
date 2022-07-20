package com.wy.scjg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wy.scjg.bean.Dept;
import com.wy.scjg.mapper.DeptMapper;
import com.wy.scjg.service.DeptService;
import org.springframework.stereotype.Service;

@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {
}
