package com.bfhl.controller;

import com.bfhl.dto.RequestDTO;
import com.bfhl.dto.ResponseDTO;
import com.bfhl.service.BfhlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bfhl")
public class BfhlController {

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> process(@RequestBody RequestDTO request) {
        ResponseDTO response = bfhlService.processData(request);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleError(Exception e) {
        ResponseDTO err = new ResponseDTO();
        err.setSuccess(false);
        return ResponseEntity.status(500).body(err);
    }
}