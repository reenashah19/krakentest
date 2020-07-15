Feature: To validate broken links on page

Scenario Outline: broken links validator

Given Setup browser "<browser>"
When Navigate to the Page "<page>"
Then Verify broken links of the Page "<page>"

Examples:
	|browser|page|
	|Firefox|https://www.kraken.com|
	|Firefox|https://www.kraken.com/prices|
	|Firefox|https://www.kraken.com/doesntexist|
	|Firefox|https://www.kraken.com/en-us/features/security/pgp|
	