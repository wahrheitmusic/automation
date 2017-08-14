package net.kezzler.ssp;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.kezzler.ssp.utils.frameworkengine.OsCheck;
import net.serenitybdd.jbehave.SerenityStories;
import net.thucydides.core.guice.Injectors;
import net.thucydides.core.util.EnvironmentVariables;

import org.jbehave.core.io.StoryFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.lambdaj.Lambda;

public class AcceptanceTestSuite extends SerenityStories {
    private static final Logger LOG = LoggerFactory.getLogger(AcceptanceTestSuite.class.getSimpleName());
    private static final String X64_ARCH = "amd64";
    private static EnvironmentVariables environmentVariables = Injectors.getInjector()
            .getProvider(EnvironmentVariables.class).get();

    public AcceptanceTestSuite() {
        try {
            Class.forName("net.kezzler.ssp.ProjectProperties");
        } catch (ClassNotFoundException e) {
            LOG.error("Error instantiating ProjectProperties", e);
        }
        setDriverAccordingToOS();
        selectStoryFilesForRunningSuite();
    }

    private void selectStoryFilesForRunningSuite() {
        String storiesPattern = environmentVariables.getProperty(ProjectProperties.Key.PROJECT_STORIES.toString());
        if (storiesPattern == null) {
            LOG.info("No suite key or pattern was provided, trying to run all stories in parallel");
            parallelAcceptanceTestSuite(storyPaths());
        } else {
            List<String> suitePath = getStoryPathsForSuite(storiesPattern);
            if (suitePath.isEmpty()) {
                LOG.info("No suite was found for the given {} key, trying to run as pattern not in parallel",
                        storiesPattern);
                List<String> storyNames = Arrays.asList(storiesPattern.split(";|,"));
                List<String> storyPaths = new ArrayList<>();
                storyNames.forEach(storyName -> storyPaths.add("**/" + storyName));
                parallelAcceptanceTestSuite(storyPaths);
            } else {
                parallelAcceptanceTestSuite(suitePath);
            }
        }
    }

    private void parallelAcceptanceTestSuite(final List<String> storyPaths) {
        List<String> stories = new StoryFinder().findPaths(System.getProperty("stories.path"), storyPaths, null);
        outputWhichStoriesAreBeingRun(stories);
        findStoriesCalled(Lambda.join(stories, ";"));
    }

    private List<String> getStoryPathsForSuite(final String runningSuite) {
        File suiteOfStories = findFile(runningSuite, new File(System.getProperty("suites.path")));
        return collectStoryPathsFromSuiteFile(suiteOfStories);
    }

    private File findFile(String searchedFile, File searchInDirectory) {
        File[] listOfAllFilesInDirectory = searchInDirectory.listFiles();
        File suiteOfStories;
        if (listOfAllFilesInDirectory != null) {
            for (File singleFileFromDirectory : listOfAllFilesInDirectory) {
                if (singleFileFromDirectory.isDirectory()) {
                    suiteOfStories = findFile(searchedFile, singleFileFromDirectory);
                    if (suiteOfStories != null) {
                        return suiteOfStories;
                    }
                } else if (searchedFile.equalsIgnoreCase(singleFileFromDirectory.getName().replaceAll("\\..+$", ""))) {
                    return singleFileFromDirectory;
                }
            }
        }
        LOG.info("There is no suite: {} in directory {}", searchedFile, searchInDirectory);
        return null;
    }

    private List<String> collectStoryPathsFromSuiteFile(final File suiteFile) {
        if (null == suiteFile) {
            return Collections.emptyList();
        }
        List<String> storyPaths;
        try {
            storyPaths = Files.readAllLines(Paths.get(suiteFile.getPath()), Charset.defaultCharset());
        } catch (IOException e) {
            LOG.error("Failed to open suite file, exiting", e);
            throw new RuntimeException(e);
        }
        LOG.info("Got story paths {}", storyPaths);
        return storyPaths;
    }

    private void outputWhichStoriesAreBeingRun(final List<String> stories) {
        LOG.info("Running " + stories.size() + " stories: ");
        for (String story : stories) {
            LOG.info(" - {}", story);
        }
    }

    private void setDriverAccordingToOS() {
        OsCheck.OSType ostype = OsCheck.getOperatingSystemType();
        switch (ostype) {
        case Windows:
            setChromeDriverWindows();
            if (X64_ARCH.equals(System.getProperty("os.arch"))) {
                setIeDriverWindows64();
            } else {
                setIeDriverWindows32();
            }
            break;
        case MacOS:
            setChromeDriverOsx();
            break;
        case Linux:
            if (X64_ARCH.equals(System.getProperty("os.arch"))) {
                setChromeDriverLinux64();
            } else {
                setChromeDriverLinux32();
            }
            break;
        case Other:
            LOG.error("Can't define OS");
            break;
        }
    }

    private void setChromeDriverLinux32() {
        System.setProperty("webdriver.chrome.driver", "drivers/linux/32bit/chromedriver");
    }

    private void setChromeDriverLinux64() {
        System.setProperty("webdriver.chrome.driver", "drivers/linux/64bit/chromedriver");
    }

    private void setChromeDriverWindows() {
        System.setProperty("webdriver.chrome.driver", "drivers/windows/chromedriver.exe");
    }

    private void setChromeDriverOsx() {
        System.setProperty("webdriver.chrome.driver", "drivers/osx/chromedriver");
    }

    private void setIeDriverWindows32() {
        System.setProperty("webdriver.ie.driver", "drivers/windows/32bit/iedriver.exe");
    }

    private void setIeDriverWindows64() {
        System.setProperty("webdriver.ie.driver", "drivers/windows/64bit/iedriver.exe");
    }

}
