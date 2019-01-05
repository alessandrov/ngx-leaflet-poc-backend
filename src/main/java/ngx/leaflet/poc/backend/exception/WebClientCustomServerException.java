package ngx.leaflet.poc.backend.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.lang.Nullable;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.nio.charset.Charset;

public class WebClientCustomServerException extends WebClientResponseException {

    private final int statusCode;
    private final String statusText;
    private final String message;

    public WebClientCustomServerException(String message, int statusCode, String statusText, @Nullable HttpHeaders headers,
                                          @Nullable byte[] responseBody, @Nullable Charset responseCharset) {

        super(message, statusCode, statusText, headers, responseBody, responseCharset);

        this.message = message;
        this.statusCode = statusCode;
        this.statusText = statusText;
    }

}
