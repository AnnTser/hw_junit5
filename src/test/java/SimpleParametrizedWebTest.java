import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;


public class SimpleParametrizedWebTest extends TestBase{


    static Stream<Arguments> findButtonsTest() {
        return Stream.of(
                arguments(List.of("Markets", "Indices", "Market data", "Listing", "Connectivity", "News and events", "About MOEX", "Investor Relations")),
                arguments(List.of("Продукты и услуги", "Биржевая информация", "Документы", "Обучение", "Медиа", "О компании"))

        );
    }

    @MethodSource
    @ParameterizedTest
    @DisplayName("Проверка наличия кнопок Входа и Регистрации мультиланг")
    @Tag("Localization")
    void findButtonsTest(List<String> expectedButtons) {


        $x("//*[@class='lang-switch']//*[@class='lang-switch__lang']").shouldBe(visible).click();

        ElementsCollection elements = $$x("//*[@class='header header-row']// following::*[@class='site-header-menu__top-link']");
        for (int i = 0; i < elements.size(); i++) {
            String textButton = elements.get(i).getText();
            textButton.equals(expectedButtons.get(i));
        }


    }

}