document.getElementById("button").addEventListener("click", function (e) {
    let enteredSymbol = document.getElementById("symbol").value;
    let div = document.getElementById("div");
    let symbol = {
        symbol: enteredSymbol,
        price: null,
        time: "null"
    }
    fetch("http://localhost:8080/nct_trade_war_exploded/live", {
        method: "POST",
        body: JSON.stringify(symbol)
    })
        .then(resp => resp.json())
        .then(resp => {
            div.innerHTML = resp.price
        })
})