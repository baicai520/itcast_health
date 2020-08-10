package com.itheima.dao;

import com.itheima.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description 预约设置dao
 * @program: health_parent
 * @ClassName : OrderSettingDao
 * @author: TAO
 * @create: 2020-08-09 20:04
 **/
public interface OrderSettingDao {
    /**
     * 根据预约日期到t_ordersetting查询数据是否存在
     * @param orderDate
     * @return
     */
    int findCountByOrderDate(Date orderDate);

    /**
     * 不存在则插入
     * @param orderSetting
     */
    void add(OrderSetting orderSetting);

    /**
     * 存在则更新
     * @param orderSetting
     */
    void editNumberByOrderDate(OrderSetting orderSetting);

    /**
     * 根据日期查询预约设置数据
     * @param map
     * @return
     */
    List<OrderSetting> getOrderSettingByMonth(Map map);

    /**
     * 返回OrderSetting（根据预约日期查询 Ordersetting）
     * @param orderDate
     * @return
     */
    //OrderSetting findAll(Date orderDate);

    /**
     * 为空则直接插入数据
     * @param orderSetting
     */
    //void insert(OrderSetting orderSetting);


}
