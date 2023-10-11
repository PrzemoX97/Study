<html>
<head>
<title>Wynajmujemy samochody</title>
<meta charset="UTF-8"/>
<link rel="stylesheet" type="text/css" href="styl.css">

</head>
<body>
<section id="baner">
<h1>Wynajmujemy samochody</h1>
</section>
<section id="lewy">
<h2>DZUS AJSHDGASJHDGAS</h2>





<?php
$connect = mysqli_connect('localhost', 'root','', 'komis');
$zapytanie = mysqli_query($connect, "select id, model, kolor from samochody where marka='Toyota' and rocznik='2014'");
if(mysqli_num_rows($zapytanie)>0){
	while($wypisz = mysqli_fetch_array($zapytanie)){
		echo" ".$wypisz['id']." Toyota ".$wypisz['model']." Kolor ".$wypisz['kolor']."</br>";
	}
}

?>


<h2>WSZYSTJUWE DISTEOBE SAICGIDHYT</h2>
<?php
$connect=mysqli_connect('localhost','root','','komis');
$zapytanie=mysqli_query($connect,"SELECT id,marka,model,rocznik from samochody");
if(mysqli_num_rows($zapytanie)>0)
{
	while($row=mysqli_fetch_array($zapytanie))
	{
		echo $row['id']." ".$row['marka']." ".$row['model']." ".$row['rocznik']."</br>";
	}
}

?>

</section>
<section id="srodkowy">
<h2>Zamowienoe auta z numerami telefonów klientow</h2>
<?php
$connect=mysqli_connect('localhost','root','','komis');
if(!$connect)
{
	echo "blad polaczenia z baza wal sie na lep pal gumne";
}
$zapytanie=mysqli_query($connect,"select Samochody_id,model,telefon from zamowienia inner join samochody on samochody.id=zamowienia.Samochody_id");
if(mysqli_num_rows($zapytanie)>0)
{
	while($row=mysqli_fetch_array($zapytanie))
	{
		echo $row['Samochody_id']." ".$row['model']." ".$row['telefon']."</br>";
	}
}
mysqli_close($connect);
?>
</section>
<section id="prawy">
<h2>NAsza oferta</h2>
<ul>
<li>Fiat</li>
<li>Toyota</li>
<li>Opel</li>
</ul>
<p>Tu pobierzewsz nasza<a href="komis.sql" >baze danych</a></p>
<p>autor strony: 00000000000</p>
</section>

</body>
</html>