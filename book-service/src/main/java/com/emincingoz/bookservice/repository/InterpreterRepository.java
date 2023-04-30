package com.emincingoz.bookservice.repository;

import com.emincingoz.bookservice.repository.entity.Interpreter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for INTERPRETER entity
 * @author Emin Cingoz
 * @version 4/29/2023
 */
public interface InterpreterRepository extends JpaRepository<Interpreter, Long> {

    /**
     * Finds interpreter by given interpreter name
     * @param interpreterName
     * @return Optional<Interpreter>
     */
    Optional<Interpreter> findByInterpreter(String interpreterName);

    /**
     * Finds interpreter list by given interpreter name list
     * @param interpreterNameList
     * @return List<Interpreter>
     */
    List<Interpreter> findInterpretersByInterpreterIn(Collection<String> interpreterNameList);
}
