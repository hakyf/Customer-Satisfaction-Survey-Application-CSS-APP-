package id.co.mii.clientapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientappApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientappApplication.class, args);

		System.out.println("----------------------------------");
		System.out.println("Client-App is Running . . .");
		System.out.println("----------------------------------");
	}

}
