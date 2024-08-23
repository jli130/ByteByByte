package com.jl.blogapplication.po;

import javax.validation.constraints.NotBlank;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="t_type")
public class Type {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Can not be empty")
    private String name;

    @OneToMany(mappedBy = "type")
    public List<Blog> blogs = new ArrayList<Blog>();

    public Type() {
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogList() {
        return blogs;
    }

    public void setBlogList(List<Blog> blogList) {
        this.blogs = blogList;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
