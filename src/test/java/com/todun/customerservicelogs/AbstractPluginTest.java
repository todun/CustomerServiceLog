package com.todun.customerservicelogs;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Plugin tests base class
 */
public abstract class AbstractPluginTest extends TestCase {
    protected static WebDriver driver;

    private final static String DOMAIN_PORT_SEPARATOR = ":";

    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    protected static String baseUrl;
    protected static String resourceProtocol;
    protected static String port;
    protected static String resourcePath;

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    /**
     * Builds a URI for the resource
     *
     * @param protocol {@link #resourceProtocol}
     * @param domain {@link #resourcePath}
     * @param port {@link #port}
     * @param isFile is resource on file system
     * @return URI to resource
     */
    protected static String buildBaseUri(String protocol, String domain, String port, boolean isFile) {
        StringBuilder url = new StringBuilder();
        url.append(protocol).append(domain);

        url = isFile ? url.append(port) : url.append(DOMAIN_PORT_SEPARATOR).append(port);
        return url.toString();
    }

    /**
     * Selenium test scenario clicking the links on the page
     *
     * @throws Exception
     */
    protected static void clickDifferentLinksScenarioTestCase() throws Exception {
        driver.get(baseUrl);

        driver.findElement(By.linkText("Load the current issues list")).click();
        // ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | Bing | 30000]]
    }

    /**
     * Browser driver is stopped after test run
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    protected boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
