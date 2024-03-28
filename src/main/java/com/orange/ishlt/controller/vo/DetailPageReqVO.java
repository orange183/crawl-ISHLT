package com.orange.ishlt.controller.vo;

import com.orange.ishlt.mybatis.PageParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DetailPageReqVO extends PageParam {

    /**
     * 会议id
     */
    private String actionId;

}
