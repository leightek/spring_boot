import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@EnableAutoConfiguration(exclude = [ActiveMQAutoConfiguration.class])
class WebApp{
    @GetMapping("/")
    String welcome(){
        "<h1><font fact='verdana'>Spring Boot Rocks!</font></h1>"
    }
}