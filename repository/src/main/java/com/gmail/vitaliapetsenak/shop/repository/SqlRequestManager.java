package com.gmail.vitaliapetsenak.shop.repository;

import java.util.ResourceBundle;

public class SqlRequestManager {
    public static String getRequest(String parameter) {
        return ResourceBundle.getBundle("SQLRequests").getString(parameter);
    }
}
