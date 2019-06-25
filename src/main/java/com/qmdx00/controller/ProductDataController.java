package com.qmdx00.controller;

import com.qmdx00.service.ProductDataService;
import com.qmdx00.util.ResultUtil;
import com.qmdx00.util.VerifyUtil;
import com.qmdx00.util.enums.ResponseStatus;
import com.qmdx00.util.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author yuanweimin
 * @date 19/06/22 17:19
 * @description 生产过程数据 Controller
 */
@Slf4j
@RestController
@RequestMapping("/api/product")
public class ProductDataController extends BaseController {

    private final ProductDataService productDataService;

    @Autowired
    public ProductDataController(ProductDataService productDataService) {
        this.productDataService = productDataService;
    }

    /**
     * 查询生产过程数据
     *
     * @param id 产品 ID
     * @return Response
     */
    @GetMapping("/{id}")
    public Response findProductDataById(@PathVariable String id) {
        if (!VerifyUtil.checkString(id)) {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        } else {
            HashMap map = productDataService.getData(id);
            log.info("get data: {}", map);
            return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS, map);
        }
    }
}
