package homework;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.time.temporal.ChronoUnit.SECONDS;

public class InsuranceParametrizedWoutCsvFileTest extends TestBase {

    @BeforeEach
        void setup(){
        open("/life-insurance/stop-klesch/calc/");
    }

    @CsvSource
            (value = {
                    "Амурская область, 20021990, 263",
                    "Амурская область, 20022020, 188",
            })
    @DisplayName("Укус клеща. Расчет по датам рождения")
    @ParameterizedTest
    @Tag("Calc")
    void withCsvSourceTest (String region, String date, String result) {
        $x("//*[@class='form__item select']").click();
       $x("//*[@class='select__body__container scroll ps ps--theme_default ps--active-y']").$(byText(region)).click();
       $(".form__item.date.date--no_picker.error input").click();
       $(".form__item.date.date--no_picker.error input").val(String.valueOf(date)).click();
       $(".formInfo2.margin4.mt48").click();
       $x("//*[@class='newPrice'][1]").shouldBe(exist, Duration.of(10, SECONDS));
       $x("//*[@class='newPrice'][1]").shouldBe(text(result));
    }

    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();
    }

}




