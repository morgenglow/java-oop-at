package ru.geekbrains.java.oop.at;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.geekbrains.java.oop.at.base.BaseTest;

import java.util.List;

public class SearchTest extends BaseTest {

//    Перейти на сайт https://geekbrains.ru/events
//    Нажать на кнопку Поиск
//    В поле Поиск ввести текст: java
//    Проверить что на странице:
//    Профессий не менее чем 2
//    Курсов более 15
//    Вебинаров больше чем 180, но меньше 300
//    В вебинарах отображается первым “Java Junior. Что нужно знать для успешного собеседования?”
//    Блогов более 300
//    Форумов не 350
//    Тестов не 0
//    В Проектах и компаниях отображается GeekBrains

    @BeforeEach
    void search() {
        WebElement buttonSearch = chromeDriver.findElement(By.cssSelector("[class=\"show-search-form\"] [class=\"svg-icon icon-search \"]"));
        buttonSearch.click();
        WebElement inputSearch = chromeDriver.findElement(By.cssSelector("[class=\"search-panel__search-field\"]"));
        inputSearch.sendKeys("java");
    }

     @DisplayName("Профессий не менее чем 2")
     @Test
        void assert1() {
         WebElement professionsCount = chromeDriver.findElement(By.xpath("//a[@class='search-page-block__more' and @data-tab='professions']/span"));
         WebElement professions = chromeDriver.findElement(By.cssSelector("[id=\"professions\"] h2"));
         wait15second.until(ExpectedConditions.textToBePresentInElement(professions, "Профессии"));
         Assertions.assertTrue((Integer.parseInt(professionsCount.getText())) >= 2, "Количество найденных профессий менее 2!");
    }

    @DisplayName("Курсов более 15")
    @Test
    void assert2() {
        WebElement coursesCount = chromeDriver.findElement(By.xpath("//a[@class='search-page-block__more' and @data-tab='courses']/span"));
        WebElement courses = chromeDriver.findElement(By.xpath(".//header/h2[text()='Курсы']"));
        wait15second.until(ExpectedConditions.textToBePresentInElement(courses, "Курсы"));
        Assertions.assertTrue((Integer.parseInt(coursesCount.getText())) > 15, "Количество найденных курсов <= 15!");
    }

    @DisplayName("Вебинаров больше чем 180, но меньше 300")
    @Test
    void assert3() {
        WebElement events = chromeDriver.findElement(By.xpath(".//header/h2[text()='Вебинары']"));
        wait15second.until(ExpectedConditions.textToBePresentInElement(events, "Вебинары"));
        WebElement eventsCount = chromeDriver.findElement(By.xpath("//a[@class='search-page-block__more' and @data-tab='webinars']/span"));
        Assertions.assertTrue((Integer.parseInt(eventsCount.getText()) > 180) & (Integer.parseInt(eventsCount.getText()) < 300),
                "Количество найденных вебинаров не в диапазоне (180; 300)");
    }

    @DisplayName("В вебинарах отображается первым “Java Junior. Что нужно знать для успешного собеседования?”")
    @Test
    void assert4() {
        WebElement professions = chromeDriver.findElement(By.cssSelector("[id=\"professions\"] h2"));
        wait15second.until(ExpectedConditions.textToBePresentInElement(professions, "Профессии"));
        List<WebElement> eventsList = chromeDriver.findElements(By.xpath("//a[@class='event__title h3 search_text']"));
        Assertions.assertEquals("Java Junior. Что нужно знать для успешного собеседования?", eventsList.get(0).getText());
    }

    @DisplayName("Блогов более 300")
    @Test
    void assert5() {
        WebElement blogs = chromeDriver.findElement(By.xpath(".//header/h2[text()='Блоги']"));
        WebElement blogsCount = chromeDriver.findElement(By.xpath("//a[@class='search-page-block__more' and @data-tab='blogs']/span"));
        wait15second.until(ExpectedConditions.textToBePresentInElement(blogs, "Блоги"));
        Assertions.assertTrue((Integer.parseInt(blogsCount.getText())) > 300, "Количество найденных блогов менее 300!");
    }

    @DisplayName("Форумов не 350")
    @Test
    void assert6() {
        WebElement forum = chromeDriver.findElement(By.xpath(".//header/h2[text()='Форум']"));
        WebElement forumsCount = chromeDriver.findElement(By.xpath("//a[@class='search-page-block__more' and @data-tab='forums']/span"));
        wait15second.until(ExpectedConditions.textToBePresentInElement(forum, "Форум"));
        Assertions.assertTrue((Integer.parseInt(forumsCount.getText())) != 350, "Количество найденных форумов равно 350!");
    }

    @DisplayName("Тестов не 0")
    @Test
    void assert7() {
        WebElement tests = chromeDriver.findElement(By.xpath(".//header/h2[text()='Тесты']"));
        WebElement testsCount = chromeDriver.findElement(By.xpath("//a[@class='search-page-block__more' and @data-tab='tests']/span"));
        wait15second.until(ExpectedConditions.textToBePresentInElement(tests, "Тесты"));
        Assertions.assertTrue((Integer.parseInt(testsCount.getText())) != 0, "Количество найденных тестов равно 0!");
    }

    @DisplayName("В Проектах и компаниях отображается GeekBrains")
    @Test
    void assert8() {
        WebElement projectAndCompany = chromeDriver.findElement(By.xpath("//header/h2[text()='Проекты и компании']"));
        wait15second.until(ExpectedConditions.textToBePresentInElement(projectAndCompany, "Проекты и компании"));
        WebElement gbCompany = chromeDriver.findElement(By.xpath("//div[@class='company-items']//h3/a[contains(text(),'GeekBrains')]"));
        Assertions.assertNotNull(gbCompany, "В Проектах и компаниях не отображается GeekBrains");
    }
}
