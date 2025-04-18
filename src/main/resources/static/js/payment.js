const user_email = response.req_user_email;
const username = response.req_username;

const buyButton = document.getElementById('payment');
buyButton.setAttribute('onclick', `kakaoPay('${user_email}', '${username}')`);

var IMP = window.IMP;

function kakaoPay(useremail, username) {
    if (!confirm("구매 하시겠습니까?")) return;

    if (!localStorage.getItem("access")) {
        alert('로그인이 필요합니다!');
        return;
    }

    const emoticonName = document.getElementById('title').innerText;

    // 고유 merchant_uid는 매번 생성되어야 함
    const now = new Date();
    const makeMerchantUid = "IMP" + now.getHours() + now.getMinutes() + now.getSeconds() + now.getMilliseconds();

    IMP.init("가맹점식별코드"); // 실제 코드로 교체해야 함

    IMP.request_pay({
        pg: 'kakaopay.TC0ONETIME',
        pay_method: 'card',
        merchant_uid: makeMerchantUid,
        name: emoticonName,
        amount: 100,
        buyer_email: useremail,
        buyer_name: username,
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
                    alert(`error:[${result.status}]\n결제는 되었으나 서버 처리에 실패했습니다.\n관리자에게 문의하세요.`);
                }
            } catch (err) {
                console.error("서버 요청 중 에러 발생:", err);
                alert("결제는 되었으나 서버 통신 중 문제가 발생했습니다.");
            }
        } else {
            alert(`결제 실패: ${rsp.error_msg}`);
        }
    });
}
