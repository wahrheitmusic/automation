Meta:

Narrative:
As a user
I want to perform a search for apartment
So that I can achieve my goal

Scenario: User searches for apartment and checks sees that price is equal to quotes
Given User searches for apartments via WS:
|LOCATION     |DAY_FROM|DAYS_TO_STAY|NUMBER_OF_GUESTS|CURRENCY|
|Paris, France|MONDAY  |7           |2               |USD     |
When User selects 'Apartment Appartement Seine' apartments from WS search results
And User requests quotes for selected apartment
Then User checks that price is equal to quotes in Quote Response