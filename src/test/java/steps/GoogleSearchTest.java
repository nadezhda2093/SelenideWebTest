package steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.xpath;

public class GoogleSearchTest {


    @BeforeClass
    public static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://www.google.com/";
        Configuration.startMaximized = true;
    }

    public void openMainPageBank() {
        open(Configuration.baseUrl);
        $(By.name("q")).val("Банк открытие").pressEnter();
        $$("#res .g").shouldHave(sizeGreaterThan(5));
        $("#res .g").shouldHave(text("open.ru"));
        $$("cite").find(Condition.text("www.open.ru")).click();
    }


    @Test
    public void compareSellingRateWithPurchaseRateUSD() {
        openMainPageBank();
        SelenideElement courseUSDBuy = $(xpath("//span[contains(.,'USD')]/parent::*/../..//td[@class='main-page-exchange__td'][2]/div/span"));
        SelenideElement courseUSDSale = $(xpath("//span[contains(.,'USD')]/parent::*/../..//td[@class='main-page-exchange__td'][3]/div/span"));
        $(".main-page-info__block").find(byText("курс обмена в")).scrollTo();
        element(courseUSDBuy).shouldBe(visible);
        element(courseUSDSale).shouldBe(visible);
        double rateBuy = Double.parseDouble(courseUSDBuy.getText().replace(",", "."));
        double rateSale = Double.parseDouble(courseUSDSale.getText().replace(",", "."));
        if (rateBuy < rateSale) {
            System.out.println("Курс продажи USD = " + rateSale + " дороже курса покупки = " + rateBuy);
        } else {
            System.out.println("Курс продажи USD = " + rateSale + " меньше курса его покупки = " + rateBuy);
        }

    }

    @Test
    public void compareSellingRateWithPurchaseRateEUR() {
        openMainPageBank();
        SelenideElement courseEURBuy = $(xpath("//span[contains(.,'EUR')]/parent::*/../..//td[@class='main-page-exchange__td'][2]/div/span"));
        SelenideElement courseEURSale = $(xpath("//span[contains(.,'EUR')]/parent::*/../..//td[@class='main-page-exchange__td'][3]/div/span"));
        $(".main-page-info__block").find(byText("курс обмена в")).scrollTo();
        element(courseEURBuy).shouldBe(visible);
        element(courseEURSale).shouldBe(visible);
        double rateBuy = Double.parseDouble(courseEURBuy.getText().replace(",", "."));
        double rateSale = Double.parseDouble(courseEURSale.getText().replace(",", "."));
        if (rateBuy < rateSale) {
            System.out.println("Курс продажи EUR = " + rateSale + " дороже курса его покупки = " + rateBuy);
        } else {
            System.out.println("Курс продажи EUR = " + rateSale + " меньше курса его покупки = " + rateBuy);
        }

    }


}