@PostalOrZipCodeValidation 
Feature: Postal/Zip code API validation 


@RetrieveInformation 
Scenario:
Test to verify whether the user can retrive information using postal/zip code 
	Given As a user i have valid Base URL 
	And I set the country as "United States" 
	And I retrieve the country code from the CSV file 
	When I send the postal code "90210" along with country code 
	Then I should be able to get the postal or zip code information 
	
	
@ApartFromUSAandGB 
Scenario:
Test to verify whether the user is allowed to get information about postal/zip code other than USA and GB 
	Given As a user i have valid Base URL 
	And I set the country as "India" 
	And I retrieve the country code from the CSV file 
	When I send the postal code "210" along with country code 
	Then I should be able to get the postal or zip code information 
	
@IncorrectPostalOrZipCode 
Scenario:
Test to verify whether the user is allowed to get information about postal/zip code after entering an incorrect postal/zip code 
	Given As a user i have valid Base URL 
	And I set the country as "United States" 
	And I retrieve the country code from the CSV file 
	When I send the postal code "210" along with country code 
	Then I should be able to get the postal or zip code information