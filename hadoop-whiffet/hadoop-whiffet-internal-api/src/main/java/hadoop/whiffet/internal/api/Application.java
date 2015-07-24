package hadoop.whiffet.internal.api;



import java.util.concurrent.TimeUnit;



import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;




@Configurable
@ComponentScan(basePackages="hadoop.whiffet")
@EnableAutoConfiguration
@SpringBootApplication
public class Application {

	private static int port=7777;
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
	        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
	        factory.setPort(port);
	        
	        factory.setSessionTimeout(10, TimeUnit.MINUTES);
	        //factory.addErrorPages(new ErrorPage(HttpStatus.404, "/notfound.html"));
	        return factory;
	    }
	
	public static void main(String[] args) {
		if(args!=null&&args.length==1){
			port=Integer.parseInt(args[0]);
		}
		
		SpringApplication.run(Application.class, args);
	}
	
}
