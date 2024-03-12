package com.orange.ishlt.client.dto.results;

import lombok.Data;

@Data
public class Results {

    // private List<Actions> actions;
    /**
     * 标题
     */
    private String Body;
    // private String CalendarDisplayBlock;
    // private String CardTemplate;
    // private String Details;
    /**
     * 位置
     */
    private String Foot;
    // private String GroupHeader;
    /**
     * 时间
     */
    private String Head;
    /**
     * 会议id
     */
    private Long Id;
    // private String Key;
    // private String ListOrder;
    // private String Thumbnail;
    // private String Type;
}
