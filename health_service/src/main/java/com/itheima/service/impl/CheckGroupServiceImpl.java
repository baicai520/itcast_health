package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.constant.MessageConstant;
import com.itheima.dao.CheckGroupDao;
import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 检查组实现类
 * @program: health_parent
 * @ClassName : CheckGroupServiceImpl
 * @author: TAO
 * @create: 2020-08-07 16:23
 **/
@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;
    /**
     * 新增检查组
     * @param checkGroup
     * @param checkItemIds
     */
    @Override
    public void add(CheckGroup checkGroup, Integer[] checkItemIds) {
        //往检查组插入记录
        checkGroupDao.add(checkGroup);
        //获取检查组id
        Integer checkGroupId = checkGroup.getId();
        //循环遍历往中间表插入数据(抽出来 编辑也能用)
        setCheckGroupAndCheckItem(checkGroupId,checkItemIds);

    }

    /**
     * 检查组分页
     * @param currentPage
     * @param pageSize
     * @param queryString
     * @return
     */
    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckGroup> page = checkGroupDao.findPage(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 删除检查组
     * @param checkGroupId
     */
    @Override
    public void delete(Integer checkGroupId) {
        //1.根据检查组id查询套餐检查组中间表，如果关系存在，不允许删除
        int count =checkGroupDao.findSetmealAndCheckGroupCountByCheckGroupId(checkGroupId);
        if (count>0){
            throw new RuntimeException(MessageConstant.DELETE_SUCCESS_SETMEALLIST_FAIL);
        }

        //2.根据检查组id查询检查组检查项中间表，如果关系存在，不允许删除
        int count2 = checkGroupDao.findCheckGroupAndCheckItemCountByCheckGroupId(checkGroupId);
        if (count2>0){
            throw new RuntimeException(MessageConstant.DELETE_CHECKITEM_SUCCESS_FAIL);
        }

        //3.关系不存在则可以删除
        checkGroupDao.deleteById(checkGroupId);
    }

    /**
     * 检查组表单回显
     * @param checkGroupId
     */
    @Override
    public CheckGroup  findById(Integer checkGroupId) {
        return checkGroupDao.findById(checkGroupId);
    }

    /**
     * 根据检查组合id查询对应的所有检查项id
     * @param checkGroupId
     * @return
     */
    @Override
    public List<Integer> findCheckItemIdsByGroupId(Integer checkGroupId) {
        return checkGroupDao.findCheckItemIdsByGroupId(checkGroupId);
    }

    /**
     * 编辑检查组
     * @param checkGroup
     * @param checkItemIds
     */
    @Override
    public void edit(CheckGroup checkGroup, Integer[] checkItemIds) {
        //根据检查组id更新检查组
        checkGroupDao.edit(checkGroup);
        //根据检查组id删除中间表关系
        checkGroupDao.deleteByGroupId(checkGroup.getId());
        //重新建立中间表关系
        setCheckGroupAndCheckItem(checkGroup.getId(),checkItemIds);
    }


    /**
     * 公共方法 - 往中间表插入数据
     * @param checkGroupId
     * @param checkItemIds
     */
    private void setCheckGroupAndCheckItem(Integer checkGroupId ,Integer[] checkItemIds){
        if (checkItemIds!=null && checkItemIds.length > 0){
            for (Integer checkItemId : checkItemIds) {
                //定义Map传入
                Map map =new HashMap<>();
                map.put("checkItemId",checkItemId);
                map.put("checkGroupId",checkGroupId);
                checkGroupDao.setCheckGroupAndCheckItem(map);
            }
        }
    }
}
