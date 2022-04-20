package com.deosite.tests.i18n;

import com.deosite.tests.model.account.NewAddressForm;
import com.deosite.tests.model.login.LoginForm;
import com.deosite.tests.model.login.DifferentLoginForm;
import com.deosite.tests.model.order.Personal;
import com.deosite.tests.model.order.Company;
import com.deosite.tests.model.order.WithoutEmailAddress;
import com.deosite.tests.model.register.RegisterForm;

import java.util.ListResourceBundle;

public class Bundle_uk_UK extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                // Labels
                {"capacity (l) label", "pojemność (l)"},
                {"capacity (ml) label", "pojemność (ml)"},

                // Products
                {"mug", "Mug"},


                {"configurable product", "kubek palette"},

                // Number of products in the cart
                {"greater", "Your cart (2)"},
                {"smaller", "Your cart (1)"},

                // Categories

                //Headers
                {"account header", "Account"},
                {"Ogród header", "Garden & Outdoors "},
                {"Jadalnia header", "Dining room "},
                {"Kuchnia header", "Kitchen "},
                {"Łazienka i garderoba header", "Cleanliness & Tidiness "},
                {"Dekoracje header", "Textiles "},
                {"Świat dziecka header", "Children's World "},
                {"Nowości header", "New "},
                {"Wyprzedaż header", "Sale "},
                {"Marki header", "Brands"},



                // Alerts
                {"added to cart", "Il prodotto è stato aggiunto al carrello"},
                {"address saved", "Dirección guardada"},
                {"address deleted", "Address deleted"},

                // Messages
                {"success order message", "Dziękujemy, Twoje zamówienie zostało złożone!"},
                {"additional success info", "Zamówienie jest kompletowane, o wysyłce poinformujemy drogą mailową."},
                {"the cart is empty message", "No products in cart"},
                {"login button text", "Log in"},
                // Objects
                {"person", new Personal(
                        "test@test.pl",
                        "Test",
                        "Test-Test",
                        "Testowa 1",
                        "Testowo",
                        "United Kingdom",
                        "11-111",
                        "123456789"
                )},
                {"company", new Company(
                        "test@test.pl",
                        "Test",
                        "Test-Test",
                        "Testowa 1",
                        "Testowo",
                        "United Kingdom",
                        "11-111",
                        "123456789",
                        "Testowa sp. z o.o",
                        "1234567891"
                )},
                {"withoutEmailAddress", new WithoutEmailAddress(
                        "Test",
                        "Test-Test",
                        "Test 1",
                        "Test",
                        "United Kingdom",
                        "00-000",
                        "0000000000"
                )},
                {"login", new LoginForm(
                        "tester-email-tester@yandex.com",
                        "Test12345!"
                )},
                {"login to order_1", new LoginForm(
                        "abisimeon22@yandex.com",
                        "Test12345!"
                )},
                {"login to order_2", new LoginForm(
                        "abisimeon222@yandex.com",
                        "Test12345!"
                )},
                {"login to order_3", new LoginForm(
                        "abisimeon223@yandex.com",
                        "Test12345!"
                )},
                {"login to see order", new LoginForm(
                        "tekasimeon00@gmail.com",
                        "Test12345!"
                )},
                {"differentEmail", new DifferentLoginForm(
                        "abisimeon223@yandex.com",
                        "Test12345!"
                )
                },
                {"newAddress", new NewAddressForm(
                        "Salomea",
                        "Kowalska",
                        "Testowa 1",
                        "Testowo",
                        "00-000",
                        "0000000000"
                )},
                {"register", new RegisterForm(
                        "Test12345!",
                        "Test",
                        "Test-Test"
                )}
        };
    }
}
