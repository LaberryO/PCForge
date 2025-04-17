/*!
* Start Bootstrap - Agency v7.0.12 (https://startbootstrap.com/theme/agency)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-agency/blob/master/LICENSE)
*/
//
// Scripts
// 

window.addEventListener('DOMContentLoaded', event => {

    // Navbar shrink function
    var navbarShrink = function () {
        const navbarCollapsible = document.body.querySelector('#mainNav');
        if (!navbarCollapsible) {
            return;
        }
        if (window.scrollY === 0) {
            navbarCollapsible.classList.remove('navbar-shrink')
        } else {
            navbarCollapsible.classList.add('navbar-shrink')
        }

    };

    // Shrink the navbar 
    navbarShrink();

    // Shrink the navbar when page is scrolled
    document.addEventListener('scroll', navbarShrink);

    //  Activate Bootstrap scrollspy on the main nav element
    const mainNav = document.body.querySelector('#mainNav');
    if (mainNav) {
        new bootstrap.ScrollSpy(document.body, {
            target: '#mainNav',
            rootMargin: '0px 0px -40%',
        });
    };

    // Collapse responsive navbar when toggler is visible
    const navbarToggler = document.body.querySelector('.navbar-toggler');
    const responsiveNavItems = [].slice.call(
        document.querySelectorAll('#navbarResponsive .nav-link')
    );
    responsiveNavItems.map(function (responsiveNavItem) {
        responsiveNavItem.addEventListener('click', () => {
            if (window.getComputedStyle(navbarToggler).display !== 'none') {
                navbarToggler.click();
            }
        });
    });

});

document.addEventListener("DOMContentLoaded", function () {
    const items = document.querySelectorAll(".portfolio-item");
    let currentIndex = 0; // 현재 인덱스 (세로 기준)
    
    function renderPortfolio() {
        items.forEach((item, index) => {
            item.style.display = "none"; // 모두 숨김
        });

        for (let i = 0; i < 3; i++) {
            let firstIdx = (currentIndex + i) % 6; // 1번째 행
            let secondIdx = firstIdx + 6; // 2번째 행
            
            if (items[firstIdx]) items[firstIdx].style.display = "block";
            if (items[secondIdx]) items[secondIdx].style.display = "block";
        }
    }

    document.getElementById("nextBtn").addEventListener("click", function () {
        currentIndex = (currentIndex + 1) % 6; // 0 → 1 → 2 → 3 → 4 → 5 → 0 순환
        renderPortfolio();
    });

    document.getElementById("prevBtn").addEventListener("click", function () {
        currentIndex = (currentIndex - 1 + 6) % 6; // 5 → 4 → 3 → 2 → 1 → 0 순환
        renderPortfolio();
    });

    renderPortfolio();
});


/*<section class="page-section bg-light" id="portfolio">
    <div class="container">
        <div class="text-center">
            <h2 class="section-heading text-uppercase">Portfolio</h2>
            <h3 class="section-subheading text-muted">Lorem ipsum dolor sit amet consectetur.</h3>
        </div>
        <div class="row" id="portfolioContainer">
            <div th:each="item, iterStat : ${portfolioItems}" 
                 class="col-lg-4 col-sm-6 mb-4 portfolio-item" 
                 th:data-index="${iterStat.index}">
                <a class="portfolio-link" data-bs-toggle="modal" th:href="'#portfolioModal' + ${item.id}">
                    <div class="portfolio-hover">
                        <div class="portfolio-hover-content"><i class="fas fa-plus fa-3x"></i></div>
                    </div>
                    <img class="img-fluid" th:src="@{${item.imageUrl}}" alt="..." />
                </a>
                <div class="portfolio-caption">
                    <div class="portfolio-caption-heading" th:text="${item.title}"></div>
                    <div class="portfolio-caption-subheading text-muted" th:text="${item.subtitle}"></div>
                </div>
            </div>
        </div>
        <div class="text-center mt-3">
            <button id="prevBtn" class="btn btn-primary">Previous</button>
            <button id="nextBtn" class="btn btn-primary">Next</button>
        </div>
    </div>
</section>*/