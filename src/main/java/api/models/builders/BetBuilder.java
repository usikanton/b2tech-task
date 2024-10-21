package api.models.builders;

import api.models.ForgotPasswordRequest;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class BetBuilder {

    public static ForgotPasswordRequest generateForgotPasswordRequest() {
        Random random = new Random();
        return ForgotPasswordRequest.builder()
                .notificationMethod(2)
                .notificationAlertType(2)
                .userName(RandomStringUtils.randomNumeric(9))
                .phoneCountryCode(String.format("+%s", random.nextInt(1000)))
                .build();
    }
}
