import org.junit.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobject.MainPage;
import pageobject.FirstOrderPage;
import pageobject.SecondOrderPage;
import org.hamcrest.CoreMatchers;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;


//Обьявил параметризованный тест
@RunWith(Parameterized.class)
public class OrderRollerTest {
    //Инициализирую переменные которые нужны для заполнения полей
    private WebDriver driver;
    private final String nameValue;
    private final String surnameValue;
    private final String addressValue;
    private final String phoneValue;
    private final String metroValue;
    private final String data;
    private final int orderButtonNumber;
//Обьявил конструктор с полями для инпутов
    public OrderRollerTest(String nameValue, String surnameValue, String addressValue, String phoneValue, String metroValue, String data, int orderButtonNumber) {
        this.nameValue = nameValue;
        this.surnameValue = surnameValue;
        this.addressValue = addressValue;
        this.phoneValue = phoneValue;
        this.metroValue = metroValue;
        this.data = data;
        // 0 - кнопка в хедере, 1 - кнопка в середине страницы
        this.orderButtonNumber = orderButtonNumber;
    }
    @Before
    public void startUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
    //создал обьект с передаваемыми значениями
    @Parameterized.Parameters
    public static Object [][] personValues(){
        return new Object[][]{
                {"Виталяя","Иванов","Санкт-Петербург","89991112233","Черкизовская","31.04.2023",0},
                {"Максим","Белый","Москва","89828282233","Чистые пруды","3.05.2024",1}
        };
    }


    @Test
    public void testOrderOfRoller() {
        MainPage objMainPage = new MainPage(driver);
        //Закрыл куки
        objMainPage.closeCookie();
        //нажал на кнопку заказать
        objMainPage.clickOrderButton(orderButtonNumber);
        //Обьявил экземпляр первой страницы заказа
        FirstOrderPage orderPageNameSurname = new FirstOrderPage(driver);
        //Заполнил все поля и кликнул Далее
        orderPageNameSurname.fillAllInputsAndPressNext(nameValue,surnameValue,addressValue,phoneValue,metroValue);
        //Обьявил экземпляр второй страницы заказа
        SecondOrderPage orderPageOrderData = new SecondOrderPage(driver);
        //Заполнил на ней все поля, кликунл на Заказать и подтвердил нажав на ДА
        orderPageOrderData.fillSecondPageFields(data);
        //Наше текст воявившейся в результате удачного заказа модалки
        String result = orderPageOrderData.successModalGetText();
        System.out.println(result);
        //завел переменную в которой сохранил появившуюся модалку
        Assert.assertThat(result, CoreMatchers.startsWith("Заказ оформлен"));
    }

    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }


}
