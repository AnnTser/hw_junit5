package homework;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.time.temporal.ChronoUnit.SECONDS;

public class InsuranceParametrizedTest extends TestBase {


    @BeforeEach
    void setup() {
        open("/life-insurance/stop-klesch/calc/");
    }


    @CsvFileSource(resources = "/insuranceTest.csv")
    @DisplayName("Укус клеща. Расчет на человека")
    @ParameterizedTest
    @Tag("Calc")
    void withCsvFileSourceTest(String region, String date, String result) {
        $x("//*[@class='form__item select']").click();
        $x("//*[@class='select__body__container scroll ps ps--theme_default ps--active-y']").$(byText(region)).click();
        $(".form__item.date.date--no_picker.error input").val(String.valueOf(date)).click();
        $(".formInfo2.margin4.mt48").click();
        $x("//*[@class='newPrice'][1]").shouldBe(visible).shouldBe(text(result));

    }


    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();
    }

}




