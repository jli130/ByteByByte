package com.jl.blogapplication.service;

import com.jl.blogapplication.po.User;

public interface UserService {

    User checkUser(String username, String password);

}
