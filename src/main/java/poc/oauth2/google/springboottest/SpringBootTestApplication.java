package poc.oauth2.google.springboottest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTestApplication.class, args);
	}

	@GetMapping("/")
	public String testHome(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient authorizedClient) {
		if (null != authorizedClient)
			return "<h1>Hello World! " + authorizedClient.getPrincipalName() +
			"</h1><p>your token is "+ authorizedClient.getAccessToken().getTokenValue()+"</p>" +
			"<p>and will expire at " + authorizedClient.getAccessToken().getExpiresAt()+"</p>";
		else
			return "<h1>Authorized Client not found :(</h1>";
	}

}
