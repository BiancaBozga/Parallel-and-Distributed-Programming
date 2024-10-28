Documentatie-PPD1
ANALIZA CERINTELOR
Tema implică dezvoltarea unui program care să aplice o operație de convoluție pe o matrice 
𝐹
F de dimensiuni 
𝑛
×
𝑚
n×m, utilizând o matrice de convoluție 
𝐶
C de dimensiuni 
𝑘
×
𝑘
k×k. Programul trebuie implementat atât secvențial, cât și paralel, cu utilizarea de threaduri.

Cerințe de implementare:
Program secvențial:

Scrie un program care aplică convoluția în mod secvențial pe matricea 
𝐹
F folosind matricea de convoluție 
𝐶
C.
Program paralel:

Folosirea a 
𝑝
p threaduri pentru calculul convoluției.
Împărțirea sarcinilor de calcul pe threaduri folosind două metode:
Împărțire pe linii: Mai multe linii din matricea 
𝐹
F sunt alocate fiecărui thread.
Împărțire pe coloane: Mai multe coloane din matricea 
𝐹
F sunt alocate fiecărui thread.
Metode avansate (opțional, pentru puncte suplimentare):

Bloc: Împărțirea matricii în submatrici, care sunt alocate fiecărui thread.
Distribuție bazată pe o funcție de distribuție delta: Atribuirea indecșilor fiecărui thread pe baza unei funcții de distribuție (liniară sau ciclică).
Date de intrare și ieșire:
Fișier de intrare: Matricea 
𝐹
F și matricea de convoluție 
𝐶
C trebuie generate și stocate anterior într-un fișier text.
Fișier de ieșire: Programul trebuie să scrie timpul rezultat în urma aplicării convoluției în consolă (utilizând fprintf sau System.out).
Constrângeri:
Tipuri de matrici:

Pentru C++:
Alocare statică: Matricile sunt definite cu o dimensiune maximă.
Alocare dinamică: Matricile sunt alocate dinamic.
În Java: Nu sunt impuse restricții specifice, dar trebuie folosită metoda standard de alocare a matricilor.
Threading: Trebuie folosite threaduri în mod explicit, fără utilizarea executorilor sau bibliotecilor de threading de nivel înalt.

Testare: Timpul de execuție trebuie măsurat pentru diverse dimensiuni ale matricilor și număr de threaduri, conform următoarelor seturi de date:

𝑁
=
𝑀
=
10
N=M=10, 
𝑛
=
𝑚
=
3
n=m=3, 
𝑝
=
4
p=4
𝑁
=
𝑀
=
1000
N=M=1000, 
𝑛
=
𝑚
=
5
n=m=5, 
𝑝
=
2
,
4
,
8
,
16
p=2,4,8,16
𝑁
=
10
N=10, 
𝑀
=
10000
M=10000, 
𝑛
=
𝑚
=
5
n=m=5, 
𝑝
=
2
,
4
,
8
,
16
p=2,4,8,16
𝑁
=
10000
N=10000, 
𝑀
=
10
M=10, 
𝑛
=
𝑚
=
5
n=m=5, 
𝑝
=
2
,
4
,
8
p=2,4,8
𝑁
=
10000
N=10000, 
𝑀
=
10000
M=10000, 
𝑛
=
𝑚
=
5
n=m=5, 
𝑝
=
2
,
4
,
8
p=2,4,8
Performanță:
Fiecare test trebuie executat de 10 ori, iar timpul de execuție raportat va fi media aritmetică a celor 10 rulari.
Cerințe de documentare și analiză:
Analiza performanței:
Compară performanța între implementările secvențiale și paralele.
Compară diferitele variante de împărțire pe threaduri.
Compară performanța implementării în Java cu cea în C++.
Analiza performanței implementării cu alocare statică vs alocare dinamică în C++.
Constrângeri adiționale:
Folosirea aceluiași fișier de intrare pentru toate testele: Fișierul „input.txt” trebuie să fie identic pentru toate rularile.
Java
Tabele cu date
1. input10_10_3.txt

Tip Matrice	Nr threads	Timp execuție	Metoda
input10_10_3.txt	1	0.00981	sequential
input10_10_3.txt	4	0.56233	lines cycle
input10_10_3.txt	4	0.65127	lines batch
input10_10_3.txt	4	0.52755	columns batch
input10_10_3.txt	4	0.7116	columns cycle
input10_10_3.txt	4	0.64214	linearization batch
input10_10_3.txt	4	0.54108	linearization cycle
2. input10_10000_5.txt

Tip Matrice	Nr threads	Timp execuție	Metoda
input10_10000_5.txt	1	2.43708	sequential
input10_10000_5.txt	4	1.42698	lines cycle
input10_10000_5.txt	4	1.45267	lines batch
input10_10000_5.txt	4	3.55268	columns batch
input10_10000_5.txt	4	1.59031	linearization batch
input10_10000_5.txt	4	1.63225	linearization cycle
input10_10000_5.txt	4	1.61954	columns cycle
3. input1000_1000_5.txt

Tip Matrice	Nr threads	Timp execuție	Metoda
input1000_1000_5.txt	1	22.43525	sequential
input1000_1000_5.txt	4	13.88335	lines cycle
input1000_1000_5.txt	4	13.14088	columns cycle
input1000_1000_5.txt	4	12.00404	linearization cycle
input1000_1000_5.txt	4	12.90631	linearization batch
input1000_1000_5.txt	4	12.44664	lines batch
input1000_1000_5.txt	4	14.47051	columns batch
4. input10000_10_5.txt

Tip Matrice	Nr threads	Timp execuție	Metoda
input10000_10_5.txt	1	2.63463	sequential
input10000_10_5.txt	4	1.76308	lines cycle
input10000_10_5.txt	4	1.84568	columns cycle
input10000_10_5.txt	4	1.93251	linearization cycle
input10000_10_5.txt	4	1.80505	linearization batch
input10000_10_5.txt	4	1.8028	columns batch
input10000_10_5.txt	4	1.80475	lines batch
5. input10_10000_5.txt

Tip Matrice	Nr threads	Timp execuție	Metoda
input10_10000_5.txt	1	2.35432	sequential
input10_10000_5.txt	2	1.93168	lines batch
input10_10000_5.txt	2	1.91336	columns batch
input10_10000_5.txt	2	1.99186	linearization batch
input10_10000_5.txt	2	1.99525	linearization cycle
input10_10000_5.txt	2	2.10213	lines cycle
C++
Tabele cu date
1. input10_10_3.txt

Tip Matrice	Nr threads	Timp execuție	Metoda
input10_10_3.txt	1	0.00827	sequential
input10_10_3.txt	4	0.52221	lines cycle
input10_10_3.txt	4	0.59324	lines batch
input10_10_3.txt	4	0.48716	columns batch
input10_10_3.txt	4	0.64514	columns cycle
input10_10_3.txt	4	0.64222	linearization batch
input10_10_3.txt	4	0.54622	linearization cycle
2. input10_10000_5.txt

Tip Matrice	Nr threads	Timp execuție	Metoda
input10_10000_5.txt	1	2.32145	sequential
input10_10000_5.txt	4	1.27688	lines cycle
input10_10000_5.txt	4	1.49821	lines batch
input10_10000_5.txt	4	3.22132	columns batch
input10_10000_5.txt	4	1.35013	linearization batch
input10_10000_5.txt	4	1.40567	linearization cycle
input10_10000_5.txt	4	1.39282	columns cycle
3. input1000_1000_5.txt

Tip Matrice	Nr threads	Timp execuție	Metoda
input1000_1000_5.txt	1	20.12125	sequential
input1000_1000_5.txt	4	11.12899	lines cycle
input1000_1000_5.txt	4	10.74162	columns cycle
input1000_1000_5.txt	4	9.44092	linearization cycle
input1000_1000_5.txt	4	10.05673	linearization batch
input1000_1000_5.txt	4	10.12739	lines batch
input1000_1000_5.txt	4	12.20157	columns batch
4. input10000_10_5.txt

Tip Matrice	Nr threads	Timp execuție	Metoda
input10000_10_5.txt	1	2.54062	sequential
input10000_10_5.txt	4	1.65053	lines cycle
input10000_10_5.txt	4	1.67524	columns cycle
input10000_10_5.txt	4	1.75219	linearization cycle
input10000_10_5.txt	4	1.72103	linearization batch
input10000_10_5.txt	4	1.73042	columns batch
input10000_10_5.txt	4	1.75219	lines batch
5. input10_10000_5.txt

Tip Matrice	Nr threads	Timp execuție	Metoda
input10_10000_5.txt	1	2.25075	sequential
input10_10000_5.txt	2	1.80963	lines batch
input10_10000_5.txt	2	1.85132	columns batch
input10_10000_5.txt	2	1.80051	linearization batch
input10_10000_5.txt	2	1.88475	linearization cycle
input10_10000_5.txt	2	1.93945	lines cycle
Observații din teste:
Java
La dimensiuni mici, metoda secvențială este cea mai rapidă, sugerând că overhead-ul generat de gestionarea firelor de execuție afectează performanța.
La dimensiuni mari, metodele bazate pe linii sunt cele mai eficiente, datorită gestionării mai bune a datelor în memorie.
C++ - Alocare statică
La dimensiuni mici, metoda secvențială este mult mai rapidă.
La dimensiuni mari, metodele paralele, în special cele bazate pe batch, obțin cele mai bune rezultate.
C++ - Alocare dinamică
Metodele secvențiale sunt mai rapide pentru matricile mici, dar devin ineficiente pe măsură ce dimensiunea matricii crește.
Distribuția pe linii oferă cele mai bune rezultate în cazul matricilor cu multe linii.
Concluzii generale:
Este important să se adapteze metoda aleasă în funcție de dimensiunea și structura matricii pentru a îmbunătăți eficiența execuției.
Accesul la coloane diferite reduce performanța din cauza accesului necontiguu în memorie, ceea ce afectează negativ utilizarea cache-ului.
