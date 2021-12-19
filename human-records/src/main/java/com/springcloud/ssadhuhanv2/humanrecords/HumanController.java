package com.springcloud.ssadhuhanv2.humanrecords;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


@Slf4j
@RestController
public class HumanController {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    HumanRepo humanRepo;

    // Proxy Feign Client
    @Autowired
    DateUtilsProxy dateUtilsFeignProxy;

    @GetMapping("/human")
    public List<Human> getAllHuman() {
        return humanRepo.findAll();
    }


    @GetMapping("/human/{humanId}")
    public ResponseEntity<EntityModel<Human>> getHuman(@PathVariable(name = "humanId") Long id) {
        try {
            log.info("Id of Human: {}", id);
            Human human = humanRepo.findById(id).get();
            log.info("Human Found: {}", human);
            //Add a link to all Humans
            EntityModel<Human> resource = EntityModel.of(human);
            WebMvcLinkBuilder webMvcLinkBuilder =
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                            .getAllHuman());
            resource.add(webMvcLinkBuilder.withRel("all-humans"));

            return new ResponseEntity<>(resource, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            //Do something
            throw new RuntimeException();
        }
    }

    //    @GetMapping("/human/{humanId}/age")
//    public ResponseEntity<EntityModel<Age>> getAgeOfHuman(@PathVariable(name = "humanId") Long id) {
//        try {
//            Human human = humanRepo.findById(id).get();
//
//            // how to send request param in resttemplate
//            //https://stackoverflow.com/questions/8297215/spring-resttemplate-get-with-parameters
//            String uriTemplate = UriComponentsBuilder.fromHttpUrl("http://localhost:9001/age").queryParam("date", "{birthDay}").encode().toUriString();
//            Map<String, String> params = new HashMap<>();
//            params.put("birthDay", human.getBirthDay());
//            Age age = restTemplate.getForObject(uriTemplate, Age.class, params);
//
//            //Add a link to the Human
//
//            EntityModel<Age> resource = EntityModel.of(age);
//            WebMvcLinkBuilder webMvcLinkBuilder =
//                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
//                            .getHuman(id));
//            resource.add(webMvcLinkBuilder.withRel("human"));
//
//            return new ResponseEntity<>(resource, HttpStatus.CREATED);
//        } catch (NoSuchElementException e) {
//            //Do something
//            throw new RuntimeException();
//        }
//    }
    @GetMapping("/human/{humanId}/age")
    public ResponseEntity<EntityModel<Age>> getAgeOfHuman(@PathVariable(name = "humanId") Long id) {
        try {
            log.info("Id of Human: {}", id);
            Human human = humanRepo.findById(id).get();
            log.info("Human Found: {}", human);
            //Using Feign Client to call instead of Rest Template

            Age age = dateUtilsFeignProxy.getDifferenceWithSystemDate(human.getBirthDay(), null);

            log.info("Age of Human: {}", age);

            //Add a link to the Human

            EntityModel<Age> resource = EntityModel.of(age);
            WebMvcLinkBuilder webMvcLinkBuilder =
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                            .getHuman(id));
            resource.add(webMvcLinkBuilder.withRel("human"));

            return new ResponseEntity<>(resource, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            //Do something
            throw new RuntimeException();
        }
    }
}
