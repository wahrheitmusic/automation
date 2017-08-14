package jbehave.core.embedder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CompositeStepsFactory;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.ScanningStepsFactory;
import org.jbehave.core.steps.SilentStepMonitor;
import org.junit.runner.RunWith;

import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;

import jbehave.core.parameterconverter.JsonParameterConverter;
import jbehave.core.parameterconverter.ToObjectParameterConverter;
import jbehave.core.reports.CustomWebDriverHtmlOutput;
import jbehave.steps.scenario.ScreenshotOnFailure;
import util.PropertyManager;

@RunWith(JUnitReportingRunner.class)
public class CustomEmbedder extends JUnitStories {
    private final String STORIES_LOCATION = "src/test/resources";
    private final String PROJECT_PROPERTIES = "src/test/resources/config/project.properties";
    private final String DEFINITION_STEPS_LOCATION = "jbehave.steps.scenario";


    @Override
    protected List<String> storyPaths() {
        List<String> stories = Arrays.asList(System.getProperty("stories").split(",")).stream()
                .map(s -> "**/" + s.trim()).collect(Collectors.toList());
        return new StoryFinder().findPaths(STORIES_LOCATION, stories, null);
    }

    @Override
    public Configuration configuration() {
        mergeToSystemProperties(PROJECT_PROPERTIES);
        Class<? extends CustomEmbedder> embedderClass = this.getClass();
        return new MostUsefulConfiguration().useStoryLoader(new LoadFromClasspath(embedderClass.getClassLoader()))
                .useStoryReporterBuilder(
                        new StoryReporterBuilder().withCodeLocation(CodeLocations.codeLocationFromClass(embedderClass))
                                .withFailureTrace(true).withFailureTraceCompression(false).withDefaultFormats()
                                // .withFormats(CONSOLE, TXT, HTML, XML)
                                .withFormats(CustomWebDriverHtmlOutput.CUSTOM_HTML)
                                .withCrossReference(new CrossReference()).withRelativeDirectory("../jbehave"))
                .useStepMonitor(new SilentStepMonitor()).useParameterConverters(setParameterConverters());
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        InstanceStepsFactory instanceStepsFactory = new InstanceStepsFactory(configuration(),
                new ScreenshotOnFailure(configuration().storyReporterBuilder()));
        ScanningStepsFactory scanningStepsFactory = new ScanningStepsFactory(configuration(), DEFINITION_STEPS_LOCATION)
                .matchingNames(".*DefinitionSteps|.*ScenarioSteps");
        return new CompositeStepsFactory(scanningStepsFactory, instanceStepsFactory);
    }

    private ParameterConverters setParameterConverters() {
        return new ParameterConverters().addConverters(new JsonParameterConverter(), new ToObjectParameterConverter());
    }

    private void mergeToSystemProperties(final String propertyFile) {
        PropertyManager.mergeProperties(propertyFile);
    }
}