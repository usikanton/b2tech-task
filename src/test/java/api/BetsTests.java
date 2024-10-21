package api;

import api.controllers.betsController.BetsController;
import api.models.ForgotPasswordRequest;
import api.models.ForgotPasswordResponse;
import api.models.builders.BetBuilder;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BetsTests {

    public static final String FAILED_REQUEST = "Request for order failed";

    @Test
    public void createForgotPasswordRequestTest() {
        ForgotPasswordRequest forgotPasswordRequest = BetBuilder.generateForgotPasswordRequest();
        ForgotPasswordResponse forgotPasswordResponse = BetsController.forgotPasswordPOST(forgotPasswordRequest);
        verifyBookResponse(forgotPasswordResponse, forgotPasswordRequest);
    }

    private void verifyBookResponse(ForgotPasswordResponse forgotPasswordResponse, ForgotPasswordRequest forgotPasswordRequest) {
        assertThat(forgotPasswordResponse.getResult().getErrorDescription()).isEqualTo(FAILED_REQUEST);
        assertThat(forgotPasswordResponse.getIsSuccessfull()).isFalse();
    }
}
