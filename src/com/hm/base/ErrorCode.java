package com.hm.base;

public enum ErrorCode {

    OK(0) {
        @Override
        public String getDescription() {
            return "success";
        }
    },
    ERROR_A(100) {
        @Override
        public String getDescription() {
            return "error a";
        }
    },
    ERROR_B(100) {
        @Override
        public String getDescription() {
            return "error b";
        }
    };


    private int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public abstract String getDescription();

    public int getCode() {
        return code;
    }

    public static void main(String[] args) {
        for (ErrorCode errorCode : ErrorCode.values()) {
            System.out.println("code:" + errorCode.getCode() + ",description:" + errorCode.getDescription());
        }
    }
}