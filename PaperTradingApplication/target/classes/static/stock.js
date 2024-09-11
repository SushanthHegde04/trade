
function displayInvestedstocks(){
fetch('stock/details', {
    method: 'GET',
    headers: {
        'content-type': 'application/json'
    }
}).then((data) => {
    return data.json();  // Parse the JSON response
}).then((stocks) => {
    console.log("stocks", stocks);  // Check the list of stocks in the console

    // Get the table where we want to insert rows
    const stockTable = document.querySelector(".stocks table");

    // Clear any existing rows except for the header
    stockTable.innerHTML = `
        <tr>
            <th>Stock ID</th>
            <th>Company ID</th>
            <th>Stock Symbol</th>
            <th>Open Price</th>
            <th>Current Price</th>
            <th>Action</th>
        </tr>
    `;
    let count = 0;

    // Loop through the array of stocks and create rows dynamically
    stocks.forEach((stock) => {
        count++;
        const row = document.createElement('tr');  // Create a new row for each stock

        // Create cells for each stock property
        row.innerHTML = `
            <td>${stock.stockId}</td>
            <td>${count}</td>
            <td>${stock.stockSymbol}</td>
            <td>${stock.openprice}</td>
            <td>${stock.currentPrice}</td>
            <td><button class='add-button' onclick='openNewPage(this)' data-stock-id='${stock.stockId}' data-user-id='${sessionStorage.getItem('UserId')}' data-type='buy' data-stock-pricepershare='${stock.currentPrice}'>Add</button></td>
        `;

        // Append the row to the table
        stockTable.appendChild(row);
    });
}).catch((err) => {
    console.log("error", err);
});
}
displayInvestedstocks();