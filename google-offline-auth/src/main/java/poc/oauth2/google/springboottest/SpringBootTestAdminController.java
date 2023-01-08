package poc.oauth2.google.springboottest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class SpringBootTestAdminController {

	@Autowired
	OAuth2AuthorizedClientService oauth2Service;

	@GetMapping("/")
	public String testHome(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient authorizedClient) {
		if (null != authorizedClient) {

			String html = "<h1>Hello World! " + authorizedClient.getPrincipalName() +
					"</h1><p>your token is " + authorizedClient.getAccessToken().getTokenValue() + "</p>" +
					"<p>yout clientRegistrationId is " + authorizedClient.getClientRegistration().getRegistrationId()
					+ "</p>" +
					"<p>your refresh token is " + authorizedClient.getRefreshToken() + "</p>" +
					"<p>and will expire at " + authorizedClient.getAccessToken().getExpiresAt() + "</p>" +
					"<h2>scopes: " + authorizedClient.getAccessToken().getScopes().size() + "</h2>";
			for (String scope : authorizedClient.getAccessToken().getScopes()) {
				html = html + "<p>" + scope + "</p>";
			}
			return html;
		} else
			return "<h1>Authorized Client not found :(</h1>";
	}

	@GetMapping("/register-device/{deviceId}")
	public String registerDevice(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient authorizedClient,
			@PathVariable String deviceId) {
		return "<h1> principal: " + authorizedClient.getPrincipalName() + "</h1><p>new device registration request for: "
				+ deviceId + "</p>";
	}

}
