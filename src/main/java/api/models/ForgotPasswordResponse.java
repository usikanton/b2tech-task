package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ForgotPasswordResponse {

    @JsonProperty("result")
    public Result result;
    @JsonProperty("data")
    public Object data;
    @JsonProperty("dataStructure")
    public Object dataStructure;
    @JsonProperty("additionalData")
    public Object additionalData;
    @JsonProperty("userInfo")
    public Object userInfo;
    @JsonProperty("isSuccessfull")
    public Boolean isSuccessfull;
}
