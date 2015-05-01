package com.speeditlab.hybrid.main;

import java.io.IOException;

import com.speeditlab.hybrid.driver.TcDriver;
import com.speeditlab.hybrid.exception.ViewNotFound;
import com.speeditlab.hybrid.utils.SpeedItUtils;


/**
 * Created by kugajjar on 2/6/15.
 */
public class RunHybrid
{
    public static void main(String[] args) throws IOException, ViewNotFound
    {
        new TcDriver().execute(SpeedItUtils.getAbsolutePath("scenarios/Scenarios.xlsm"), "Demo");
    }

}
