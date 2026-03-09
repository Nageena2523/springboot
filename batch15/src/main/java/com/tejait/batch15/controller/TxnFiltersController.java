package com.tejait.batch15.controller;


import com.tejait.batch15.model.Transactions;
import com.tejait.batch15.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
@CrossOrigin(origins = "*")
public class TxnFiltersController {
    @Autowired
    private TransactionService service;

    @GetMapping("/getTxnsData/{appId}")
    public List<Transactions> getTransactions(@PathVariable Integer appId) {

        return service.getTransactionsByAppId(appId);

    }
   // @GetMapping("fetchtransactions/{appId}")
    //public List<Transactions> fetchtransactions(@PathVariable Integer appId) {

      //  return service.getTransactionsByAppId(appId);

    //}
    @GetMapping("/filtertransactions/{appId}")
    public List<Transactions> filterTransactions(
            @PathVariable Integer appId,
            @RequestParam(required = false) String statusOrInstrument,
            @RequestParam(required = false) List<String> statusOrInstrumentTypesList) {

        return service.filterTransactions(appId, statusOrInstrument, statusOrInstrumentTypesList);
    }
    @GetMapping("/fetchtransactions/{appId}")
    public List<Transactions> getStatement(
            @PathVariable Integer appId,
            @RequestParam(required = false) String duration,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {

        return service.getTransactionStatement(appId, duration, startDate, endDate);
    }










}


