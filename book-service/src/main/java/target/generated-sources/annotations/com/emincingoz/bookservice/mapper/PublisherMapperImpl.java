package com.emincingoz.bookservice.mapper;

import com.emincingoz.bookservice.dto.PublisherCreateDTO;
import com.emincingoz.bookservice.dto.PublisherDTO;
import com.emincingoz.bookservice.repository.entity.Publisher;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-02T10:45:55+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class PublisherMapperImpl implements PublisherMapper {

    @Override
    public Publisher map2Publisher(PublisherDTO publisherDTO) {
        if ( publisherDTO == null ) {
            return null;
        }

        Publisher.PublisherBuilder publisher = Publisher.builder();

        publisher.id( publisherDTO.getId() );
        publisher.name( publisherDTO.getName() );

        return publisher.build();
    }

    @Override
    public Publisher map2Publisher(PublisherCreateDTO publisherCreateDTO) {
        if ( publisherCreateDTO == null ) {
            return null;
        }

        Publisher.PublisherBuilder publisher = Publisher.builder();

        publisher.name( publisherCreateDTO.getName() );

        return publisher.build();
    }

    @Override
    public PublisherDTO map2PublisherDTO(Publisher publisher) {
        if ( publisher == null ) {
            return null;
        }

        PublisherDTO.PublisherDTOBuilder publisherDTO = PublisherDTO.builder();

        publisherDTO.id( publisher.getId() );
        publisherDTO.name( publisher.getName() );

        return publisherDTO.build();
    }

    @Override
    public PublisherDTO map2PublisherDTO(PublisherCreateDTO publisherCreateDTO) {
        if ( publisherCreateDTO == null ) {
            return null;
        }

        PublisherDTO.PublisherDTOBuilder publisherDTO = PublisherDTO.builder();

        publisherDTO.name( publisherCreateDTO.getName() );

        return publisherDTO.build();
    }
}
