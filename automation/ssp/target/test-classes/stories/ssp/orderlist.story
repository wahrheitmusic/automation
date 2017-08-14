Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal


Scenario: IDOL-2 Verify that a newly created order is displayed in orders list
Given SSP-UI-User is logged into the system
When SSP-UI-User opens orders perspective
And SSP-UI-User creates an order with section checkbox: false
Then SSP-UI-User verifies that created order is displayed in list