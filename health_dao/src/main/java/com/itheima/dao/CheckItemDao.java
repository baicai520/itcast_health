package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckItem;

import java.util.List;

/**
 * @Description TODO
 * @program: health_parent
 * @ClassName : CheckItemDao
 * @author: TAO
 * @create: 2020-08-05 18:09
 **/
public interface CheckItemDao {
    /**
     * 查询检查项
     * @return
     */
    List<CheckItem> findAll();

    /**
     * 新增检查项
     * @param checkItem
     * @return
     */
    void add(CheckItem checkItem);

    /**
     * 分页查询
     * @param queryString
     * @return
     */
    Page<CheckItem> selectByCondition(String queryString);

    /**
     * 先查询检查组检查项中间表
     * @param checkItemId
     * @return
     */
    int findCountByCheckItemId(Integer checkItemId);

    /**
     * 删除检查项
     * @param checkItemId
     */
    void deleteById(Integer checkItemId);

    /**
     * 回显表单数据
     * @param checkItemId
     * @return
     */
    CheckItem findById(Integer checkItemId);

    /**
     * 编辑检查项
     * @param checkItem
     */
    void edit(CheckItem checkItem);
}
