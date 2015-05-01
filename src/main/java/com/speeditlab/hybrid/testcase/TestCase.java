package com.speeditlab.hybrid.testcase;

import com.speeditlab.hybrid.exception.EndOfTestCase;
import com.speeditlab.hybrid.utils.Excel;
import com.speeditlab.hybrid.utils.Keys;


/**
 * Created by kugajjar on 2/8/15.
 */
public class TestCase extends Excel
{
    public TestCase(String workbook, String worksheet)
    {
        super(workbook, worksheet);
    }

    public int getStartTestRow() throws EndOfTestCase
    {
        int row = Keys.TestCase.Rows.START_ROW;

        while (true)
        {
            if (_isEndTestFound(row))
            {
                throw new EndOfTestCase("End Of Test Case");
            }
            else if (_isStartTestFound(row))
            {
                return ++row;
            }

            row++;
        }
    }

    private boolean _isEndTestFound(int row)
    {
        return getKeyword(row).equals(Keys.TestCase.END_TEST);
    }

    private boolean _isStartTestFound(int row)
    {
        return getKeyword(row).equals(Keys.TestCase.START_TEST);
    }

    public String getKeyword(Integer row)
    {
        return getCellData(row, Keys.TestCase.Columns.KEYWORD);
    }

    public boolean isEnd(int row)
    {
        return _isEndTestFound(row);
    }

    public String getFieldName(int row)
    {
        return getCellData(row, Keys.TestCase.Columns.FIELD_NAME);
    }

    public String getFieldValue(int row)
    {
        return getCellData(row, Keys.TestCase.Columns.VALUE);
    }

    public void close()
    {
        super.close();
    }

    public String getDataBook(int row)
    {
        return getCellData(row, Keys.TestCase.Columns.Data.DATA_BOOK);
    }

    public String getDataSheet(int row)
    {
        return getCellData(row, Keys.TestCase.Columns.Data.DATA_SHEET);
    }

    public int getHeaderRow(int row)
    {
        return Integer.parseInt(getCellData(row, Keys.TestCase.Columns.Data.HEADER_ROW));
    }

    public int getStartRow(int row)
    {
        return Integer.parseInt(getCellData(row, Keys.TestCase.Columns.Data.START_ROW));
    }

    public int getEndRow(int row)
    {
        return Integer.parseInt(getCellData(row, Keys.TestCase.Columns.Data.END_ROW));
    }
}
