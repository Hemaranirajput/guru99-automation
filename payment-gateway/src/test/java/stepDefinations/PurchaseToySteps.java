package stepDefinations;


import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import static utils.TestContext.getExtentTest;


import com.aventstack.extentreports.Status;


import pages.*;
import utils.CardDetails;
import utils.TestContext;

public class PurchaseToySteps {

    WebDriver driver;
    WebDriverWait wait;
    
    PurchaseToyPage purchasePage;
    GenerateCardPage cardPage;
    PaymentPage paymentPage;
    PaymentResultPage resultPage;

    String cardNumber;
    String cvv;
    String expMonth;
    String expYear;
    
    public PurchaseToySteps(TestContext context) {
    
        this.driver = TestContext.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("I am on the Purchase Toy page")
    public void i_am_on_the_purchase_toy_page() {
    	getExtentTest().log(Status.INFO, "Opening the Purchase Toy page.");
        purchasePage = new PurchaseToyPage(driver);
        purchasePage.open();
    }


    @When("I set quantity to {string}")
    public void i_set_quantity_to(String qty) throws InterruptedException {
    	getExtentTest().log(Status.INFO, "Setting quantity to: " + qty);
    	System.out.println("Setting quantity to " + qty);
        purchasePage.enterQuantity(qty);
        Thread.sleep(2000);
        purchasePage.clickBuy();
        Thread.sleep(2000);
    }

    @When("I generate a valid card")
    public void i_generate_a_valid_card() {
    	 getExtentTest().log(Status.INFO, "Generating a new valid card.");
      
        purchasePage.clickGenerateCard();

        String mainWindow = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
     cardPage = new GenerateCardPage(driver);
        cardNumber = cardPage.getCardNumber();
        cvv = cardPage.getCvv();
        expMonth = cardPage.getExpMonth();
        System.out.println("Generated Month: [" + expMonth + "]");
        expYear = cardPage.getExpYear();
        TestContext.setCardDetails(new CardDetails(cardNumber, cvv, expMonth ,expYear));
        driver.close();
        driver.switchTo().window(mainWindow);
    
    }

    @When("I pay using the generated card")
    public void i_pay_using_the_generated_card() throws InterruptedException {
    	  getExtentTest().log(Status.INFO, "Initiating payment with the generated card.");
    	System.out.println("Paying with card");
    	CardDetails generatedCard = TestContext.getCardDetails();
    	String card = generatedCard.getCardNumber();    // e.g., 1234567890123456
        String cvv = generatedCard.getCvv();            // e.g., 123
        String mm = generatedCard.getExpMonth();
        String yy = generatedCard.getExpYear();      // e.g., "052028"

       

        // Now pay using separated values
        paymentPage = new PaymentPage(driver);
        paymentPage.payWith(card, cvv, mm, yy);
    }

    @Then("I should see a success message with an order ID")
    public void i_should_see_a_success_message_with_an_order_id() {
    	
        resultPage = new PaymentResultPage(driver);
        String orderId = resultPage.getOrderId();
        System.out.println("Order ID: " + orderId);
        getExtentTest().log(Status.PASS, "Payment successful. Order ID: " + orderId);
        Assert.assertNotNull(orderId, "Order ID should not be null");
        Assert.assertFalse(orderId.trim().isEmpty(), "Order ID should not be empty");
    }

    @When("I pay using an invalid card {string} {string} {string} {string}")
    public void iPayUsingInvalidCard(String card, String cvv, String mm, String yy) {
    	getExtentTest().log(Status.INFO, "Paying with invalid card: " + card);
    	
    	paymentPage = new PaymentPage(driver);
        paymentPage.enterCardDetails(card, mm, yy, cvv);
        paymentPage.clickPay();
    }

    @Then("I should see an alert with message {string}")
    public void iShouldSeeAnAlertWithMessage(String expectedMsg) {
    	 getExtentTest().log(Status.INFO, "Expecting alert message: " + expectedMsg);
        paymentPage.verifyAlertMessage(expectedMsg);
    }
}
