const btnSearch = document.getElementById("btnSearch");
const checkboxes = document.querySelectorAll('input[name="item_option"]');
const selected1 = document.getElementById("selectedItem1");
const selected2 = document.getElementById("selectedItem2");
const pageStatus = document.getElementById("pageStatus").value;
Chart.register(ChartDataLabels);

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
	checkbox.addEventListener("change", function () {
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
	let charts = target.querySelectorAll("canvas"); // 여러 개의 canvas 요소를 선택

	charts.forEach(chart => {
		let chartInstance = Chart.getChart(chart); // 각 canvas에 대해 이미 생성된 차트가 있는지 확인

		// 기존 차트가 있으면 삭제
		if (chartInstance) {
			chartInstance.destroy();
		}
	});
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
			//			console.log("Server Response: ", data);
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

// 제품 정보 표시
function updateDataContent(status, data, target) {
	target.querySelector("#item_name").textContent = data.name;
	const chartIds = ["item_ramSpeed", "item_price", "forgeChart"];
	let barWidth = null;
	
	switch (status) {
		case "cpu":
			target.querySelector("#item_socket").textContent = data.socket;
			break;
	}

	chartIds.forEach(chartId => {
		const chartCanvas = target.querySelector(`#${chartId}`);

		if (!chartCanvas) {
			console.error(`Canvas with id ${chartId} not found.`);
			return; // canvas가 없다면 차트를 그리지 않음
		}

		if (chartCanvas) {
			let chartInstance = Chart.getChart(chartCanvas);

			if (chartInstance) {
				chartInstance.destroy();
			}

			const dataMap = new Map();

			switch (chartId) {
				case "item_ramSpeed":
					dataMap.set("RamSpeed", data.ddrSpeed);
					break;
				case "item_price":
					dataMap.set('Price', data.price);
					barWidth = 25;
					break;
				// 남은 데이터 짬 때리기
				case "forgeChart":
					let itemId = data.id;
					let itemName = data.name;
					let itemPrice = data.price;
					let itemImage = data.fileName;

					if (!itemImage || itemImage.trim() === "") {
						itemImage = "/assets/img/exam-cpu.jpg";
					}
					
					target.querySelector("#item_image").src = itemImage;

					delete data.id;
					delete data.name;
					delete data.price;
					delete data.fileName;

					const keys = Object.keys(data);
					const values = Object.values(data);

					for (let i = 0; i < keys.length; i++) {
						dataMap.set(keys[i], values[i]);
					}

					break;
			}

			chartInstance = new Chart(chartCanvas, {
				type: "bar",
				data: {
					labels: Array.from(dataMap.keys()),
					datasets: [{
						data: Array.from(dataMap.values()),
						borderWidth: 1
					}]
				},
				options: {
					indexAxis: "y",
					responsive: true,
					maintainAspectRatio: false,
					barThickness: barWidth,
					scales: {
						x: {
							display: true,
							grid: {
								display: false
							},
							ticks: {
								display: false
							},
							border: {
								display: false
							}
						},
						y: {
							display: true,
							grid: {
								display: false
							},
							ticks: {
								display: false
							},
							border: {
								display: true
							}
						}
					},
					plugins: {
						legend: {
							display: false
						}
					}
				}
			});
		}
	});
}

// GB -> TB 함수
function formatStorage(sizeGB) {
	if (sizeGB >= 1000) {
		return (sizeGB / 1000).toFixed(2) + " TB";
	} else {
		return sizeGB + " GB";
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
			
			console.log(itemId);

			if (itemId) {
				// ✅ 선택 목록에서 제거
				selectedItems = selectedItems.filter(item => item !== itemId);

				// ✅ 체크박스 해제
				let checkbox = document.querySelector(`input[name='item_option'][value='${itemId}']`);
				console.log("체크박스 찾음?", checkbox);
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
