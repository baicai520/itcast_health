package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Setmeal;

import java.util.List;

/**
 * @Description TODO
 * @program: health_parent
 * @ClassName : SetMealService
 * @author: TAO
 * @create: 2020-08-08 17:28
 **/
public interface SetMealService {
    /**
     *
     * 新增套餐
     * @param setMeal
     * @param checkGroupIds
     */
    void add(Setmeal setMeal, Integer[] checkGroupIds);

    /**
     * 套餐分页
     * @param currentPage
     * @param pageSize
     * @param queryString
     * @return
     */
    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

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
     * @param checkItemIds
     */
    void edit(Setmeal setmeal, Integer[] checkItemIds);

    /**
     * 删除套餐
     * @param setMealID
     */
    void delete(Integer setMealID);
}
