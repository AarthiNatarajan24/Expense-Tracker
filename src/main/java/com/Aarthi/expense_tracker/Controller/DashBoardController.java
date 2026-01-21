package com.Aarthi.expense_tracker.Controller;


import com.Aarthi.expense_tracker.model.Expense;
import com.Aarthi.expense_tracker.model.Income;
import com.Aarthi.expense_tracker.repository.ExpenseRepository;
import com.Aarthi.expense_tracker.repository.IncomeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class DashBoardController {

    private final ExpenseRepository expenseRepo;
    private final IncomeRepository incomeRepo;

    public DashBoardController(ExpenseRepository expenseRepo, IncomeRepository incomeRepo) {
        this.expenseRepo = expenseRepo;
        this.incomeRepo = incomeRepo;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Double income = incomeRepo.getTotalIncome();
        Double expense = expenseRepo.getTotalExpense();

        double totalIncome = income == null ? 0 : income;
        double totalExpense = expense == null ? 0 : expense;

        model.addAttribute("income", totalIncome);
        model.addAttribute("expense", totalExpense);
        model.addAttribute("balance", totalIncome - totalExpense);

        return "dashboard";
    }

    @PostMapping("/add-income")
    public String addIncome(@RequestParam Double amount) {
        Income i = new Income();
        i.setAmount(amount);
        incomeRepo.save(i);
        return "redirect:/dashboard";
    }

   @PostMapping("/add-expense")
public String addExpense(@RequestParam String title,
                         @RequestParam String category,
                         @RequestParam Double amount,
                         @RequestParam String expenseDate) {
    Expense e = new Expense();
    e.setTitle(title);                 // 👈 FIXED
    e.setCategory(category);
    e.setAmount(amount);
    e.setExpenseDate(LocalDate.parse(expenseDate)); // 👈 FIX
    //e.setExpenseDate(LocalDate.now());
    expenseRepo.save(e);
    return "redirect:/dashboard";
}


    @GetMapping("/view-expenses")
    public String viewExpenses(Model model) {
        model.addAttribute("expenses", expenseRepo.findAll());
        return "view-expenses";
    }
}
