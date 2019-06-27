package com.qmdx00.service;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;

/**
 * @author yuanweimin
 * @date 19/06/27 10:25
 * @description 下载 app 逻辑
 */
public interface DownloadService {
    /**
     * 文件下载
     *
     * @param src      文件地址
     * @param response 响应
     * @throws FileNotFoundException 文件不存在
     */
    void download(String src, HttpServletResponse response);
}
