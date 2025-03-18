const btnSearch = document.getElementById("btnSearch");
const checkboxes = document.querySelectorAll('input[name="item_option"]');
const selected1 = document.getElementById("selectedItem1");
const selected2 = document.getElementById("selectedItem2");
const pageStatus = document.getElementById("pageStatus").value;

btnSearch.addEventListener("click", () => {
	document.getElementById("kw").value = document.getElementById("search_kw").value;
	document.getElementById("searchForm").submit();
});

// ✅ 선택된 항목을 저장하는 배열
let selectedItems = [];

// ✅ box1, box2를 고정된 위치로 매핑하는 객체 (null 대신 "")
const targetMap = {
	first: "",
	second: ""
};

// ✅ 체크박스 이벤트 리스너 추가
checkboxes.forEach(checkbox => {
	checkbox.addEventListener("change", function() {
		const value = this.value; // 선택된 부품의 ID
		const path = window.location.pathname;

		if (this.checked) {
			// ✅ 최대 2개까지만 선택 가능
			if (selectedItems.length < 2) {
				selectedItems.push(value);
			} else {
				this.checked = false;
				return;
			}
		} else {
			// ✅ 선택 해제 시 해당 박스 내용만 초기화
			if (targetMap.first === value) {
				clearTarget(selected1);
				targetMap.first = "";
			} else if (targetMap.second === value) {
				clearTarget(selected2);
				targetMap.second = "";
			}
			selectedItems = selectedItems.filter(item => item !== value);
		}

		// ✅ 박스1과 박스2를 원래 위치에 유지하면서 업데이트
		updateTargetMapping();

		// ✅ 선택된 체크박스에 따라 데이터 가져오기
		if (targetMap.first !== "") {
			fetchPCPart(path, targetMap.first, selected1);
		} else {
			clearTarget(selected1);
		}

		if (targetMap.second !== "") {
			fetchPCPart(path, targetMap.second, selected2);
		} else {
			clearTarget(selected2);
		}
	});
});

// ✅ 특정 target 내부의 텍스트 초기화 함수
function clearTarget(target) {
	if (!target) return;
	target.querySelectorAll("span, p, h3").forEach(el => el.textContent = "");
	target.querySelectorAll("img").forEach(el => el.src = "");
}

// ✅ 선택된 항목을 box1, box2에 배치하는 함수 (box2 내용이 box1으로 이동하지 않도록 유지)
function updateTargetMapping() {
	const firstItem = targetMap.first;
	const secondItem = targetMap.second;

	// ✅ box1에 있던 항목이 삭제되었더라도 box2를 그대로 유지
	if (selectedItems.includes(firstItem)) {
		targetMap.first = firstItem;
	} else {
		targetMap.first = selectedItems[0] || "";
	}

	if (selectedItems.includes(secondItem)) {
		targetMap.second = secondItem;
	} else {
		targetMap.second = selectedItems.length > 1 ? selectedItems[1] : "";
	}

	// ✅ box1이 비워지면 box2의 내용을 box1으로 옮기지 않음
	if (targetMap.first === targetMap.second) {
		targetMap.second = "";
	}
}

// ✅ PC 부품 정보 가져오기 (AJAX)
function fetchPCPart(path, id, target) {
	fetch(`${path}/getThis?id=${id}`)
		.then(response => response.json())
		.then(data => {
			console.log("Server Response: ", data);
			if (data.data) {
				updateDataContent(pageStatus, data.data, target);
			} else {
				clearTarget(target); // 데이터가 없을 경우 초기화
			}
		})
		.catch(error => {
			console.log("데이터 요청 실패", error);
			clearTarget(target); // 오류 발생 시 초기화
		});
}


function updateDataContent(status, data, target) {
	target.querySelector("#item_name").textContent = data.name;
	target.querySelector("#item_price").textContent = data.price;
	let image = data.fileName;
	if (image === null || image.trim() === "") {
		image = "/assets/img/exam-cpu.jpg";
	}
	target.querySelector("#item_image").src = image;
	switch (status) {
		case "cpu":
			break;
		case "ram":
			target.querySelector("#item_type").textContent = data.type;
			target.querySelector("#item_speed").textContent = data.speed;
			target.querySelector("#item_channel").textContent = data.memoryChannel;
			target.querySelector("#item_size").textContent = data.capacity;
			break;
		case "gpu":
			target.querySelector("#item_speed").textContent = data.defaultSpeed;
			target.querySelector("#item_power").textContent = data.powerConsumption;
			target.querySelector("#item_memory").textContent = data.memorySize;
			break;


	}
}

// 선택 해제 버튼 작동하게 하는 코드
document.addEventListener("DOMContentLoaded", function () {
    // 선택 해제 버튼 이벤트 리스너 추가
    document.querySelectorAll("#select_disable").forEach(button => {
        button.addEventListener("click", function () {
            let card = this.closest(".card"); // 현재 버튼이 속한 카드 찾기
            if (!card) return;

            let cardId = card.id; // selectedItem1 또는 selectedItem2
            let itemId = null;

            // ✅ 카드 내부의 item_name을 이용해 체크박스와 매칭할 ID 찾기
            let itemNameElement = card.querySelector("#item_name");
            if (itemNameElement && itemNameElement.textContent.trim() !== "") {
                itemId = [...checkboxes].find(cb => cb.nextElementSibling.textContent.trim() === itemNameElement.textContent.trim())?.value;
            }

            if (itemId) {
                // ✅ 선택 목록에서 제거
                selectedItems = selectedItems.filter(item => item !== itemId);

                // ✅ 체크박스 해제
                let checkbox = document.querySelector(`input[name='item_option'][value='${itemId}']`);
                if (checkbox) checkbox.checked = false;
            }

            // ✅ 해당 카드 내용 초기화
            clearTarget(card);

            // ✅ targetMap 업데이트
            if (cardId === "selectedItem1") {
                targetMap.first = "";
            } else if (cardId === "selectedItem2") {
                targetMap.second = "";
            }

            updateTargetMapping(); // 선택 목록 정리
        });
    });
});
