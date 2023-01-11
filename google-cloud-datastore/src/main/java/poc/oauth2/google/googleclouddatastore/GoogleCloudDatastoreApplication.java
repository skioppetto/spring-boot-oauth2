package poc.oauth2.google.googleclouddatastore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.google.cloud.spring.data.datastore.core.DatastoreTemplate;

@SpringBootApplication
@RestController
public class GoogleCloudDatastoreApplication {

	@Autowired
	DatastoreTemplate datastoreTemplate;

	public static void main(String[] args) {
		SpringApplication.run(GoogleCloudDatastoreApplication.class, args);
	}

	@GetMapping("/register-device/{deviceId}")
	public String registerDevice(@PathVariable String deviceId) {

		Device device = new Device(deviceId, "principal");
		datastoreTemplate.save(device);
		return "<h1>device " + deviceId + " was saved</h1>";
	}

	@GetMapping("/device/{deviceId}")
	public String getDevice(@PathVariable String deviceId) {

		Device device = datastoreTemplate.findById(deviceId, Device.class);
		if (null != device)
			return "<h1>device " + device.getDeviceId() + " was found :)</h1><p>the principal is: " + device.getPrincipal()
					+ "</p>";
		else
			return "<h1>device was not found :(</h1>";
	}

}
