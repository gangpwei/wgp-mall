package com.xxl.job.admin.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.xxl.job.admin.core.model.Order;
import com.xxl.job.admin.core.model.XxlJobUser;
import com.xxl.job.core.biz.model.ReturnT;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xuxueli 2019-05-04 16:39:50
 */
@Controller
@RequestMapping("/order")
public class OrderController {


    @RequestMapping
    public String index(Model model) {

        return "order/order.index";
    }

    @RequestMapping("/pageList")
    @ResponseBody
    public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int start,
                                        @RequestParam(required = false, defaultValue = "10") int length,
                                        String orderId) {

        // page list
        List<Order> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Order o = new Order();
            o.setOrderId(System.currentTimeMillis());
            o.setBuyerId(10L);
            o.setGmtCreate(new Date());
            o.setStatus("INIT");
            o.setPrice(BigDecimal.valueOf(new Random().nextInt(1000)));
            o.setSkuCode("sku_" + new Random().nextInt(1000));
            o.setSkuName("商品_" + new Random().nextInt(1000));
            list.add(o);
        }

        // package result
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("recordsTotal", 200);		// 总记录数
        maps.put("recordsFiltered", 200);	// 过滤后的总记录数
        maps.put("data", list);  					// 分页列表
        return maps;
    }

    @RequestMapping("/add")
    @ResponseBody
    public ReturnT<String> add(XxlJobUser xxlJobUser) {

        //// valid username
        //if (!StringUtils.hasText(xxlJobUser.getUsername())) {
        //    return new ReturnT<String>(ReturnT.FAIL_CODE, I18nUtil.getString("system_please_input")+I18nUtil.getString("user_username") );
        //}
        //xxlJobUser.setUsername(xxlJobUser.getUsername().trim());
        //if (!(xxlJobUser.getUsername().length()>=4 && xxlJobUser.getUsername().length()<=20)) {
        //    return new ReturnT<String>(ReturnT.FAIL_CODE, I18nUtil.getString("system_lengh_limit")+"[4-20]" );
        //}
        //// valid password
        //if (!StringUtils.hasText(xxlJobUser.getPassword())) {
        //    return new ReturnT<String>(ReturnT.FAIL_CODE, I18nUtil.getString("system_please_input")+I18nUtil.getString("user_password") );
        //}
        //xxlJobUser.setPassword(xxlJobUser.getPassword().trim());
        //if (!(xxlJobUser.getPassword().length()>=4 && xxlJobUser.getPassword().length()<=20)) {
        //    return new ReturnT<String>(ReturnT.FAIL_CODE, I18nUtil.getString("system_lengh_limit")+"[4-20]" );
        //}
        //// md5 password
        //xxlJobUser.setPassword(DigestUtils.md5DigestAsHex(xxlJobUser.getPassword().getBytes()));
        //
        //// check repeat
        //XxlJobUser existUser = xxlJobUserDao.loadByUserName(xxlJobUser.getUsername());
        //if (existUser != null) {
        //    return new ReturnT<String>(ReturnT.FAIL_CODE, I18nUtil.getString("user_username_repeat") );
        //}
        //
        //// write
        //xxlJobUserDao.save(xxlJobUser);
        return ReturnT.SUCCESS;
    }

    @RequestMapping("/update")
    @ResponseBody
    public ReturnT<String> update(HttpServletRequest request, XxlJobUser xxlJobUser) {

        //// avoid opt login seft
        //XxlJobUser loginUser = (XxlJobUser) request.getAttribute(LoginService.LOGIN_IDENTITY_KEY);
        //if (loginUser.getUsername().equals(xxlJobUser.getUsername())) {
        //    return new ReturnT<String>(ReturnT.FAIL.getCode(), I18nUtil.getString("user_update_loginuser_limit"));
        //}
        //
        //// valid password
        //if (StringUtils.hasText(xxlJobUser.getPassword())) {
        //    xxlJobUser.setPassword(xxlJobUser.getPassword().trim());
        //    if (!(xxlJobUser.getPassword().length()>=4 && xxlJobUser.getPassword().length()<=20)) {
        //        return new ReturnT<String>(ReturnT.FAIL_CODE, I18nUtil.getString("system_lengh_limit")+"[4-20]" );
        //    }
        //    // md5 password
        //    xxlJobUser.setPassword(DigestUtils.md5DigestAsHex(xxlJobUser.getPassword().getBytes()));
        //} else {
        //    xxlJobUser.setPassword(null);
        //}
        //
        //// write
        //xxlJobUserDao.update(xxlJobUser);
        return ReturnT.SUCCESS;
    }

    @RequestMapping("/remove")
    @ResponseBody
    public ReturnT<String> remove(HttpServletRequest request, int id) {
        //
        //// avoid opt login seft
        //XxlJobUser loginUser = (XxlJobUser) request.getAttribute(LoginService.LOGIN_IDENTITY_KEY);
        //if (loginUser.getId() == id) {
        //    return new ReturnT<String>(ReturnT.FAIL.getCode(), I18nUtil.getString("user_update_loginuser_limit"));
        //}
        //
        //xxlJobUserDao.delete(id);
        return ReturnT.SUCCESS;
    }

    @RequestMapping("/updatePwd")
    @ResponseBody
    public ReturnT<String> updatePwd(HttpServletRequest request, String password){
        //
        //// valid password
        //if (password==null || password.trim().length()==0){
        //    return new ReturnT<String>(ReturnT.FAIL.getCode(), "密码不可为空");
        //}
        //password = password.trim();
        //if (!(password.length()>=4 && password.length()<=20)) {
        //    return new ReturnT<String>(ReturnT.FAIL_CODE, I18nUtil.getString("system_lengh_limit")+"[4-20]" );
        //}
        //
        //// md5 password
        //String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        //
        //// update pwd
        //XxlJobUser loginUser = (XxlJobUser) request.getAttribute(LoginService.LOGIN_IDENTITY_KEY);
        //
        //// do write
        //XxlJobUser existUser = xxlJobUserDao.loadByUserName(loginUser.getUsername());
        //existUser.setPassword(md5Password);
        //xxlJobUserDao.update(existUser);

        return ReturnT.SUCCESS;
    }

}
