package com.speeditlab.hybrid.datadrive;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.speeditlab.hybrid.testcase.TestCase;
import org.apache.commons.lang3.StringUtils;


/**
 * Created by kugajjar on 2/12/15.
 */
public class DataDriver
{

    private static final String START_DATA_MATCHER = "<START_DATA::(.+)>(.*)";
    private static final String END_DATA_MATCHER = "<END_DATA::(.+)>(.*)";
    private Map<String, Data> dataMap = new HashMap<String, Data>();
    private String _keyword;
    private Data data;

    public Boolean isData()
    {
        return dataMap.size() == 0;
    }

    public String start(TestCase tc, int row)
    {
        String keyword = tc.getKeyword(row);

        String group = getGroup(getStartMatcher(keyword));

        if (StringUtils.isNotBlank(group))
        {
            if (!dataMap.containsKey(group))
            {
                this.data = new Data(tc.getDataBook(row), tc.getDataSheet(row));

                data.setRow(row);
                data.setHeaderRow(tc.getHeaderRow(row));
                data.setStartRow(tc.getStartRow(row));
                data.setEndRow(tc.getEndRow(row));

                dataMap.put(group, data);
            }

            _keyword = getKeyword(getStartMatcher(keyword));

            return _keyword;

        }

        _keyword = StringUtils.EMPTY;

        return _keyword;
    }

    public String getKeyword()
    {
        return _keyword;
    }

    public int end(TestCase tc, int row)
    {
        String keyword = tc.getKeyword(row);

        String group = getGroup(getEndMatcher(keyword));

        if (StringUtils.isNotBlank(group))
        {

            if (dataMap.containsKey(group))
            {
                data = dataMap.get(group);
                data.incrementStartRow();

                if (data.completed())
                {
                    dataMap.remove(group);
                    return row;
                }
                else
                {
                    return data.getRow();
                }
            }
        }

        return row;
    }

    private Matcher getStartMatcher(String keyword)
    {
        Pattern m = Pattern.compile(START_DATA_MATCHER);
        Matcher matcher = m.matcher(keyword);
        return matcher;
    }

    private Matcher getEndMatcher(String keyword)
    {
        Pattern m = Pattern.compile(END_DATA_MATCHER);
        Matcher matcher = m.matcher(keyword);
        return matcher;
    }

    private String getGroup(Matcher matcher)
    {
        return matcher.find()
               ? matcher.group(1).trim()
               : StringUtils.EMPTY;
    }

    private String getKeyword(Matcher matcher)
    {
        return matcher.find()
               ? matcher.group(2).trim()
               : StringUtils.EMPTY;
    }

    public String getValue(String column)
    {
        return data.getValue(column);
    }
}
