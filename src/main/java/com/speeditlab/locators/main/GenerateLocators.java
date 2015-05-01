package com.speeditlab.locators.main;

import java.io.IOException;

import com.speeditlab.hybrid.exception.ViewNotFound;
import com.speeditlab.locators.scan.Scanner;


/**
 * Created by kugajjar on 2/6/15.
 */
public class GenerateLocators
{
    public static void main(String[] args) throws IOException, ViewNotFound
    {
        new Scanner().run();
    }

}
