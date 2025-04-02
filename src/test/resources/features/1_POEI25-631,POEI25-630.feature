Feature: 

	@POEI25-631 @karateH
	Scenario: Create an user from api
		Given url 'https://reqres.in/api/users'
		    And request {name: "Alice", job : "Dev"}
		    When method POST
		    Then status 201
		    And match response.name contains 'Alice'
		    And match response.job == 'Dev'
		    And match response.id != null
		    And print response
		
	@POEI25-630 @karateH
	Scenario: Get user Karate
		Given url 'https://reqres.in/api/users?page=2'
		    And param page = 2
		    When method GET
		    Then status 200
		    And match response.page == 2
		    And match response.data == '#[6]'
		    And match response.data[0].id == 7
		    And print response
		
