package com.imissyou.service.impl;
import java.util.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imissyou.dao.SysMenuMapper;
import com.imissyou.dto.DataGridResult;
import com.imissyou.dto.QueryDTO;
import com.imissyou.pojo.SysMenu;
import com.imissyou.service.MenuService;
import com.imissyou.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    //菜单显示
    @Override
    public DataGridResult findMenu(QueryDTO queryDTO) {

        PageHelper.offsetPage(queryDTO.getOffset(),queryDTO.getLimit());
        if (queryDTO.getSort() != null && !queryDTO.getSort().equals("")){
            queryDTO.setSort("menu_id");
        }
        List<SysMenu> menuByPages = sysMenuMapper.findMenuByPages(queryDTO);
        PageInfo<SysMenu> info = new PageInfo<>(menuByPages);
        DataGridResult result = new DataGridResult(info.getTotal(),info.getList());
        return result;
    }

    //批量删除
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public R deleteMenu(List<Long> ids) {
        for (Long id : ids) {
            if (id<50){
                return R.error(-200,"系统菜单，没有删除权限！");
            }
        }

        int menu = sysMenuMapper.deleteMenu(ids);
        if (menu>0){
            return R.ok();
        }else {
            return R.error(-200,"操作有误，删除失败！");
        }
    }

    @Override
    public R selectMenu() {
        List<SysMenu> menu = sysMenuMapper.findMenu();

        //添加一个跟目录
        SysMenu sysMenu  = new SysMenu();
        sysMenu.setMenuId(0L);
        sysMenu.setType(0);
        sysMenu.setParentId(-1L);
        sysMenu.setName("以及菜单");
        menu.add(sysMenu);
        return R.ok().put("menuList",menu);
    }

    @Override
    public R saveMenu(SysMenu sysMenu) {
        int i = sysMenuMapper.insertSelective(sysMenu);
        return i>0 ? R.ok() : R.ok("新增失败@！");
    }

    @Override
    public R findMenuById(Long menuId) {
        SysMenu sysMenu = sysMenuMapper.selectByPrimaryKey(menuId);
        return R.ok().put("menu",sysMenu);
    }

    @Override
    public R updateMenu(SysMenu sysMenu) {
        int i = sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
        return i>0 ? R.ok() : R.ok("更新失败！！");
    }

    @Override
    public List<String> findPermsByUserId(Long userId) {
        List<String> permsByUserId = sysMenuMapper.findPermsByUserId(userId);
        Set<String> set = new HashSet<> ();
        for (String s : permsByUserId) {
            if (s != null && !s.equals("")) {
                String[] split = s.split(",");
                for (String s1 : split) {
                    set.add(s1);
                }
            }
        }
        List<String> perms = new ArrayList<>();
        perms.addAll(set);
        return perms;
    }

    @Override
    public R findUserMenu(Long userId) {
        //查询用户的一级目录
        List<Map<String, Object>> dirMenuByUserId = sysMenuMapper.findDirMenuByUserId(userId);
        //查询目录对应的子菜单
        for (Map<String, Object> map : dirMenuByUserId) {
            Long menuId = Long.parseLong(map.get("menuId")+"");
            List<Map<String, Object>> subList = sysMenuMapper.findMenuNotButtonByUserId(userId, menuId);
            map.put("list",subList);
        }
        List<String> permsByUserId = sysMenuMapper.findPermsByUserId(userId);
        return R.ok().put("menuList",dirMenuByUserId).put("permissions",permsByUserId);
    }


}
