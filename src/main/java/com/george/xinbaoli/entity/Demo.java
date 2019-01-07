package com.george.xinbaoli.entity;

import com.george.xinbaoli.annotation.Column;
import com.george.xinbaoli.annotation.Id;
import com.george.xinbaoli.annotation.Table;

@Table(value = "cms_demo")
public class Demo {
    private Long id;

    private String name;

    private String remark;

    @Id(value = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(value = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(value = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}