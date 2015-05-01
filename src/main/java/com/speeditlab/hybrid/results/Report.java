package com.speeditlab.hybrid.results;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.speeditlab.hybrid.exception.SpeedItException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by kugajjar on 2/9/15.
 */
public class Report
{
    private static final Logger LOG = LoggerFactory.getLogger(Report.class);

    private Page page;
    private Result result;
    private List<Page> pages;
    private List<Step> steps;

    public Report()
    {
        result = new Result();
        pages = new ArrayList<Page>();
        steps = new ArrayList<Step>();
    }

    public void setKeyword(String keyword)
    {
        page = new Page();
        page.setPage_name(keyword);
    }

    public void setSteps(String fieldName, String fieldValue, String result)
    {
        Step step = new Step();
        step.setFieldName(fieldName);
        step.setFieldValue(fieldValue);
        step.setResult(result);

        steps.add(step);
    }

    public void process()
    {
        if (page != null)
        {
            page.setSteps(steps);
            pages.add(page);
            result.setPages(pages);
            steps = new ArrayList<Step>();
        }
    }

    public void error(String error)
    {
        if (page == null)
        {
            page = new Page();
        }

        page.setError(error);
    }

    public void prettyPrint()
    {
        ObjectMapper mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try
        {
            LOG.info(mapper.writeValueAsString(result));
        }
        catch (JsonProcessingException e)
        {
            throw new SpeedItException("Unknown error parsing result", e);
        }
    }

}
