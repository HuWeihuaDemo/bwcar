package com.imissyou.controller;

import com.imissyou.dto.DataGridResult;
import com.imissyou.dto.QueryDTO;
import com.imissyou.log.MyLog;
import com.imissyou.pojo.SysMenu;
import com.imissyou.service.MenuService;
import com.imissyou.utils.R;
import com.imissyou.utils.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/sys")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @MyLog("菜单列表")
    @RequestMapping("/menu/list")
    @ResponseBody
    @RequiresPermissions("sys:menu:list")
    public DataGridResult findMenu(QueryDTO queryDTO){
        return menuService.findMenu(queryDTO);
    }

    @RequestMapping("/menu/del")
    @ResponseBody
    public R deleteMenuByIds(@RequestBody List<Long> Ids){
        return menuService.deleteMenu(Ids);
    }

    @RequestMapping("/menu/select")
    @ResponseBody
    public R selectMenu(){
        return menuService.selectMenu();
    }

    @RequestMapping("/menu/save")
    @ResponseBody
    public R saveMenu(@RequestBody SysMenu sysMenu){
        return menuService.saveMenu(sysMenu);
    }

    @RequestMapping("/menu/info/{menuId}")
    @ResponseBody
    public R findMenuById(@PathVariable("menuId") Long menuId){
        return menuService.findMenuById(menuId);
    }

    @RequestMapping("/menu/update")
    @ResponseBody
    public R updateMenu(@RequestBody SysMenu sysMenu){
        return menuService.updateMenu(sysMenu);
    }

    @RequestMapping("/menu/user")
    @ResponseBody
    public R userMenu(){
        long userId = ShiroUtils.getUserId();
        return menuService.findUserMenu(userId);
    }
}
