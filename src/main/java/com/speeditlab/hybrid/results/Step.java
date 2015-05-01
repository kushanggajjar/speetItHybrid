package com.speeditlab.hybrid.results;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Created by kugajjar on 2/9/15.
 */
public class Step
{
    @JsonProperty("field_name")
    private String fieldName;
    @JsonProperty("field_value")
    private String fieldValue;
    @JsonProperty("result")
    private String result;

    public String getFieldName()
    {
        return fieldName;
    }

    public String getFieldValue()
    {
        return fieldValue;
    }

    public String getResult()
    {
        return result;
    }

    public void setFieldName(String fieldName)
    {
        this.fieldName = fieldName;
    }

    public void setFieldValue(String fieldValue)
    {
        this.fieldValue = fieldValue;
    }

    public void setResult(String result)
    {
        this.result = result;
    }
}
