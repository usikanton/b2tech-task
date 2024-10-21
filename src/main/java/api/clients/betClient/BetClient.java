package api.clients.betClient;

import api.clients.Client;
import io.restassured.specification.RequestSpecification;
import utils.PropertyUtils;

import java.util.Objects;

public class BetClient extends Client {

    protected static ThreadLocal<RequestSpecification> requestSpecification = new ThreadLocal<>();
    protected static final String BASE_URI = PropertyUtils.getServiceUrl();

    private static void configureClient() {
        requestSpecification.set(generateDefaultBuilder()
                .setBaseUri(BASE_URI)
                .build()
                .given());
    }

    protected static RequestSpecification getRequestSpecification() {
        if (Objects.isNull(requestSpecification.get())) {
            configureClient();
        }
        return requestSpecification.get();
    }
}
