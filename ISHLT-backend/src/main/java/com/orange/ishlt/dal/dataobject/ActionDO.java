package com.orange.ishlt.dal.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName(value = "action")
public class ActionDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 会议日期
     */
    private LocalDateTime sessionDate;

    /**
     * 会议主题
     */
    private String title;

    /**
     * 会议位置
     */
    private String place;

    /**
     * 会议概要
     */
    private String description;

    /**
     * 会议id
     */
    private Long sessionId;
}
