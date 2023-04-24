package com.emincingoz.bookservice.service;

import com.emincingoz.bookservice.dto.PublisherCreateDTO;
import com.emincingoz.bookservice.dto.PublisherDTO;

public interface PublisherService {

    PublisherDTO getPublisherByName(String publisherName);
    PublisherDTO addPublisher(PublisherCreateDTO publisherCreateDTO);
}
