package com.speeditlab.hybrid.results;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Created by kugajjar on 2/9/15.
 */
public class Page
{
    @JsonProperty("steps")
    private List<Step> steps = new ArrayList<Step>();

    @JsonProperty("page_name")
    private String page_name;
    @JsonProperty("error")
    private String error;


    public String getPage_name()
    {
        return page_name;
    }

    public void setPage_name(String page_name)
    {
        this.page_name = page_name;
    }

    public void setSteps(List<Step> steps)
    {
        this.steps = steps;
    }

    public List<Step> getSteps()
    {
        return steps;
    }

    public void setError(String error)
    {
        this.error = error;
    }

    public String getError()
    {
        return error;
    }
}
