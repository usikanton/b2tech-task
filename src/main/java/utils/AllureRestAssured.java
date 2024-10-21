package utils;

import io.qameta.allure.attachment.DefaultAttachmentProcessor;
import io.qameta.allure.attachment.FreemarkerAttachmentRenderer;
import io.qameta.allure.attachment.http.HttpRequestAttachment;
import io.qameta.allure.attachment.http.HttpResponseAttachment;
import io.restassured.filter.FilterContext;
import io.restassured.filter.OrderedFilter;
import io.restassured.internal.NameAndValue;
import io.restassured.internal.support.Prettifier;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AllureRestAssured implements OrderedFilter {

    private final String requestTemplate = "http-request.ftl";
    private final String responseTemplate = "http-response.ftl";

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Response filter(final FilterableRequestSpecification requestSpec, final FilterableResponseSpecification responseSpec,
                           final FilterContext ctx) {
        final Prettifier prettifier = new Prettifier();
        final HttpRequestAttachment.Builder requestAttachmentBuilder = HttpRequestAttachment.Builder.create(
                String.format("Request : %s to %s", requestSpec.getMethod(), requestSpec.getBasePath()), requestSpec.getBaseUri())
                .setMethod(requestSpec.getMethod())
                .setHeaders(convertToMap(requestSpec.getHeaders()))
                .setCookies(convertToMap(requestSpec.getCookies()));

        if (Objects.nonNull(requestSpec.getBody()))
            requestAttachmentBuilder.setBody(prettifier.getPrettifiedBodyIfPossible(requestSpec));

        final HttpRequestAttachment requestAttachment = requestAttachmentBuilder.build();

        new DefaultAttachmentProcessor().addAttachment(requestAttachment, new FreemarkerAttachmentRenderer(requestTemplate));

        final Response response = ctx.next(requestSpec, responseSpec);
        final HttpResponseAttachment responseAttachment = HttpResponseAttachment.Builder.create(response.getStatusLine())
                .setResponseCode(response.getStatusCode())
                .setHeaders(convertToMap(response.getHeaders()))
                .setBody(prettifier.getPrettifiedBodyIfPossible(response, response.getBody())).build();

        new DefaultAttachmentProcessor().addAttachment(responseAttachment, new FreemarkerAttachmentRenderer(responseTemplate));
        return response;
    }

    private Map<String, String> convertToMap(final Iterable<? extends NameAndValue> iterables) {
        final Map<String, String> result = new HashMap<>();
        iterables.forEach(i -> result.put(i.getName(), i.getValue()));
        return result;
    }
}
