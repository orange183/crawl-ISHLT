package com.orange.ishlt.client;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.orange.ishlt.client.dto.results.ResultsApiDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ActionClient {

    public static final List<String> ADDRESS = new ArrayList<>(5);

    @PostConstruct
    public void init() {
        ADDRESS.add("https://www.abstractsonline.com/oe3/Program/11008/Search/4210/Results?page=1&pagesize=100&total_pages=1&total_entries=18&sort=1&order=asc");
        ADDRESS.add("https://www.abstractsonline.com/oe3/Program/11008/Search/1/Results?page=1&pagesize=100&total_pages=1&total_entries=4&sort=1&order=asc");
        ADDRESS.add("https://www.abstractsonline.com/oe3/Program/11008/Search/14/Results?page=1&pagesize=100&total_pages=1&total_entries=42&sort=1&order=asc");
        ADDRESS.add("https://www.abstractsonline.com/oe3/Program/11008/Search/35/Results?page=1&pagesize=100&total_pages=1&total_entries=48&sort=1&order=asc");
        ADDRESS.add("https://www.abstractsonline.com/oe3/Program/11008/Search/17/Results?page=1&pagesize=100&total_pages=1&total_entries=48&sort=1&order=asc");
    }

    public ResultsApiDTO getAction(String address) {
        HttpResponse execute = HttpUtil.createGet(address)
                .header("Backpack", "ba0746d4-1fc1-4dc9-8c73-64dd0fb7818c")
                .header("Accept", "application/json")
                .execute();

        String body = execute.body();

        return JSONUtil.toBean(body, ResultsApiDTO.class);
    }
}
