
function Product(pic,name,price,qte){
  this.pic = pic;
  this.name = name;
  this.price =price;
  this.qte =qte;
}


let products = [];
const p1 = new Product("images/8min.jpg",'Thétoscope',70,1);
products.push(p1);

function populateTableList() {
   let  listOfProducts ='';
   products.forEach(prod=>
    listOfProducts += `
      <tr >
        <td><img src=${prod.pic}  style="width:200px; height:200px; "></td>
        <td class="textcenter">${prod.name}</td>
        <td class="table-td-th" class="textcenter">${prod.price}DH</td>
        <td class="textcenter"><input style="width:60px; " border: red solid; type="number" value="${prod.qte}"></td>
        <td class="textcenter"><button id="btn">supprimer</button></td>
      </tr>
      `   
  )
 
document.getElementById('productList').innerHTML = listOfProducts;
}