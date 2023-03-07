package com.manong.utils;

/**
 * @Author: XiaoDu
 * @CreateTime: 2023/3/7 16:36
 * @Description:
 */
public class QiniuyunUtils {
//    public static void main(String[] args) {
//        //构造一个带指定 Region 对象的配置类
//        Configuration cfg = new Configuration(Region.region0());
//        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
////...其他参数参考类注释
//        UploadManager uploadManager = new UploadManager(cfg);
////...生成上传凭证，然后准备上传
//        String accessKey = "f_PWt4TB_UXQhsQ346OS65RRlkHDbRBZwK3UskX9";
//        String secretKey = "84zYp9iJs_VH2AmTqkXYQN5BSSFrOp8OepeEPRkk";
//        String bucket = "authority-system";
////如果是Windows情况下，格式是 D:\\qiniu\\test.png
//        String localFilePath = "C:\\Users\\25129\\Desktop\\2.jpg";
////默认不指定key的情况下，以文件内容的hash值作为文件名
//        String key = "UHeads/";
//        Auth auth = Auth.create(accessKey, secretKey);
//        String upToken = auth.uploadToken(bucket,key);
//        try {
//            Response response = uploadManager.put(localFilePath, key, upToken);
//            //解析上传成功的结果
//            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//            System.out.println(putRet.key);
//            System.out.println(putRet.hash);
//        } catch (QiniuException ex) {
//            Response r = ex.response;
//            System.err.println(r.toString());
//            try {
//                System.err.println(r.bodyString());
//            } catch (QiniuException ex2) {
//                //ignore
//            }
//        }
//    }

    public static void main(String[] args) {
//        //构造一个带指定 Region 对象的配置类
//        Configuration cfg = new Configuration(Region.region0());
//        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
////...其他参数参考类注释
//        UploadManager uploadManager = new UploadManager(cfg);
////...生成上传凭证，然后准备上传
//        String accessKey = "f_PWt4TB_UXQhsQ346OS65RRlkHDbRBZwK3UskX9";
//        String secretKey = "84zYp9iJs_VH2AmTqkXYQN5BSSFrOp8OepeEPRkk";
//        String bucket = "authority-system";
////默认不指定key的情况下，以文件内容的hash值作为文件名
//        String key = null;
//        try {
//            byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
//            ByteArrayInputStream byteInputStream=new ByteArrayInputStream(uploadBytes);
//            Auth auth = Auth.create(accessKey, secretKey);
//            String upToken = auth.uploadToken(bucket);
//            try {
//                Response response = uploadManager.put(byteInputStream,"11.jpg",upToken,null, null);
//                //解析上传成功的结果
//                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//                System.out.println(putRet.key);
//                System.out.println(putRet.hash);
//            } catch (QiniuException ex) {
//                Response r = ex.response;
//                System.err.println(r.toString());
//                try {
//                    System.err.println(r.bodyString());
//                } catch (QiniuException ex2) {
//                    //ignore
//                }
//            }
//        } catch (UnsupportedEncodingException ex) {
//            //ignore
//        }
    }
}
