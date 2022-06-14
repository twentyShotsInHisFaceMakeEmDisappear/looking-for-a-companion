document.getElementById("emailInput").addEventListener('change', validateEmail);
document.getElementById("popularCatList").onload(categoriesParse());

async function getInvitationButtonClick() {
    if (!validateEmail())
        return;

    let infoLabel = document.getElementById('informationText');
    let userEmail = document.getElementById('emailInput').value;
    let url = "http://localhost:8080/api/invite/rl?e=" + userEmail;

    let response = await fetch(url);
    let jsonResponse = await response.json();

    if (response.ok) {
        infoLabel.innerHTML = 'Check your email, there is something there!';
        infoLabel.style.color = "#4bd55d";
        document.getElementById("getLinkButton").disabled = true;
    } else {
        infoLabel.innerHTML = jsonResponse.message;
        infoLabel.style.color = "#d54b4b";
    }

    infoLabel.style.visibility = "visible";
}

function validateEmail() {
    let email = document.getElementById("emailInput").value;

    if (email === undefined || email === "") {
        document.getElementById("emailInput").style.color = "#000000";
        return false;
    }

    if (emailValidationRegEx(email)) {
        document.getElementById("emailInput").style.color = "#000000";
        return true;
    } else {
        document.getElementById("emailInput").style.color = "#d54b4b";
        return false;
    }
}

function emailValidationRegEx(email) {
    return (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email));
}

async function popularCategoriesRequest() {
    let response = await fetch('http://localhost:8080/api/category/gt?c=3');
    return await response.json();
}

function categoriesParse() {
    popularCategoriesRequest().then(function (result) {
        result.forEach(value => document.getElementById("popularCatList").insertAdjacentHTML("afterbegin",
            '<li class="padding-16" href="localhost:8080/category/' + (value.id) + '">' +
            '<img class="image-position" src="' + (value.imageUrl) + '" style="width: 50px; height: 50px;">' +
            '<span class="large">' + (value.title) + '</span><br><span>' + (value.shortDescription) + '</span>' +
            '</li>'));

    });
}