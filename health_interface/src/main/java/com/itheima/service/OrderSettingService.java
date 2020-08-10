package com.itheima.service;

import com.itheima.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * @Description 预约列表服务接口
 * @program: health_parent
 * @ClassName : OrderSettingService
 * @author: TAO
 * @create: 2020-08-09 20:00
 **/
public interface OrderSettingService {

    /**
     * 批量预约设置
     * @param orderSettingList
     */
    void add(List<OrderSetting> orderSettingList);


    /**
     * 根据日期查询预约设置数据
     * @param date
     * @return
     */
    List<Map> getOrderSettingByMonth(String date);

    /**
     * 根据指定日期修改可预约人数
     * @param orderSetting
     */
    void editNumberByDate(OrderSetting orderSetting);
}
