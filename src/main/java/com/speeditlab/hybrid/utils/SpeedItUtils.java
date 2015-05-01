package com.speeditlab.hybrid.utils;

import java.io.IOException;

import com.speeditlab.hybrid.exception.SpeedItException;
import com.speeditlab.hybrid.exception.SpeedItIOException;
import org.springframework.core.io.ClassPathResource;


/**
 * Created by kugajjar on 2/8/15.
 */
public class SpeedItUtils
{

    public static String getAbsolutePath(String fileName)
    {
        try
        {
            return new ClassPathResource(fileName).getFile().getAbsolutePath();
        }
        catch (IOException e)
        {
            throw new SpeedItIOException("File not found: " + fileName, e);
        }
    }

    public static void sleep(int mill_seconds)
    {
        try
        {
            Thread.sleep(mill_seconds);
        }
        catch (InterruptedException e)
        {
            throw new SpeedItException("Error in sleep", e);
        }
    }
}
