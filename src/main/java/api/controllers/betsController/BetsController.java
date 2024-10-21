package api.controllers.betsController;

import api.clients.betClient.BetClient;
import api.models.ForgotPasswordResponse;
import io.restassured.http.Method;
import api.models.ForgotPasswordRequest;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.describedAs;
import static org.hamcrest.Matchers.is;

public class BetsController extends BetClient {

    private static final String BASE_PATH = "/Bet/placebetsport";

    public static ForgotPasswordResponse forgotPasswordPOST(ForgotPasswordRequest bookCreateRequest) {
        return given(getRequestSpecification())
                .basePath(BASE_PATH)
                .body(bookCreateRequest)
                .post()
                .then()
                .statusCode(describedAs(logMessage(BASE_URI, Method.POST, HttpStatus.SC_OK), is(HttpStatus.SC_OK)))
                .extract().as(ForgotPasswordResponse.class);
    }
}
