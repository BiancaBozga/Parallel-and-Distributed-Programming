Documentație Proiect PPD1
Analiza Cerințelor
Tema: Dezvoltarea unui program care aplică o operație de convoluție pe o matrice F de dimensiuni 
𝑛
×
𝑚
n×m, utilizând o matrice de convoluție C de dimensiuni 
𝑘
×
𝑘
k×k. Programul va fi implementat atât secvențial, cât și paralel, folosind threaduri.

Cerințe de Implementare
Program Secvențial:

Scrierea unui program care aplică convoluția secvențial pe matricea F utilizând matricea de convoluție C.
Program Paralel:

Utilizarea a 
𝑝
p threaduri pentru calculul convoluției.
Împărțirea sarcinilor pe threaduri folosind două metode:
Împărțire pe Linii: Fiecare thread primește mai multe linii din matricea F.
Împărțire pe Coloane: Fiecare thread primește mai multe coloane din matricea F.
Metode Avansate (opțional, pentru puncte suplimentare):

Bloc: Împărțirea matricei în submatrici, care sunt alocate fiecărui thread.
Distribuție Bazată pe o Funcție de Distribuție Delta: Atribuirea indecșilor fiecărui thread pe baza unei funcții de distribuție (liniară sau ciclică).
Date de Intrare și Ieșire
Fișier de Intrare: Matricea F și matricea de convoluție C trebuie generate și stocate anterior într-un fișier text.
Fișier de Ieșire: Programul trebuie să scrie timpul rezultat în urma aplicării convoluției în consolă (fprintf pentru C, respectiv System.out pentru Java).
Constrângeri
Tipuri de Matrici:

Pentru C++:
Alocare statică: Matricile sunt definite cu o dimensiune maximă.
Alocare dinamică: Matricile sunt alocate dinamic.
Pentru Java: Nu sunt impuse restricții specifice, dar trebuie utilizată metoda standard de alocare a matricilor.
Threading:

Trebuie utilizate threaduri în mod explicit, fără utilizarea executorilor sau bibliotecilor de threading de nivel înalt.
Testare:

Timpul de execuție trebuie măsurat pentru diverse dimensiuni ale matricilor și număr de threaduri, conform următoarelor seturi de date:
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
Cerințe de Documentare și Analiză
Analiza Performanței:
Compararea performanței între implementările secvențiale și paralele.
Compararea diferitelor variante de împărțire pe threaduri.
Compararea performanței implementării în Java cu cea în C++.
Analiza performanței implementării cu alocare statică vs. dinamică în C++.
Constrângeri Adiționale
Fișier de Intrare: Toate testele vor folosi același fișier de intrare: „input.txt” trebuie să fie identic pentru toate rularile.
Rezultate și Observații
Java - input10_10_3.txt:

Timpul de execuție pentru metoda secvențială este cel mai mic. Metodele paralele nu oferă un avantaj semnificativ pentru dimensiuni mici, din cauza overhead-ului.
Java - input10_10000_5.txt:

Metodele bazate pe linii sunt cele mai eficiente, datorită gestionării eficiente a memoriei.
Java - input1000_1000_5.txt:

Metoda secvențială devine ineficientă, cele mai rapide metode fiind cele de linearizare, cu un număr mic de threaduri oferind cele mai bune rezultate.
Java - input10000_10_5.txt:

Timpul de execuție pentru metodele cu linii rămâne superior celor cu coloane. Performanțele variază semnificativ în funcție de numărul de threaduri.
C++ (Static) - input10_10_3.txt:

Metoda secvențială este mai rapidă pentru dimensiuni mici din cauza costurilor de gestionare a threadurilor.
C++ (Dynamic) - input1000_1000_5.txt:

Metodele paralele, în special cele bazate pe batch, au timpi mai buni pentru matrice mari.
Analiza Performanței
Accesul la memorie:
Metodele de tip columns batch sunt cele mai puțin eficiente din cauza accesului la zone îndepărtate de memorie, în timp ce liniarizarea permite un acces rapid la date, reducând cache misses.
Concluzie
Rezultatele sugerează că alegerea metodei de împărțire a sarcinilor pe fire, precum și dimensiunea și structura matricei, au un impact semnificativ asupra performanței. Este esențial să se adapteze metoda aleasă în funcție de dimensiunea și structura matricei pentru a optimiza eficiența execuției.
