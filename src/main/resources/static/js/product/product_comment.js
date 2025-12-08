/**
 * 
 */
const list = document.getElementById('list');
let num = list.getAttribute('data-product-num');
const addComment = document.getElementById('addComment');
const closeComment = document.getElementById('closeComment');
const commentContents = document.getElementById('commentContents');

const addCartBtn = document.getElementById('addCartBtn');

commentList(1);

addComment.addEventListener('click', () => {
	const param = new URLSearchParams();
	param.append("productNum", num);
	param.append("boardContents", commentContents.value);
	
	fetch('commentAdd', {
		method:"POST",
		body: param
	})
	.then(r => r.json())
	.then(r => {
		console.log(r);
		if (r > 0) commentList(1);
	})
	.catch(e => console.log(e))
	.finally(() => {
		closeComment.click();
		commentContents.value='';
	});
});

/*const pageLink = document.getElementsByClassName('page-link');
for (p of pageLink) {
	p.addEventListener('click', () => {
		console.log(1);
	});
}*/
/* 부모에 이벤트를 걸어야 함 */ 
list.addEventListener('click', (e) => {
	let t = e.target;
	if (t.classList.contains('page-link')) {
		let p = t.getAttribute('data-pager-num');
		commentList(p);
	} else if (t.classList.contains('deleteComment')) {
		if (confirm('정말 삭제하시겠습니까?')) {
			commentDelete();
		}
	}
});

addCartBtn.addEventListener('click', () => {
	addCart();
	if (confirm('장바구니에 상품을 담았습니다. 장바구니로 이동하시겠습니까?')) {
		alert('Yes');
	}
});

function commentList(page) {
	fetch(`commentList?productNum=${num}&page=${page}`)
		.then(res => res.text())
		.then(res => list.innerHTML = res)
		.catch(e => console.log(e));
}

function commentDelete() {
	console.log('delete');
}

function addCart() {
	fetch(`addCart?productNum=${num}`)
		.then(res => res.json())
		.then(res => {
			console.log(res);
			if (res > 0 && confirm('장바구니에 상품을 담았습니다. 장바구니로 이동하시겠습니까?')) {
				location.href='./cart';
			}
		})
		.catch(e => console.log(e));
}
