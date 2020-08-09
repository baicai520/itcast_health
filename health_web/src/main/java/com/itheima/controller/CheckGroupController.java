package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description 检查组系列
 * @program: health_parent
 * @ClassName : CheckGroupController
 * @author: TAO
 * @create: 2020-08-07 16:13
 **/
@RestController
@RequestMapping("/checkGroup")
public class CheckGroupController {

    @Reference
    private CheckGroupService checkGroupService;

    /**
     * 新增检查组
     * @param checkGroup
     * @param checkItemIds
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup, Integer[] checkItemIds){
        try {
            checkGroupService.add(checkGroup,checkItemIds);
            return new Result(true , MessageConstant.ADD_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
    }

    /**
     * 检查组分页
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
       return checkGroupService.findPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize(),queryPageBean.getQueryString());
    }

    /**
     * 删除检查组
     * @param checkGroupId
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Integer checkGroupId){
        try {
            checkGroupService.delete(checkGroupId);
            return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
        }catch (RuntimeException e){
            return new Result(false,e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
    }

    /**
     * 检查组表单回显
     * @param checkGroupId
     * @return
     */
    @RequestMapping("/findById")
    public Result findById(Integer checkGroupId){
        try {
            CheckGroup checkGroup =checkGroupService.findById(checkGroupId);
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    /**
     * 根据检查组合id查询对应的所有检查项id
     * @param checkGroupId
     * @return
     */
    @RequestMapping("/findCheckItemIdsByGroupId")
    public List<Integer> findCheckItemIdsByGroupId(Integer checkGroupId){
        List<Integer> checkGroupList = checkGroupService.findCheckItemIdsByGroupId(checkGroupId);
        return checkGroupList;
    }

    /**
     * 编辑检查组
     * @param checkGroup
     * @param checkItemIds
     * @return
     */
    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckGroup checkGroup, Integer[] checkItemIds){
        try {
            checkGroupService.edit(checkGroup,checkItemIds);
            return new Result(true , MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);

        }
    }

    /**
     * 查询所有检查组
     * @return
     */
    @RequestMapping(value = "/findAll" ,method = RequestMethod.GET)
    public Result findAll() {
        try {
            List<CheckItem> checkItemList = checkGroupService.findAll();
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItemList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }
}
