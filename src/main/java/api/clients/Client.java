package api.clients;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import utils.AllureRestAssured;

public abstract class Client {

    private static RestAssuredConfig generateDefaultConfig() {
        HttpClientConfig httpClientConfig = HttpClientConfig.httpClientConfig()
                .setParam("http.connection.timeout", 300000)
                .setParam("http.connection.socket", 300000);
        ObjectMapperConfig objectMapperConfig = ObjectMapperConfig.objectMapperConfig()
                .jackson2ObjectMapperFactory((_class, s) -> JsonMapper.builder()
                        .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
                        .build());
        return RestAssuredConfig.config()
                .httpClient(httpClientConfig)
                .objectMapperConfig(objectMapperConfig);
    }

    protected static RequestSpecBuilder generateDefaultBuilder() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setConfig(generateDefaultConfig())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addFilter(new AllureRestAssured());
        return requestSpecBuilder;
    }

    protected static String logMessage(String baseUri, Method method, int statusCode) {
        return String.format("%s. %s request to %s failed due to incorrect status code", statusCode, method.name(), baseUri);
    }
}
