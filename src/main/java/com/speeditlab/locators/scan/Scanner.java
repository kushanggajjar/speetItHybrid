package com.speeditlab.locators.scan;

import java.io.FileInputStream;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.speeditlab.hybrid.browser.Browser;
import com.speeditlab.hybrid.browser.BrowserFactory;
import com.speeditlab.hybrid.utils.Keys;
import com.speeditlab.hybrid.utils.SpeedItUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by kugajjar on 2/17/15.
 */
public class Scanner
{

    private static final Logger LOG = LoggerFactory.getLogger(Scanner.class);

    public void run()
    {
        Browser browser = _launchBrowser();

        browser.navigate(getProp(Keys.Properties.HOST_URL));

        browser.scanAllTags("input");

        browser.quit();

    }

    private String getProp(String key)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            JsonNode jsonNode = objectMapper.readTree(new FileInputStream(SpeedItUtils.getAbsolutePath("speedIt/speedIt.json")));

            return jsonNode.get(System.getProperty("env")).get(key).asText();

        }
        catch (IOException e)
        {
            return StringUtils.EMPTY;
        }

    }


    private Browser _launchBrowser()
    {
        LOG.info("Launching '{}' browser", "firefox");

        final BrowserFactory factory = BrowserFactory.getDriverFromString("firefox");
        return new Browser(factory.getLocalDriver());
    }

}
