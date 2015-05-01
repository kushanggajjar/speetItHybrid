package com.speeditlab.hybrid.locators;

/**
 * Created by kugajjar on 2/8/15.
 */

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
                           "field_name",
                           "by",
                           "selector"
                   })
public class View
{

    public static final String FIELD_NAME = "field_name";
    public static final String BY = "by";
    public static final String SELECTOR = "selector";

    @JsonProperty(FIELD_NAME)
    private String field_name;

    @JsonProperty(BY)
    private String by;

    @JsonProperty(SELECTOR)
    private String selector;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty(FIELD_NAME)
    public String getField_name()
    {
        return field_name;
    }

    @JsonProperty(FIELD_NAME)
    public void setField_name(String field_name)
    {
        this.field_name = field_name;
    }

    @JsonProperty(BY)
    public void setBy(String by)
    {
        this.by = by;
    }

    @JsonProperty(BY)
    public String getBy()
    {
        return by;
    }

    @JsonProperty(SELECTOR)
    public void setSelector(String selector)
    {
        this.selector = selector;
    }

    @JsonProperty(SELECTOR)
    public String getselector()
    {
        return selector;
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
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            return mapper.writeValueAsString(this);
        }
        catch (JsonProcessingException e)
        {
            return StringUtils.EMPTY;
        }
    }
}