package com.Aarthi.expense_tracker.model;

import jakarta.persistence.*;

@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long userId;
    private String category;
    private double amount;
    private String title;
    private String description;
    private java.time.LocalDate expenseDate;
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
     public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public java.time.LocalDate getExpenseDate() { return expenseDate; }
    public void setExpenseDate(java.time.LocalDate expenseDate) { this.expenseDate = expenseDate; }
}