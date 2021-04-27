package com.test.shop;

import com.test.shop.models.Product;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class TestRemoveProduct {

    @Test
    public void testDeleteProductShouldReturnSuccessAndProductInfo() {
        Product response = given()
                .baseUri("https://6082ebcc5dbd2c001757ad03.mockapi.io")
                .when().delete("/api/v1/product/59")
                .then()
                .statusCode(200)
                .extract().as(Product.class);

        assertThat(response).extracting(Product::getId).isEqualTo("59");
    }
}
