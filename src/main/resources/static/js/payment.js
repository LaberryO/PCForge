window.addEventListener('DOMContentLoaded', () => {
    const buyBtn = document.getElementById("payment");
    if (!buyBtn) return;

    const email = buyBtn.dataset.useremail;
    const name = buyBtn.dataset.username;
    const price = buyBtn.dataset.price;
    const productName = document.getElementById('title')?.innerText || buyBtn.dataset.productname;

    buyBtn.addEventListener('click', async () => {
        if (!confirm("구매 하시겠습니까?")) return;

		try {
		      // 로그인 상태를 서버로부터 확인
		      const res = await fetch("/api/auth/check", {
		          method: "GET",
		          credentials: "include"  // 쿠키 포함해서 인증 정보 전송
		      });

		      if (res.status === 401) {  // 401 Unauthorized
		          alert("로그인이 필요합니다!");
		          return;
		      }

		      // 로그인 되어있으면 구매 처리
		      alert("구매가 시작됩니다!");
		      // 예: 구매 요청 등...
		      
		  } catch (err) {
		      console.error("오류 발생:", err);
		      alert("서버와의 연결에 문제가 있습니다.");
		  }
        const now = new Date();
        const merchantUid = "IMP" + now.getHours() + now.getMinutes() + now.getSeconds() + now.getMilliseconds();

        const IMP = window.IMP;
        IMP.init("imp45362878"); // 본인의 실제 가맹점 식별코드 입력

        IMP.request_pay({
            pg: "kakaopay.TC0ONETIME",
            pay_method: "card",
            merchant_uid: merchantUid,
            name: productName,
            amount: price,
            buyer_email: email,
            buyer_name: name,
        }, async function (rsp) {
            if (rsp.success) {
                console.log("[결제 성공]", rsp);

                try {
                    const result = await fetch('/api/payment/complete', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                            'Authorization': `Bearer ${localStorage.getItem("access")}`
                        },
                        body: JSON.stringify({
                            imp_uid: rsp.imp_uid,
                            merchant_uid: rsp.merchant_uid,
                            paid_amount: rsp.paid_amount,
                            buyer_email: rsp.buyer_email,
                            buyer_name: rsp.buyer_name,
                            product_name: rsp.name,
                            pay_method: rsp.pay_method,
                            pg_provider: rsp.pg_provider
                        })
                    });

                    if (result.status === 200) {
                        alert('결제 완료!');
                        window.location.reload();
                    } else {
                           window.location.href = "/shop/completed";
                    }
                } catch (err) {
                    console.error("서버 요청 중 에러 발생:", err);
                    alert("결제는 되었으나 서버 통신 중 문제가 발생했습니다.");
                }
            } else {
                alert(`결제 실패: ${rsp.error_msg}`);
            }
        });
    });
});
