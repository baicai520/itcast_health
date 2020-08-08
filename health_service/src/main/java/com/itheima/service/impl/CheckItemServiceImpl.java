package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.constant.MessageConstant;
import com.itheima.dao.CheckItemDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description TODO
 * @program: health_parent
 * @ClassName : CheckItemServiceImpl
 * @author: TAO
 * @create: 2020-08-05 18:07
 **/
@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;
    /**
     * 查询检查项
     * @return
     */
    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }

    /**
     * 新增检查项
     * @param checkItem
     * @return
     */
    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param queryString
     * @return
     */
    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        //设置分页参数
        PageHelper.startPage(currentPage,pageSize);

        //调用查询语句
        Page<CheckItem> page = checkItemDao.selectByCondition(queryString);

        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 删除检查项
     * @param checkItemId
     * @return
     */
    @Override
    public void delete(Integer checkItemId) {
        //1.先查询检查组检查项中间表 如果关系存在，提示错误
        int count = checkItemDao.findCountByCheckItemId(checkItemId);
        if (count > 0){
             throw new RuntimeException(MessageConstant.DELETE_CHECKITEM_SUCCESS_FAIL);
        }

        //2.如果不存在，则删除
        checkItemDao.deleteById(checkItemId);
    }

    /**
     * 回显表单数据
     * @param checkItemId
     * @return
     */
    @Override
    public CheckItem findById(Integer checkItemId) {
        return checkItemDao.findById(checkItemId);
    }

    /**
     * 编辑检查项
     * @param checkItem
     * @return
     */
    @Override
    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }
}
