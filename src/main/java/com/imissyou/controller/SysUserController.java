package com.imissyou.controller;

import com.imissyou.pojo.SysUser;
import com.imissyou.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/findall")
    @ResponseBody
    // @RequiresRoles("qf")RequestMapping
    public List<SysUser> findAll(){
        int i = 1/0;
        return sysUserService.findAll();
    }



}
