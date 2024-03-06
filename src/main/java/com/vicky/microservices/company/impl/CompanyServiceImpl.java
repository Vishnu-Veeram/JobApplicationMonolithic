package com.vicky.microservices.company.impl;

import com.vicky.microservices.company.Company;
import com.vicky.microservices.company.CompanyRepository;
import com.vicky.microservices.company.CompanyService;
import com.vicky.microservices.job.Job;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Company findCompanyByID(Long companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }

    @Override
    public boolean deleteCompanyByID(Long companyId) {
        try{
            if(companyRepository.existsById(companyId)){
                companyRepository.deleteById(companyId);
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateCompanyById(Long companyId, Company updatedCompany) {
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        if(companyOptional.isPresent()){
            Company company = companyOptional.get();
            company.setDescription(updatedCompany.getDescription());
            company.setName(updatedCompany.getName());
            company.setJobs(updatedCompany.getJobs());
            companyRepository.save(company);
            return true;
        }
        return false;
    }
}
