package com.tejait.batch15.serviceimpl;
import com.tejait.batch15.model.Transactions;
import com.tejait.batch15.repository.TransactionRepository;
import com.tejait.batch15.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;

    // =========================
    // CSV UPLOAD
    // =========================

    @Override
    @Transactional
    public synchronized void uploadCsv(Integer appId, MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("CSV file is empty");
        }

        try {
            // 🔥 STEP 1: Delete existing records for this appId
            repository.deleteAllByAppId(appId);
            repository.flush();

            // 🔥 STEP 2: Reset AUTO_INCREMENT
            repository.resetAutoIncrement();

            // 🔥 STEP 3: Parse CSV
            List<Transactions> list = parseCsv(file, appId);

            // 🔥 STEP 4: Save fresh records
            repository.saveAll(list);

        } catch (Exception e) {
            throw new RuntimeException("CSV upload failed: " + e.getMessage());
        }
    }

    // =========================
    // SAVE CHANGES FROM UI
    // =========================

    @Override
    @Transactional
    public synchronized void saveAll(Integer appId, List<Transactions> list) {

        if (list == null || list.isEmpty()) return;

        try {
            // 🔥 STEP 1: Delete old data
            repository.deleteAllByAppId(appId);
            repository.flush();

            // 🔥 STEP 2: Reset AUTO_INCREMENT
            repository.resetAutoIncrement();

            // 🔥 STEP 3: Prepare new data
            list.forEach(t -> {
                t.setId(null);      // Important
                t.setAppId(appId);  // Ensure correct appId
            });

            // 🔥 STEP 4: Save
            repository.saveAll(list);

        } catch (Exception e) {
            throw new RuntimeException("Save changes failed: " + e.getMessage());
        }
    }

    // =========================
    // FETCH DATA
    // =========================

    @Override
    public List<Transactions> getAllByAppId(Integer appId) {
        return repository.findAllByAppId(appId);
    }



    // =========================
    // CSV PARSER
    // =========================

    private List<Transactions> parseCsv(MultipartFile file, Integer appId) {

        List<Transactions> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {

                // Skip header
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] cols = line.split(",");

                if (cols.length < 9) continue;

                Transactions t = new Transactions();

                t.setAppId(appId);
                t.setTransactionDate(cols[0].trim());
                t.setActivity(cols[1].trim());
                t.setInstrument(cols[2].trim());
                t.setTxnId(cols[3].trim());
                t.setComment(cols[4].trim());
                t.setDebtAmt(parseDouble(cols[5]));
                t.setCreditAmt(parseDouble(cols[6]));
                t.setTransactionBreakup(cols[7].trim());
                t.setTransactionStatus(cols[8].trim());

                list.add(t);
            }

        } catch (Exception e) {
            throw new RuntimeException("CSV parsing failed");
        }

        return list;
    }

    // =========================
    // SAFE DOUBLE PARSER
    // =========================

    private Double parseDouble(String val) {
        try {
            if (val == null || val.isEmpty()) return 0.0;
            val = val.replaceAll("[^0-9.]", "");
            return val.isEmpty() ? 0.0 : Double.parseDouble(val);
        } catch (Exception e) {
            return 0.0;
        }
    }
    @Override
    public List<Transactions> getTransactionsByAppId(Integer appId) {

        return repository.findByAppId(appId);

    }

   // @Override
    //public List<Transactions> getTransactions(Integer appId) {
       // return repository.findByAppId(appId);
    //}

    @Override
    public List<Transactions> filterTransactions(Integer appId,
                                                 String statusOrInstrument,
                                                 List<String> statusOrInstrumentTypesList) {

        if (statusOrInstrumentTypesList == null || statusOrInstrumentTypesList.isEmpty()) {
            return repository.findByAppId(appId);
        }

        List<String> statusList = new ArrayList<>();
        List<String> instrumentList = new ArrayList<>();

        for (String value : statusOrInstrumentTypesList) {

            if (value.equalsIgnoreCase("SUCCESS") ||
                    value.equalsIgnoreCase("FAILED") ||
                    value.equalsIgnoreCase("PENDING") ||
                    value.equalsIgnoreCase("CANCELLED")) {

                statusList.add(value);
            }

            if (value.equalsIgnoreCase("UPI") ||
                    value.equalsIgnoreCase("WALLET") ||
                    value.equalsIgnoreCase("CREDITCARD") ||
                    value.equalsIgnoreCase("DEBITCARD")) {

                instrumentList.add(value);
            }
        }

        if (!statusList.isEmpty() && !instrumentList.isEmpty()) {
            return repository.findByAppIdAndTransactionStatusInAndInstrumentIn(appId, statusList, instrumentList);
        }

        if (!statusList.isEmpty()) {
            return repository.findByAppIdAndTransactionStatusIn(appId, statusList);
        }

        if (!instrumentList.isEmpty()) {
            return repository.findByAppIdAndInstrumentIn(appId, instrumentList);
        }

        return repository.findByAppId(appId);
    }

    @Override
    public List<Transactions> getTransactionStatement(Integer appId, String duration, String startDate, String endDate) {

        if (duration != null) {

            LocalDate today = LocalDate.now();
            LocalDate fromDate = null;

            switch (duration.toLowerCase()) {

                case "last month":
                    fromDate = today.minusMonths(1);
                    break;

                case "last 3 months":
                    fromDate = today.minusMonths(3);
                    break;

                case "last 6 months":
                    fromDate = today.minusMonths(6);
                    break;

                case "last year":
                    fromDate = today.minusYears(1);
                    break;
            }

            return repository.findByAppIdAndTransactionDateBetween(
                    appId,
                    fromDate.toString(),
                    today.toString()
            );
        }

        if (startDate != null && endDate != null) {

            return repository.findByAppIdAndTransactionDateBetween(
                    appId,
                    startDate,
                    endDate
            );
        }

        return repository.findByAppId(appId);
    }

}
