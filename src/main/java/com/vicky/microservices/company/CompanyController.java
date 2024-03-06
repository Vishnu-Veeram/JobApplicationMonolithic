package com.vicky.microservices.company;

import com.vicky.microservices.job.Job;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> findAllCompanies(){
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>("Company created successfully", HttpStatus.OK);
    }

    @GetMapping("/{companyId}")
    //@RequestMapping(value = "/{jobId}", method = RequestMethod.GET)
    public ResponseEntity<Company> findCompanyByID(@PathVariable Long companyId){
        Company company = companyService.findCompanyByID(companyId);
        if(company != null){
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<String> deleteCompanyByID(@PathVariable Long companyId){
        boolean response = companyService.deleteCompanyByID(companyId);
        if(response){
            return new ResponseEntity<>("Company Deleted Successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Company Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<String> updateCompanyByID(@PathVariable Long companyId, @RequestBody Company updatedCompany){
        boolean response = companyService.updateCompanyById(companyId, updatedCompany);
        if(response){
            return new ResponseEntity<>("Company Updated Successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Company Not Found", HttpStatus.NOT_FOUND);
        }
    }
}
