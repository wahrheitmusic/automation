Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: IDCH-4 Verify that schema can be added to order successfully
Given SSP-WS-User creates an order
And SSP-UI-User is logged into the system
And SSP-UI-User opens Product Metadata Schemas view
When SSP-WS-User creates schema from file: test_schema.txt
And SSP-UI-User opens orders perspective
And SSP-UI-User adds schema to order by index 0
Then SSP-UI-User verifies that selected schema is displayed on Order Details view