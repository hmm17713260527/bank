package com.dj.bank.web.page;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dj.bank.common.SystemConstant;
import com.dj.bank.pojo.BankProduct;
import com.dj.bank.service.BankProductService;
import com.dj.bank.util.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
            String fileName = UUID.randomUUID().toString().replace(SystemConstant.PARENT_NAME, SystemConstant.EXCEPTION)
                    + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(SystemConstant.SYMBOL));
            //通过inputStream上传文件
            InputStream inputStream = file.getInputStream();
            //调用七牛云工具类中的上传方法
            QiniuUtils.uploadByInputStream(inputStream, fileName);
            bankProduct.setProImg(fileName);
            bankProductService.save(bankProduct);
            model.addAttribute("msg", SystemConstant.SUCCESS);
            return "product/show";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", SystemConstant.ERROR);
            return "product/add";
        }
    }

    /**
     * 去修改
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("toUpdateById/{id}")
    public String toUpdateById(@PathVariable("id") Integer id, Model model) {
        BankProduct product = bankProductService.getById(id);
        model.addAttribute("product", product);
        return "product/update";
    }

    /**
     * 修改
     * @param bankProduct
     * @param file
     * @return
     */
    @RequestMapping("updateById")
    public String updateById(BankProduct bankProduct, MultipartFile file) {
        try {
            UpdateWrapper<BankProduct> updateWrapper = new UpdateWrapper<>();
            if (file != null && !file.isEmpty()) {
                String fileName = UUID.randomUUID().toString().replace(SystemConstant.PARENT_NAME, SystemConstant.EXCEPTION)
                        + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(SystemConstant.SYMBOL));
                //通过inputStream上传文件
                InputStream inputStream = file.getInputStream();
                //调用七牛云工具类中的上传方法
                QiniuUtils.uploadByInputStream(inputStream, fileName);
                updateWrapper.set("pro_img", fileName);
                //删掉之前的图片
                QiniuUtils.delFile(bankProduct.getProImg());
            }
            updateWrapper.set("count", bankProduct.getCount());
            updateWrapper.set("pro_integral", bankProduct.getProIntegral());
            updateWrapper.eq("id", bankProduct.getId());
            bankProductService.update(updateWrapper);
            return "product/show";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
