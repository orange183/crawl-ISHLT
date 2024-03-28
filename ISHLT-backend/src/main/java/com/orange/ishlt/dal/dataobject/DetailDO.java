package com.orange.ishlt.dal.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "detail")
public class DetailDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 所属会议id
     */
    private Long actionId;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;


    /**
     * 结束时间
     */
    private LocalDateTime endTime;


    /**
     * 标题
     */
    private String title;


    /**
     * 作者块
     */
    private String authorBlock;


}
