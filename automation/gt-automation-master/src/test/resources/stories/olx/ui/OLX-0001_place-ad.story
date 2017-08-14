Meta:

Narrative:
As a user
I want to be able to input data about a product
So that I can publish a new AD on OLX

Scenario: User publishes new AD on OLX
Given User opens OLX Home Page
And User clicks New Ad Button and proceeds with authorisation:
|LOGIN               |PASSWORD             |
|testemail@google.com|someInsanePassword123|
When User publishes new Ad: examples/json/ad/test_ad.json
Then User sees 'Спасибо за размещение объявлений на OLX!' on PaymentPage