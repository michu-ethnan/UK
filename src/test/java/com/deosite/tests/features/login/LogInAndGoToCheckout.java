package com.deosite.tests.features.login;

import com.deosite.tests.actions.Open;
import com.deosite.tests.pages.*;

import com.deosite.tests.steps.SetupSteps;
import com.deosite.tests.tasks.Setup;
import com.deosite.tests.tasks.basic.MoveMouseToTop;
import com.deosite.tests.tasks.basic.ReturnToPreviousPage;
import com.deosite.tests.tasks.login.FillInLoginForm;
import com.deosite.tests.tasks.login.SubmitLoginForm;
import com.deosite.tests.tasks.mainMenu.ClickCategory;
import com.deosite.tests.tasks.order.ChooseDelivery;
import com.deosite.tests.tasks.order.FillInBillingData;
import com.deosite.tests.tasks.order.PayAfterLogin;
import com.deosite.tests.tasks.product.AddProduct;
import com.deosite.tests.tasks.product.MoveMouseDown;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.MoveMouse;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Steps;

import static com.deosite.tests.abilities.Load.as;
import static com.deosite.tests.pages.LoginPage.LOGIN_BUTTON;
import static com.deosite.tests.pages.LoginPage.SUBMIT_BUTTON;
import static com.deosite.tests.pages.MainMenu.*;
import static com.deosite.tests.pages.MiniCart.GO_TO_CHECKOUT_BUTTON;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class LogInAndGoToCheckout {

    @Steps
    SetupSteps setupSteps;

    @Given("that {word} has a product in the minicart")
    public void that_actor_has_a_product_in_the_minicart(String actor) {
        theActorCalled(actor).wasAbleTo(
                Setup.site(),
                ClickCategory.byCategoryNumber(0),
                Open.productPageByPosition(0),
                AddProduct.toCart(),
                MoveMouseDown.move(),
                ReturnToPreviousPage.goToPreviousPage()
        );
    }

    @When("he logs in")
    public void actor_logs_in() {
        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(LOGIN_BUTTON, isClickable()).forNoMoreThan(100).seconds(),
                Click.on(LOGIN_BUTTON),
                FillInLoginForm.type("login"),
                SubmitLoginForm.submitLoginForm(),
                WaitUntil.the(SUBMIT_BUTTON, isNotPresent()).forNoMoreThan(10).seconds()
        );
    }

    @And("he goes to the checkout page using {word} delivery and {word}")
    public void actor_goes_to_the_checkout_page_with_product(String deliveryType, String paymentType) {
        theActorInTheSpotlight().attemptsTo(
                MoveMouseToTop.move(),
                Click.on(MINI_CART_BUTTON),
                WaitUntil.the(GO_TO_CHECKOUT_BUTTON, isClickable()),
                Click.on(GO_TO_CHECKOUT_BUTTON),
                WaitUntil.the(CheckoutPage.DELIVERY_TYPE_COURIER, isPresent()).forNoMoreThan(100).seconds(),
                ChooseDelivery.byType(deliveryType),
                PayAfterLogin.by(paymentType)
        );
    }

    @Then("he should see that he is on payment page")
    public void actor_should_see_that_he_is_on_payment_page() {
        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(PaymentPage.PLACE_ORDER_BUTTON, isPresent()),
                Ensure.that(PaymentPage.PLACE_ORDER_BUTTON).isDisplayed(),
                Click.on(PaymentPage.DELETE_PRODUCT_BUTTON_ON_PAYMENT_PAGE_AFTER_ORDER)
        );
    }

}
