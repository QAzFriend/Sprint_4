package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class FirstOrderPage {
    //Инициализировал драйвер
    private final WebDriver driver;
    //Конструктор страницы заказа с аргументом Driver
    public FirstOrderPage(WebDriver driver){
        this.driver = driver;
    }

    //Нашел все локаторы инпутов
    //Имя
    private By nameField = By.xpath(".//input[@placeholder = '* Имя']");
    //Фамилия
    private By surnameField = By.xpath(".//input[@placeholder = '* Фамилия']");
    //Адрес
    private By addressField  = By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ']");
    //Станция метро
    private By metroField = By.xpath(".//input[@placeholder = '* Станция метро']");
    //Теефон
    private By phoneNumber = By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер']");
    //Кнопка далее
    private By buttonNext = By.xpath(".//button[text()='Далее']");

    //Методы заполнения инпутов
    //Общий метод для заполнения инпута - передаю в него локатор инпута и то что хочу туда вписать
    public void fillInput(By inputName,String keys){

        driver.findElement(inputName).sendKeys(keys);
    }
    //метод заполнения поля станции метро
    public void fillMetroField(By metro, String metroValue) {
        driver.findElement(metro).sendKeys(metroValue, Keys.ARROW_DOWN, Keys.ENTER);
    }
    //Собрал в один метод заполнение всех инпутов и клик на кнопку Далее
    public void fillAllInputsAndPressNext(String name,String surname,String address, String phone,String metro){
        fillInput(nameField,name);
        fillInput(surnameField,surname);
        fillInput(addressField,address);
        fillMetroField(metroField,metro);
        fillInput(phoneNumber,phone);
        driver.findElement(buttonNext).click();

    }
}
