Feature: Amazon Automation Scenario
    As a user, I want to automate the Amazon task scenario using Selenium with Cucumber

    Scenario Outline: Add all products below 15k EGP to the cart
        Given the user is on the Amazon homepage
        When I log in with email "<email>" and password "<password>"
        And I navigate to "<menu>"
        And I apply the filters for "<filter1>" and "<filter2>"
        And I sort by "<sortingOption>"
        And I add products below 15000 EGP to the cart
        Then I verify all products are added to the cart
        When the user adds a new address with full name "John Doe", phone number "01056345345", street "Test Street", building "Building 123", and landmark "Near Park"
        Then the user proceeds to checkout

        Examples:
            | email                  | password       | menu            | filter1       | filter2   | sortingOption     |
            | 01002386433 | 8303645  |    All Video Games | Free Shipping | New       | High to Low       |