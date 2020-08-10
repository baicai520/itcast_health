package com.itheima.job;

import com.itheima.constant.RedisConstant;
import com.itheima.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Iterator;
import java.util.Set;

/**
 * @Description TODO
 * @program: health_parent
 * @ClassName : ClearImgJob
 * @author: TAO
 * @create: 2020-08-09 17:31
 **/
public class ClearImgJob {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 定时清理垃圾图片
     */
    public void clearImg(){
        //计算redis中两个集合的差值,获取垃圾图片
        Set<String> set = jedisPool.getResource().sdiff(
                RedisConstant.SETMEAL_PIC_RESOURCES,
                RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String pic = iterator.next();
            System.out.println("删除图片的名称是:"+pic);
            //删除图片服务器中的图片文件
            QiniuUtils.deleteFileFromQiniu(pic);
            //删除redis中的数据
            jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,pic);
        }
    }

}
