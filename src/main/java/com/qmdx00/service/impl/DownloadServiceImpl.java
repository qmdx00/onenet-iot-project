package com.qmdx00.service.impl;

import com.qmdx00.service.DownloadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * @author yuanweimin
 * @date 19/06/27 10:25
 * @description 下载文件逻辑实现
 */
@Slf4j
@Service
public class DownloadServiceImpl implements DownloadService {

    @Override
    public void download(String filename, HttpServletResponse response) {
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);

        URL url = this.getClass().getResource("/apk/" + filename);
        byte[] buffer = new byte[1024];
        InputStream in = null;
        OutputStream out = null;
        try {
            if (url != null) {
                in = url.openStream();
                out = response.getOutputStream();

                int len;
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                    out.flush();
                }
                log.info("download {} success", filename);
            } else {
                log.error("download {} failed", filename);
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("download {} failed", filename);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
