package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SecondOrderPage {
    //Инициализировал драйвер
    WebDriver driver;
    public SecondOrderPage(WebDriver driver){
        this.driver = driver;
    }
//Локаторы элементов на второй странице для заказа
    private By dataField = By.xpath(".//input[@placeholder = '* Когда привезти самокат']");
    private By rentTimeField = By.xpath(".//input[@placeholder = '* Срок аренды']");
    private By colorBlack = By.cssSelector("#black");
    private By colorGrey = By.cssSelector("#grey");
    private By timeOfRentDropDown = By.cssSelector(".Dropdown-control");
    private By getTimeOfRentDropDownItem1 = By.xpath(".//div[text()='сутки']");
    private By getTimeOfRentDropDownItem2 = By.xpath(".//div[text()='двое суток']");
    private By orderButtonFinal = By.xpath(".//div[@class= 'Order_Buttons__1xGrp']/button[text()='Заказать']");
    private By orderConfirmButton = By.xpath(".//button[text()='Да']");

    private By orderSuccessModal = By.cssSelector(".Order_Modal__YZ-d3 .Order_ModalHeader__3FDaJ");


    //Методы для работы с этими локаторами
    //Метод ввода даты
    public void inputData(String data){
        driver.findElement(dataField).sendKeys(data, Keys.ENTER);
    }
    //Метод выбора количества дней
    public void chooseCountOfDays(By itemNumber){
        driver.findElement(timeOfRentDropDown).click();
        driver.findElement(itemNumber).click();
    }
    //Метод выбора цвета
    public void chooseColor(By color){
        driver.findElement(color).click();
    }
    //Метод клика на кнопку заказать
    public void confirmOrder(){
        driver.findElement(orderButtonFinal).click();
    }
    //метод получения текста из модалки
    public String successModalGetText(){
      return driver.findElement(orderSuccessModal).getText();
    }

    //Общий метод заполнения всех инпутов на второй странице, клик на кнопку заказать и на Кнопку Да в модалке подтверждения заказа
    public void fillSecondPageFields(String data){
        inputData(data);
        chooseCountOfDays(getTimeOfRentDropDownItem1);
        chooseColor(colorBlack);
        confirmOrder();
        driver.findElement(orderConfirmButton).click();

    }

}

