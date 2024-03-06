package com.vicky.microservices.job;

import com.vicky.microservices.company.Company;
import jakarta.persistence.*;

@Entity
@Table(name = "Job_Details") //by default class name(Job) is the table name
public class Job {

    @Id //to mark the field as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //to generate unique values automatically
    private Long id;
    private String title;
    private String description;
    private double minSalary;
    private double maxSalary;
    private String location;

    @ManyToOne
    private Company company;

    //to instantiate the Entity object by the JPA
    public Job() {
    }

    public Job(Long id, String title, String description, double minSalary, double maxSalary, String location) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.location = location;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(double minSalary) {
        this.minSalary = minSalary;
    }

    public double getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(double maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
