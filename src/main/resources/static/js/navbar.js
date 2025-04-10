const accountBtn = document.getElementById("navbarDropdown");

function getEmailById() {
    fetch(`${window.location.origin}/user/api/get-email`, {
        method: 'POST'
    })
    .then(response => response.text())
    .then(data => {
        document.getElementById("emailResult").innerHTML = data;
    })
    .catch(error => {
        console.error('Error: ', error);
    });
}

accountBtn.addEventListener("click", getEmailById);