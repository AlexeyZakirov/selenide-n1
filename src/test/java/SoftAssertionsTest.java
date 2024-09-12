import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SoftAssertionsTest {

    String snippetJUnit5 = """
                @ExtendWith({SoftAssertsExtension.class})
                class Tests {
                  @Test
                  void test() {
                    Configuration.assertionMode = SOFT;
                    open("page.html");
                
                    $("#first").should(visible).click();
                    $("#second").should(visible).click();
                  }
                }
                """;

    @BeforeAll
    static void setUp(){
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://github.com";
    }
    @Test
    public void softAssertionsJUnit5ExampleTest(){
        open("/selenide/selenide");

        $("#wiki-tab").click();
        $("#wiki-pages-filter").setValue("SoftAssertions");
        $("div.js-wiki-sidebar-toggle-display").shouldHave(text("SoftAssertions"));
        $("div.js-wiki-sidebar-toggle-display").$(byText("SoftAssertions")).click();
        $("#wiki-body").shouldHave(text(snippetJUnit5));
    }
}
