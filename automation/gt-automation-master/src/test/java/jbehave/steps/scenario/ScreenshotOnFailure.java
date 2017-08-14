package jbehave.steps.scenario;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.AfterScenario.Outcome;
import org.jbehave.core.failures.PendingStepFound;
import org.jbehave.core.failures.UUIDExceptionWrapper;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.web.selenium.RemoteWebDriverProvider;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.webdriver.WebDriverFactory;

public class ScreenshotOnFailure {
    private static final Logger LOG = LoggerFactory.getLogger(ScreenshotOnFailure.class.getSimpleName());
    protected final StoryReporterBuilder reporterBuilder;
    public static final String DEFAULT_SCREENSHOT_PATH_PATTERN = "{0}/screenshots/failed-scenario-{1}.png";

    public ScreenshotOnFailure(final StoryReporterBuilder reporterBuilder) {
        this.reporterBuilder = reporterBuilder;
    }


    @AfterScenario(uponOutcome = Outcome.FAILURE)
    public void afterScenarioFailure(UUIDExceptionWrapper uuidWrappedFailure) throws Exception {
        if (uuidWrappedFailure instanceof PendingStepFound) {
            return; // we don't take screen-shots for Pending Steps
        }
        WebDriverFactory webDriverFactory = WebDriverFactory.getInstance();
        if (!webDriverFactory.isInstantiated()) {
            return;
        }
        WebDriver driver = webDriverFactory.getDriver();
        String screenshotPath = screenshotPath(uuidWrappedFailure.getUUID());
        String currentUrl = "[unknown page title]";
        try {
            currentUrl = driver.getCurrentUrl();
        } catch (Exception e) {
        }
        boolean savedIt = false;
        try {
            savedIt = saveScreenshotTo(driver, screenshotPath);
        } catch (RemoteWebDriverProvider.SauceLabsJobHasEnded e) {
            LOG.error(
                    "Screenshot of page '{}' has **NOT** been saved. The SauceLabs job has ended, possibly timing out on their end.",
                    currentUrl);
            return;
        } catch (Exception e) {
            LOG.error("Screenshot of page '{}'. Will try again. Cause: {}", currentUrl, e.getMessage());
            // Try it again. WebDriver (on SauceLabs at least?) has blank-page and zero length files issues.
            try {
                savedIt = saveScreenshotTo(driver, screenshotPath);
            } catch (Exception e1) {
                LOG.error("Screenshot of page '" + currentUrl + "' has **NOT** been saved to '" + screenshotPath
                        + "' because error '" + e.getMessage() + "' encountered. Stack trace follows:");
                e.printStackTrace();
                return;
            }
        }
        if (savedIt) {
            LOG.info("Screenshot of page '" + currentUrl + "' has been saved to '" + screenshotPath + "' with "
                    + new File(screenshotPath).length() + " bytes");
        } else {
            LOG.error("Screenshot of page '" + currentUrl
                    + "' has **NOT** been saved. If there is no error, perhaps the WebDriver type you are using is not compatible with taking screenshots");
        }
    }

    protected String screenshotPath(UUID uuid) {
        return MessageFormat.format(DEFAULT_SCREENSHOT_PATH_PATTERN, reporterBuilder.outputDirectory(), uuid);
    }

    public boolean saveScreenshotTo(WebDriver driver, String path) {
        if (driver instanceof TakesScreenshot) {
            File file = new File(path);
            byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
                IOUtils.write(bytes, new FileOutputStream(file));
            } catch (IOException e) {
                throw new RuntimeException("Can't save file", e);
            }
            return true;
        }
        return false;
    }
}
