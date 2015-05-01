package com.speeditlab.hybrid.keywords;

import com.speeditlab.hybrid.browser.Browser;
import com.speeditlab.hybrid.datadrive.DataDriver;
import com.speeditlab.hybrid.exception.ViewNotFound;
import com.speeditlab.hybrid.locators.View;
import com.speeditlab.hybrid.results.Report;
import com.speeditlab.hybrid.testcase.Repository;
import com.speeditlab.hybrid.utils.Keys;
import com.speeditlab.hybrid.utils.SpeedItUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by kugajjar on 2/9/15.
 */
public class Keywords implements Kw
{
    private static final Logger LOG = LoggerFactory.getLogger(Keywords.class);

    private final Browser browser;
    private String _value;

    public Keywords(Browser browser)
    {
        this.browser = browser;
    }

    public Report process
            (
                    Repository repository,
                    String fieldName,
                    String value,
                    DataDriver dataDriver,
                    Report report

            ) throws ViewNotFound
    {
        if (isDataDriven(value))
        {
            value = dataDriver.getValue(removeKeyword(value));
            SpeedItUtils.sleep(1000);

        }
        if (fieldName.equals(NAVIGATE))
        {
            browser.navigate(value);
            SpeedItUtils.sleep(5000);
        }
        else
        {
            View view = repository.getView(fieldName);

            if (isClick(value))
            {
                LOG.info("Click '{}' element", view.toString());
                browser.click(view);
            }
            else if (isVerify(value))
            {
                LOG.info("Verify the text for '{}' element", view.toString());

                value = removeKeyword(value);

                if (isContains(value))
                {
                    LOG.info("Verify the in-string text for '{}' element", view.toString());
                    assertThat("Expected text did not contain actual value", browser.getVisibleText(view),
                               containsString(removeKeyword(value)));
                }
                else
                {
                    assertThat("Expected text did not match", browser.getVisibleText(view),
                               is(removeKeyword(value)));
                }

                report.setSteps(fieldName, value, Keys.Result.PASS);

                return report;
            }
            else
            {
                if (browser.getTag(view).equals(Browser.SELECT))
                {
                    LOG.info("Select a value '{}' element", view.toString());
                    browser.selectByText(view, value);
                }
                else if (browser.getType(view).equals(Browser.CHECK_BOX))
                {
                    LOG.info("Checkbox '{}' element", view.toString());

                    if (value.equalsIgnoreCase(Kw.ON))
                    {
                        browser.activateCheckBox(view);

                    }
                    else if (value.equalsIgnoreCase(Kw.OFF))
                    {
                        browser.deactivateCheckBox(view);
                    }

                }

                else
                {
                    LOG.info("Type on '{}' element", view.toString());
                    browser.clearAndType(view, value);
                }
            }
        }

        report.setSteps(fieldName, value, Keys.Result.NONE);

        return report;
    }

    private boolean isDataDriven(String value)
    {
        return isKeywordPresent(value, PIPE_LINE);
    }

    private boolean isOff(String value)
    {
        return value.equals(OFF);
    }

    private boolean isOn(String value)
    {
        return value.equals(ON);
    }

    private boolean isClick(String value)
    {
        return value.equals(CLICK);
    }

    private boolean isVerify(String value)
    {
        return value.substring(0, 1).equals(VERIFY_BEGINS) &&
                value.substring(value.length() - 1, value.length()).equals(VERIFY_ENDS);
    }

    private boolean isContains(String value)
    {
        return isKeywordPresent(value, CONTAINS);
    }

    private boolean isKeywordPresent(String value, String Kw)
    {
        return value.substring(0, 1).equals(Kw) &&
                value.substring(value.length() - 1, value.length()).equals(Kw);
    }

    private String removeKeyword(String value)
    {
        if (isVerify(value) || isDataDriven(value))
        {
            return value.substring(1, value.length() - 1);
        }

        if (isContains(value))
        {
            return value.substring(1, value.length() - 1);
        }

        _value = value;

        return value;
    }

    public String getValue()
    {
        return _value;
    }
}
