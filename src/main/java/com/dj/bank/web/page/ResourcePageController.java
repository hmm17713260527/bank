package com.dj.bank.web.page;

import com.dj.bank.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ProjectName: pms
 * @Package: com.dj.pms.web.page
 * @ClassName: ResourcePageController
 * @Author: Administrator
 * @Description:
 * @Date: 2020/2/7 17:15
 * @Version: 1.0
 */
@Controller
@RequestMapping("/resource/")
public class ResourcePageController {
    @Autowired
    private ResourceService resourceService;



}
