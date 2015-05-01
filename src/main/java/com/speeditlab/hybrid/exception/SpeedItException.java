package com.speeditlab.hybrid.exception;

/**
 * Created by kugajjar on 2/8/15.
 */
public class SpeedItException extends RuntimeException
{
    public SpeedItException(String s, Exception e)
    {
        super(s, e);
    }

    public SpeedItException(Exception e)
    {
        super(e);
    }
}
