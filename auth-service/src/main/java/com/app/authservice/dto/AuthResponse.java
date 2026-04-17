//package com.app.authservice.dto;
//
//public class AuthResponse {
//
//    private String token;
//
//    public AuthResponse(String token) {
//        this.token = token;
//    }
//
//    public String getToken() {
//        return token;
//    }
//}

package com.app.authservice.dto;

public class AuthResponse {

    private String token;
    private String name;

    public AuthResponse(String token, String name) {
        this.token = token;
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public String getName() {
        return name;
    }
}