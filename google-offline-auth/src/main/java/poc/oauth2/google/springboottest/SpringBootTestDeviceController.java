package poc.oauth2.google.springboottest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/device")
public class SpringBootTestDeviceController {

	@Autowired
	OAuth2AuthorizedClientService oauth2Service;

	@GetMapping("/")
	public String testHome() {
		String principal = "108499947294924205917";
		String welcome = "<h1>Anibody can access</h1>";
		OAuth2AuthorizedClient authorized = oauth2Service.loadAuthorizedClient("google", principal);
		if (null != authorized)
			return welcome + "<p>:) token found: "
					+ authorized.getAccessToken().getTokenValue() + "</p>";
		else
			return welcome + "<p>Cannot find the authorized principal " + principal + "</p>";
	}

}
