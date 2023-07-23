package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"/stepdefs", "/common"},
        plugin =  {"html:target/cucumber-html-report",
                "pretty"
        },
        tags = ""
)
public class Runner extends AbstractTestNGCucumberTests {
}
