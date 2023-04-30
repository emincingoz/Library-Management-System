package com.emincingoz.bookservice.service;

import java.util.Collection;
import java.util.List;

import com.emincingoz.bookservice.dto.InterpreterCreateDTO;
import com.emincingoz.bookservice.dto.InterpreterDTO;

/**
 * Interpreter service interface for interpreter operation for book
 * @author Emin Cingoz
 * @version 4/29/2023
 */
public interface InterpreterService {

    /**
     * Returns interpreter by given interpreter name
     * @param interpreterName
     * @return InterpreterDTO
     */
    InterpreterDTO getInterpreterByName(String interpreterName);

    /**
     * Returns interpreter list by given interpreter name list
     * @param interpreterNameList
     * @return List<InterpreterDTO>
     */
    List<InterpreterDTO> getInterpretersByNameList(Collection<String> interpreterNameList);

    /**
     * Adds new interpreter if the interpreter is not already added. Then returns the added interpreter as dto
     * @param interpreterCreateDTO
     * @return InterpreterDTO
     */
    InterpreterDTO addInterpreter(InterpreterCreateDTO interpreterCreateDTO);
}
