package com.tutorial.apidemo.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private int contractTypeId;
    private String contractSignedOn;
    private Long budget;
    @Column(name = "active")
    private boolean isActive;

    public Project() {
    }

    public Project(String name, String description, String imageUrl, Long budget, boolean isActive) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.budget = budget;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getContractTypeId() {
        return contractTypeId;
    }

    public void setContractTypeId(int contractTypeId) {
        this.contractTypeId = contractTypeId;
    }

    public String getContractSignedOn() {
        return contractSignedOn;
    }

    public void setContractSignedOn(String contractSignedOn) {
        this.contractSignedOn = contractSignedOn;
    }

    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
