package com.speeditlab.hybrid.testcase;

import java.util.Iterator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.speeditlab.hybrid.utils.Excel;
import com.speeditlab.hybrid.exception.SpeedItException;
import com.speeditlab.hybrid.exception.ViewNotFound;
import com.speeditlab.hybrid.locators.Locator;
import com.speeditlab.hybrid.locators.View;
import com.speeditlab.hybrid.utils.SpeedItUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Created by kugajjar on 2/8/15.
 */
public class Repository extends Excel
{
    private static final String LOCATOR_FILE = "locators/Locators.xlsx";

    private final ObjectMapper MAPPER = new ObjectMapper();
    private Locator locator;

    public Repository(String locatorSheet)
    {
        super(SpeedItUtils.getAbsolutePath(LOCATOR_FILE), locatorSheet);
        _initLocator();
    }

    public Locator getLocator()
    {
        return locator;
    }

    private void _initLocator()
    {
        try
        {
            JSONObject json = new JSONObject();
            JSONArray views = new JSONArray();

            for (Iterator<Row> rowsIT = worksheet.rowIterator(); rowsIT.hasNext(); )
            {
                Row row = rowsIT.next();
                JSONObject viewJson = new JSONObject();

                Iterator<Cell> cellsIT = row.cellIterator();

                _createView(View.FIELD_NAME, viewJson, cellsIT);
                _createView(View.BY, viewJson, cellsIT);
                _createView(View.SELECTOR, viewJson, cellsIT);

                if (viewJson.length() != 0)
                {
                    views.put(viewJson);
                }
            }

            json.put(Locator.VIEWS, views);

            this.locator = MAPPER.readValue(json.toString(), Locator.class);
        }
        catch (Exception e)
        {
            throw new SpeedItException("Error in processing Locator Sheet", e);
        }
    }

    private void _createView(String key, JSONObject viewJson, Iterator<Cell> cellsIT)
    {
        if (cellsIT.hasNext())
        {
            String value = cellsIT.next().getStringCellValue();

            if (StringUtils.isNotEmpty(value))
            {
                viewJson.put(key, value);
            }
        }

    }

    public View getView(String fieldName) throws ViewNotFound
    {
        for (View view : locator.getViews())
        {
            if (view.getField_name().equals(fieldName))
            {
                return view;
            }
        }

        throw new ViewNotFound("view for field name: " + fieldName + " not found");
    }
}
