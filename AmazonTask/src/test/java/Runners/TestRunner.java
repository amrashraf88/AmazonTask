package Runners;
import io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        features = "/Users/amrashraf/IdeaProjects/Nojom/src/main/resources/Features/Add to card",
        glue = "StepDefinitions"
)
public class TestRunner {
}
//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
//import org.junit.runner.RunWith;
//
//@RunWith(Cucumber.class)
//@CucumberOptions(
//        plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
//        features = {"src/main/resources/Features"},
//        glue = {"StepDefinitions"}
//)
//public class TestRunner {
//}
