package com.tejait.batch15.service;

import com.tejait.batch15.model.Transactions;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TransactionService {

    void uploadCsv(Integer appId, MultipartFile file);

    void saveAll(Integer appId, List<Transactions> list);

    List<Transactions> getAllByAppId(Integer appId);   //

    List<Transactions> getTransactionsByAppId(Integer appId);//


   // List<Transactions> getTransactions(Integer appId);


    List<Transactions> filterTransactions(Integer appId, String statusOrInstrument, List<String> statusOrInstrumentTypesList);

    List<Transactions> getTransactionStatement(Integer appId, String duration, String startDate, String endDate);
}