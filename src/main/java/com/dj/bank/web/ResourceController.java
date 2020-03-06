package com.dj.bank.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.bank.pojo.BankResource;
import com.dj.bank.pojo.BankUser;
import com.dj.bank.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * @ProjectName: pms
 * @Package: com.dj.pms.web
 * @ClassName: ResourceController
 * @Author: Administrator
 * @Date: 2020/2/3 17:18
 * @Version: 1.0
 */
@RestController
@RequestMapping("/resource/")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;


    @RequestMapping("toList")
    public List<BankResource> toList(Integer id, HttpSession session) throws Exception {

        BankUser user = (BankUser) session.getAttribute("user");
        QueryWrapper<BankResource> wrapper = new QueryWrapper<BankResource>();
        if (user.getType() == 1) {
            wrapper.or().eq("type", 1)
                    .or().eq("type", 2);
        } else {
            wrapper.or().eq("type", 1)
                    .or().eq("type", 3);
        }
        List<BankResource> ResourceList = resourceService.list(wrapper);

        return ResourceList;

    }






}
