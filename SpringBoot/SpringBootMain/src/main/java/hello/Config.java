package hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan("com.baeldung.spring")
public class Config {
 
	public static class Repo{
		public String name = "";
	}
	
	
    @Bean
    public Repo repo1() {
    	Repo r = new Repo();
    	r.name = "string";
        return r;
    }

    @Bean
    public Repo repo2() {
    	Repo r = new Repo();
    	r.name = "string2";
        return r;
    }
}