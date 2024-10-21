package api.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ForgotPasswordRequest {

    @JsonProperty("notificationMethod")
    public Integer notificationMethod;
    @JsonProperty("notificationAlertType")
    public Integer notificationAlertType;
    @JsonProperty("userName")
    public String userName;
    @JsonProperty("phoneCountryCode")
    public String phoneCountryCode;
}
