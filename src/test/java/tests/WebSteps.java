package tests;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class WebSteps {

    @Step("Open repository")
    public void openRepository() {
        open("/selenide/selenide");
    }

    @Step("Open Issues tab")
    public void openIssuesTab() {
        $("#issues-tab").click();
    }

    @Step("Check issue name")
    public void checkIssueName() {
        $$("div[aria-label='Issues'] a")
                .findBy(text("PageObject example"))
                .shouldHave(text("PageObject example"));
    }
}