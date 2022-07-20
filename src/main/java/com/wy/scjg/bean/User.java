package com.wy.scjg.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.util.Date;

@ToString
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
//@Document(indexName = "users")
public class User implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
//    @Id
    private Long id;

    /**
     * 姓名
     */
//    @Field(index=true,store=true,analyzer="ik_max_word",searchAnalyzer="ik_max_word",type= FieldType.Text)
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 生日
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date brith;

    private Integer did;

    //表中不存在的字段
    @TableField(exist = false)
    private String dname;

    /**
     * 照片
     */
    private String zp;

    /**
     * 个人照，任意张
     */
    private String grz;

    @TableField(exist = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date stime;

    @TableField(exist = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date etime;
}
