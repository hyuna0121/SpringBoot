/**
 * 
 */
const list = document.getElementById('list');
let num = list.getAttribute('data-product-num');

fetch(`./commentList?productNum=${num}`)
.then(res => res.json())
.then(res => {
	console.log(res);

	res.forEach(dto => {
		let tr = document.createElement('tr');
		let td = document.createElement('td');
		td.innerText = dto.username;
		tr.append(td);
		td = document.createElement('td');
		td.innerText = dto.boardContents;
		tr.append(td);
		td = document.createElement('td');
		td.innerText = dto.boardDate;
		tr.append(td);
		
		list.append(tr);
	});
	
	
})
.catch(e => console.log(e));