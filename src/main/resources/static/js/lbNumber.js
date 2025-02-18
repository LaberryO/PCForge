/** 
 * lb_속성-숫자 속성을 각각 1~100 까지 생성해줍니다.
 * 
 * @param {string} styleName 속성값의 이름
 * @param {string} unit 단위
 * 
 * 리턴 값은 없습니다.
 *  */
function styleGenerator(styleName, unit) {
    document.querySelectorAll(`[class^='lb_${styleName}-']`).forEach(element => {
        let regex = new RegExp(`lb_${styleName}-(\\d+)`);
        let match = element.className.match(regex);
        
        if (match) {
            let value = match[1] + unit;  // 여기서 "%" 또는 다른 단위를 붙일 수 있습니다.
            // 원하는 스타일을 적용
            element.style.width = value; // 예시: width 스타일 설정
        }
    });
}

document.addEventListener("DOMContentLoaded", () => {
	styleGenerator("width", "%");
	styleGenerator("height", "%");
});