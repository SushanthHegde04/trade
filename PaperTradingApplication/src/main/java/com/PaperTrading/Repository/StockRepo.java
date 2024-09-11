package com.PaperTrading.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.PaperTrading.Model.Stock;

public interface StockRepo extends JpaRepository<Stock, Integer> {
    Optional<Stock> findByStockId(int stockId); // Use stockId instead of Id for consistency
}
