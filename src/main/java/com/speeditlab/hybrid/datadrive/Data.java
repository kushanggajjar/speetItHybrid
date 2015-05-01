package com.speeditlab.hybrid.datadrive;

import com.speeditlab.hybrid.utils.Excel;


/**
 * Created by kugajjar on 2/12/15.
 */
public class Data extends Excel
{
    private int _row;
    private int _headerRow;
    private int _startRow;
    private int _endRow;

    public Data(String dataBook, String dataSheet)
    {
        super(dataBook, dataSheet);
    }

    public void setRow(int row)
    {
        _row = row;
    }

    public int getRow()
    {
        return _row;
    }

    public void setHeaderRow(int headerRow)
    {
        _headerRow = headerRow;
    }

    public int getHeaderRow()
    {
        return _headerRow;
    }


    public void setStartRow(int startRow)
    {
        _startRow = startRow;
    }

    public int getStartRow()
    {
        return _startRow;
    }

    public void setEndRow(int endRow)
    {
        _endRow = endRow;
    }

    public int getEndRow()
    {
        return _endRow;
    }

    public void incrementStartRow()
    {
        _startRow++;
    }

    public boolean completed()
    {
        return _startRow > _endRow;
    }

    public String getValue(String column)
    {
        return getCellData(_startRow, 1);
    }
}
