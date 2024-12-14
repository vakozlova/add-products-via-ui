package com.autotest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Метод для нахождения кнопки "Добавить"
    public WebElement findAddButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-primary")));
    }

    // Метод для нахождения поля "Наименование"
    public WebElement findNameField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
    }

    // Метод для нахождения поля "Тип"
    public WebElement findTypeField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("type")));
    }

    // Метод для нахождения чекбокса "Экзотический"
    public WebElement findExoticCheckbox() {
        return wait.until(ExpectedConditions.elementToBeClickable(By.name("exotic")));
    }

    // Метод для нахождения кнопки "Сохранить"
    public WebElement findSaveButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#save")));
    }

    // Метод для добавления нового товара
    public void addNewProduct(String name, String type, boolean isExotic) {
        findAddButton().click();
        findNameField().sendKeys(name);
        findTypeField().sendKeys(type);
        if (isExotic) findExoticCheckbox().click();
        findSaveButton().click();
    }

    // Метод для проверки, что товар добавлен
    public boolean isProductInList(String productName) {
        try {
            WebElement productRow = driver.findElement(By.xpath("//table//td[text()='" + productName + "']"));
            return productRow != null;
        } catch (Exception e) {
            return false;
        }
    }

    // Метод для сброса данных на "заводские"
    public void resetData() {
        driver.findElement(By.linkText("Песочница")).click();
        driver.findElement(By.linkText("Сброс данных")).click();
    }
}
