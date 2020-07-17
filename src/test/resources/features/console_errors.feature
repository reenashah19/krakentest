Feature: To validate console error on page

Scenario Outline: console error

Given Setup browser "<browser>"
Given Navigate to the Page "<page>"
Then Verify console error on page

Examples:
	|browser|page|
	|Chrome|https://www.kraken.com|
	|Chrome|https://www.kraken.com/prices|
	|Chrome|https://www.kraken.com/doesntexist|
	|Chrome|https://www.kraken.com/en-us/features/security/pgp|
	