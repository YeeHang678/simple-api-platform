package com.ehong.ehongapiinterface.controller;

import com.ehong.ehongapiclientsdk.model.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 名称 API
 *
 * @author ehong
 */
@RestController
@RequestMapping("/name")
public class NameController {

    /**
     * 通过get方式获取姓名
     * @param name
     * @param request
     * @return
     */
    @GetMapping("/get")
    public String getNameByGet(String name, HttpServletRequest request) {
        return "GET 你的名字是" + name;
    }

    /**
     * 通过post方式获取姓名
     * @param name
     * @return
     */
    @PostMapping("/post")
    public String getNameByPost(@RequestParam String name) {
        return "POST 你的名字是" + name;
    }

    /**
     * 通过restful方式获取姓名
     * @param user
     * @param request
     * @return
     */
    @PostMapping("/user")
    public String getUsernameByPost(@RequestBody User user, HttpServletRequest request) {
        return "POST 用户名字是" + user.getUsername();
    }
}
