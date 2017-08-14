Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: IDP-1 Verify that product can be connected to order
Given SSP-WS-User creates an order
And SSP-UI-User is logged into the system
And SSP-UI-User opens products perspective
When SSP-UI-User creates a new product
And SSP-UI-User opens orders perspective
And SSP-UI-User connects the order by index 0 to the product by index 0
Then SSP-UI-User verifies that product by index 0 is displayed on Order Details view