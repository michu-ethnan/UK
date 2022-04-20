package com.deosite.tests.features.checkout;

import com.deosite.tests.actions.Open;
import com.deosite.tests.actions.OpenProductPage;
import com.deosite.tests.pages.*;
import com.deosite.tests.questions.product.Product;
import com.deosite.tests.questions.product.ProductName;
import com.deosite.tests.steps.SetupSteps;
import com.deosite.tests.tasks.Setup;
import com.deosite.tests.tasks.basic.MoveMouseToTop;
import com.deosite.tests.tasks.basic.RefreshPage;
import com.deosite.tests.tasks.basic.ReturnToPreviousPage;
import com.deosite.tests.tasks.mainMenu.ClickCategory;
import com.deosite.tests.tasks.order.FillInBillingData;
import com.deosite.tests.tasks.product.AddProduct;
import com.deosite.tests.tasks.product.AddToCart;
import com.deosite.tests.tasks.product.MoveMouseDown;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_scouse.An;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.MoveMouse;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Steps;
import org.apache.bcel.generic.MONITORENTER;

import static com.deosite.tests.pages.CategoryPage.CATEGORY_HEADER;
import static com.deosite.tests.pages.CheckoutPage.EMAIL_INPUT;
import static com.deosite.tests.pages.MainMenu.*;
import static com.deosite.tests.pages.MiniCart.GO_TO_CHECKOUT_BUTTON;
import static com.deosite.tests.pages.ProductPage.ADD_TO_CART_BUTTON;
import static com.deosite.tests.pages.ProductPage.OTHER_PRODUCTS_HEADING;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isPresent;

public class AddTwoDifferentProductsAndGoToCheckout {

    @Steps
    SetupSteps setupSteps;
    String firstProduct;
    String secondProduct;


    @Given("that {word} happens to be on the first category page")
    public void abel_is_on_the_first_category_page(String actor){
        theActorCalled(actor).wasAbleTo(
                Setup.site(),
                ClickCategory.byCategoryNumber(4)
        );

    }
    @And("he adds the first product to cart")
    public void actor_adda_the_first_product_to_cart(){
        theActorInTheSpotlight().attemptsTo(
                Open.productPageByPosition(0),
                AddProduct.toCart(),
                Click.on(Alert.CLOSE_ALERT_BOX_BUTTON)
        );

    }
    @When("he returns to the previous category page")
    public void actor_returns_to_the_previous_page(){
        theActorInTheSpotlight().attemptsTo(
                ReturnToPreviousPage.goToPreviousPage(),
                WaitUntil.the(SEARCH_BAR, isPresent())
        );
    }
    @And("he adds another product to the cart and goes to checkout")
    public void actor_adds_another_product_to_cart_and_goes_to_checkout(){
        theActorInTheSpotlight().attemptsTo(
                Open.productPageByPosition(7),
                Click.on(ADD_TO_CART_BUTTON),
                WaitUntil.the(Alert.ALERT_BOX, isPresent()),
                Click.on(Alert.CLOSE_ALERT_BOX_BUTTON),
                Scroll.to(OTHER_PRODUCTS_HEADING),
                Scroll.to(MINI_CART_BUTTON),
                Click.on(MINI_CART_BUTTON),
                WaitUntil.the(GO_TO_CHECKOUT_BUTTON, isClickable()),
                Click.on(GO_TO_CHECKOUT_BUTTON),
                WaitUntil.the(EMAIL_INPUT, isPresent())
        );

    }
    @And("he adds another product to the cart from another category and goes to checkout")
    public void actor_adds_another_product_to_cart_from_different_category_and_goes_to_checkout() {
        theActorInTheSpotlight().attemptsTo(
                RefreshPage.refresh(),
                WaitUntil.the(CLOSE_NEWSLETTER_POPUP, isPresent()),
                Click.on(CLOSE_NEWSLETTER_POPUP),
                WaitUntil.the(FIRST_MAIN_CATEGORY, isPresent()),
                ClickCategory.byCategoryNumber(4),
                WaitUntil.the(CATEGORY_HEADER, isPresent()),
                Open.productPageByPosition(6),
                Click.on(ADD_TO_CART_BUTTON),
                WaitUntil.the(Alert.ALERT_BOX, isPresent()),
                Click.on(Alert.CLOSE_ALERT_BOX_BUTTON),
                Scroll.to(OTHER_PRODUCTS_HEADING),
                Scroll.to(MINI_CART_BUTTON),
                Click.on(MINI_CART_BUTTON),
                WaitUntil.the(GO_TO_CHECKOUT_BUTTON, isClickable()),
                Click.on(GO_TO_CHECKOUT_BUTTON),
                WaitUntil.the(EMAIL_INPUT, isPresent())
        );
    }
    @Then("he should see that he is on the payment page as a {word}")
    public void actor_should_see_that_he_is_on_checkout_page(String userType){
        theActorInTheSpotlight().attemptsTo(
                FillInBillingData.type(userType),
                Click.on(CheckoutPage.SUBMIT_BUTTON),
                WaitUntil.the(PaymentPage.PLACE_ORDER_BUTTON, isPresent()),
                Ensure.that(PaymentPage.PLACE_ORDER_BUTTON).isDisplayed()
        );

    }
}
