package com.tejait.batch15.serviceimpl;

import com.tejait.batch15.dto.ApplicationOverview;
import com.tejait.batch15.model.BusinessProduct;
import com.tejait.batch15.model.CompanyAddress;
import com.tejait.batch15.model.CompanyDetails;
import com.tejait.batch15.model.Loan;
import com.tejait.batch15.repository.BusinessProductRepository;
import com.tejait.batch15.repository.CompanyAddressRepository;
import com.tejait.batch15.repository.CompanyDetailsRepository;
import com.tejait.batch15.repository.LoanRepository;
import com.tejait.batch15.service.ApplicationOverviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplicationOverviewServiceImpl implements ApplicationOverviewService {

    private BusinessProductRepository productRepository;
    private CompanyDetailsRepository detailsRepository;
    private CompanyAddressRepository addressRepository;
    private LoanRepository loanRepository;


    @Override
    public ApplicationOverview getOverviewByAppId(int appId) {

        ApplicationOverview dto = new ApplicationOverview();

        BusinessProduct product = productRepository.findByAppId(appId).orElse(null);
        CompanyDetails details = detailsRepository.findByAppId(appId).orElse(null);
        CompanyAddress address = addressRepository.findByAppId(appId).orElse(null);
        Loan loans = loanRepository.findByAppId(appId);

        dto.setAppId(appId);

        if (details != null) {
            dto.setCompanyName(details.getCompanyName());
            dto.setCompanyPan(details.getCompanyPan());
        }

        if (loans != null) {
            dto.setMobile(loans.getMobile());
            dto.setMail(loans.getMailId());
        }

        if (address != null) {
            dto.setCity(address.getCity());
        }

        if (product != null) {
            dto.setLoanAmt(product.getLoanAmount());
            dto.setTenure(product.getTenure());
        }

        return dto;
    }
}
