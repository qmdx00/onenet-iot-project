package com.qmdx00.controller;

import com.qmdx00.service.DownloadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author yuanweimin
 * @date 19/06/27 10:21
 * @description App 下载接口
 */
@Slf4j
@RestController
@RequestMapping("/api/download")
public class DownloadController extends BaseController {

    private final DownloadService downloadService;

    @Autowired
    public DownloadController(DownloadService downloadService) {
        this.downloadService = downloadService;
    }

    /**
     * 边缘网关 App 下载
     *
     * @param response 响应
     */
    @GetMapping("/gateway")
    public void getEdgeGateway(HttpServletResponse response) {
        downloadService.download("gateway.apk", response);
    }

    /**
     * 边缘网关 App 下载
     *
     * @param response 响应
     */
    @GetMapping("/origins")
    public void getOrigins(HttpServletResponse response) {
        downloadService.download("origins.apk", response);
    }
}
