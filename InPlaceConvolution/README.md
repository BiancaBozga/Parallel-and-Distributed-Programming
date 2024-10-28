

Proiectare

Secvențială
Pentru abordarea secvențială, pentru fiecare operație, dacă facem operațiile linie cu linie, trebuie să păstrăm o copie a celor (K - 1) / 2 linii anterioare și a uneia din linia curentă, deci (K + 1) \* M / 2 memorie suplimentară, deoarece atunci când aplicăm operația de convoluție pe un element și îl modificăm imediat, vom pierde valoarea veche (avem nevoie de valoarea veche pt a calcula val convolutiei pt alte elem din matrice).

Pentru marginile matricei, am dus valorile la cea mai apropiată valoare din matrice ( cel mai apropiat vecin pentru fiecare valoare).

Linii
Pentru abordarea paralelă pe linii, sarcina fiecărui thread ar fi să completeze [N / P] linii din matrice, iar primele N % P linii vor fi distribuite în mod egal primelor threaduri.

Similar cu abordarea secvențială, avem nevoie de memorie suplimentară, deoarece valorile vechi din matrice vor fi pierdute după aplicarea operației de convoluție, dar de data asta, pentru fiecare thread, va trebui să păstrăm primele (K + 1) / 2 linii (ultima fiind linia pe care facem operația de convoluție), însă va trebui să păstrăm și următoarele (K - 1) / 2 linii după ultima linie pe care lucrează thread-ul, deoarece acestea vor fi modificate de threadul următor, deci vom avea nevoie de K \* M memorie pentru fiecare thread, adică K \* M \* P memorie suplimentară.

Deoarece momentul în care threadurile obțin resursele de la so pentru a-și face treaba nu poate fi controlat și unele th ar putea fi mai rapide (astfel, un th va termina sarcina și va modifica primele linii înainte ca firul anterior să le copieze), trebuie să punem o barieră (care așteaptă ca toate th să ajungă acolo pentru a o  “ridica”,dupa initializarea cache-ului) după ce copiem, pentru a fi siguri că toate threadurile primesc resursele reale(initiale).


C++ DINAMIC

|Tip Matrice|Nr threads|Timp executie|Metoda|
| :- | :- | :- | :- |
|input10\_10\_3.txt|0-main|0\.00785|sequential|
|input10\_10\_3.txt|2|1\.45281|lines|
|input10\_10\_3.txt|4|1\.9697|lines|
|input10\_10\_3.txt|8|2\.04297|lines|
|||||
|input1000\_1000\_3.txt|0-main|40\.02054|sequential|
|input1000\_1000\_3.txt|2|22\.71797|lines|
|input1000\_1000\_3.txt|4|16\.40087|lines|
|input1000\_1000\_3.txt|8|12\.25808|lines|
|input1000\_1000\_3.txt|16|15\.18328|lines |
|||||
|input10000\_10000\_3.txt|0-main|3889\.589|sequential|
|input10000\_10000\_3.txt|2|1935\.887|lines|
|input10000\_10000\_3.txt|4|1059\.869|lines|
|input10000\_10000\_3.txt|8|713\.8717|lines|
|input10000\_10000\_3.txt|16|566\.6331|lines|

Java 

|Tip Matrice|Nr threads|Timp executie|Metoda||
| :- | :- | :- | :- | :- |
|input10\_10\_3.txt|0-main|0\.13555|sequential||
|input10\_10\_3.txt|2|3\.48815|lines||
||||||
|input1000\_1000\_3.txt|0-main|61\.57573|sequential||
|input1000\_1000\_3.txt|2|48\.4922|lines||
|input1000\_1000\_3.txt|4|62\.85993|lines||
|input1000\_1000\_3.txt|8|95\.07787|lines||
|input1000\_1000\_3.txt|16|137\.5972|lines||
||||||
|input10000\_10000\_3.txt|0-main|1344\.304|sequential||
|input10000\_10000\_3.txt|2|777\.3929|lines||
|input10000\_10000\_3.txt|4|503\.0823|lines||
|input10000\_10000\_3.txt|8|442\.5507|lines||
|input10000\_10000\_3.txt|16|442\.6494|lines||



C++ cu alocare dinamică are in general rezultate mai bune  pt ca matricea și kernelul sunt în heap, deci accesul la elemente este mai rapid.

Cazul n=m=10, k=3

-atât în ***C++*** cât și în ***Java***, abordarea secvențială a fost vizibil mai rapidă, deoarece programele au nevoie de timp pentru a obține threadurile de la sistemul de operare + timpul de creare a unui thread(overhead mare). În restul testelor, abordarea paralelă fiind mai rapidă în ambele cazuri.

Cazul n=m=1000, k=3

***C++***

-abordarea pe linii cu 8 threaduri a fost cea mai eficientă, ușor mai rapidă decât cea cu 16 threaduri, deoarece 1000 este divizibil cu 8, dar nu cu 16. Astfel, pentru abordarea cu 8 threaduri, fiecare thread a procesat 125 de linii, în timp ce în cazul celor 16 threaduri, primele 8 au procesat 76 de linii (76.000 elemente), iar restul de 8 au procesat 75 de linii (75.000 elemente). De asemenea, timpul necesar pentru a obține mai multe threaduri ar fi putut contribui la diferența de timp.

***Java***

` `-abordarea pe linii cu 2 fire a fost mai rapidă, probabil deoarece Java obține threadurile mai lent (posibil pentru că programul rulează pe JVM, nu direct pe sistemul de operare =>gestionarea threadurilor este mai dificilă).

Cazul n=m=10000, k=3

***C++***

pentru C++, abordarea pe linii cu 16 fire a fost cea mai rapidă .

***Java***

` `Pentru Java, abordarea cu 8 fire a fost mai eficientă, probabil din același motiv ca mai sus.

Comparând Java și C++, observăm că C++ are o performanta mai buna pentru primele două teste, iar Java pentru al treilea. Principalele motive sunt,dupa parerea mea:

- Java funcționează mai slab pentru primele teste deoarece JVM trebuie să fie lansată (pe care rulează programul Java), ceea ce ia timp.
- Java funcționează mai bine pentru ultimele teste probabil datorită utilizării System.arraycopy, care permite mutarea unui bloc întreg de date dintr-o adresă de memorie în alta (sau o mare parte din acesta, deoarece Java aplică sigur unele optimizări aici), în timp ce în C++ am copiat element cu element, iar optimizările realizate sunt probabil mai slabe decât în Java.

