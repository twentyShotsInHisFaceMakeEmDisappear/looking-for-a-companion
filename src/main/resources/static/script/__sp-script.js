document.getElementById('popularCatList').onload(loadPageWithAllData());
document.getElementById('emailInput').addEventListener('change', emailValidation);

function loadPageWithAllData() {
    isTokenPresentsInLocalStorage();

    apiRequestForResponse('sub-category/gt?c=3').then(function (result) {
        result.forEach(value => document.getElementById("popularCatList").insertAdjacentHTML("afterbegin",
            '<li class="padding-16" onclick="redirect(\'category/' + (value.id) + '\')">' +
            '<img class="image-position" src="' + (value.imageUrl) + '" style="width: 50px; height: 50px;">' +
            '<span class="large">' + (value.title) + '</span><br><span>' + (value.shortDescription) + '</span>' +
            '</li>'));
    });

    apiRequestForResponse('category').then(function (result) {
        result.forEach(value =>  document.getElementById("mainCatBlock").insertAdjacentHTML("afterbegin",
            '<div class="wall-card">' +
            '                    <img src="' + value.imageUrl + '" alt="' + value.title + '" style="width: 100%; height: 500px;">' +
            '                    <div class="container">' +
            '                        <h3>' +
            '                            <b>' + value.title.toUpperCase() + '</b>' +
            '                        </h3>' +
            '                        <h5>' + value.shortDescription + '</h5>' +
            '                    </div>' +
            '                    <div class="container">' +
            '                        <p>' + value.description +
            '                        </p>' +
            '                        <div class="content-container">' +
            '                            <div class="left-side-wall-element">' +
            '                                <p>' +
            '                                    <button class="button-decoration">' +
            '                                        <b>FIND COMPANION »</b>' +
            '                                    </button>' +
            '                                </p>' +
            '                            </div>' +
            '                        </div>' +
            '                    </div>' +
            '                </div>'));
    });

}

function isTokenPresentsInLocalStorage() {
    if (localStorage.getItem('__auth-token') === null) {
        document.getElementById('joinUs').hidden = false
        document.getElementById('accountMenu').hidden = true;
        document.getElementById('login').hidden = true;
    } else {
        document.getElementById('accountMenu').hidden = false;
        document.getElementById('joinUs').hidden = true;
        document.getElementById('login').hidden = true;

        document.getElementById('userInfoCont').insertAdjacentHTML("afterbegin",
            '<h3><b>Hello, <b style="text-decoration: underline;">' + localStorage.getItem('__username') + '</b>!</b>' +
            '</h3>')
    }
}

function authentication() {
    let warningInformationLabel = document.getElementById('informationTextSUP');

    if (!emailValidation(document.getElementById('loginInput').value)) {
        warningInformationLabel.style.visibility = 'visible';
        warningInformationLabel.innerHTML = 'Invalid email or password.';
        warningInformationLabel.style.color = '#d54b4b';
        return;
    }

    let authenticationBody = {
        email: document.getElementById('loginInput').value,
        password: document.getElementById('passwordInput').value
    };

    try {
        apiRequestForResponseWithBody('security/auth', 'POST', authenticationBody).then(function (res) {
            localStorage.setItem('__auth-token', res.token);
            localStorage.setItem('__username', res.username);

            isTokenPresentsInLocalStorage();
        });


    } catch (e) {
        warningInformationLabel.style.visibility = 'visible';
        warningInformationLabel.innerHTML = 'Invalid email or password.';
        warningInformationLabel.style.color = '#d54b4b';
    }
}

function getInvitationButtonClick() {
    let warningInformationLabel = document.getElementById('informationText');

    if (!emailValidation(document.getElementById('emailInput').value)) {
        warningInformationLabel.style.visibility = 'visible';
        warningInformationLabel.innerHTML = 'Invalid e-mail address.';
        warningInformationLabel.style.color = "#d54b4b";
        return;
    }

    let invitationRequestJson = {
        email: document.getElementById('emailInput').value
    }

    try {
        apiRequestForResponseWithBody('invite/rl', 'POST', invitationRequestJson).then(function (res) {
            warningInformationLabel.style.color = "#4bd55d";
            document.getElementById('emailInput').value = "";
            document.getElementById('getLinkButton').disabled = true;
            warningInformationLabel.innerHTML = 'Check your email, there is something there!';
        });
    } catch (e) {
        warningInformationLabel.style.visibility = 'visible';
        warningInformationLabel.innerHTML = 'Email already used.';
        warningInformationLabel.style.color = "#d54b4b";
    }
}

function emailValidation(email) {
    if (email === undefined || email === '')
        return false;

    return (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email));
}

async function apiRequestForResponse(apiUrl) {
    let response = await fetch('http://localhost:8080/api/' + apiUrl);

    if (response.ok)
        return await response.json();
    else
        throw new Error();
}

async function apiRequestForResponseWithBody(apiUrl, requestMethod, bodyObject) {
    let response = await fetch('http://localhost:8080/api/' + apiUrl, {
        method: requestMethod,
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(bodyObject)
    });

    if (response.ok)
        return await response.json();
    else
        throw new Error();
}

function redirection(nodeUrl) {
    document.location.href = 'http://localhost:3010/' + nodeUrl;
}

function showSignInFields() {
    document.getElementById('joinUs').hidden = !document.getElementById('joinUs').hidden;
    document.getElementById('login').hidden = !document.getElementById('login').hidden;
}

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}