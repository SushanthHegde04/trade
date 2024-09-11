
let userid = sessionStorage.getItem('UserId');
function investedstocks(){
// Fetch invested stocks for the user
fetch(`/transaction/bought/${userid}`, {
	method: 'GET',
	headers: {
		"Content-Type": 'application/json'  // Corrected header
	}
}).then((response) => {
	return response.json();  // Parse the response as JSON
}).then((stocks) => {
	const investedDiv = document.querySelector(".INVESTED table"); // Select the table inside the .INVESTED div
	console.log("invested", stocks)
	if (!stocks || stocks.length === 0) {
		investedDiv.innerHTML = "<tr><td colspan='4'>You haven't bought any stocks yet.</td></tr>";
		return;  // Exit if no stocks are found
	}
	// Reset the table content and add the headers

	investedDiv.innerHTML = `
        <tr>
            <th>Stock ID</th>
           
            <th>Price</th>
            <th>Quantity</th>
            <th>Action</th>
        </tr>
    `;
	console.log("invested stocks");
	// Loop through each stock and create table rows
	stocks.forEach((stock) => {
		let row = document.createElement('tr');  // Create a new table row
		console.log("stock Id is ", stock.stockId);
		let stockId = stock.stock?.stockId;
		// Populate the row with stock data
		let userId=sessionStorage.getItem('UserId');
		row.innerHTML = `
            <td>${stockId}</td>
         
            <td>${stock.pricePerShare}</td>
            <td>${stock.quantity}</td>
   <button class='sell-button' 
        onclick='openNewPage(this)' 
        data-stock-id='${stockId}' 
        data-user-id='${userId}' 
        data-type='sell' 
        data-stock-pricepershare='${stock.pricePerShare}'>
        Sell
    </button>        `;

		// Append the row to the table
		investedDiv.appendChild(row);
	});
}).catch((error) => {
	console.log("Error fetching stocks:", error);
});



}
investedstocks();

function openNewPage(button) {
    const stockId = button.getAttribute('data-stock-id');
    const userId = button.getAttribute('data-user-id');
    const actionType = button.getAttribute('data-type');
    const pricePerShare=button.getAttribute('data-stock-pricepershare')
    
    // Open a new popup window
    const popup = window.open("", "PopupWindow", "width=400,height=300");

    // Write HTML content to the new popup window
    popup.document.write(`
        <html>
        <head>
            <title>Stock Action</title>
            <script src="buySell.js"></script>
        </head>
        <body>
            <h1>${actionType.charAt(0).toUpperCase() + actionType.slice(1)} Stock</h1>
            <p>Stock ID: ${stockId}</p>
            <p>User ID: ${userId}</p>
            <p>Action: ${actionType}</p>
            <p>Price :${pricePerShare}</p>
            <form id="quantityForm">
                <label for="quantity">Enter Quantity:</label>
                <input type="number" id="quantity" name="quantity" required>
                <br>
                <button type="button" onclick="submitForm()">Submit</button>
            </form>
            <script>
                // Function to handle form submission
                function submitForm() {
                    const quantity = document.getElementById('quantity').value;
                    if (quantity) {
                        if (window.opener && window.opener.handleStockAction) {
                            window.opener.handleStockAction('${stockId}', '${userId}', '${actionType}', quantity,'${pricePerShare}');
                        }
                        window.close(); // Close the popup window
                    } else {
                        alert("Please enter a quantity.");
                    }
                }
            </script>
        </body>
        </html>
    `);
}

