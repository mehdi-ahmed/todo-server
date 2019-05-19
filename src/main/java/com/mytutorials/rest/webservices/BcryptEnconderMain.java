package com.mytutorials.rest.webservices;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptEnconderMain {

    public static void main(String[] args) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


        for (int i = 0; i <= 10; i++) {
            String encoderPwd = passwordEncoder.encode("isThisReal123_");
            System.out.println(encoderPwd);

        }

    }
}
