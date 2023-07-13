package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class MainPage {
    //Инициализировал драйвер
    private WebDriver driver ;
    //Кнопка закрытия куков
    private final By cookieCloseButton = By.cssSelector(".App_CookieButton__3cvqF");
    //Тайтл списка "Вопросы о важном"
    private final By mainQuestionText = By.xpath(".//div[text()='Вопросы о важном']");
    //Кнопка заказа в хедере
    private By orderButtonHeader = By.cssSelector(".Header_Nav__AGCXC .Button_Button__ra12g");
    //Кнопка заказа в середине страницы
    private By orderButtonMiddleOfPage = By.cssSelector(".Home_FinishButton__1_cWm button");
    //Вопросы и ответы о Важном
    private String [] questions = new String[] {"#accordion__heading-0","#accordion__heading-1","#accordion__heading-2",
            "#accordion__heading-3","#accordion__heading-4","#accordion__heading-5","#accordion__heading-6","#accordion__heading-7"};
    private String [] answers = new String[] { "#accordion__panel-0","#accordion__panel-1","#accordion__panel-2","#accordion__panel-3","#accordion__panel-4",
            "#accordion__panel-5","#accordion__panel-6","#accordion__panel-7"};

    //Кнопка перехода на страницу статуса заказа
    private By orderStatusButton = By.cssSelector(".Header_Nav__AGCXC .Header_Link__1TAG7");

    //Конструктор MainPage с аргументом
    public MainPage(WebDriver driver) {

        this.driver = driver;
    }
        //Метод для закрытия куков
        public void closeCookie() {
            driver.findElement(cookieCloseButton).click();
        }

        //Метод скролла к элементу
        public void scrollToTheElement(){
            WebElement searchingElement = driver.findElement(mainQuestionText);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", searchingElement);
        }

    //метод клика на одну из кнопок "Заказать"
    public void clickOrderButton(int numberOfButton){
        driver.findElement(numberOfButton == 0 ?  orderButtonHeader : orderButtonMiddleOfPage).click();

    }
        public void clickOnListOfQuestions(int numOfElement){
            driver.findElement(By.cssSelector(questions[numOfElement])).click();
        }
        public String getTextFromListOfAnswers(int numOfElement){
           return driver.findElement(By.cssSelector(answers[numOfElement])).getText();
        };


}


