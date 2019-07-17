package com.gwy.controller;

import com.gwy.bean.User;
import com.gwy.service.impl.UserService;
import com.sun.org.glassfish.gmbal.ParameterNames;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api("用户相关")
@Controller
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("获取用户信息根据id")
    @GetMapping("/{id}")
    @ResponseBody
    public User getUserById(@PathVariable("id") String id){
        return userService.getUserById(id);
    }


    @ApiOperation("获取用户信息根据用户名称")
    @GetMapping("/getByName")
    @ResponseBody
    public User getUserByName(@RequestParam("name") String name){
        return userService.getUserByName(name);
    }
}