package jp.vmi.selenium.selenese;

import java.io.File;
import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.junit.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import jp.vmi.selenium.selenese.result.Result;
import jp.vmi.selenium.webdriver.DriverOptions;
import jp.vmi.selenium.webdriver.DriverOptions.DriverOption;

import static org.junit.Assert.*;

/**
 * Test for {@link Runner}.
 */
public class RunnerTest {

    /**
     * Test of --proxy option.
     *
     * @throws IllegalArgumentException exception.
     * @throws IOException exception.
     */
    @Test
    public void invalidProxyOption() throws IllegalArgumentException, IOException {
        File tmp = File.createTempFile("aaa", "test.html");
        CommandLine cli = new Main().parseCommandLine(new String[] { "--proxy", "proxy.example.com", tmp.getAbsolutePath() });
        DriverOptions opt = new DriverOptions(cli);
        assertEquals("proxy.example.com", opt.get(DriverOption.PROXY));
    }

    /**
     * Test of empty file.
     *
     * @throws IOException exception.
     */
    @Test
    public void emptyFile() throws IOException {
        File tmp = File.createTempFile("aaa", "test.html");
        Runner runner = new Runner();
        runner.setDriver(new HtmlUnitDriver());
        runner.run(tmp.getCanonicalPath());
    }

    /**
     * Test of no file.
     *
     * @throws IOException exception.
     */
    @Test
    public void nosuchFile() throws IOException {
        Runner runner = new Runner();
        runner.setDriver(new HtmlUnitDriver());
        Result result = runner.run("nosuchfile.html");
        assertTrue(result.isFailed());
    }

    /**
     * Test of {@link Runner#run(String...)}).
     *
     * @throws IOException exception.
     */
    @Test
    public void runFiles() throws IOException {
        File tmp = File.createTempFile("aaa", "test.html");
        Runner runner = new Runner();
        runner.setDriver(new HtmlUnitDriver());
        runner.run(tmp.getPath(), tmp.getPath());
    }
}
