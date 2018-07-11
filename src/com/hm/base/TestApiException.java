package com.hm.base;

import com.hm.pattern.singleton.Singleton;

/**
 * Created by dumingwei on 2018/5/31 0031.
 */
public class TestApiException {

    public static void main(String[] args) {
        ApiException apiException = new ApiException("message", 3);
        Throwable throwable = apiException;

        System.out.println(throwable instanceof ApiException);
        System.out.println(((ApiException) throwable).getCode());
    }

    public static class ApiException extends Exception {

        private int code;

        public ApiException(String message, int code) {
            super(message);
            this.code = code;
        }

        public int getCode() {
            return code;
        }

    }
}
