package com.speeditlab.hybrid.results;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Created by kugajjar on 2/9/15.
 */
public class Result
{
    @JsonProperty("pages")
    private List<Page> pages = new ArrayList<Page>();

    public List<Page> getPages()
    {
        return pages;
    }

    public void setPages(List<Page> pages)
    {
        this.pages = pages;
    }

}
