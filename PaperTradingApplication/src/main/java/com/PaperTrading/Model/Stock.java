package com.PaperTrading.Model;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Stocks")
public class Stock {

    @Id
    @Column(name = "stockid")
    private int stockId;

    @Column(name = "stocksymbol", nullable = false)
    private String stockSymbol;

    @Column(name = "currentprice", nullable = false)
    private BigDecimal currentPrice;

    @Column(name = "openprice", nullable = false)
    private BigDecimal openprice;

    // Default constructor
    public Stock() {}

    // Parameterized constructor
    public Stock(int stockId, String stockSymbol, BigDecimal currentPrice, BigDecimal openprice) {
        this.stockId = stockId;
        this.stockSymbol = stockSymbol;
        this.currentPrice = currentPrice;
        this.openprice = openprice;
    }

    // Getters and setters
    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public BigDecimal getOpenprice() {
        return openprice;
    }

    public void setOpenprice(BigDecimal openprice) {
        this.openprice = openprice;
    }
}
