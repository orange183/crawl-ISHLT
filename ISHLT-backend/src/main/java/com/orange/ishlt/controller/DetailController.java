package com.orange.ishlt.controller;

import com.orange.ishlt.common.CommonResult;
import com.orange.ishlt.controller.vo.DetailPageReqVO;
import com.orange.ishlt.dal.dataobject.DetailDO;
import com.orange.ishlt.mybatis.PageResult;
import com.orange.ishlt.service.DetailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.orange.ishlt.common.CommonResult.success;

@RestController
@RequestMapping(value = "/detail")
public class DetailController {

    @Resource
    private DetailService detailService;

    @GetMapping("/page")
    public CommonResult<PageResult<DetailDO>> page(DetailPageReqVO reqVO) {
        PageResult<DetailDO> page = detailService.page(reqVO);
        return success(page);
    }

}
