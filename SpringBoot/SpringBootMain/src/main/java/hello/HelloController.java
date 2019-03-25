package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.Config.Repo;

@RestController
public class HelloController {

	private Repo repo1;
	private Repo repo2;
	
	@Autowired
	public HelloController(Repo repo1, Repo repo2) {
		this.repo1 = repo1;
		this.repo2 = repo2;
	}
	
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}