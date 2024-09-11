function handleStockAction(stockId, userId, actionType, quantity, pricepershare) {
		const jsonData = {
			stock: {
				stockId: stockId  
			},
			user: {
				userId: userId  
			},
			transactionType: 'BUY',
			quantity: quantity,  
			pricePerShare: pricepershare 
		};
		console.log(jsonData);
	if (actionType === 'buy') {
		fetch('/transaction/buy', {
	method: 'POST',
	headers: {
		'Content-Type': 'application/json'
	},
	body: JSON.stringify(jsonData)
})
.then((response) => response.text())  // Changed from response.json() to response.text()
.then((data) => {
	alert(`Response from server: ${data}`);  // Alert the string response
	investedstocks();
	userdata();
})
.catch((err) => {
	console.log("Error:", err);
});

	}
else {
		fetch('/transaction/sell', {
	method: 'POST',
	headers: {
		'Content-Type': 'application/json'
	},
	body: JSON.stringify(jsonData)
})
.then((response) => response.text())  
.then((data) => {
	alert(`Response from server: ${data}`);  // Alert the string response
	investedstocks();
	userdata();
})
.catch((err) => {
	console.log("Error:", err);
});

		
		
		alert("Sell");
	}

	console.log(`Stock ID: ${stockId}, User ID: ${userId}, Action: ${actionType}, Quantity: ${quantity}, Price: ${pricepershare}`);
}
