package com.speeditlab.hybrid.driver;

import java.io.FileInputStream;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.speeditlab.hybrid.browser.Browser;
import com.speeditlab.hybrid.browser.BrowserFactory;
import com.speeditlab.hybrid.datadrive.DataDriver;
import com.speeditlab.hybrid.exception.EndOfTestCase;
import com.speeditlab.hybrid.exception.ViewNotFound;
import com.speeditlab.hybrid.keywords.Keywords;
import com.speeditlab.hybrid.results.Report;
import com.speeditlab.hybrid.testcase.Repository;
import com.speeditlab.hybrid.testcase.TestCase;
import com.speeditlab.hybrid.utils.Keys;
import com.speeditlab.hybrid.utils.SpeedItUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by kugajjar on 2/6/15.
 */
public class TcDriver
{

    private static final Logger LOG = LoggerFactory.getLogger(TcDriver.class);

    private Repository repository;

    public void execute(String workBook, String workSheet) throws ViewNotFound
    {
        TestCase tc = new TestCase(workBook, workSheet);

        Browser browser = _launchBrowser(getCommon(Keys.Properties.BROWSER));

        Keywords keywords = new Keywords(browser);

        Report report = new Report();

        DataDriver dataDriver = new DataDriver();

        browser.navigate(getProp(Keys.Properties.HOST_URL));

        try
        {
            int row = tc.getStartTestRow();

            while (!tc.isEnd(row))
            {
                String keyword = tc.getKeyword(row);
                if (StringUtils.isNotEmpty(keyword))
                {
                    row = dataDriver.end(tc, row);

                    dataDriver.start(tc, row);

                    keyword = dataDriver.getKeyword();

                    if (StringUtils.isNotEmpty(keyword))
                    {
                        LOG.info("Initializing '{}' repository", keyword);
                        repository = new Repository(keyword);
                        report.process();
                        report.setKeyword(keyword);
                    }
                }
                else
                {
                    String fieldName = tc.getFieldName(row);

                    if (StringUtils.isNotEmpty(fieldName))
                    {
                        LOG.info("Executing '{}' field", fieldName);

                        String fieldValue = tc.getFieldValue(row);

                        try
                        {
                            report = keywords.process
                                    (
                                            repository, fieldName, fieldValue, dataDriver, report
                                    );
                        }
                        catch (AssertionError e)
                        {
                            report.setSteps
                                    (
                                            fieldName,
                                            keywords.getValue(),
                                            Keys.Result.FAIL
                                    );

                            if (!dataDriver.isData())
                                throw new EndOfTestCase(Keys.Result.FAIL);
                        }
                    }
                }

                row++;
            }

        }
        catch (EndOfTestCase endOfTestCase)
        {
            LOG.error(endOfTestCase.getMessage());
        }
        catch (Exception e)
        {
            report.error(e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            LOG.info("Quit Browser");

            report.process();

            browser.quit();

            report.prettyPrint();

            tc.close();
        }

        LOG.info("Tests ends. SpeedIt AGAIN...!");

    }

    private Browser _launchBrowser(String browser)
    {
        LOG.info("Launching '{}' browser", browser);

        final BrowserFactory factory = BrowserFactory.getDriverFromString(browser);
        return new Browser(factory.getLocalDriver());
    }

    private String getCommon(String key)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            JsonNode jsonNode = objectMapper.readTree(new FileInputStream(SpeedItUtils.getAbsolutePath("speedIt/speedIt.json")));

            return jsonNode.get(key).asText();

        }
        catch (IOException e)
        {
            return StringUtils.EMPTY;
        }

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
}
