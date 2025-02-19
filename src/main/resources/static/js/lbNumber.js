/** 
 * lb_속성-숫자 속성을 각각 1~100 까지 생성해줍니다.
 * 
 * @param {string} styleName 속성값의 이름
 * @param {string} unit 단위
 * 
 * 리턴 값은 없습니다.
 *  */
function styleGenerator(styleName, unit) {
    console.log(`styleName: ${styleName}`);  // 전달된 styleName 확인
	console.log(document.querySelectorAll(`[class^='lb_${styleName}-']`));

    // 클래스명이 lb_width-50, lb_height-50 형식인 요소들 찾아서 스타일 적용
    document.querySelectorAll(`[class^='lb_${styleName}-']`).forEach(element => {
        let regex = new RegExp(`lb_${styleName}-(\\d+)`);
        let match = element.className.match(regex);
        
        if (match) {
            let value = match[1] + unit;  // 예: 50%
            element.style[styleName] = value;  // 스타일 적용
        } else {
            console.log(`No match for element with class ${element.className}`);
        }
    });
}

document.addEventListener("DOMContentLoaded", () => {
    console.log("DOM fully loaded");
    styleGenerator("width", "%");
    styleGenerator("height", "%");
    styleGenerator("top", "%");
});


