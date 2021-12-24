package com.springcloud.ssadhuhanv2.humanrecords;


import com.springcloud.ssadhuhanv2.humanrecords.client.DateUtilsFeignClient;
import com.springcloud.ssadhuhanv2.humanrecords.service.HumanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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

//    @Autowired
//    RestTemplate restTemplate;
//    @Autowired
//    HumanRepo humanRepo;
//
//    // Proxy Feign Client
//    @Autowired
//    DateUtilsFeignClient dateUtilsFeignProxy;
//

    @Autowired
    HumanService humanService;


    @GetMapping("/human")
    public List<Human> getAllHuman() {
        return humanService.getAllHumans();
    }


    @GetMapping("/human/{humanId}")
    public ResponseEntity<EntityModel<Human>> getHuman(@PathVariable(name = "humanId") Long id) {
        try {
            log.info("Id of Human: {}", id);
            Human human = humanService.getHumanById(id);
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

    //http://localhost:9999/human/100/age?client=discovery
    @GetMapping("/human/{humanId}/age")
    public ResponseEntity<EntityModel<Age>> getAgeOfHuman(@PathVariable(name = "humanId") Long id, @RequestParam(required = false, name = "client") String clientType) {
        if (clientType == null || clientType.isEmpty()) {
            clientType = HumanRecordsClientConstant.FEIGN;
        }
        try {
            Age age = humanService.getHumanAgeById(id, clientType);
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
