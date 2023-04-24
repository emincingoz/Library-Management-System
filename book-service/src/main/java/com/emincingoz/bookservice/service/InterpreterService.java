package com.emincingoz.bookservice.service;

import com.emincingoz.bookservice.dto.InterpreterCreateDTO;
import com.emincingoz.bookservice.dto.InterpreterDTO;
import com.emincingoz.bookservice.repository.entity.Interpreter;

import java.util.Collection;
import java.util.List;

public interface InterpreterService {

    InterpreterDTO getInterpreterByName(String interpreterName);
    List<InterpreterDTO> getInterpretersByNameList(Collection<String> interpreterNameList);
    InterpreterDTO addInterpreter(InterpreterCreateDTO interpreterCreateDTO);
}
