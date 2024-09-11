package com.PaperTrading.Service;

import java.util.List;
import java.util.Optional;

import org.apache.el.parser.AstInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PaperTrading.Model.Stock;
import com.PaperTrading.Repository.StockRepo;

@Service
public class stockService {
	@Autowired
	private StockRepo stockrepo;

	public Stock saveStock(Stock stock) {
		return stockrepo.save(stock);	}

	public Optional<Stock> findById(Integer stockId) {
		return stockrepo.findById(stockId);
	}
	public List<Stock> getstocks()
	{
		return stockrepo.findAll();
	}
}
