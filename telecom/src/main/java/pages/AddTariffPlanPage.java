package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.netty.handler.timeout.TimeoutException;

public class AddTariffPlanPage extends BasePage {

    private By rental = By.id("rental1");
    private By localMinutes = By.id("local_minutes");
    private By internationalMinutes = By.id("inter_minutes");
    private By smsPack = By.id("sms_pack");
    private By localCharges = By.id("minutes_charges");
    private By interCharges = By.id("inter_charges");
    private By smsCharges = By.id("sms_charges");
    private By submitButton = By.name("submit");

    public AddTariffPlanPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://demo.guru99.com/telecom/addtariffplans.php");
    }

    public void fillForm(String rent, String local, String inter, String sms, String lCharges, String iCharges, String sCharges) {
        type(rental, rent);
        type(localMinutes, local);
        type(internationalMinutes, inter);
        type(smsPack, sms);
        type(localCharges, lCharges);
        type(interCharges, iCharges);
        type(smsCharges, sCharges);
    }

    public void submit() {
        try {
            click(By.name("submit"));
        } catch (TimeoutException e) {
            System.out.println("Submit button not found, maybe already clicked.");
        }
    }
}
