// navbar
const btnMenu = document.getElementById("navbar-menu");
const menu = document.getElementById("side-menu");
const overlay = document.getElementById("overlay");
let isOpen;

function smOpen() {
	overlay.classList.remove("d-none");
	overlay.classList.add("d-block");
	menu.classList.remove("lb_disabled");
	menu.classList.add("lb_active");
	isOpen = true;
}

function smClose() {
	overlay.classList.remove("d-block");
	overlay.classList.add("d-none");
	menu.classList.remove("lb_active");
	menu.classList.add("lb_disabled");
	isOpen = false;
}

btnMenu.addEventListener("click", () => {
	if (isOpen) {
		smClose();
	} else {
		smOpen();
	}
});
overlay.addEventListener("click", smClose)