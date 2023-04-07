package com.manong.test;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 * @Author: XiaoDu
 * @CreateTime: 2023/4/7 1:57
 * @Description:
 */
public class HttpDemo {
    public static void main(String[] args) {
        String servername = "Silkworm";
        String methodname = "SilkwormService.GetStorePromotionList";
        String garen = System.currentTimeMillis()+"";
        String nami = StrUtil.uuid();
        String body = "{ \"latitude\": 30.241426467895508, \"longitude\": 119.93916320800781, \"promotion_sort\": 1, \"store_type\": 0, \"offset\": 0, \"number\": 20, \"promotion_filter\": 0, \"promotion_category\": 0 }";
        JSONObject jsonObject = JSONUtil.parseObj(body);
        String ashe = SecureUtil.md5(SecureUtil.md5((servername+"."+methodname).toLowerCase())+garen+nami);
        Console.log(ashe);
        String url = "https://gw.xiaocantech.com/rpc";
        //链式构建请求
        String result2 = HttpRequest.post(url)
                .header(Header.USER_AGENT, "Hutool http")//头信息，多个头信息多次调用此方法即可
                .header( "servername",servername)
                .header( "methodname",methodname)
                .header( "X-Garen",garen)
                .header( "X-Nami",nami)
                .header( "X-Ashe",ashe)
                .body(jsonObject.toString())//表单内容
                .timeout(20000)//超时，毫秒
                .execute().body();
        Console.log(result2);
    }


}