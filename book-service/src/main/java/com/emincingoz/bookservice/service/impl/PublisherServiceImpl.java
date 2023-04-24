package com.emincingoz.bookservice.service.impl;

import com.emincingoz.bookservice.dto.PublisherCreateDTO;
import com.emincingoz.bookservice.dto.PublisherDTO;
import com.emincingoz.bookservice.exception.PublisherException;
import com.emincingoz.bookservice.mapper.PublisherMapper;
import com.emincingoz.bookservice.repository.PublisherRepository;
import com.emincingoz.bookservice.repository.entity.Publisher;
import com.emincingoz.bookservice.service.PublisherService;
import com.google.common.base.Strings;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    public PublisherServiceImpl(PublisherRepository publisherRepository, PublisherMapper publisherMapper) {
        this.publisherRepository = publisherRepository;
        this.publisherMapper = publisherMapper;
    }

    @Override
    public PublisherDTO getPublisherByName(String publisherName) {
        if (Strings.isNullOrEmpty(publisherName)) {
            log.warn("publisherName is null or empty");
            throw PublisherException.invalidParameter(PublisherException.INVALID_PARAMETER);
        }
        Publisher publisher = publisherRepository.findByName(publisherName).orElseThrow(() -> {
            log.warn("publisher is null or empty");
            throw PublisherException.invalidParameter(PublisherException.PUBLISHER_NOT_FOUND);
        });
        return publisherMapper.map2PublisherDTO(publisher);
    }

    @Override
    public PublisherDTO addPublisher(PublisherCreateDTO publisherCreateDTO) {
        if (publisherCreateDTO == null) {
            log.warn("publisherCreateDTO is null or empty");
            throw PublisherException.invalidParameter(PublisherException.INVALID_PARAMETER);
        }
        Optional<Publisher> publisherOptional = publisherRepository.findByName(publisherCreateDTO.getName());
        if (publisherOptional.isPresent()) {
            log.warn("publisherOptional is already added with id: {}", publisherOptional.get().getId());
            throw PublisherException.invalidParameter(PublisherException.PUBLISHER_ALREADY_ADDED);
        }
        return publisherMapper.map2PublisherDTO(publisherRepository.save(publisherMapper.map2Publisher(publisherCreateDTO)));
    }
}
