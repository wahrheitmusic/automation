package jbehave.core.reports;

import java.io.PrintStream;
import java.util.Properties;

import org.jbehave.core.configuration.Keywords;
import org.jbehave.core.reporters.FilePrintStreamFactory;
import org.jbehave.core.reporters.HtmlOutput;
import org.jbehave.core.reporters.StoryReporter;
import org.jbehave.core.reporters.StoryReporterBuilder;

/**
 * Created by Someone on 29.01.2017.
 */
public class CustomWebDriverHtmlOutput extends HtmlOutput {
    public static final org.jbehave.core.reporters.Format CUSTOM_HTML = new CustomWebDriverHtmlFormat();

    public CustomWebDriverHtmlOutput(PrintStream output) {
        super(output);
        changeALine();
    }

    public CustomWebDriverHtmlOutput(PrintStream output, Properties outputPatterns) {
        super(output, outputPatterns);
        changeALine();
    }

    public CustomWebDriverHtmlOutput(PrintStream output, Keywords keywords) {
        super(output, keywords);
        changeALine();
    }

    public CustomWebDriverHtmlOutput(PrintStream output, Properties outputPatterns, Keywords keywords) {
        super(output, outputPatterns, keywords);
        changeALine();
    }

    public CustomWebDriverHtmlOutput(PrintStream output, Properties outputPatterns, Keywords keywords,
            boolean reportFailureTrace) {
        super(output, outputPatterns, keywords, reportFailureTrace);
        changeALine();
    }

    private static class CustomWebDriverHtmlFormat extends org.jbehave.core.reporters.Format {

        public CustomWebDriverHtmlFormat() {
            super("HTML");
        }

        @Override
        public StoryReporter createStoryReporter(FilePrintStreamFactory factory,
                StoryReporterBuilder storyReporterBuilder) {
            factory.useConfiguration(storyReporterBuilder.fileConfiguration("html"));
            return new CustomWebDriverHtmlOutput(factory.createPrintStream(), storyReporterBuilder.keywords())
                    .doReportFailureTrace(storyReporterBuilder.reportFailureTrace())
                    .doCompressFailureTrace(storyReporterBuilder.compressFailureTrace());
        }
    }

    private void changeALine() {
        String imgPath = "../screenshots/failed-scenario-{3}.png";
        super.overwritePattern("failed",
                "<div class=\"step failed\">{0} <span class=\"keyword failed\">({1})</span><br/><span class=\"message failed\">{2}</span>"
                        + "<br/><a color=\"black\" target=\"jb_scn_shot\" href=\"" + imgPath + "\"><img src=\""
                        + imgPath + "\" alt=\"failing screenshot\" height=\"200\" width=\"350\"/></a></div>\n");
    }
}
