package com.emincingoz.bookservice.mapper;

import com.emincingoz.bookservice.dto.PublisherCreateDTO;
import com.emincingoz.bookservice.dto.PublisherDTO;
import com.emincingoz.bookservice.repository.entity.Publisher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublisherMapper {
    Publisher map2Publisher(PublisherDTO publisherDTO);
    Publisher map2Publisher(PublisherCreateDTO publisherCreateDTO);
    PublisherDTO map2PublisherDTO(Publisher publisher);
    PublisherDTO map2PublisherDTO(PublisherCreateDTO publisherCreateDTO);
}
