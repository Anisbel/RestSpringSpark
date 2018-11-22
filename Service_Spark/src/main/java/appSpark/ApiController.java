package appSpark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api")
@Controller
public class ApiController {
    @Autowired
    Calculator calculator;

    @RequestMapping("produitFrequent")
    public ResponseEntity<String> produitFrequent() {
        return new ResponseEntity<>(calculator.count(), HttpStatus.OK);
    }
}
