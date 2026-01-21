package com.Aarthi.expense_tracker.Controller;

import com.Aarthi.expense_tracker.model.Expense;
import com.Aarthi.expense_tracker.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "*") // allow frontend to call APIs
public class Expensecontroller {

    private final ExpenseService expenseService;

    public Expensecontroller(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    // Get all expenses
    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    // Get expenses by user
    @GetMapping("/user/{userId}")
    public List<Expense> getExpensesByUser(@PathVariable Long userId) {
        return expenseService.getExpensesByUserId(userId);
    }

    // Get expenses by category and user
    @GetMapping("/user/{userId}/category/{category}")
    public List<Expense> getExpensesByCategory(@PathVariable Long userId, @PathVariable String category) {
        return expenseService.getExpensesByCategoryAndUser(category, userId);
    }

    // Add new expense
    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {
        return expenseService.addExpense(expense);
    }

    // Update expense
    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        return expenseService.updateExpense(id, expense);
    }

    // Delete expense
    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
    }
}
