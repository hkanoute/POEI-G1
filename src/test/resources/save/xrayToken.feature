Feature: Xray Cloud Authentication

  Scenario: Get authentication token from Xray Cloud
    Given url 'https://xray.cloud.getxray.app/api/v2/authenticate'
    And header Content-Type = 'application/json'
    And request { client_id: 'E577F9CAEC8A4171985D2F82F129D73F', client_secret: 'fe3003afe6ef037751c6e1f8321f8145f9f01b274b0ab43bbb609aad7759957a' }
    When method post
    Then status 200
    And print 'Response:', response