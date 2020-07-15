Feature: To validate the response code of the page

Scenario Outline: Response code validation

Given Setup browser "<browser>"
When Navigate to the Page "<page>"
Then Verify response code "<code>" of the Page "<page>"

Examples:
	|browser|page|code|
	|Chrome|https://www.kraken.com|200|
	|Chrome|https://www.kraken.com/prices|200|
	|Chrome|https://www.kraken.com/doesntexist|404|
	|Chrome|https://www.kraken.com/en-us/features/security/pgp|200|
	