Feature: Test the shopping cart checkout flow of the 'Dafiti' app
  @shopping_cart
  Scenario: User adds two items to the shopping cart
    Given the user is logged into the app
    And the user adds two items to the shopping cart
    When the user goes to the checkout before the payment process
    Then the displayed total value is the sum of the two products added by the user
