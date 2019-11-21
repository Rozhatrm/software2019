software2019

1. Sørg for at du lastet ned nyeste versjon av IntelliJ. 

2. Last ned javafx (SDK - 13) https://gluonhq.com/products/javafx/ 

3. Last ned zip-mappa fra github https://github.com/Rozhatrm/software2019 

4. Åpne MainJavaFX i IntelliJ 

5. Import Changes fra Maven. (Kommer opp nederst til høyre) 

6. Ordne prosjekt strukturen. Gå inn på file --> Project Structure --> Project --> Project SDK --> 13

7. Language level til Default. 

8. Dependecies --> Module SDK skal være Project SDK (13). 

9. Husk å legge til lib-mappen (lastet ned fra SDK). 
	- Se til at SDK mappa er 13. 

10. File ---> Settings ----> Path Variables 

	Legg til PATH_TO_FX og Value skal være lib-mappa.  
11. ----> Java Compiler 

	Her skal Java Compiler også byttes til 13.  

12. Edit Configuration ---> Application ---> MainJavaFX 



KJØRING AV TESTENE: 
1. Åpne Test mappa ---> Java ---> Høyre klikk ApplicationTest ---> “Run ApplicationTest” 