package com.speeditlab.hybrid.utils;

import org.apache.commons.lang3.StringUtils;


/**
 * Created by kugajjar on 2/6/15.
 */
public interface Keys
{
    String BROWSER = "browser";


    public interface TestCase
    {
        String START_TEST = "START TEST";
        String END_TEST = "END TEST";


        public interface Rows
        {
            Integer START_ROW = 7;
        }


        public interface Columns
        {
            Integer KEYWORD = 0;
            Integer FIELD_NAME = 1;
            Integer VALUE = 2;


            public interface Data
            {
                int DATA_BOOK = 3;
                int DATA_SHEET = 4;
                int HEADER_ROW = 5;
                int START_ROW = 6;
                int END_ROW = 7;
            }
        }
    }


    public interface Result
    {
        String FAIL = "FAIL";
        String PASS = "PASS";
        String NONE = StringUtils.EMPTY;
    }


    public interface Properties
    {
        String BROWSER = "browser";
        String HOST_URL = "host_url";
    }
}
