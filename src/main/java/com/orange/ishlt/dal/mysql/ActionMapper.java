package com.orange.ishlt.dal.mysql;

import com.orange.ishlt.controller.vo.ActionPageReqVO;
import com.orange.ishlt.dal.dataobject.ActionDO;
import com.orange.ishlt.mybatis.BaseMapperX;
import com.orange.ishlt.mybatis.LambdaQueryWrapperX;
import com.orange.ishlt.mybatis.PageResult;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActionMapper extends BaseMapperX<ActionDO> {
    default PageResult<ActionDO> selectPage(ActionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ActionDO>()
                .likeIfPresent(ActionDO::getTitle, reqVO.getTitle())
                .orderByDesc(ActionDO::getId));
    }
}
