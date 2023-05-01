package com.emincingoz.bookservice.service;

import com.emincingoz.bookservice.dto.PublisherCreateDTO;
import com.emincingoz.bookservice.dto.PublisherDTO;

/**
 * Publisher service interface for publisher operations
 * @author Emin Cingoz
 * @version 4/29/2023
 */
public interface PublisherService {

    /**
     * Returns publisher by given publisher name
     * @param publisherName
     * @return PublisherDTO
     */
    PublisherDTO getPublisherByName(String publisherName);

    /**
     * Adds new publisher if it is not already added. Then returns it as dto
     * @param publisherCreateDTO
     * @return PublisherDTO
     */
    PublisherDTO addPublisher(PublisherCreateDTO publisherCreateDTO);
}
