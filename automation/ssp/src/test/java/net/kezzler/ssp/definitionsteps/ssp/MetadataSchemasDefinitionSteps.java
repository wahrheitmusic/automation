package net.kezzler.ssp.definitionsteps.ssp;

import net.kezzler.ssp.engine.session.Session;
import net.kezzler.ssp.engine.session.dictionary.Key;
import net.kezzler.ssp.steps.ssp.order.HeaderPanelSteps;
import net.kezzler.ssp.steps.ssp.order.MetadataSchemaPanelSteps;
import net.kezzler.ssp.steps.ws.ssp.SspRestClientSteps;
import net.kezzler.ssp.utils.FrameworkFileUtils;
import net.kezzler.ssp.utils.FrameworkRandomUtils;
import net.thucydides.core.annotations.Steps;

import org.jbehave.core.annotations.When;

public class MetadataSchemasDefinitionSteps {

    @Steps
    private MetadataSchemaPanelSteps metadataSchemaPanelSteps;
    @Steps
    private HeaderPanelSteps headerPanelSteps;
    @Steps
    private SspRestClientSteps sspRestClientSteps;
    @Steps
    private LogInDefinitionSteps logInDefinitionSteps;

    @When("SSP-WS-User creates schema from file: $fileName")
    public void ui_creates_schema_from_file(final String fileName) {
        String schemaName = String.valueOf(FrameworkRandomUtils.generateRandomProductId());
        String schema = FrameworkFileUtils.readTestSchema(fileName);
        logInDefinitionSteps.ws_user_logged_into_system();
        sspRestClientSteps.send_post_metadata_schema_request(schemaName, schema);
        Session.put(Key.SCHEMA_NAME, schemaName);
    }
}
