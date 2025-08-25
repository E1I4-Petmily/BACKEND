package e1i4.petmily.users.service;

import e1i4.petmily.users.domain.User;
import e1i4.petmily.users.domain.UserRole;
import e1i4.petmily.users.dto.CustomOAuth2User;
import e1i4.petmily.users.dto.GoogleResponse;
import e1i4.petmily.users.dto.OAuth2Response;
import e1i4.petmily.users.dto.UserDTO;
import e1i4.petmily.users.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("oAuth2User = " + oAuth2User); // 로그 확인용

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if(registrationId.equals("google")) {

            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        } else {

            return null;
        }

        String username = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();

        User existData = userRepository.findbyUsername(username);
        if(existData == null) {

            User user = new User();
            user.setUsername(username);
            user.setEmail(oAuth2Response.getEmail());
            user.setName(oAuth2User.getName());
            user.setUserRole(UserRole.ROLE_USER);

            userRepository.save(user);

            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(username);
            userDTO.setName(oAuth2Response.getName());
            userDTO.setRole(UserRole.ROLE_USER);

            return new CustomOAuth2User(userDTO);
        } else {

            existData.setEmail(oAuth2Response.getEmail());
            existData.setName(oAuth2Response.getName());

            userRepository.save(existData);

            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(existData.getUsername());
            userDTO.setName(oAuth2Response.getName());
            userDTO.setRole(existData.getUserRole());

            return new CustomOAuth2User(userDTO);
        }

    }
}
