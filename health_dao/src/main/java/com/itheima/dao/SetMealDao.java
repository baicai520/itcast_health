package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Setmeal;

import java.util.List;
import java.util.Map;

/**
 * @Description 套餐dao接口
 * @program: health_parent
 * @ClassName : SetMealDao
 * @author: TAO
 * @create: 2020-08-08 17:46
 **/
public interface SetMealDao {
    /**
     * 往套餐插入数据
     * @param setMeal
     */
    void add(Setmeal setMeal);

    /**
     * 公共方法 - 往中间表插入数据
     * @param map
     */
    void setSetMealAndCheckGroup(Map map);

    /**
     * 套餐分页
     * @param queryString
     * @return
     */
    Page<Setmeal> findPage(String queryString);

    /**
     * 根据套餐ID回显数据
     * @param setMealID
     * @return
     */
    Setmeal findById(Integer setMealID);

    /**
     * 根据检查组id查询检查项ids
     * @param setMealID
     * @return
     */
    List<Integer> findCheckGroupIdsBySetMealId(Integer setMealID);

    /**
     * 编辑套餐
     * @param setmeal
     */
    void edit(Setmeal setmeal);

    /**
     * 根据套餐id删除套餐检查组中间表关系数据
     * @param setMealId
     */
    void deleteAssociation(Integer setMealId);

    /**
     * 根据套餐id查询套餐检查组中间表
     * @param setMealID
     * @return
     */
    int findSetmealAndCheckGroupCountBySetmealId(Integer setMealID);

    /**
     * 删除套餐
     * @param setMealID
     */
    void delete(Integer setMealID);
}
