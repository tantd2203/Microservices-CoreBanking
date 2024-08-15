package com.ojt.kbl.service.impl;

import com.ojt.kbl.dto.NewUserRecord;
import com.ojt.kbl.service.UserService;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    @Value("${app.keycloak.realm}")
    private String realm;
    private final Keycloak keycloak;

    /*
     * @author: TanTD1
     * @since: 8/15/2024 10:33 AM
     * @description:  create user in keycloak then verify email

     * */
    @Override
    public void createUser(NewUserRecord newUserRecord) {

        // user entity in keycloak
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(newUserRecord.username());
        userRepresentation.setFirstName(newUserRecord.firstName());
        userRepresentation.setLastName(newUserRecord.lastName());
        userRepresentation.setEmail(newUserRecord.email());
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(false);


        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue(newUserRecord.password());
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);

        userRepresentation.setCredentials(List.of(credentialRepresentation));

        UsersResource usersResource = getUsersResource();

        Response response = usersResource.create(userRepresentation);

        log.info("Status Code " + response.getStatus());

        if (!Objects.equals(201, response.getStatus())) {

            throw new RuntimeException("Status code " + response.getStatus());
        }

        log.info("New user has bee created");

        List<UserRepresentation> userRepresentationListCurrent = usersResource.searchByUsername(newUserRecord.username(), true);
        UserRepresentation userRepresentationCurrent = userRepresentationListCurrent.get(0);
        sendVerificationEmail(userRepresentationCurrent.getId());


    }

    @Override
    public void sendVerificationEmail(String id) {
        UsersResource usersResource = getUsersResource();
        usersResource.get(id).sendVerifyEmail();

    }

    @Override
    public void deleteUser(String userId) {
        UsersResource userResource = getUsersResource();
        userResource.delete(userId);
    }

    @Override
    public void forgotPassword(String username) {
        UsersResource usersResource = getUsersResource();
        List<UserRepresentation> userRepresentations = usersResource.searchByUsername(username, true);
        UserRepresentation userRepresentation1 = userRepresentations.get(0);
        UserResource userResource = usersResource.get(userRepresentation1.getId());
        userResource.executeActionsEmail(List.of("UPDATE_PASSWORD"));



    }

    private UsersResource getUsersResource() {
        return keycloak.realm(realm).users();
    }

}
