package com.emincingoz.bookservice.controller;

import com.emincingoz.bookservice.dto.InterpreterCreateDTO;
import com.emincingoz.bookservice.service.InterpreterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/book-service/interpreter")
public class InterpreterController {
    private final InterpreterService interpreterService;

    public InterpreterController(InterpreterService interpreterService) {
        this.interpreterService = interpreterService;
    }

    @GetMapping("/get-interpreter-by-name")
    public ResponseEntity<?> getInterpreterByName(@PathVariable String interpreterName) {
        return new ResponseEntity<>(interpreterService.getInterpreterByName(interpreterName), HttpStatus.OK);
    }

    @PostMapping("/add-new-interpreter")
    public ResponseEntity<?> addNewInterpreter(@RequestBody InterpreterCreateDTO interpreterCreateDTO) {
        return new ResponseEntity<>(interpreterService.addInterpreter(interpreterCreateDTO), HttpStatus.CREATED);
    }
}
