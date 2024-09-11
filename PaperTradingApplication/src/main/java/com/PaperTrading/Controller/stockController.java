package com.PaperTrading.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PaperTrading.Model.Stock;
import com.PaperTrading.Service.stockService;

@RestController
@RequestMapping("/stock")
public class stockController {
     @Autowired
	stockService sr;
	@GetMapping("/details")
	public List<Stock> getDetails()
	{
	return sr.getstocks();
	}
	
}
