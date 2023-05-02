package com.emincingoz.bookservice.mapper;

import com.emincingoz.bookservice.dto.InterpreterCreateDTO;
import com.emincingoz.bookservice.dto.InterpreterDTO;
import com.emincingoz.bookservice.repository.entity.Interpreter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-02T10:45:55+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class InterpreterMapperImpl implements InterpreterMapper {

    @Override
    public Interpreter map2Interpreter(InterpreterDTO interpreterDTO) {
        if ( interpreterDTO == null ) {
            return null;
        }

        Interpreter interpreter = new Interpreter();

        interpreter.setId( interpreterDTO.getId() );
        interpreter.setInterpreter( interpreterDTO.getInterpreter() );

        return interpreter;
    }

    @Override
    public Interpreter map2Interpreter(InterpreterCreateDTO interpreterCreateDTO) {
        if ( interpreterCreateDTO == null ) {
            return null;
        }

        Interpreter interpreter = new Interpreter();

        interpreter.setInterpreter( interpreterCreateDTO.getInterpreter() );

        return interpreter;
    }

    @Override
    public List<Interpreter> map2InterpreterList(List<InterpreterDTO> interpreterDTOList) {
        if ( interpreterDTOList == null ) {
            return null;
        }

        List<Interpreter> list = new ArrayList<Interpreter>( interpreterDTOList.size() );
        for ( InterpreterDTO interpreterDTO : interpreterDTOList ) {
            list.add( map2Interpreter( interpreterDTO ) );
        }

        return list;
    }

    @Override
    public InterpreterDTO map2InterpreterDTO(Interpreter interpreter) {
        if ( interpreter == null ) {
            return null;
        }

        InterpreterDTO.InterpreterDTOBuilder interpreterDTO = InterpreterDTO.builder();

        interpreterDTO.id( interpreter.getId() );
        interpreterDTO.interpreter( interpreter.getInterpreter() );

        return interpreterDTO.build();
    }

    @Override
    public InterpreterDTO map2InterpreterDTO(InterpreterCreateDTO interpreterCreateDTO) {
        if ( interpreterCreateDTO == null ) {
            return null;
        }

        InterpreterDTO.InterpreterDTOBuilder interpreterDTO = InterpreterDTO.builder();

        interpreterDTO.interpreter( interpreterCreateDTO.getInterpreter() );

        return interpreterDTO.build();
    }

    @Override
    public List<InterpreterDTO> map2InterpreterDTOList(List<Interpreter> interpreterList) {
        if ( interpreterList == null ) {
            return null;
        }

        List<InterpreterDTO> list = new ArrayList<InterpreterDTO>( interpreterList.size() );
        for ( Interpreter interpreter : interpreterList ) {
            list.add( map2InterpreterDTO( interpreter ) );
        }

        return list;
    }
}
