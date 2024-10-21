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
public class Result {

    @JsonProperty("errorDescription")
    public Object errorDescription;
    @JsonProperty("additionalInfo")
    public Object additionalInfo;
    @JsonProperty("eventData")
    public Object eventData;
    @JsonProperty("closedOdds")
    public Object closedOdds;
    @JsonProperty("errorCode")
    public Integer errorCode;
    @JsonProperty("resultCode")
    public Integer resultCode;
    @JsonProperty("errorCodeDescription")
    public String errorCodeDescription;
}
