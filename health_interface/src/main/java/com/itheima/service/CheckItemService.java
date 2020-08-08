package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckItem;

import java.util.List;

/**
 * @Description 检查项接口
 * @program: health_parent
 * @ClassName : CheckItemService
 * @author: TAO
 * @create: 2020-08-05 18:06
 **/
public interface CheckItemService {
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
     * @param currentPage
     * @param pageSize
     * @param queryString
     * @return
     */
    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    /**
     * 删除检查项
     * @param checkItemId
     * @return
     */

    void delete(Integer checkItemId);

    /**
     * 回显表单数据
     * @param checkItemId
     * @return
     */
    CheckItem findById(Integer checkItemId);

    /**
     * 编辑检查项
     * @param checkItem
     * @return
     */
    void edit(CheckItem checkItem);
}
