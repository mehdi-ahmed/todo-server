package basicauth.Login;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginResource {

    @RequestMapping(method = RequestMethod.GET, path = "/basicauth", produces = MediaType.APPLICATION_JSON_VALUE)
    public String basicAuth() {
        return "OK";
    }
}
