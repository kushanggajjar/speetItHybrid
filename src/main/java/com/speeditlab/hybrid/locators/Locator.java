package com.speeditlab.hybrid.locators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * Created by kugajjar on 2/8/15.
 */
public class Locator
{
    public static final String VIEWS = "views";

    @JsonProperty(VIEWS)
    private List<View> views = new ArrayList<View>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty(VIEWS)
    public List<View> getViews()
    {
        return views;
    }

    @JsonProperty(VIEWS)
    public void setViews(List<View> views)
    {
        this.views = views;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties()
    {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value)
    {
        this.additionalProperties.put(name, value);
    }


    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

}
