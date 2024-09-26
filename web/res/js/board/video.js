let ratingElem = document.querySelectorAll(".rating")

let ratingbar = document.querySelectorAll(".progress-bar")

for (let i = 0; i<ratingElem.length; i++) {
    let ratingVal = Number(ratingElem[i].textContent.substr(5))*10
    console.log(ratingVal);
    ratingbar[i].style.width = ratingVal + "%";
}

