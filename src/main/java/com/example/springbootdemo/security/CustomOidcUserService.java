package com.example.springbootdemo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomOidcUserService extends OidcUserService {

    Logger logger = LoggerFactory.getLogger(CustomOidcUserService.class);

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        Map<String, Object> attributes = oidcUser.getAttributes();

//        GoogleOAuth2UserInfo userInfo = new GoogleOAuth2UserInfo();
//        userInfo.setEmail((String) attributes.get("email"));
//        userInfo.setId((String) attributes.get("sub"));
//        userInfo.setImageUrl((String) attributes.get("picture"));
//        userInfo.setName((String) attributes.get("name"));
//        updateUser(userInfo);

        logger.info((String)attributes.get("email"));

        return oidcUser;
    }

//    private void updateUser(GoogleOAuth2UserInfo userInfo) {
//        User user = userRepository.findByEmail(userInfo.getEmail());
//        if(user == null) {
//            user = new User();
//        }
//        user.setEmail(userInfo.getEmail());
//        user.setImageUrl(userInfo.getImageUrl());
//        user.setName(userInfo.getName());
//        user.setUserType(UserType.google);
//        userRepository.save(user);
//    }
}
