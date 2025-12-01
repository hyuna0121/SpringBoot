const fileBtn = document.getElementById("fileAdd");
const fileArea = document.getElementById("files");

let idx = 0;

fileBtn.addEventListener('click', () => {
	
	if (idx > 4) {
		alert('최대 5개까지 추가 가능합니다.');
		return;
	}
	
	let div = document.createElement("div");
	let fileInput = document.createElement("input");
	let btn = document.createElement("button");
	
  	fileInput.type = "file";
	fileInput.name = "attach";
	
	btn.type = "button";
	btn.innerHTML = "X";
	btn.classList.add('delBtn');
	
  	div.append(fileInput);
	div.append(btn)
	fileArea.append(div);
	
	idx++;
});

fileArea.addEventListener('click', (e) => {
	let element = e.target;
	if (element.classList.contains('delBtn')) {
		if (idx > 0) idx--;
		element.parentElement.remove();
	}
});