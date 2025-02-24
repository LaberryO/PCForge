const accountBtn = document.getElementById("AccountInfo");

function getEmailById() {
    fetch(`${window.location.origin}/user/api/get-email`, {
        method: 'POST', // POST 방식으로 변경
        headers: {
            'Content-Type': 'application/json' // JSON 형식의 데이터를 보낸다고 명시
        },
        body: JSON.stringify({ userId: 123 }) // 필요한 데이터를 JSON 형식으로 전달 (예: userId)
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