package cn.yat.controller;

import cn.yat.service.TestcaseService;
import cn.yat.service.UserService;
import cn.yat.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class GeneralController {

    @Autowired
    UserService us;
    @Autowired
    DataService ds;
    @Autowired
    TestcaseService tcs;

    @RequestMapping(value = "/user/get", method = RequestMethod.GET)
    public void getUser(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(us.get(request).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "/user/post", method = RequestMethod.POST)
    public void postUser(HttpServletRequest request,HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(us.post(request).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/data", method = RequestMethod.POST)
    public void postData(HttpServletRequest request,HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(ds.post(request).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "/tc", method = RequestMethod.GET)
    public void getTc(HttpServletRequest request,HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(tcs.get(request).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "/tc", method = RequestMethod.POST)
    public void postTc(HttpServletRequest request,HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(tcs.post(request).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
