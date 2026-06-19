package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class SelenideTests {

    WebSteps steps = new WebSteps();

    @BeforeAll
    static void beforeAll() {

        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";

        SelenideLogger.addListener(
                "allure",
                new AllureSelenide()
        );
    }

    @Test
    void pureSelenideTest() {

        open("/selenide/selenide");

        $("#issues-tab").click();

        $$("div[aria-label='Issues'] a")
                .findBy(text("PageObject example"))
                .shouldHave(text("PageObject example"));
    }

    @Test
    void lambdaStepsTest() {

        step("Open repository", () -> {
            open("/selenide/selenide");
        });

        step("Open Issues tab", () -> {
            $("#issues-tab").click();
        });

        step("Check issue name", () -> {
            $$("div[aria-label='Issues'] a")
                    .findBy(text("PageObject example"))
                    .shouldHave(text("PageObject example"));
        });
    }

    @Test
    void annotatedStepsTest() {

        steps.openRepository();
        steps.openIssuesTab();
        steps.checkIssueName();
    }
}