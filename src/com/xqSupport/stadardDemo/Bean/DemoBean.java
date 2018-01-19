package com.xqSupport.stadardDemo.Bean;


import com.xqSupport.Entity.BaseEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "DemoBean", schema = "xq", catalog = "")
public class DemoBean extends BaseEntity {
    @Basic
    @Column(name = "demoValue", nullable = true, length = 20)
    String demoValue;



    //---------默认值---------
    //1、属性为Integer
    @Column(nullable = false, columnDefinition = "INT default 0")
    private Integer commentApprove;
    //2、属性为String
    @Column(columnDefinition = "varchar(128) default ‘hello’")
    private String test;
//3、对于String类型的默认值，也可以直接给属性赋值，如：
//    private String userName=”蓝色裂痕~”；
//    当添加时，如果userName为空，则”蓝色裂痕~”即为该属性的默认值。




    //---------类型定义---------
    @Column(name = "content", nullable = true, columnDefinition = "TEXT")
    private String content;


}
