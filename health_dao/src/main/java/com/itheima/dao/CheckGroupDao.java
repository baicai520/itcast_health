package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;

import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @program: health_parent
 * @ClassName : CheckGroupDao
 * @author: TAO
 * @create: 2020-08-07 16:39
 **/
public interface CheckGroupDao {
    /**
     * 公共方法 - 往中间表插入数据
     * @param map
     */
    void setCheckGroupAndCheckItem(Map map);

    /**
     * 新增检查组
     * @param checkGroup
     */
    void add(CheckGroup checkGroup);

    /**
     * 检查组分页
     * @param queryString
     * @return
     */
    Page<CheckGroup> findPage(String queryString);

    /**
     * 根据检查组id查询套餐检查组中间表
     * @param checkGroupId
     */
    int findSetmealAndCheckGroupCountByCheckGroupId(Integer checkGroupId);

    /**
     * 根据检查组id查询检查组检查项中间表
     * @param checkGroupId
     * @return
     */
    int findCheckGroupAndCheckItemCountByCheckGroupId(Integer checkGroupId);

    /**
     * 删除检查组
     * @param checkGroupId
     */
    void deleteById(Integer checkGroupId);

    /**
     * 检查组表单回显
     * @param checkGroupId
     */
    CheckGroup findById(Integer checkGroupId);

    /**
     * 根据检查组合id查询对应的所有检查项id
     * @param checkGroupId
     * @return
     */
    List<Integer> findCheckItemIdsByGroupId(Integer checkGroupId);

    /**
     * 根据检查组id更新检查组
     * @param checkGroup
     */
    void edit(CheckGroup checkGroup);

    /**
     * 根据检查组id删除中间表关系
     * @param checkGroupId
     */
    void deleteByGroupId(Integer checkGroupId);
}