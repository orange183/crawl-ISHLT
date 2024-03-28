package com.orange.ishlt.service;

import com.orange.ishlt.controller.vo.DetailPageReqVO;
import com.orange.ishlt.dal.dataobject.DetailDO;
import com.orange.ishlt.dal.mysql.DetailMapper;
import com.orange.ishlt.mybatis.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class DetailService {

    @Resource
    private DetailMapper detailMapper;


    public PageResult<DetailDO> page(DetailPageReqVO reqVO) {
        return detailMapper.selectPage(reqVO);
    }

}
