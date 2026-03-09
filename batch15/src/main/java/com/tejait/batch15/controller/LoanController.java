package com.tejait.batch15.controller;

import com.tejait.batch15.model.Loan;
import com.tejait.batch15.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("loans")
public class LoanController {
    LoanService service;

    @PostMapping("applyLoan")
    public ResponseEntity<Loan>saveLoan(@RequestBody Loan loan){
      Loan saveloan= service.saveloan(loan);
      return new ResponseEntity<>(saveloan , HttpStatus.CREATED);

    }
    @GetMapping("loanTaskboard")
    public ResponseEntity<List<Loan>>GetAll(){
        List<Loan>list= service.getAll();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

}
