package com.orange.ishlt.service;

import com.orange.ishlt.client.ActionClient;
import com.orange.ishlt.client.PresentationsClient;
import com.orange.ishlt.client.SessionDescriptionClient;
import com.orange.ishlt.client.dto.presentations.PresentationsApiDTO;
import com.orange.ishlt.client.dto.results.Results;
import com.orange.ishlt.client.dto.results.ResultsApiDTO;
import com.orange.ishlt.controller.vo.ActionPageReqVO;
import com.orange.ishlt.dal.dataobject.ActionDO;
import com.orange.ishlt.dal.dataobject.DetailDO;
import com.orange.ishlt.dal.mysql.ActionMapper;
import com.orange.ishlt.dal.mysql.DetailDOMapper;
import com.orange.ishlt.mybatis.PageResult;
import com.orange.ishlt.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.orange.ishlt.client.ActionClient.ADDRESS;

@Service
@Slf4j
public class ActionService {

    @Resource
    private ActionClient actionClient;
    @Resource
    private SessionDescriptionClient sessionDescriptionClient;
    @Resource
    private PresentationsClient presentationsClient;
    @Resource
    private ActionMapper actionMapper;
    @Resource
    private DetailDOMapper detailDOMapper;


    public PageResult<ActionDO> page(ActionPageReqVO reqVO){
        return actionMapper.selectPage(reqVO);
    }


    public void save() {
        // 1. 获取每天的会议对象
        ResultsApiDTO action;
        for (String address : ADDRESS) {
            action = actionClient.getAction(address);

            List<Results> results = action.getResults();
            results.forEach(result -> {
                log.info("开始保存大主题" + result.getBody());
                long sessionId = result.getId();

                // 获取每个会议的简要概述
                String description = sessionDescriptionClient.getDescription(sessionId).getDescription();

                // 保存大主题
                ActionDO actionDO = this.results2ActionDO(result, description);
                actionMapper.insert(actionDO);

                // 获取会议小主题的对象
                List<PresentationsApiDTO> presentations = presentationsClient.getPresentations(sessionId);
                presentations.forEach(presentation -> {
                    // 保存小主题
                    DetailDO detailDO = this.presentations2DetailDO(presentation, actionDO.getId());
                    detailDOMapper.insert(detailDO);
                });

            });
        }
        log.info("结束");
    }

    private DetailDO presentations2DetailDO(PresentationsApiDTO dto, Long actionId) {
        DetailDO detailDO = new DetailDO();
        detailDO.setTitle(dto.getTitle());
        detailDO.setAuthorBlock(dto.getAuthorBlock());
        detailDO.setStartTime(DateUtils.convert(dto.getStart()));
        detailDO.setEndTime(DateUtils.convert(dto.getEnd()));
        detailDO.setActionId(actionId);
        return detailDO;
    }

    private ActionDO results2ActionDO(Results results, String description) {
        ActionDO actionDO = new ActionDO();
        actionDO.setDescription(description);
        actionDO.setTitle(results.getBody());
        actionDO.setPlace(results.getFoot());
        actionDO.setSessionDate(DateUtils.convert(results.getHead()));
        actionDO.setSessionId(results.getId());
        return actionDO;
    }


}
