Feature: To test the search features of "Salesforce Argentina"

  Background: 
    Given I am on the Google page
    When I enter "Salesforce Argentina" on the input search
    And I start the search

  Scenario: Showing the ads-pages
    Then I need to show the ads-urls in the console

  Scenario: Showing urls from Argentina
    Then I need to show in the console the argentinian urls of the first "5" pages

  Scenario: Showing url positioning
    Then I need to show in the console the positioning of "Xappia"
