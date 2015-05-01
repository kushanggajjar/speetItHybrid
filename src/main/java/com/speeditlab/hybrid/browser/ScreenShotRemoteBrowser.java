package com.speeditlab.hybrid.browser;

import java.net.URL;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteWebDriver;

// TODO: Auto-generated Javadoc


/**
 * The Class ScreenShotRemoteDriver.
 */
public class ScreenShotRemoteBrowser extends RemoteWebDriver implements
        TakesScreenshot
{

	/**
	 * Instantiates a new screen shot remote driver.
	 * 
	 * @param url the url
	 * @param capability the capability
	 */
	public ScreenShotRemoteBrowser(URL url, DesiredCapabilities capability) {
		super(url, capability);
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.TakesScreenshot#getScreenshotAs(org.openqa.selenium.OutputType)
	 */
	public <X> X getScreenshotAs(OutputType<X> arg0) throws WebDriverException
  {
		if ((Boolean) getCapabilities().getCapability(
				CapabilityType.TAKES_SCREENSHOT)) {
			final String base64Str = execute(DriverCommand.SCREENSHOT)
					.getValue().toString();
			return arg0.convertFromBase64Png(base64Str);
		}
		return null;
	}

}
