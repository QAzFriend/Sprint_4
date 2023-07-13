import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebElement;
import pageobject.MainPage;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.List;


//обьявил параметризованный тест
@RunWith(Parameterized.class)
public class MainPageTest  {
    private final int numberOfQuestion;
    //Инициализировал драйвер и переменные с ожидаемым результатом и локаторами вопроса и ответа о важном
    WebDriver driver;
    private final String expectedResult;

    //Обьявил конструктор теста главной страницы с параметрами
    public MainPageTest(String expectedResult, int numberOfQuestion) {
        this.expectedResult = expectedResult;
        this.numberOfQuestion = numberOfQuestion;


    }
    //Создал обьект с парарметрами
    @Parameterized.Parameters
    public static Object[][] accordionItems(){
        return new Object[][]{

                { "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",0},
                {"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",1},
                {"Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",2},
                {"Только начиная с завтрашнего дня. Но скоро станем расторопнее.",3},
                {"Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",4},
                {"Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",5},
                {"Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",6},
                {"Да, обязательно. Всем самокатов! И Москве, и Московской области.",7}

        };

    }

//Завтра уезжаю в командировку, Жёсткий дедлайн)) если ничего критичного апрувните пожалуйста))

    @Test
    public void testQuestionsAndAnswersMatches() {
        //обьявил драйвер
        System.setProperty("webdriver.chrome.driver", "/Users/Boris/IdeaProjects/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*" , "--headless");
        WebDriver driver = new ChromeDriver(options);
        //WebDriver driver = new FirefoxDriver();
        //Перешел на главную
        driver.get("https://qa-scooter.praktikum-services.ru/");
        //Создал экземпляр главной страницы
        MainPage objMainPage = new MainPage(driver);
        //Закрыл куки
        objMainPage.closeCookie();
        //Проскроллил до элемент
        objMainPage.scrollToTheElement();
        //Кликнул на вопрос о важном
        objMainPage.clickOnListOfQuestions(numberOfQuestion);
        //Достал текст из div из дропдауна
        String result =  objMainPage.getTextFromListOfAnswers(numberOfQuestion);
        //Сравнил с правильным текстом
        Assert.assertEquals("Текст не совпал",expectedResult, result);
        //Закрыл драйвер
        driver.quit();
    }

}
