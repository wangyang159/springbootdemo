package com.wy.scjg;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MyplusAutoGenerator {
    public static void main(String[] args) {
        //1.自动生成代码的对象
        AutoGenerator mp = new AutoGenerator();
        //1.1 全局设置
        GlobalConfig gc = new GlobalConfig();
        //设置作者
        gc.setAuthor("wy");
        //设置输出的路径
        gc.setOutputDir(System.getProperty("user.dir")+"/src/main/java");
        //设置输出是否打开
        gc.setOpen(false);
        //设置生成返回的map结果集
        gc.setBaseResultMap(true);
        //设置生成返回的列
        gc.setBaseColumnList(true);

        mp.setGlobalConfig(gc);

        //1.2 数据库设置
        DataSourceConfig dsc = new DataSourceConfig();
        //数据库驱动
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        //考试的时候，记得修改自己的数据库的连接地址
        dsc.setUrl("jdbc:mysql://localhost:3306/test?characterEncoding=UTF8&userSSL=false&serverTimezone=GMT%2B8");
        //数据库用户名
        dsc.setUsername("root");
        //数据库密码
        dsc.setPassword("123456");
        mp.setDataSource(dsc);

        //1.3 包的设置
        PackageConfig pc = new PackageConfig();
        //设置xml文件的名称
        pc.setXml("mapper.mappers");
        //系统当前包名
        pc.setParent(MyplusAutoGenerator.class.getPackage().getName());
        mp.setPackageInfo(pc);


        //1.4 生成策略
        StrategyConfig sc = new StrategyConfig();
        //生成那些表
        sc.setInclude("user"); //包括那些表
        //sc.setExclude();//排除那些表
        sc.setEntityLombokModel(true);//开启lombok模式
        //设置命名规范
        sc.setNaming(NamingStrategy.underline_to_camel);
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        //生成命名规范
        mp.setStrategy(sc);

        //2.执行生成
        mp.execute();
    }
}
