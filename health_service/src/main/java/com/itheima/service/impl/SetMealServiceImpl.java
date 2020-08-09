package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.constant.MessageConstant;
import com.itheima.dao.SetMealDao;
import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetMealService;
import com.itheima.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 套餐服务接口
 * @program: health_parent
 * @ClassName : SetMealServiceImpl
 * @author: TAO
 * @create: 2020-08-08 17:43
 **/
@Service(interfaceClass = SetMealService.class)
@Transactional
public class SetMealServiceImpl implements SetMealService{

    @Autowired
    private SetMealDao setMealDao;
    /**
     * 新增套餐
     * @param setMeal
     * @param checkGroupIds
     */
    @Override
    public void add(Setmeal setMeal, Integer[] checkGroupIds) {
        //往套餐插入数据
        setMealDao.add(setMeal);
        //获取套餐Id
        Integer setMealId = setMeal.getId();
        //根据套餐Id  和  检查组Id 往中间表插入数据
        setSetMealAndCheckGroup(setMeal.getId(),checkGroupIds);
    }

    /**
     * 套餐分页
     * @param currentPage
     * @param pageSize
     * @param queryString
     * @return
     */
    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<Setmeal> setMealPage=setMealDao.findPage(queryString);
        return new PageResult(setMealPage.getTotal(),setMealPage.getResult());
    }

    /**
     * 根据套餐ID回显数据
     * @param setMealID
     * @return
     */
    @Override
    public Setmeal findById(Integer setMealID) {
        return setMealDao.findById(setMealID);

    }

    /**
     * 根据检查组id查询检查项ids
     * @param setMealID
     * @return
     */
    @Override
    public List<Integer> findCheckGroupIdsBySetMealId(Integer setMealID) {
        return setMealDao.findCheckGroupIdsBySetMealId(setMealID);
    }

    /**
     * 编辑套餐
     * @param setmeal
     * @param checkItemIds
     */
    @Override
    public void edit(Setmeal setmeal, Integer[] checkItemIds) {
        //根据套餐id修改套餐数据
        setMealDao.edit(setmeal);
        //根据套餐id删除套餐检查组中间表关系数据
        setMealDao.deleteAssociation(setmeal.getId());
        //根据套餐id+检查组ids往套餐检查组中间表插
        setSetMealAndCheckGroup(setmeal.getId(),checkItemIds);
    }

    /**
     * 删除套餐
     * @param setMealID
     */
    @Override
    public void delete(Integer setMealID) {
        //根据套餐id查询套餐检查组中间表
        int count = setMealDao.findSetmealAndCheckGroupCountBySetmealId(setMealID);
        // 关系存在往上抛异常
        if (count>0){
            throw new RuntimeException(MessageConstant.DELETE_SETMEAL_SUCCESS_FAIL);
        }
        //先查询套餐数据
        Setmeal setmeal = setMealDao.findById(setMealID);

        //关系不存在直接直接删除（dao）
        setMealDao.delete(setMealID);

        //调用七牛云接口删除图片  注意：先将套餐查询 再删除
        QiniuUtils.deleteFileFromQiniu(setmeal.getImg());
    }

    /**
     * 公共方法 - 往中间表插入数据
     * @param setMealId
     * @param checkGroupIds
     */
    private void setSetMealAndCheckGroup(Integer setMealId ,Integer[] checkGroupIds){
        if (checkGroupIds!=null && checkGroupIds.length > 0){
            for (Integer checkGroupId : checkGroupIds) {
                //定义Map传入
                Map map =new HashMap<>();
                map.put("setMealId",setMealId);
                map.put("checkGroupId",checkGroupId);
                setMealDao.setSetMealAndCheckGroup(map);
            }
        }
    }
}
