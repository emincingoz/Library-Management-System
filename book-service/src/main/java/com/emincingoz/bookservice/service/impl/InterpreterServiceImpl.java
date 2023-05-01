package com.emincingoz.bookservice.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.emincingoz.bookservice.dto.InterpreterCreateDTO;
import com.emincingoz.bookservice.dto.InterpreterDTO;
import com.emincingoz.bookservice.exception.InterpreterExceptionUtility;
import com.emincingoz.bookservice.mapper.InterpreterMapper;
import com.emincingoz.bookservice.repository.InterpreterRepository;
import com.emincingoz.bookservice.repository.entity.Interpreter;
import com.emincingoz.bookservice.service.InterpreterService;

import com.google.common.base.Strings;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Interpreter service interface implementation
 *
 * @author Emin Cingoz
 * @version 4/29/2023
 */
@Log4j2
@Service
public class InterpreterServiceImpl implements InterpreterService {

    private final InterpreterRepository interpreterRepository;
    private final InterpreterMapper interpreterMapper;

    public InterpreterServiceImpl(InterpreterRepository interpreterRepository, InterpreterMapper interpreterMapper) {
        this.interpreterRepository = interpreterRepository;
        this.interpreterMapper = interpreterMapper;
    }

    /**
     * Returns interpreter dto by given interpreter name
     *
     * @param interpreterName
     * @return InterpreterDTO
     */
    @Override
    public InterpreterDTO getInterpreterByName(String interpreterName) {
        if (Strings.isNullOrEmpty(interpreterName)) {
            log.warn("interpreterName is null or empty");
            throw InterpreterExceptionUtility.invalidRequestException(InterpreterExceptionUtility.INVALID_PARAMETER);
        }
        Interpreter interpreter = interpreterRepository.findByInterpreter(interpreterName).orElseThrow(() -> {
            log.warn("interpreter is null or empty");
            throw InterpreterExceptionUtility.dataNotFoundException(InterpreterExceptionUtility.INTERPRETER_NOT_FOUND);
        });
        return interpreterMapper.map2InterpreterDTO(interpreter);
    }

    /**
     * Returns interpreter dto list by given interpreter name list
     *
     * @param interpreterNameList
     * @return List<InterpreterDTO>
     */
    @Override
    public List<InterpreterDTO> getInterpretersByNameList(Collection<String> interpreterNameList) {
        if (CollectionUtils.isEmpty(interpreterNameList)) {
            log.warn("interpreterNameList is null or empty");
            throw InterpreterExceptionUtility.invalidRequestException(InterpreterExceptionUtility.INVALID_PARAMETER);
        }
        List<Interpreter> interpreterList = interpreterRepository.findInterpretersByInterpreterIn(interpreterNameList);
        if (CollectionUtils.isEmpty(interpreterList)) {
            log.warn("interpreterList is null or empty");
            throw InterpreterExceptionUtility.dataNotFoundException(InterpreterExceptionUtility.INTERPRETER_NOT_FOUND);
        }
        return interpreterMapper.map2InterpreterDTOList(interpreterList);
    }

    /**
     * Adds new interpreter if it is not already added. Then returns the added interpreter as dto
     *
     * @param interpreterCreateDTO
     * @return InterpreterDTO
     */
    @Override
    public InterpreterDTO addInterpreter(InterpreterCreateDTO interpreterCreateDTO) {
        if (interpreterCreateDTO == null) {
            log.warn("interpreterCreateDTO is null or empty");
            throw InterpreterExceptionUtility.invalidRequestException(InterpreterExceptionUtility.INVALID_PARAMETER);
        }
        Optional<Interpreter> interpreterOptional = interpreterRepository.findByInterpreter(interpreterCreateDTO.getInterpreter());
        if (interpreterOptional.isPresent()) {
            log.warn("interpreter is already added with id: {}", interpreterOptional.get().getId());
            throw InterpreterExceptionUtility.invalidRequestException(InterpreterExceptionUtility.INTERPRETER_ALREADY_ADDED);
        }
        return interpreterMapper.map2InterpreterDTO(interpreterRepository.save(interpreterMapper.map2Interpreter(interpreterCreateDTO)));
    }
}
