package com.training.microservices.birthday;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.training.microservices.birthday.models.BirthdayPerson;
import com.training.microservices.birthday.repositories.BirthdayRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RefreshScope // will refresh @Value and @Beans when config changes
public class BirthdayController {

    @Autowired
    private BirthdayRepository repository;

    @Autowired
    private EurekaClient client;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private BirthdayConfiguration properties;

    @Value("${some.other.property}")
    private String someOtherProperty;

    private static final Logger LOG = Logger.getLogger(BirthdayController.class.getName());

    @GetMapping("/config")
    public String printConfig() {
        StringBuilder sb = new StringBuilder();
        sb.append(properties.getProperty());
        sb.append(" || ");
        sb.append(someOtherProperty);
        return sb.toString();
    }

    @GetMapping("/birthday/{id}")
    public String getBirthday(@PathVariable Long id) {
        LOG.info("Get birthday for id:" + id);
        BirthdayPerson birthdayPerson = repository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return birthdayPerson.getBirthday();
    }

    @GetMapping("/birthday/{lastName}/{firstName}")
    public List<BirthdayPerson> getBirthday(@PathVariable String lastName, @PathVariable String firstName) {
        LOG.info("Get birthday for: " + firstName + " " + lastName);
        return repository.findByFirstNameAndLastName(firstName, lastName);
    }

    @PostMapping("/birthday")
    public BirthdayPerson createBirthdayChild(@RequestBody BirthdayPerson birthdayPerson) {
        LOG.info("Create new Birthday: " + birthdayPerson.toString());
        return repository.insert(birthdayPerson);
    }
  
    @GetMapping("/birthday/list")
    public BirthdayPerson[] getAllBirthdays() {
        LOG.info("Get a list of all birthdays");
        return repository.findAll().toArray(new BirthdayPerson[0]);
    }

    @PostMapping("/celebrate/{id}")
    public String celebrateBirthday(@PathVariable Long id) {
        LOG.info("Celebrate birthday of: " + id);

        Optional<BirthdayPerson> birthdayPerson = repository.findById(id);

        if (birthdayPerson.isPresent()) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            BirthdayPerson person = Objects.requireNonNull(birthdayPerson.stream().findFirst().orElse(null));
            JSONObject birthdayEventJSON = new JSONObject();
            birthdayEventJSON.put("firstName", person.getFirstName());
            birthdayEventJSON.put("lastName", person.getLastName());
            birthdayEventJSON.put("birthday", person.getBirthday());

            InstanceInfo instanceInfo = client.getNextServerFromEureka("notification", false);
            String baseUrl = instanceInfo.getHomePageUrl();

            HttpEntity<String> request = new HttpEntity<>(birthdayEventJSON.toString(), headers);

            return restTemplate.postForObject(baseUrl, request, String.class);
        } else {
            return "Sorry we cannot celebrate this birthday.";
        }
    }

    private List<HttpMessageConverter<?>> getJsonMessageConverters() {
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new MappingJackson2HttpMessageConverter());
        return converters;
    }
}
