package com.test.shop;

import com.test.shop.api.RequestBuilder;
import com.test.shop.models.Product;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class TestGetProductsListPositive {
    private static final RequestSpecification SPEC = new RequestSpecBuilder()
            .setBaseUri("https://6082ebcc5dbd2c001757ad03.mockapi.io")
            .setBasePath("/api/v1/product")
            .setContentType(ContentType.JSON)
            .build();

    @BeforeMethod
    public void testCreateProductsShouldReturnProductInfo() {
        RequestBuilder request = RequestBuilder.builder()
                .name("test5")
                .price(50)
                .quantity(100)
                .available(true)
                .build();

        Product response = given()
                .spec(SPEC)
                .body(request)
                .when().post()
                .then().extract().as(Product.class);

        assertThat(response)
                .isNotNull().extracting(Product::getName).isEqualTo(request.getName());

    }

    @AfterMethod
    public void testDeleteProductShouldReturnSuccessAndProductInfo() {
        Product response = given()
                .baseUri("https://6082ebcc5dbd2c001757ad03.mockapi.io")
                .when().delete("/api/v1/product/59")
                .then()
                .statusCode(200)
                .extract().as(Product.class);

        assertThat(response).extracting(Product::getId).isEqualTo("59");
    }

    @Test
    public void testGetProductsListShouldReturnAllProducts() {
        List <Product> res = given()
                .baseUri("https://6082ebcc5dbd2c001757ad03.mockapi.io")
                .when().get("/api/v1/product")
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("", Product.class);

        assertThat(res).extracting(Product::getId).contains("59");
    }
}
