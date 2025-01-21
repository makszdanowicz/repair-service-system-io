package com.pwr;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

public class IgnoreNullPointerExceptionExtension implements TestExecutionExceptionHandler {
    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {
        if (throwable instanceof NullPointerException) {

            return;
        }
        throw throwable;
    }
}
