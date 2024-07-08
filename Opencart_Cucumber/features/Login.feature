
Feature: Login with Valid Credentials
 
  @sanity @regression 
  Scenario: Successful Login with Valid credentials 
    Given The user navigates to login page
    When  user enters email as "test@gmail.com" and password as "test123" 
    And the user click on the Login button
    Then the user should be redirect to MyAccount Page
    


