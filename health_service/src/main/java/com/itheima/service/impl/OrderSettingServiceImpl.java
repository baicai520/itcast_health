package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.constant.MessageConstant;
import com.itheima.dao.OrderSettingDao;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 预约设置实现
 * @program: health_parent
 * @ClassName : OrderSettingServiceImpl
 * @author: TAO
 * @create: 2020-08-09 20:02
 **/
@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private OrderSettingDao orderSettingDao;

    /**
     * 批量预约设置
     * @param orderSettingList
     */
    @Override
    public void add(List<OrderSetting> orderSettingList) {
        //判断
        if (orderSettingList !=null && orderSettingList.size()>0){
            for (OrderSetting orderSetting : orderSettingList) {
                //调用抽取公共预约设置方法
                addOrderSetting(orderSetting);
            }
        }
    }

    /**
     * 根据日期查询预约设置数据
     * @param date
     * @return
     */
    @Override
    public List<Map> getOrderSettingByMonth(String date) {
        //拼接起始年月 2020-8-1  结束年月 2020-8-31
        String  dateBegin = date+"-1";
        String  dateEnd = date+"-31";
        Map map = new HashMap();
        map.put("dateBegin",dateBegin);
        map.put("dateEnd",dateEnd);
        //调用dao查询数据返回List<OrderSetting>
        List<OrderSetting> orderSettingList = orderSettingDao.getOrderSettingByMonth(map);
        List<Map> data = new ArrayList<>();
        //将List<OrderSetting>转换为List<Map>
        for (OrderSetting orderSetting : orderSettingList) {
            Map orderSettingMap=new HashMap<>();
            //{ date: 1, number: 120, reservations: 1 }
            //获取日期   几号
            orderSettingMap.put("date",orderSetting.getOrderDate().getDate());
            //获取可预约人数
            orderSettingMap.put("number",orderSetting.getNumber());
            //获取已预约人数
            orderSettingMap.put("reservations",orderSetting.getReservations());
            data.add(orderSettingMap);
        }
        return data;
     }

    /**
     * 根据指定日期修改可预约人数()单个
     * @param orderSetting
     */
    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        //调用抽取公共预约设置方法
        addOrderSetting(orderSetting);
        /*//1返回OrderSetting（根据预约日期查询 Ordersetting）
        OrderSetting setting = orderSettingDao.findAll(orderSetting.getOrderDate());

        //2.判断OrderSetting是否为空，如果不为空
        if (setting !=null){
            //3.不为空，页面输入的可预约人数(orderSetting,getNumber页面输入的) < 已经预约人数（dbOrderSeting.getReservations） 抛出异常
                if (orderSetting.getNumber() < setting.getNumber()){
                    throw new RuntimeException(MessageConstant.IMPORT_ORDERSETTING_DB_FAIL);
                }
        }else {
            //4.为空则直接插入数据
            orderSettingDao.insert(orderSetting);
        }*/
    }

    /**
     * 抽取公共预约设置方法
     * @param orderSetting
     */
    private void addOrderSetting(OrderSetting orderSetting){
        //根据预约日期到t_ordersetting查询数据是否存在
        int count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
        if (count >0){
            //存在则更新
            orderSettingDao.editNumberByOrderDate(orderSetting);
        }else {
            //不存在则插入
            orderSettingDao.add(orderSetting);
        }
    }
}
