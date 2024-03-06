package com.vicky.microservices.company;

import com.vicky.microservices.job.Job;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    void createCompany(Company company);
    Company findCompanyByID(Long companyId);
    boolean deleteCompanyByID(Long companyId);
    boolean updateCompanyById(Long companyId, Company updatedCompany);
}
