package jp.vmi.selenium.selenese;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;

import jp.vmi.selenium.webdriver.DriverOptions;
import jp.vmi.selenium.webdriver.WebDriverManager;

/**
 * Test for Safari with proxy.
 */
@Ignore("not yet ready to safari proxy test.")
public class CommandRunnerSafariProxyTest extends CommandRunnerSafariTest {
    static WebrickServer proxy = new Proxy();

    /**
     * Start proxy server.
     */
    @BeforeClass
    public static void startProxy() {
        proxy.start();
    }

    /**
     * Stop proxy server.
     */
    @AfterClass
    public static void stopProxy() {
        proxy.kill();
    }

    @Override
    protected void setupWebDriverManager() {
        WebDriverManager manager = WebDriverManager.getInstance();
        manager.setWebDriverFactory(WebDriverManager.SAFARI);
        manager.setDriverOptions(new DriverOptions());
    }
}
