package com.orange.ishlt.client;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.orange.ishlt.client.dto.presentations.PresentationsApiDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PresentationsClient {

    public List<PresentationsApiDTO> getPresentations(Long sessionId) {
        HttpResponse execute = HttpUtil.createGet("https://www.abstractsonline.com/oe3/Program/11008/Session/" + sessionId + "/presentations")
                .header("Backpack", "ba0746d4-1fc1-4dc9-8c73-64dd0fb7818c")
                .header("Accept", "application/json")
                .execute();

        String body = execute.body();

        return JSONUtil.toList(body, PresentationsApiDTO.class);
    }
}
