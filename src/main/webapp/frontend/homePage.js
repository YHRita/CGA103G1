(() =>{
    fetch('/CGA103G1/setSessionValue')
    .then(res => res.json())
    .then(data => console.log(data));
})();
