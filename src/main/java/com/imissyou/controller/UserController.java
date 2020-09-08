package com.imissyou.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.imissyou.dto.DataGridResult;
import com.imissyou.dto.QueryDTO;
import com.imissyou.dto.UserDTO;
import com.imissyou.pojo.SysUser;
import com.imissyou.service.SysUserService;
import com.imissyou.utils.MD5Utils;
import com.imissyou.utils.R;
import com.imissyou.utils.ShiroUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URLEncoder;

@Controller
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private DefaultKaptcha kaptcha;

    @RequestMapping("/captcha.jpg")
    public void captcha(HttpServletResponse response){
        //缓存操作，设置不缓存，可选操作
        response.setHeader("Cache-Control","no-store,no-cache");
        //响应内容
        response.setContentType("image/jpg");
        //生成验证码
        String text = kaptcha.createText();//文本
        BufferedImage image = kaptcha.createImage(text);
        //存储到shiro的session
        ShiroUtils.setKaptcha(text);
        try {
            ServletOutputStream servletOutputStream = response.getOutputStream();
            ImageIO.write(image,"jpg",servletOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/sys/login")
    @ResponseBody
    public R login(@RequestBody UserDTO userDTO ){
        String serverKaptcha = ShiroUtils.getKaptcha();
        if (!serverKaptcha.equalsIgnoreCase(userDTO.getCaptcha())){
            return R.error("验证码错误");
        }
        Subject subject = SecurityUtils.getSubject();
        String passMd5 = MD5Utils.md5(userDTO.getPassword(), userDTO.getUsername(), 1024);

        UsernamePasswordToken tocken = new UsernamePasswordToken(userDTO.getUsername(),passMd5);
        if (userDTO.isRememberMe())
            userDTO.setRememberMe(true);
        subject.login(tocken);
        //会去调用自定义的realm
        return R.ok();
    }

    @RequestMapping("/sys/user/list")
    @ResponseBody
    public DataGridResult findAllUser(QueryDTO queryDTO){
        return sysUserService.findUserByPage(queryDTO);
    }

    @RequestMapping("/sys/user/export")
    public void exportUserFile(HttpServletResponse response){

        Workbook workbook = sysUserService.exportUser();
        try {
        //设置响应头
        response.setContentType("application/octet-stream");//支持所有文件
        String fileName = "某用户信息表导出.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("content-disposition","attachment;filename="+fileName);

        //文件输出
        workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @RequestMapping("/logout")
    public String logout(){
        ShiroUtils.logout();
        return "redirect:index.html";
    }

    @RequestMapping("sys/user/info")
    @ResponseBody
    public R userinfo(){
        //从shiro中获取
        SysUser userEntity = ShiroUtils.getUserEntity();
        return R.ok().put("user",userEntity);
    }

}
