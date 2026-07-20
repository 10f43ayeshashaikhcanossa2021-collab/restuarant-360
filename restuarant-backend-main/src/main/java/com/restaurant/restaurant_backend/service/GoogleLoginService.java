package com.restaurant.restaurant_backend.service;

import java.util.Collections;

import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

@Service
public class GoogleLoginService {

    public GoogleIdToken.Payload verify(String idTokenString) {

        try {

            GoogleIdTokenVerifier verifier =
                    new GoogleIdTokenVerifier.Builder(
                            GoogleNetHttpTransport.newTrustedTransport(),
                            GsonFactory.getDefaultInstance())
                            .setAudience(Collections.singletonList(
                                    "YOUR_GOOGLE_CLIENT_ID"))
                            .build();

            GoogleIdToken idToken =
                    verifier.verify(idTokenString);

            if (idToken != null) {
                return idToken.getPayload();
            }

            return null;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}