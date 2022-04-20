package com.deosite.tests.features.checkout;

import com.deosite.tests.actions.Open;
import com.deosite.tests.pages.*;
import com.deosite.tests.steps.SetupSteps;
import com.deosite.tests.tasks.Setup;
import com.deosite.tests.tasks.basic.*;
import com.deosite.tests.tasks.mainMenu.ClickCategory;
import com.deosite.tests.tasks.order.ChooseDelivery;
import com.deosite.tests.tasks.order.FillInBillingData;
import com.deosite.tests.tasks.product.*;
import com.deosite.tests.tasks.order.Pay;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actions.*;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Steps;

import static com.deosite.tests.pages.CheckoutPage.EMAIL_INPUT;
import static com.deosite.tests.pages.MainMenu.CLOSE_NEWSLETTER_POPUP;
import static com.deosite.tests.pages.PaymentPage.TRANSFER_PAYMENT_CHECKBOX;
import static com.deosite.tests.pages.PaymentPage.PICKUP_PAYMENT_CHECKBOX;
import static com.deosite.tests.pages.ProductPage.OTHER_PRODUCTS_HEADING;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isPresent;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class RefreshPaymentPage {

    @Steps
    SetupSteps setupSteps;

    @Given("that {word} goes to the payment page as {word}")
    public void that_rebecca_goes_to_the_payment_page(String actor, String userType) {
        theActorCalled(actor).wasAbleTo(
                Setup.site(),
                ClickCategory.byCategoryNumber(2),
                Open.productPageByPosition(5),
                AddProduct.toCart(),
                Scroll.to(OTHER_PRODUCTS_HEADING),
                Scroll.to(MiniCart.MINICART_BUTTON),
                Open.miniCart(),
                Open.checkoutPage(),
                WaitUntil.the(EMAIL_INPUT, isPresent()).forNoMoreThan(50).seconds(),
                FillInBillingData.type(userType)
        );
    }

    @And("she proceeds to the payment page to pay using {word}")
    public void actor_proceeds_to_the_payment_page(String paymentType) {
        theActorInTheSpotlight().attemptsTo(
                Pay.by(paymentType)
        );
    }

    @When("she refreshes the page")
    public void actor_refreshes_the_page() {
        theActorInTheSpotlight().attemptsTo(
                RefreshPage.refresh(),
                WaitUntil.the(CLOSE_NEWSLETTER_POPUP, isPresent()),
                Click.on(CLOSE_NEWSLETTER_POPUP),
                WaitUntil.the(TRANSFER_PAYMENT_CHECKBOX, isPresent()).forNoMoreThan(100).seconds(),
                WaitUntil.the(TRANSFER_PAYMENT_CHECKBOX, isVisible())
        );
    }

    @Then("she should not see the cash on delivery option")
    public void actor_should_not_see_the_cash_on_delivery_option() {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(PaymentPage.PLACE_ORDER_BUTTON).isEnabled()
        );
    }
}
