package com.dj.bank.web.page;
import com.dj.bank.pojo.BankProduct;
import com.dj.bank.service.BankProductService;
import com.dj.bank.util.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author 86150
 */
@Controller
@RequestMapping("/product/")
public class BankProductPageController {

    @Autowired
    private BankProductService bankProductService;

    /**
     * 去展示
     * @return
     */
    @RequestMapping("toShow")
    public String toShow() {
        return "product/show";
    }

    /**
     * 去添加
     * @return
     */
    @RequestMapping("toAdd")
    public String toAdd() {
        return "product/add";
    }

    /**
     * 上传图片
     * @param
     * @return
     */
    @RequestMapping("add")
    public String updateImg(MultipartFile file, BankProduct bankProduct, Model model) {
        try {
            String fileName = UUID.randomUUID().toString().replace("-", "")
                    + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
            //通过inputStream上传文件
            InputStream inputStream = file.getInputStream();
            //调用七牛云工具类中的上传方法
            QiniuUtils.uploadByInputStream(inputStream, fileName);
            bankProduct.setProImg(fileName);
            bankProductService.save(bankProduct);
            model.addAttribute("msg", "成功");
            return "product/show";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "失败");
            return "product/add";
        }
    }


}
