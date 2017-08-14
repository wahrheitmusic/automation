package core.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static util.OsCheck.getOperatingSystemType;

import util.OsCheck;


public final class WebDriverFactory {

    private static final String X64_ARCH = "amd64";
    private static String driverName = System.getProperty("webdriver.driver");
    private static WebDriverFactory factoryInstance;
    private boolean isInitialized = false;

    private WebDriverFactory() {
    }

    public static WebDriverFactory getInstance() {
        if (null == factoryInstance)
            factoryInstance = new WebDriverFactory();
        return factoryInstance;
    }

    private final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>() {
        protected WebDriver initialValue() {
            isInitialized = true;
            return initDriver(driverName);
        }
    };

    public WebDriver getDriver() {
        return threadDriver.get();
    }

    public boolean isInstantiated() {
        return isInitialized;
    }

    public void reset() {
        isInitialized = false;
        threadDriver.remove();
    }

    private WebDriver initDriver(final String driverName) {
        WebDriver driver;
        setDriverProperties(getOperatingSystemType());
        switch (driverName) {
        case "chrome":
            driver = new ChromeDriver(withChromeOptions());
            break;
        default:
            throw new RuntimeException(
                    String.format("%s driver is not supported. Please check if the right driver was set."));
        }
        return driver;
    }

    private void setDriverProperties(final OsCheck.OSType operatingSystemType) {
        boolean is64Arch = X64_ARCH.equals(System.getProperty("os.arch"));
        switch (operatingSystemType) {
        case WINDOWS:
            setChromeDriverWin32();
            break;
        case LINUX:
            if (is64Arch)
                setChromeDriverLinux64();
            else
                setChromeDriverLinux32();
        case MAC:
            setChromeDriverMac64();
        }
    }

    private void setChromeDriverWin32() {
        System.setProperty("webdriver.chrome.driver", "driver/chrome/win32/chromedriver.exe");
    }

    private void setChromeDriverLinux32() {
        System.setProperty("webdriver.chrome.driver", "driver/chrome/linux32/chromedriver");
    }

    private void setChromeDriverLinux64() {
        System.setProperty("webdriver.chrome.driver", "driver/chrome/linux64/chromedriver");
    }

    private void setChromeDriverMac64() {
        System.setProperty("webdriver.chrome.driver", "driver/chrome/mac64/chromedriver");
    }

    private ChromeOptions withChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        return options;
    }

}
