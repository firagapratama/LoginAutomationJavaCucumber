@All
  Feature: Login

    @Login @successLogin
    Scenario: Login
      Given User is on login page
      When User fill username and password
      And User click login button
      Then User verify login result

    @Login @failedLogin
    Scenario: Login
      Given User is on login page
      When User fill wrong username or password
      And User click login button
      Then User get error message