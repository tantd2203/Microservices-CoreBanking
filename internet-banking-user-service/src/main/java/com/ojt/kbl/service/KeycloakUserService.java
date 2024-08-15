package com.ojt.kbl.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeycloakUserService {
 private final Keycloak keycloak;
    @Value("${app.keycloak.realm}")
    private String realm;
    public List<UserRepresentation> readUserByEmail(String email) {
        return keycloak.realm(realm).users().searchByEmail(email,true);
    }
}
