/*!
* Start Bootstrap - Stylish Portfolio v6.0.6 (https://startbootstrap.com/theme/stylish-portfolio)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-stylish-portfolio/blob/master/LICENSE)
*/
window.addEventListener('DOMContentLoaded', event => {

    const sidebarWrapper = document.getElementById('sidebar-wrapper');
    let scrollToTopVisible = false;
    // Closes the sidebar menu
    const menuToggle = document.body.querySelector('.menu-toggle');
    menuToggle.addEventListener('click', event => {
        event.preventDefault();
        sidebarWrapper.classList.toggle('active');
        _toggleMenuIcon();
        menuToggle.classList.toggle('active');
    })

    // Closes responsive menu when a scroll trigger link is clicked
    var scrollTriggerList = [].slice.call(document.querySelectorAll('#sidebar-wrapper .js-scroll-trigger'));
    scrollTriggerList.map(scrollTrigger => {
        scrollTrigger.addEventListener('click', () => {
            sidebarWrapper.classList.remove('active');
            menuToggle.classList.remove('active');
            _toggleMenuIcon();
        })
    });

    function _toggleMenuIcon() {
        const menuToggleBars = document.body.querySelector('.menu-toggle > .fa-bars');
        const menuToggleTimes = document.body.querySelector('.menu-toggle > .fa-xmark');
        if (menuToggleBars) {
            menuToggleBars.classList.remove('fa-bars');
            menuToggleBars.classList.add('fa-xmark');
        }
        if (menuToggleTimes) {
            menuToggleTimes.classList.remove('fa-xmark');
            menuToggleTimes.classList.add('fa-bars');
        }
    }

    // Scroll to top button appear
    document.addEventListener('scroll', () => {
        const scrollToTop = document.body.querySelector('.scroll-to-top');
        if (document.documentElement.scrollTop > 100) {
            if (!scrollToTopVisible) {
                fadeIn(scrollToTop);
                scrollToTopVisible = true;
            }
        } else {
            if (scrollToTopVisible) {
                fadeOut(scrollToTop);
                scrollToTopVisible = false;
            }
        }
    })
})

function fadeOut(el) {
    el.style.opacity = 1;
    (function fade() {
        if ((el.style.opacity -= .1) < 0) {
            el.style.display = "none";
        } else {
            requestAnimationFrame(fade);
        }
    })();
};

function fadeIn(el, display) {
    el.style.opacity = 0;
    el.style.display = display || "block";
    (function fade() {
        var val = parseFloat(el.style.opacity);
        if (!((val += .1) > 1)) {
            el.style.opacity = val;
            requestAnimationFrame(fade);
        }
    })();
};

document.addEventListener("DOMContentLoaded", function () {
    const carouselElement = document.getElementById("newsroomCarousel");
    const categoryElements = document.querySelectorAll(".category-item");

    // bootstrap Carousel 초기화
    const carousel = new bootstrap.Carousel(carouselElement);

    // 캐러셀 이동 시 카테고리 업데이트
    carouselElement.addEventListener("slid.bs.carousel", function (event) {
        updateCategoryHighlight(event.to);
    });

    // 카테고리 클릭 시 해당 캐러셀 슬라이드로 이동
    categoryElements.forEach((category, index) => {
        category.addEventListener("click", function () {
            carousel.to(index); // 해당 인덱스로 이동
            updateCategoryHighlight(index); // 카테고리 강조
        });
    });

    // 현재 선택된 카테고리 강조
    function updateCategoryHighlight(activeIndex) {
        categoryElements.forEach((element, index) => {
            if (index === activeIndex) {
                element.style.fontWeight = "bold"; // 클릭된 카테고리 굵게 표시
            } else {
                element.style.fontWeight = "normal"; // 다른 카테고리는 얇게
            }
        });
    }

    // 초기 강조 설정 (첫 번째 항목 기본 선택)
    updateCategoryHighlight(0);
});
