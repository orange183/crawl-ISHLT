package com.orange.ishlt.dal.mysql;

import com.orange.ishlt.controller.vo.DetailPageReqVO;
import com.orange.ishlt.dal.dataobject.DetailDO;
import com.orange.ishlt.mybatis.BaseMapperX;
import com.orange.ishlt.mybatis.LambdaQueryWrapperX;
import com.orange.ishlt.mybatis.PageResult;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DetailMapper extends BaseMapperX<DetailDO> {

    default PageResult<DetailDO> selectPage(DetailPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DetailDO>()
                .eq(DetailDO::getActionId, reqVO.getActionId())
                .orderByAsc(DetailDO::getId));
    }

}
