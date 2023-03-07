package com.manong.service.impl;

import com.manong.config.oss.OssProperties;
import com.manong.service.FileService;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.apache.commons.io.FilenameUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Author: XiaoDu
 * @CreateTime: 2023/3/7 17:55
 * @Description:
 */
@Service
@Transactional
public class FileServiceImpl implements FileService {

    @Resource
    private OssProperties ossProperties;

    @Override
    public String upload(MultipartFile file, String module) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
//...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
        String accessKey = ossProperties.getAccessKey();
        String secretKey = ossProperties.getSecretKey();
        String bucket = ossProperties.getBucketName();
//默认不指定key的情况下，以文件内容的hash值作为文件名
        String newFileName = null;

        try {
            //上传文件流
            InputStream inputStream = file.getInputStream();
//获取旧名称
            String originalFilename = file.getOriginalFilename();
//获取文件后缀名
            String extension = FilenameUtils.getExtension(originalFilename);
//将文件名重命名
            newFileName = UUID.randomUUID().toString()
                    .replace("-", "") + "."+extension;
            //使用当前日期进行分类管理
            String datePath = new DateTime().toString("yyyy/MM/dd");
//构建文件名
            newFileName = module + "/" + datePath + "/" + newFileName;
//调用OSS文件上传的方法

            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                //上传
                //上传
                //上传
                //上传
                Response response = uploadManager.put(inputStream, newFileName, upToken, null, null);
                //解析上传成功的结果
//                new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                return ossProperties.getUrlPath()+newFileName;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (IOException ex) {
            //ignore
        }
        return ossProperties.getUrlPath()+newFileName;
    }

    @Override
    public void deleteFile(String url) {
//构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());
//...其他参数参考类注释
        String accessKey = ossProperties.getAccessKey();
        String secretKey = ossProperties.getSecretKey();
        String bucket = ossProperties.getBucketName();
        //组装文件地址
        String host = ossProperties.getUrlPath();
//获取文件名称
        String objectName = url.substring(host.length());


//        String key = "your file key";
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, objectName);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }
}
