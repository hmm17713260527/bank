package com.dj.bank.web.page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.bank.common.SystemConstant;
import com.dj.bank.pojo.BankCard;
import com.dj.bank.pojo.BankUser;
import com.dj.bank.pojo.BaseData;
import com.dj.bank.service.BankCardService;
import com.dj.bank.service.BaseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

/**
 * @ProjectName: bank
 * @Package: com.dj.bank.web.page
 * @ClassName: BankCardPageController
 * @Author: Liuwf
 * @Description: 银行卡
 * @Date: 2020/3/6 20:17
 * @Version: 1.0
 */
@Controller
@RequestMapping("/bankCard/")
public class BankCardPageController {
    @Autowired
    private BaseDataService baseDataService;

    @Autowired
    private BankCardService bankCardService;

    /**
     * 去审核
     */
    @RequestMapping("toUpdateStatus/{id}")
    public ModelAndView toUpdateStatus(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        QueryWrapper<BankCard> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        BankCard bankCard = bankCardService.getOne(wrapper);
        modelAndView.addObject("bankCard", bankCard);

        QueryWrapper<BaseData> baseDataQueryWrapper = new QueryWrapper<>();
        baseDataQueryWrapper.eq("parent_id", SystemConstant.BANK_STATUS_PID);
        List<BaseData> baseDataList = baseDataService.list(baseDataQueryWrapper);
        modelAndView.addObject("baseDataList",baseDataList);

        QueryWrapper<BaseData> baseDataQueryWrapper1 = new QueryWrapper<>();
        baseDataQueryWrapper1.eq("parent_id", SystemConstant.BANK_TYPE_PID);
        List<BaseData> baseDataList1 = baseDataService.list(baseDataQueryWrapper1);
        modelAndView.addObject("baseDataList1",baseDataList1);

        modelAndView.setViewName("bank_card/update_status");
        return modelAndView;
    }

    /**
     * @Description:去申请银行卡页面
     * @Author: Liuwf
     * @Date:
     * @param model:
     * @return: java.lang.String
     **/
    @RequestMapping("toAdd")
    public String toAdd(Model model) {
        QueryWrapper<BaseData> baseDataQueryWrapper = new QueryWrapper<>();
        baseDataQueryWrapper.eq("parent_id", SystemConstant.BANK_TYPE_PID);
        List<BaseData> baseDataList = baseDataService.list(baseDataQueryWrapper);
        model.addAttribute("baseDataList",baseDataList);
        return "bank_card/bank_card_add";
    }
    /**
     * @Description:去修改银行卡密码
     * @Author: Liuwf
     * @Date:
     * @param:
     * @param
     * @return: java.lang.String
     **/
    @RequestMapping("toUpdatePassword")
    public String toUpdatePassword() {
        return "bank_card/update_password";
    }

    @RequestMapping("toList")
    public String toList() {
        return "bank_card/bank_card_list";
    }
    /**
     * @Description:密码修改确认页面
     * @Author: Liuwf
     * @Date:

     * @return: null
     **/
    @RequestMapping("surePassword/{id}")
    public String oldPassword(@PathVariable Integer id, Model model) {
        QueryWrapper<BankCard> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        BankCard bankCard = bankCardService.getOne(wrapper);
        model.addAttribute("bankCard",bankCard);
        return "bank_card/old_password";
    }


    @RequestMapping("toUpdateStatusShow")
    public String toUpdateStatusShow() {
        return "bank_card/update_status_show";
    }

    /**
     * 展示信誉值
     * @param user
     * @param model
     * @return
     */
    @RequestMapping("toShowReputationValue")
    public String toShowReputationValue(@SessionAttribute(SystemConstant.USER_SESSION) BankUser user, Model model) {
        QueryWrapper<BankCard> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user.getId());
        queryWrapper.eq("status", SystemConstant.APPROVE_STATUS);
        List<BankCard> bankCardList = bankCardService.list(queryWrapper);
        for (BankCard list : bankCardList) {
            BaseData baseData = baseDataService.getById(list.getType());
            list.setBaseName(baseData.getName());
        }
        model.addAttribute("bankCardList", bankCardList);
        return "bank_card/show_reputation";
    }

    /**
     * 展示积分
     * @param user
     * @param
     * @return
     */
    @RequestMapping("toShowIntegral")
    private String toShowIntegral(@SessionAttribute(SystemConstant.USER_SESSION) BankUser user, Model model) {
        QueryWrapper<BankCard> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user.getId());
        queryWrapper.eq("status", SystemConstant.APPROVE_STATUS);
        List<BankCard> bankCardList = bankCardService.list(queryWrapper);
        Integer sumIntegral = SystemConstant.SUM_INTEGRAL;
        for (BankCard list : bankCardList
        ) {
            sumIntegral += list.getIntegral();
        }
        model.addAttribute("sumIntegral", sumIntegral);
        return "bank_card/show_integral";
    }

    @RequestMapping("toNewPassword/{id}")
    public String toNewPassword(@PathVariable Integer id, Model model) {
        QueryWrapper<BankCard> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        BankCard bankCard = bankCardService.getOne(wrapper);
        model.addAttribute("bankCard",bankCard);
        return "bank_card/new_password";
    }

    /**
     * 转账
     * @return
     */
    @RequestMapping("toTransfer")
    public String toTransfer(@SessionAttribute(SystemConstant.USER_SESSION) BankUser user, Model model) {
        QueryWrapper<BankCard> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user.getId());
        queryWrapper.eq("status", SystemConstant.APPROVE_STATUS);
        List<BankCard> bankCardList = bankCardService.list(queryWrapper);
        model.addAttribute("bankCardList" , bankCardList);
        return "bank_card/bank_card_transfer";
    }

}
