Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: IDCOD-1 Verify that codes can be uploaded successfully in csv format
Given SSP-WS-User creates an order
And SSP-UI-User is logged into the system
When SSP-UI-User opens orders perspective
And SSP-UI-User opens order by index 0 via 'Goto order' button
And SSP-UI-User uploads random generated csv codes file
Then SSP-UI-User verifies that count of code lines is equal to 1

Scenario: IDCOD-2 Verify a new top-up can't be created with empty 'Top-up size' field
Given SSP-WS-User creates an order
And SSP-UI-User is logged into the system
When SSP-UI-User opens orders perspective
And SSP-UI-User opens order by index 0 via 'Goto order' button
And SSP-UI-User opens 'New Top-up' modal
Then SSP-UI-User verifies that 'Top up' button is disabled on the 'New Top-up' modal

Scenario: IDCOD-3 Verify that appropriate error message displays when upload existing codes
Given SSP-WS-User creates an order
And SSP-UI-User is logged into the system
When SSP-UI-User opens orders perspective
And SSP-UI-User opens order by index 0 via 'Goto order' button
And SSP-UI-User uploads random generated csv codes file
And SSP-UI-User uploads previously used csv codes file
Then SSP-UI-User verifies that error alert appears
And SSP-UI-User verifies that count of code lines is equal to 1