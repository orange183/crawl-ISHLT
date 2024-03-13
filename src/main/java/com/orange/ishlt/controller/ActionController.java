package com.orange.ishlt.controller;

import com.orange.ishlt.controller.vo.ActionPageReqVO;
import com.orange.ishlt.dal.dataobject.ActionDO;
import com.orange.ishlt.mybatis.PageResult;
import com.orange.ishlt.service.ActionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/session")
public class ActionController {

    @Resource
    private ActionService actionService;

    @GetMapping("/page")
    public PageResult<ActionDO> page(ActionPageReqVO reqVO) {
        return actionService.page(reqVO);
    }

    /**
     * 将会议数据保存至数据库
     */
    @GetMapping("/execute")
    public void execute() {
        actionService.save();
    }

}
