package upeu.mse_notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MseNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MseNotificationApplication.class, args);
	}

}
