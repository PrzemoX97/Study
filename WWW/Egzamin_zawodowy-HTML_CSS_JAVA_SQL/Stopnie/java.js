function oblicz_k(){
	pole1=document.forms["form1"].pole1.value;
	wynik=document.getElementById("wynik");
	
	if(pole1=="" || isNaN(pole1)) alert("Błąd danych");
	wynik.innerHTML=parseInt(pole1)+273.15+"K";
}
function oblicz_f(){
	pole1=document.forms["form1"].pole1.value;
	wynik=document.getElementById("wynik");
	
	if(pole1=="" || isNaN(pole1)) alert("Błąd danych");
	wynik.innerHTML=parseInt(pole1)*1.8+32+"<sup>o</sup>F";
}