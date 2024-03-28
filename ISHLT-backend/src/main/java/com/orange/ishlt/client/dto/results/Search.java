package com.orange.ishlt.client.dto.results;

import lombok.Data;


@Data
public class Search {

    private String Count;
    private String DataSet;
    private String Filters;
    private String Phrase;
    private String SearchId;
    private String Status;

}