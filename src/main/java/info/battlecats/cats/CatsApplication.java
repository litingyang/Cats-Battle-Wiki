package info.battlecats.cats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatsApplication.class, args);
	}
	@GetMapping("/")
	public  String apiRoot(){
		return "Cat Info";
	}
}
