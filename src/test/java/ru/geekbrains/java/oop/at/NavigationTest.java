package ru.geekbrains.java.oop.at;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.geekbrains.java.oop.at.base.BaseTest;

import java.util.stream.Stream;


public class NavigationTest extends BaseTest {

//    Перейти на сайт https://geekbrains.ru/events
//    Нажать на кнопку Курсы
//    Проверить что страница Курсы открылась
//    Повторить для
//    Курсы
//    Вебинары
//    Форум
//    Блог
//    Тесты
//    Карьера
//    Реализовать проверку отображения блоков Header и Footer на каждой странице сайта (как минимум самого блока)

    @AfterEach
    void tearDown() {
        WebElement header = chromeDriver.findElementByCssSelector("[class*=\"gb-header__content\"]");
        WebElement footer = chromeDriver.findElementByCssSelector("[class=\"site-footer__content\"]");
        wait15second.until(ExpectedConditions.visibilityOf(header));
        wait15second.until(ExpectedConditions.visibilityOf(footer));
    }

    public static Stream<Arguments> listNameMulti() {
        return Stream.of(
                Arguments.of("[id=\"nav\"] [href=\"/topics\"]", "Форум"),
                Arguments.of("[id=\"nav\"] [href=\"/events\"]", "Вебинары"),
                Arguments.of("[id=\"nav\"] [href=\"/posts\"]", "Блог"),
                Arguments.of("[id=\"nav\"] [href=\"/tests\"]", "Тесты"),
                Arguments.of("[id=\"nav\"] [href=\"/career\"]", "Карьера"),
                Arguments.of("[id=\"nav\"] [href=\"/courses\"]", "Курсы")
        );
    }

    @ParameterizedTest
    @MethodSource("listNameMulti")
    void methodMulti(String selector, String name) {
        chromeDriver.findElement(By.cssSelector(selector)).click();
        Assertions.assertEquals(
                name,
                chromeDriver.findElement(By.cssSelector("[id=\"top-menu\"] h2")).getText()
        );
    }
}
