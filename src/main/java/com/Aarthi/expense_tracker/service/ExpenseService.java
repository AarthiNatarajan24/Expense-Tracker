package com.Aarthi.expense_tracker.service;

import com.Aarthi.expense_tracker.model.Expense;
import com.Aarthi.expense_tracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    // Get all expenses
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    // Get expenses for a specific user
    public List<Expense> getExpensesByUserId(Long userId) {
        return expenseRepository.findByUserId(userId);
    }

    // Get expenses by category and user
    public List<Expense> getExpensesByCategoryAndUser(String category, Long userId) {
        return expenseRepository.findByCategoryAndUserId(category, userId);
    }

    // Get single expense
    public Optional<Expense> getExpenseById(Long id) {
        return expenseRepository.findById(id);
    }

    // Add new expense
    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    // Update expense
    public Expense updateExpense(Long id, Expense updatedExpense) {
        return expenseRepository.findById(id)
                .map(expense -> {
                    if (updatedExpense.getTitle() != null) {
                        expense.setTitle(updatedExpense.getTitle());
                    }
                    if (updatedExpense.getAmount() > 0) {
                        expense.setAmount(updatedExpense.getAmount());
                    }
                    if (updatedExpense.getCategory() != null) {
                        expense.setCategory(updatedExpense.getCategory());
                    }
                    if (updatedExpense.getExpenseDate() != null) {
                        expense.setExpenseDate(updatedExpense.getExpenseDate());
                    }
                    if (updatedExpense.getUserId() != null) {
                        expense.setUserId(updatedExpense.getUserId());
                    }
                    return expenseRepository.save(expense);
                })
                .orElseThrow(() -> new RuntimeException("Expense not found with id " + id));
    }

    // Delete expense
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
}
