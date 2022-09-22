package com.creativeminds.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@SpringBootApplication (exclude = {SecurityAutoConfiguration.class})
public class AppApplication {

//	@GetMapping("/")
//	public String home(){
//		return "Bienvenidos a la app de Creative Minds" +
//				"<br> <img src=\"https://miviaje.com/wp-content/uploads/2018/09/cano-cristales.jpg\" alt=\"modelado\">";
//	}


	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}
