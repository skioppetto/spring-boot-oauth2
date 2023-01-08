package poc.oauth2.google.springboottest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class CustomOauth2SecureConfig {

      @Autowired
      private ClientRegistrationRepository clientRegistrationRepository;

      @Bean
      public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http

                        .authorizeHttpRequests(authorize -> authorize
                                    .mvcMatchers("/admin/**").authenticated()
                                    .mvcMatchers("/device/**").permitAll()
                                    .anyRequest().denyAll())
                        .oauth2Login(oauth2 -> oauth2.authorizationEndpoint(
                                    authorization -> authorization
                                                .authorizationRequestResolver(authorizationRequestResolver(
                                                            this.clientRegistrationRepository))));

            return http.build();
      }

      private OAuth2AuthorizationRequestResolver authorizationRequestResolver(
                  ClientRegistrationRepository clientRegistrationRepository) {
            DefaultOAuth2AuthorizationRequestResolver authorizationRequestResolver = new DefaultOAuth2AuthorizationRequestResolver(
                        clientRegistrationRepository,
                        "/oauth2/authorization");
            authorizationRequestResolver.setAuthorizationRequestCustomizer(
                        customizer -> customizer.additionalParameters(params -> params.put("access_type", "offline")));
            return authorizationRequestResolver;
      }

}
