package com.dj.bank.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.bank.mapper.ResourceMapper;
import com.dj.bank.pojo.BankResource;
import com.dj.bank.service.ResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @ProjectName: pms
 * @Package: com.dj.pms.service.impl
 * @ClassName: ResourceServiceImpl
 * @Author: Administrator
 * @Description:
 * @Date: 2020/2/3 16:45
 * @Version: 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, BankResource> implements ResourceService {

}
