package com.choucair.utils;

public enum ReqresResources {
    API_BASE_URL("https://dummy.restapiexample.com/api/v1/"),
    GET_EMPLEADO_RESOURCE("employee/"),
    POST_EMPLEADO_RESOURCE("create"),
    DELETE_EMPLEADO_RESOURCE("delete/"),
    PUT_EMPLEADO_RESOURCE("update/");

    private final String  value;

    ReqresResources(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
