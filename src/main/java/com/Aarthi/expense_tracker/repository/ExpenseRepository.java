package com.Aarthi.expense_tracker.repository;

import com.Aarthi.expense_tracker.model.Expense; // import your entity
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserId(Long userId);
    List<Expense> findByCategoryAndUserId(String category, Long userId);



    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e")
    Double getTotalExpense();
}