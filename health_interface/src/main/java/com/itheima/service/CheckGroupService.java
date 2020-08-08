package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckGroup;

import java.util.List;

/**
 * @Description 检查组接口
 * @program: health_parent
 * @ClassName : CheckGroupService
 * @author: TAO
 * @create: 2020-08-07 16:20
 **/
public interface CheckGroupService {
    /**
     * 新增检查组
     * @param checkGroup
     * @param checkItemIds
     */
    void add(CheckGroup checkGroup, Integer[] checkItemIds);

    /**
     * 检查组分页
     * @param currentPage
     * @param pageSize
     * @param queryString
     * @return
     */
    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    /**
     * 删除检查组
     * @param checkGroupId
     */
    void delete(Integer checkGroupId);

    /**
     * 检查组表单回显
     * @param checkGroupId
     * @return
     */
    CheckGroup findById(Integer checkGroupId);

    /**
     * 根据检查组合id查询对应的所有检查项id
     * @param checkGroupId
     * @return
     */
    List<Integer> findCheckItemIdsByGroupId(Integer checkGroupId);

    /**
     * 编辑检查组
     * @param checkGroup
     * @param checkItemIds
     */
    void edit(CheckGroup checkGroup, Integer[] checkItemIds);
}
