package com.qmdx00.onenet.mq;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMReader;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @author yuanweimin
 * @date 19/06/12 17:07
 * @description 证书加载类
 */
@SuppressWarnings("unused")
public class SslUtil {

    public static SSLSocketFactory getSocketFactory(final InputStream caCrtFile) throws NoSuchAlgorithmException,
            IOException, KeyStoreException, CertificateException, KeyManagementException {
        Security.addProvider(new BouncyCastleProvider());

        //===========加载 ca 证书==================================
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        if (null != caCrtFile) {
            // 加载本地指定的 ca 证书
            PEMReader reader = new PEMReader(new InputStreamReader(caCrtFile));
            X509Certificate caCert = (X509Certificate) reader.readObject();
            reader.close();

            // CA certificate is used to authenticate server
            KeyStore caKs = KeyStore.getInstance(KeyStore.getDefaultType());
            caKs.load(null, null);
            caKs.setCertificateEntry("ca-certificate", caCert);
            // 把ca作为信任的 ca 列表,来验证服务器证书
            tmf.init(caKs);
        } else {
            //使用系统默认的安全证书
            tmf.init((KeyStore) null);
        }

        // ============finally, create SSL socket factory==============
        SSLContext context = SSLContext.getInstance("TLSv1.2");
        context.init(null, tmf.getTrustManagers(), null);

        return context.getSocketFactory();
    }

}
