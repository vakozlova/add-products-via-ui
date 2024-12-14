package com.autotest.tests;

import com.autotest.pages.ProductPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class AddProductTest {
    private static WebDriver driver;
    private static ProductPage productPage;

    @BeforeAll
    static void setup() {
        System.setProperty("webdriver.chrome.driver", "D:\\ibs-task\\autotest-add-products-via-ui\\src\\test\\resources\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-extensions");

        driver = new ChromeDriver(options);
        productPage = new ProductPage(driver);
    }

    @AfterAll
    static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Тест добавления товаров типа "Фрукт"
     * Автор: Валерия Козлова
     */
    @Test
    void testAddFruitAndVegetable() throws InterruptedException {
        // Step1: Открыть стенд
        driver.get("http://localhost:8080");

        // Step2: Перейти на вкладку "Песочница" - "Товары"
        driver.findElement(By.linkText("Песочница")).click();
        driver.findElement(By.linkText("Товары")).click();

        // Step3: Добавить экзотический фрукт "Рамбутан"
        productPage.addNewProduct("Рамбутан", "Фрукт", true);
        Thread.sleep(3000);

        // Step4: Проверить, что "Рамбутан" добавлен
        Assertions.assertTrue(productPage.isProductInList("Рамбутан"), "Товар 'Рамбутан' не добавлен!");

        // Step5: Добавить не экзотический фрукт "Абрикос"
        productPage.addNewProduct("Абрикос", "Фрукт", false);
        Thread.sleep(3000);

        // Step6: Проверить, что "Абрикос" добавлен
        Assertions.assertTrue(productPage.isProductInList("Абрикос"), "Товар 'Абрикос' не добавлен!");

        // Step7: Сбросить данные
        productPage.resetData();
        Thread.sleep(3000);

        // Step8: Проверить, что товары удалены
        Assertions.assertFalse(productPage.isProductInList("Рамбутан"), "Товар 'Рамбутан' не был удален!");
        Assertions.assertFalse(productPage.isProductInList("Абрикос"), "Товар 'Абрикос' не был удален!");
    }

    /**
     * Тест добавления товаров типа "Овощ"
     * Автор: Валерия Козлова
     */
    @Test
    void testAddExoticAndNonExoticVegetables() throws InterruptedException {
        // Step1: Открыть стенд
        driver.get("http://localhost:8080");

        // Step2: Перейти на вкладку "Песочница" - "Товары"
        driver.findElement(By.linkText("Песочница")).click();
        driver.findElement(By.linkText("Товары")).click();

        // Step3: Добавить экзотический овощ "Пепино"
        productPage.addNewProduct("Пепино", "Овощ", true);
        Thread.sleep(3000);

        // Step4: Проверить, что "Пепино" добавлен
        Assertions.assertTrue(productPage.isProductInList("Пепино"), "Товар 'Пепино' не добавлен!");

        // Step5: Добавить не экзотический овощ "Баклажан"
        productPage.addNewProduct("Баклажан", "Овощ", false);
        Thread.sleep(3000);

        // Step6: Проверить, что "Баклажан" добавлен
        Assertions.assertTrue(productPage.isProductInList("Баклажан"), "Товар 'Баклажан' не добавлен!");

        // Step7: Сбросить данные
        productPage.resetData();
        Thread.sleep(3000);

        // Step8: Проверить, что товары удалены
        Assertions.assertFalse(productPage.isProductInList("Пепино"), "Товар 'Пепино' не был удален!");
        Assertions.assertFalse(productPage.isProductInList("Баклажан"), "Товар 'Баклажан' не был удален!");
    }

}
