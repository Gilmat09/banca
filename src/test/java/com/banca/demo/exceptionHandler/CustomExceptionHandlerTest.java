package com.banca.demo.exceptionHandler;

import com.banca.demo.exceptionHandlers.CustomExceptionHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.LinkedHashMap;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class CustomExceptionHandlerTest {

    private CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler();


    @Test
    void testHandleRequestValidationExceptionBadRequest() {
        Exception e = new Exception("message");
        HttpServletRequest request = mock(HttpServletRequest.class);
        LinkedHashMap<String, Object> ret = (LinkedHashMap<String, Object>) customExceptionHandler.handleRequestValidationException(e, request);

        Assertions.assertEquals(ret.get("status"), HttpStatus.BAD_REQUEST.value());
    }

}
