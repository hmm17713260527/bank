package com.dj.bank.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dj.bank.common.ResultModel;
import com.dj.bank.common.SystemConstant;
import com.dj.bank.pojo.*;
import com.dj.bank.service.BankCardService;
import com.dj.bank.service.LoansService;
import com.dj.bank.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@RequestMapping("/bankCard/")
public class BankCardController {

    @Autowired
    private BankCardService bankCardService;

    @Autowired
    private LoansService loansService;


    @GetMapping("show")
    public ResultModel<Object> show(Integer status) {
        try {
            QueryWrapper<BankCard> bankCardQueryWrapper = new QueryWrapper<>();
            bankCardQueryWrapper.eq("status",status);
            List<BankCard> list = bankCardService.list(bankCardQueryWrapper);
            return new ResultModel<>().success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error(SystemConstant.EXCEPTION);
        }
    }

    @GetMapping("findCardStatus")
    public ResultModel<Object> findCardStatus(HttpSession session) {
        try {
            BankUser user = (BankUser) session.getAttribute(SystemConstant.USER_SESSION);
            QueryWrapper<BankCard> bankCardQueryWrapper = new QueryWrapper<>();
            bankCardQueryWrapper.eq("user_id", user.getId());
            List<BankCard> bankCardList = bankCardService.list(bankCardQueryWrapper);
            for (BankCard bankCard : bankCardList) {
                if (bankCard.getStatus() == 19) {
                    return new ResultModel<>().error(SystemConstant.ACCOUNT_IS_FROZEN);
                }
            }

            List<BankLoans> repaymentList = loansService.findRepaymentList(2, user.getId());
            for (BankLoans bankLoans : repaymentList) {
                Integer i = loansService.findDate(bankLoans.getRepaymentTime());
                if (i+1 > 0) {
                    UpdateWrapper<BankLoans> updateWrapper = new UpdateWrapper<>();
                    updateWrapper.set("is_del", 1);
                    updateWrapper.eq("id", bankLoans.getId());
                    loansService.update(updateWrapper);
                }
            }

            List<BankLoans> repaymentList2 = loansService.findRepaymentList(1, user.getId());
            for (BankLoans bankLoans : repaymentList2) {
                Integer i = loansService.findDate(bankLoans.getRepaymentTime());
                BankCard card = bankCardService.getById(bankLoans.getBankCardId());
                if (i+1 > 0 && bankLoans.getType() == 0) {
                    int i1 = card.getReputationValue() - 10;
                    card.setReputationValue(i1);
                }
                card.setStatus(19);
                bankCardService.updateById(card);
                return new ResultModel<>().error(SystemConstant.ACCOUNT_IS_FROZEN);
            }
            return new ResultModel<>().success("欢迎登陆掌上银行APP");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error(SystemConstant.EXCEPTION);
        }
    }

    /**
     * 银行卡展示
     * @return
     */
    @GetMapping("bankCardList")
    public ResultModel<Object> bankCardList(HttpSession session, Integer status) {
        try {
            BankUser user = (BankUser) session.getAttribute(SystemConstant.USER_SESSION);
            List<BankCard> listByUserId = bankCardService.findListByUserId(status,user.getId());
            return new ResultModel<>().success(listByUserId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error(SystemConstant.EXCEPTION);
        }
    }
    /**
     * @Description:获取银行卡号
     * @Author: Liuwf
     * @Date:
     * @param
     * @return: null
     **/
    @RequestMapping("getNumber")
    public ResultModel<Object> getNumber(Integer type){
        try {
            String number = "";
            SimpleDateFormat format = new SimpleDateFormat("YYYYMMddHHmm");
            if(null != type){
                number = format.format(new Date()) + Random.getRandNum();
            }
            ResultModel resultModel = new ResultModel().success(true);
            resultModel.setData(number);
            return resultModel;
        }catch (Exception e){
            e.printStackTrace();
            return new ResultModel<>().error(SystemConstant.EXCEPTION);
        }

    }
    /**
     * @Description:申请银行卡
     * @Author: Liuwf
     * @Date:
     * @param bankCard:
     * @param session:
     * @return: com.dj.bank.common.ResultModel<java.lang.Object>
     **/
    @RequestMapping("insertCard")
    public ResultModel<Object> insertCard(BankCard bankCard, HttpSession session){
        try {
            BankUser user = (BankUser) session.getAttribute(SystemConstant.USER_SESSION);
            QueryWrapper<BankCard> bankCardQueryWrapper = new QueryWrapper<>();
            bankCardQueryWrapper.eq("type",bankCard.getType());
            bankCardQueryWrapper.eq("user_id",user.getId());
            BankCard card = bankCardService.getOne(bankCardQueryWrapper);
            if (null != card){
                return new ResultModel<>().error(SystemConstant.BANK_CARD_TYPE_IS_ONE);
            }
            bankCard.setUserId(user.getId()).setReputationValue(60).setIntegral(1000)
                    .setCreateTime(new Date()).setStatus(SystemConstant.CARD_STATUS_AWAIT)
                    .setBalance(0.00).setBorrowBalance(30000.00);
            bankCardService.save(bankCard);
            return new ResultModel<>().success(SystemConstant.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResultModel<>().error(SystemConstant.EXCEPTION + e.getMessage());
        }

    }


    /**
     * 借款
     * @param bankCard
     * @param bankLoans
     * @return
     */
    @RequestMapping("updateLoansById")
    public ResultModel<Object> updateLoansById(BankCard bankCard, BankLoans bankLoans){
        try {
            bankCardService.updateBankCardAndSaveLoans(bankCard, bankLoans);
            return new ResultModel<>().success();
        }catch (Exception e){
            e.printStackTrace();
            return  new ResultModel<>().error(SystemConstant.EXCEPTION + e.getMessage());
        }

    }

    /**
     * 充值
     * @param balance
     * @param bankCardId
     * @param tradingRecord
     * @return
     */
    @RequestMapping("updateCardBalance")
    public ResultModel<Object> updateCardBalance(Double balance, Integer bankCardId, TradingRecord tradingRecord){
        try {
            bankCardService.updateBankCardAndUpdateTradingRecord(balance, bankCardId, tradingRecord);
            return new ResultModel<>().success();
        }catch (Exception e){
            e.printStackTrace();
            return  new ResultModel<>().error(SystemConstant.EXCEPTION + e.getMessage());
        }

    }
    /**
     * @Description:x修改密码银行卡展示
     * @Author: Liuwf
     * @Date:  
     * @param session: 
     * @param status: 
     * @return: com.dj.bank.common.ResultModel<java.lang.Object>
     **/
    @GetMapping("userCardList")
    public ResultModel<Object> userCardList(HttpSession session, Integer status) {
        try {
            BankUser bankUser = (BankUser) session.getAttribute(SystemConstant.USER_SESSION);
            List<BankCard> bankCardList = bankCardService.findListByUserId(status, bankUser.getId());
            return new ResultModel<>().success(bankCardList);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResultModel<>().error(SystemConstant.EXCEPTION + e.getMessage());
        }

    }

    @PutMapping("updateStatusById")
    public ResultModel<Object> updateStatusById(BankCard bankCard) {
        try {
            bankCardService.updateById(bankCard);
            return new ResultModel<>().success();
        }catch (Exception e){
            e.printStackTrace();
            return  new ResultModel<>().error(SystemConstant.EXCEPTION + e.getMessage());
        }

    }
    /**
     * @Description:查询旧密码
     * @Author: Liuwf
     * @Date:
     * @param bankCard:
     * @return: com.dj.bank.common.ResultModel<java.lang.Object>
     **/
   @GetMapping("getPassword")
    public ResultModel<Object> getPassword(BankCard bankCard) {
       try {
           QueryWrapper<BankCard> bankCardQueryWrapper = new QueryWrapper<>();
           bankCardQueryWrapper.eq("id",bankCard.getId());
           bankCardQueryWrapper.eq("password",bankCard.getPassword());
           BankCard bankCard1 = bankCardService.getOne(bankCardQueryWrapper);
           if (null == bankCard1){
               return  new ResultModel<>().error(SystemConstant.INPUT_PASSWORD_ERROR);
           }
           return new ResultModel<>().success();
       }catch (Exception e){
           e.printStackTrace();
           return  new ResultModel<>().error(SystemConstant.EXCEPTION + e.getMessage());
       }

    }
    /**
     * @Description: 修改银行卡密码
     * @Author: Liuwf
     * @Date:
     * @param bankCard:
     * @return: com.dj.bank.common.ResultModel<java.lang.Object>
     **/
    @PutMapping("updatePassword")
    public ResultModel<Object> updatePassword(BankCard bankCard) {
        try {
            bankCardService.updateById(bankCard);
            return new ResultModel<>().success();
        }catch (Exception e){
            e.printStackTrace();
            return  new ResultModel<>().error(SystemConstant.EXCEPTION + e.getMessage());
        }

    }


}
