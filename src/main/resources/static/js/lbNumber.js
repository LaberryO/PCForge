document.addEventListener("DOMContentLoaded", () => {
  /** lb_height-숫자 로 height를 동적으로 설정 */
  document.querySelectorAll("[class^='lb_height-']").forEach(element => {
    let match = element.className.match(/lb_height-(\d+)/);
    if (match) {
      let heightValue = match[1] + "%";
      element.style.height = heightValue;
    }
  });

  /** lb_width-숫자 로 width를 동적으로 설정 */
  document.querySelectorAll("[class^='lb_width-']").forEach(element => {
    let match = element.className.match(/lb_width-(\d+)/);
    if (match) {
      let widthValue = match[1] + "%";
      element.style.width = widthValue;
    }
  });
});