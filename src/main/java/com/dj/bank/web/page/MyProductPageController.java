package com.dj.bank.web.page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.bank.pojo.BankCard;
import com.dj.bank.pojo.BankProduct;
import com.dj.bank.pojo.BankUser;
import com.dj.bank.pojo.BaseData;
import com.dj.bank.service.BankCardService;
import com.dj.bank.service.BankProductService;
import com.dj.bank.service.BaseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import java.util.List;

/**
 * @author 86150
 */
@Controller
@RequestMapping("/myProduct/")
public class MyProductPageController {

    @Autowired
    private BankCardService bankCardService;

    @Autowired
    private BankProductService bankProductService;

    @Autowired
    private BaseDataService baseDataService;

    /**
     * 信息展示
     * @return
     */
    @RequestMapping("toShow")
    private String toShow() {
        return "my_product/show";
    }

    @RequestMapping("toAddMyProduct/{id}")
    private String toAddMyProduct(@PathVariable Integer id, Model model, @SessionAttribute("USER_SESSION") BankUser user) {
        QueryWrapper<BankCard> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user.getId());
        queryWrapper.eq("status", 17);
        List<BankCard> bankCardList = bankCardService.list(queryWrapper);
        for (BankCard list : bankCardList
             ) {
            BaseData baseData = baseDataService.getById(list.getType());
            list.setTypeShow(baseData.getName());
        }
        BankProduct bankProduct = bankProductService.getById(id);
        model.addAttribute("integral", bankProduct.getProIntegral());
        model.addAttribute("bankCardList", bankCardList);
        model.addAttribute("proId", id);
        return "my_product/add";
    }


}
