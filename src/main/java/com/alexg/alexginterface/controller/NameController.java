package com.alexg.alexginterface.controller;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.alexg.alexgclientsdk.model.User;
import com.alexg.alexgclientsdk.utils.SignUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/*
* 名称 API
*
* @author alexg
* */
@RestController
@RequestMapping(value = "/name",produces = "text/html;charset=utf-8")
public class NameController {


    @GetMapping("/get")
    public String getNameByGet(String name,HttpServletRequest request) {
        return "发送GET请求 你的名字是：" + name;
    }

    @PostMapping("/post")
    public String getNameByPost(@RequestParam(value = "name") String name) {
        return "发送POST请求 你的名字是：" + name;
    }

    @PostMapping("/user")
    public String getNameByPostWithJson(@RequestBody User user, HttpServletRequest request)throws UnsupportedEncodingException {
        String accessKey = request.getHeader("accessKey");
        // 防止中文乱码
        String body = URLDecoder.decode(request.getHeader("body"), StandardCharsets.UTF_8.name());
        String sign = request.getHeader("sign");
        String nonce = request.getHeader("nonce");
        String timestamp = request.getHeader("timestamp");
        boolean hasBlank = StrUtil.hasBlank(accessKey, body, sign, nonce, timestamp);
        // 判断是否有空
        if (hasBlank) {
            return "无权限";
        }
        // TODO 使用accessKey去数据库查询secretKey
        // 假设查到的secret是abc 进行加密得到sign
        String secretKey = "abc";
        String sign1 = SignUtil.getSign(body, secretKey);
        if (!StrUtil.equals(sign, sign1)) {
            return "无权限";
        }
        // TODO 判断随机数nonce
        // 时间戳是否为数字
        if (!NumberUtil.isNumber(timestamp)) {
            return "无权限";
        }
        // 五分钟内的请求有效
        if (System.currentTimeMillis() - Long.parseLong(timestamp) > 5 * 60 * 1000) {
            return "无权限";
        }
        return "Your username:" + user.getUsername();
    }

}
