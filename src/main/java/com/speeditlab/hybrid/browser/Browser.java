package com.speeditlab.hybrid.browser;

import java.util.List;

import com.speeditlab.hybrid.locators.View;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by kugajjar on 2/8/15.
 */
public class Browser
{
    public static final String CSS = "css";
    public static final String ID = "id";
    public static final String XPATH = "xpath";
    public static final String NAME = "name";
    public static final String TAG = "tag";
    public static final String SELECT = "select";
    public static final String CHECK_BOX = "checkbox";
    private static final int ELEMENT_SYNC_WAIT = 5;

    private final WebDriver driver;

    private static final Logger LOG = LoggerFactory.getLogger(Browser.class);
    private final WebDriverWait wait;

    public Browser(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, ELEMENT_SYNC_WAIT);
    }

    public void clearAndType(View view, String textToType)
    {
        WebElement element = findElement(view);

        element.clear();
        element.sendKeys(textToType);
    }

    public void navigate(String url)
    {
        LOG.info("Navigate to '{}' url", url);
        driver.get(url);

    }


    public String getTitle()
    {
        return driver.getTitle();
    }


    public void activateCheckBox(View view)
    {
        WebElement element = findElement(view);

        if (!element.isSelected())
            click(element);
    }


    public void deactivateCheckBox(View view)
    {
        WebElement element = findElement(view);

        if (element.isSelected())
            click(element);
    }

    public void click(View view)
    {
        findElement(view).click();
    }


    public void click(WebElement element)
    {
        element.click();
    }

    public void selectByText(View view, String text)
    {
        _genericSelect(view, false, text);
    }

    public void selectByValue(View view, String value)
    {
        _genericSelect(view, true, value);
    }

    private void _genericSelect(View view,
                                boolean isSelectByValue, String valueOrText)
    {
        Select select = new Select(findElement(view));
        if (isSelectByValue)
            select.selectByValue(valueOrText);
        else
            select.selectByVisibleText(valueOrText);
    }

    public WebElement wait_until_visibilityOfElementLocated(By findBy)
    {
        WebElement element = wait.until(ExpectedConditions
                                                .visibilityOfElementLocated(findBy));
        if (element == null)
            throw new NoSuchElementException("Element with given selector:"
                                                     + findBy.toString()
                                                     + " is still invisible even after waiting for:"
                                                     + ELEMENT_SYNC_WAIT + " seconds");
        else
            return element;
    }

    public WebElement findElement(View view)
    {
        if (view.getBy().equals(CSS))
        {
            return wait_until_visibilityOfElementLocated(By.cssSelector(view.getselector()));
        }
        else if (view.getBy().equals(ID))
        {
            return wait_until_visibilityOfElementLocated(By.id(view.getselector()));
        }
        else if (view.getBy().equals(XPATH))
        {
            return wait_until_visibilityOfElementLocated(By.xpath(view.getselector()));
        }
        else if (view.getBy().equals(NAME))
        {
            return wait_until_visibilityOfElementLocated(By.name(view.getselector()));
        }
        else if (view.getBy().equals(TAG))
        {
            return wait_until_visibilityOfElementLocated(By.tagName(view.getselector()));
        }

        throw new NoSuchElementException("element not found: " + view.toString());
    }

    public void quit()
    {
        driver.quit();
    }

    public String getVisibleText(View view)
    {
        return findElement(view).getAttribute("value");
    }

    public String getTag(View view)
    {
        return findElement(view).getTagName();
    }

    public String getType(View view)
    {
        return findElement(view).getAttribute("type");
    }

    public void scanAllTags(String tagName)
    {
        List<WebElement> elementList = driver.findElements(By.tagName(tagName));

        int i = 0;
        for (WebElement element : elementList)
        {
            String precedingName = "";

            try
            {
                WebElement precedingElement = element.findElement(By.xpath("preceding-sibling::*"));
                precedingName = precedingElement.getTagName();

                if (precedingName.equalsIgnoreCase("strong") || precedingName.equalsIgnoreCase("label"))
                {
                    System.out.println("Element Name is : " + precedingElement.getAttribute("textContent"));
                }

                System.out.println("CSS: "
                                           + element.findElement(By.xpath("parent::*")).getTagName()
                                           + ">"
                                           + tagName + getCss(element)
                                           + element.findElement(By.xpath("following-sibling::*")).getTagName());
            }
            catch (Exception e)
            {

            }

            i++;
        }

        System.out.println("Total input tags are " + elementList.size());
    }

    private String getCss(WebElement element)
    {
        String css = element.getAttribute("id");

        if (StringUtils.isNotEmpty(css))
        {
            return "#" + css;
        }

        css = element.getAttribute("class");

        if (StringUtils.isNotEmpty(css))
        {
            return "." + css;
        }

        return StringUtils.EMPTY;

    }
}

