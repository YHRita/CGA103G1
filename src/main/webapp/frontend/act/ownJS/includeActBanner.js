(() =>{

    fetch("actBanner.html")
        .then(response => {
            return response.text()
        })
        .then(data => {
            document.querySelector("#actBanner").innerHTML = data;
        });
    
})();

