package com.Aarthi.expense_tracker.repository;

import com.Aarthi.expense_tracker.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    
    @Query("SELECT SUM(i.amount) FROM Income i")
    Double getTotalIncome();
}
