package com.kraken.automation;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = { "com.kraken.automation" },
plugin = { "pretty", "html:target/cucumber-reports" })

public class TestRunner {

}
