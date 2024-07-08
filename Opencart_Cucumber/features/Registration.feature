
Feature: Account Registration
  
  @regression
  Scenario: Successful Account registration 
    Given the user navigates to Account registration page
    When the user enters the details into below fileds 
      | firstName | UTK02            |
      | lastName  | test02           |
      | email     | test02@gmail.com |
      | telephone | 9999999999       |
      | password  | test02@123       |    
      
    And the user select privacy policy  
    And the user click on continue button
    Then the user account should get created successfully 
    
