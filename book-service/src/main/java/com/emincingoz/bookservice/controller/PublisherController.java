package com.emincingoz.bookservice.controller;

import com.emincingoz.bookservice.controller.validation.WhiteSpaceChecker;
import com.emincingoz.bookservice.dto.PublisherCreateDTO;
import com.emincingoz.bookservice.service.PublisherService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/book-service/publisher")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("/get-publisher-by-name/{publisherName}")
    public ResponseEntity<?> getPublisherByName(@PathVariable("publisherName") @WhiteSpaceChecker String publisherName) {
        return new ResponseEntity<>(publisherService.getPublisherByName(publisherName), HttpStatus.OK);
    }

    @PostMapping("/add-new-publisher")
    public ResponseEntity<?> addNewPublisher(@RequestBody PublisherCreateDTO publisherCreateDTO) {
        return new ResponseEntity<>(publisherService.addPublisher(publisherCreateDTO), HttpStatus.CREATED);
    }
}
