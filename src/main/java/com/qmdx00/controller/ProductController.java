package com.qmdx00.controller;

import com.qmdx00.entity.Product;
import com.qmdx00.service.ProductDataService;
import com.qmdx00.service.ProductService;
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
 * @description 产品信息和生产过程数据 Controller
 */
@Slf4j
@RestController
@RequestMapping("/api/product")
public class ProductController extends BaseController {

    private final ProductDataService productDataService;
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService, ProductDataService productDataService) {
        this.productService = productService;
        this.productDataService = productDataService;
    }

    /**
     * 通过 ID 查找产品信息
     *
     * @param id 产品 ID
     * @return Response
     */
    @GetMapping("/{id}")
    public Response findProductById(@PathVariable String id) {
        if (!VerifyUtil.checkString(id)) {
            return ResultUtil.returnStatus(ResponseStatus.PARAMS_ERROR);
        } else {
            Product product = productService.findProductById(id);
            if (product != null) {
                log.info("get product: {}", product);
                return ResultUtil.returnStatusAndData(ResponseStatus.SUCCESS, product);
            } else {
                return ResultUtil.returnStatus(ResponseStatus.NOT_FOUND);
            }
        }
    }

    /**
     * 查询生产过程数据
     *
     * @param id 产品 ID
     * @return Response
     */
    @GetMapping("/data/{id}")
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
