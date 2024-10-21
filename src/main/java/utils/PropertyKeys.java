package utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PropertyKeys {

    SERVICE_URL("service.url");

    @Getter
    private final String value;
}
