package com.speeditlab.hybrid.exception;

/**
 * Created by kugajjar on 2/8/15.
 */
public class SpeedItIOException extends RuntimeException
{
    public SpeedItIOException(String s, Exception e)
    {
        super(s, e);
    }
}
