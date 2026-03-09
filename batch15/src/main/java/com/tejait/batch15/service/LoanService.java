package com.tejait.batch15.service;

import com.tejait.batch15.model.Loan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoanService {
    Loan saveloan(Loan loan);

    public List<Loan> getAll();
}
