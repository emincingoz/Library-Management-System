package com.emincingoz.bookservice.repository;

import com.emincingoz.bookservice.repository.entity.Interpreter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface InterpreterRepository extends JpaRepository<Interpreter, Long> {
    Optional<Interpreter> findByInterpreter(String interpreterName);

    List<Interpreter> findInterpretersByInterpreterIn(Collection<String> interpreterNameList);
}
