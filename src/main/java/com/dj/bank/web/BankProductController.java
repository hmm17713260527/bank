package com.dj.bank.web;

import com.dj.bank.service.BankProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 86150
 */
@RestController
@RequestMapping("/product/")
public class BankProductController {

    @Autowired
    private BankProductService bankProductService;



}
