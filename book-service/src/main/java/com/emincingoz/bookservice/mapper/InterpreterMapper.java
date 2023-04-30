package com.emincingoz.bookservice.mapper;

import com.emincingoz.bookservice.dto.InterpreterCreateDTO;
import com.emincingoz.bookservice.dto.InterpreterDTO;
import com.emincingoz.bookservice.repository.entity.Interpreter;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interpreter mapper between dto and interpreter entity
 * @author Emin Cingoz
 * @version 4/29/2023
 */
@Mapper(componentModel = "spring")
public interface InterpreterMapper {
    Interpreter map2Interpreter(InterpreterDTO interpreterDTO);
    Interpreter map2Interpreter(InterpreterCreateDTO interpreterCreateDTO);
    List<Interpreter> map2InterpreterList(List<InterpreterDTO> interpreterDTOList);
    InterpreterDTO map2InterpreterDTO(Interpreter interpreter);
    InterpreterDTO map2InterpreterDTO(InterpreterCreateDTO interpreterCreateDTO);
    List<InterpreterDTO> map2InterpreterDTOList(List<Interpreter> interpreterList);
}
