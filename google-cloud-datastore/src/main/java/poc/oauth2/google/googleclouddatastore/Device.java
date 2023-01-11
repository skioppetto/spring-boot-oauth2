package poc.oauth2.google.googleclouddatastore;

import org.springframework.data.annotation.Id;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;

@Entity(name = "device")
public class Device {

   @Id
   private String deviceId;

   private String principal;

   public Device(String deviceId, String principal) {
      this.deviceId = deviceId;
      this.principal = principal;
   }

   public String getDeviceId() {
      return deviceId;
   }

   public void setDeviceId(String deviceId) {
      this.deviceId = deviceId;
   }

   public String getPrincipal() {
      return principal;
   }

   public void setPrincipal(String principal) {
      this.principal = principal;
   }

   
}
