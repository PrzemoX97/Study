function sprawdz()
{	
	sila=document.getElementById('sila');
	haslo=document.forms["form1"].haslo.value;
	var reg = /([a-z])([0-9])/;
	var reg1 = /([0-9])([a-z])/;
	
	if(haslo.length=="") sila.innerHTML="<p style='color:red;'>HASĹO JEST PUSTE</p>";
	else if(reg.test(haslo) || reg1.test(haslo) && haslo.length>=7) sila.innerHTML="<p style='color:green;'>HASĹO JEST DOBRE</p>";
	else if(reg.test(haslo) || reg1.test(haslo) && haslo.length>=4 && haslo.length<=6) sila.innerHTML="<p style='color:blue;'>HASĹO JEST Ĺ?REDNIE</p>";
	else sila.innerHTML="<p style='color:yellow;'>HASĹO JEST SĹABE</p>";
	
}