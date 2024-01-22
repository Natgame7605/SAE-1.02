//Importation des extensions
import extensions.CSVFile;

class HerosDuSavoir extends Program {
    //Variables globales
    final char RETOUR = '\n';
    final String MECHANT = "<";

    //Création des String d'affichage
    final String LOGO_FULL = fileAsString("./ressources/logo/logo_full.txt");
    final String LOGO1 = fileAsString("./ressources/logo/heros.txt");
    final String LOGO2 = fileAsString("./ressources/logo/heros_du.txt");
    final String INTRO = fileAsString("./ressources/intro.txt");


    //****************************************************************/
    //                             Tests                             //
    //****************************************************************/

    void testInitPerso(){
        Personnage perso = new Personnage();
        perso.sprite = ">";
        perso.fichier = "fichier";
        perso.numPerso = 1;
        perso.nomPerso = "test";
        Personnage perso2 = initPerso(">", "test", "fichier", 1);
        assertEquals(perso.sprite, perso2.sprite);
        assertEquals(perso.nomPerso, perso2.nomPerso);
        assertEquals(perso.fichier, perso2.fichier);
        assertEquals(perso.numPerso, perso2.numPerso);
    }

    void testPersonnageChoisi(){
        Personnage perso = initPerso(fileAsString("./ressources/personnages/archer.txt"), "Archer" ,"Géometrie", 2);
        Personnage perso2 = initPerso(fileAsString("./ressources/personnages/alchimiste.txt"), "Alchimiste" ,"Science", 3);
        assertEquals(perso.sprite, personnageChoisi(2).sprite);
        assertEquals(perso.nomPerso, personnageChoisi(2).nomPerso);
        assertEquals(perso.fichier, personnageChoisi(2).fichier);
        assertEquals(perso.numPerso, personnageChoisi(2).numPerso);
        assertEquals(perso2.sprite, personnageChoisi(3).sprite);
        assertEquals(perso2.nomPerso, personnageChoisi(3).nomPerso);
        assertEquals(perso2.fichier, personnageChoisi(3).fichier);
        assertEquals(perso2.numPerso, personnageChoisi(3).numPerso);
    }

    void testToStringTerrain(){
        String[] terrain = new String[]{">",null,null,null,null,"^", null,null,null,null,"<"};
        String result = " >  ___  ___  ___  ___  ^  ___  ___  ___  ___  < ";
        assertEquals(result, toStringTerrain(terrain));
    }


    void testToStringQuestions(){
        String[][] questions = new String[2][5];
        questions[0][0] = "Combien de côté a un triangle";
        questions[0][1] = "3";
        questions[0][2] = "2";
        questions[0][3] = "4";
        questions[0][4] = "5";
        String result = "Combien de côté a un triangle"+ RETOUR +
                        "1. 3" + RETOUR +
                        "2. 2" + RETOUR +
                        "3. 4" + RETOUR +
                        "4. 5" + RETOUR ;
        assertEquals(result, toStringQuestions(questions, 0));
    }


    void testReponse(){
        String[][] questions = new String[2][5];
        questions[0][0] = "Combien de côté a un triangle";
        questions[0][1] = "3";
        questions[0][2] = "2";
        questions[0][3] = "4";
        questions[0][4] = "5";
        assertEquals(true, reponse(questions, "3", 0, 1));
        assertEquals(false, reponse(questions, "3", 0, 2));

    }

    //****************************************************************/
    //                          Fonctions                            //
    //****************************************************************/

    //Initialise les personnages
    Personnage initPerso(String sprite, String nomPerso, String fichier, int numPerso){
        Personnage perso = new Personnage();
        perso.sprite = sprite;
        perso.fichier = fichier;
        perso.numPerso = numPerso;
        perso.nomPerso = nomPerso;
        return perso;
    }

    //Affiche le personnage
    String printPerso(Personnage perso){
        return perso.sprite;
    }

    //Affiche le menu
    void printMenu(){
        clearScreen();
        println(LOGO_FULL + fileAsString("./ressources/menu.txt"));
        println();
    }

    //Affiche l'animation d'accueil
    void printAccueil(){
        clearScreen(); 
        println(LOGO1);
        delay(1000);
        clearScreen(); 
        println(LOGO2);
        delay(1000);
        clearScreen(); 
        println(LOGO_FULL);
        delay(3000);
        clearScreen(); 
    }

    //Affiche le classement
    void printClassement(){
        clearScreen();
        println(fileAsString("./ressources/classement.txt") + RETOUR);
        println(toStringClassement(loadClassement()));
        println();
        println("Entrez 1 pour quitter");
        choixGlobal('1');
        clearScreen();
    }

    //Transforme le tableau de string en string
    String toStringClassement(String[][] tab){
        String result = "";
        for(int idxC = 0; idxC <length(tab, 2); idxC++){
            result = result + tab[0][idxC];
            for(int taille = length(tab[0][idxC]); taille < 21; taille++){
                result = result + " ";
            }
        }        
        result = result + RETOUR;
        for(int idxL = length(tab)-2; idxL >length(tab)-11 && idxL>0; idxL= idxL -1){
            for(int idxC = 0; idxC <length(tab, 2); idxC++){
                result = result + tab[idxL][idxC];
                for(int taille = length(tab[idxL][idxC]); taille < 21; taille++){
                    result = result + " ";
                }
            }
            result = result + RETOUR;        
        }
        return result;
    }

    //Charge le fichier CSV de classement
    String[][] loadClassement(){
        CSVFile fichier = loadCSV("./ressources/CSV/Classement/Classement.csv", ',');
        int tColonne = columnCount(fichier);
        int tLigne = rowCount(fichier);
        String[][] classement = new String[tLigne+1][tColonne];
        for (int idxL = 0; idxL < tLigne; idxL++){
            for (int idxC = 0; idxC < tColonne; idxC++){
                classement[idxL][idxC] = getCell(fichier, idxL, idxC);
            }
        }
        return classement;
    }

    //Save le fichier CSV de classement
    void saveClassement(String[][] classement, String nom, String matière, int vies, int[] iPersos){
        classement[length(classement)-1][0] = nom;
        classement[length(classement)-1][1] = matière;
        classement[length(classement)-1][2] = vies + " vies";
        classement[length(classement)-1][3] = "" + iPersos[0];
        classement[length(classement)-1][4] = "" + (10-iPersos[1]);
        saveCSV(classement, "./ressources/CSV/Classement/Classement.csv");
    }

    //Fonction réutilisable des différents choix
    int choixGlobal(char plage){
        char choix = '0';
        while (choix < '1' || choix > plage){
            choix = stringToChar();
        }
        return (int)choix-48;
    }

    //Fais une saisie et transforme le string en char
    char stringToChar(){
        String saisie = "";
        while (length(saisie) == 0){
            saisie = readString();
        }
        return charAt(saisie, 0);
    }

    //Affiche les personnages pour choisir
    void printPersonnages(){
        clearScreen();
        println(INTRO);
        delay(2000);
        clearScreen();
        println(INTRO);
        println(fileAsString("./ressources/histoire_intro.txt"));
        delay(3000);
        println(fileAsString("./ressources/personnages.txt"));
    }

    //Sélectionne le bon personnage en fonction du choix
    Personnage personnageChoisi(int choix){
        switch(choix){
            case 1:
                return initPerso(fileAsString("./ressources/personnages/chevalier.txt"),"Chevalier" ,"Histoire", 1);
            case 2:
                return initPerso(fileAsString("./ressources/personnages/archer.txt"),"Archer" ,"Géometrie", 2);
            case 3:
                return initPerso(fileAsString("./ressources/personnages/alchimiste.txt"), "Alchimiste" ,"Science", 3);
            case 4:
                return initPerso(fileAsString("./ressources/personnages/barde.txt"),"Barde" ,"Anglais", 4);
            case 5:
                return initPerso(fileAsString("./ressources/personnages/princesse.txt"), "Princesse","Francais", 5);
            case 6:
                return initPerso(fileAsString("./ressources/personnages/cartographe.txt"),"Cartographe" ,"Géographie", 6);
            case 7:
                return initPerso(fileAsString("./ressources/personnages/Roi.txt"),"Roi" ,"Général", 7);
            case 8:
                return initPerso(fileAsString("./ressources/personnages/mimique.txt"),"Mimique" ,"JV", 8);
            default:
                println("Erreur inconnue, refaites un choix");
                return personnageChoisi(choixGlobal('8'));
        }
    }


    //Initialise le terrain
    void initTerrain(String[] tab, int vies, String heros){
        int iCrystal = length(tab)-vies-1;
        String crystal = "^";
        tab[iCrystal] = crystal;
        tab[length(tab)-1] = MECHANT;
        tab[0] = heros;
    }
 
    //Transforme le terrain en string
    String toStringTerrain(String[] terrain){
        String result = "";
        for (int idx = 0; idx < length(terrain); idx++){
            if (terrain[idx] == null ){
                result = result + " ___ ";
            }
            else {
                result = result + " "+ terrain[idx]+ " ";
            }
        }
        return result;
    }

    //Change l'ordre des questions
    void randomQuestion(String[][] questions){
        int rdm = 0;
        String tampon = "";
        for (int idxL = 0; idxL < length(questions, 1); idxL++){
            rdm = (int) (random()*(length(questions)-idxL)+idxL);
            for (int idxC = 0; idxC < length(questions, 2); idxC++){
                tampon = questions[idxL][idxC];
                questions[idxL][idxC] = questions[rdm][idxC];
                questions[rdm][idxC] = tampon;
            }
        }
    }

    //Mélange les réponse et sauvegarde la bonne réponse dans une variable
    String randomReponse(int idxQ, String[][] tab){
        String result = tab[idxQ][1];  // dans le csv, la réponse est la première proposition 
        String tampon = "";
        int random;
        for (int idx = 1; idx < length(tab, 2); idx++){
            random = (int) (random()*(length(tab,2)-1)+1);
            tampon = tab[idxQ][idx];
            tab[idxQ][idx] = tab[idxQ][random];
            tab[idxQ][random] = tampon;
        }
        return result;
    }

    //Charge un fichier csv dans un tableau a deux dimensions
    String[][] loadFichiers(Personnage heros){
        CSVFile fichier = loadCSV("./ressources/CSV/" + heros.fichier + ".csv", ',');
        int tColonne = columnCount(fichier);
        int tLigne = rowCount(fichier);
        String[][] questions = new String[tLigne][tColonne];
        for (int idxL = 0; idxL < tLigne; idxL++){
            for (int idxC = 0; idxC < tColonne; idxC++){
                questions[idxL][idxC] = getCell(fichier, idxL, idxC);
            }
        }
        return questions;
    }

    //Fais un String avec la ligne de question correspondante
    String toStringQuestions(String[][] questions, int idxQ){
        String result = "";
        result = result + questions[idxQ][0] + RETOUR;
        for (int idx = 1; idx < length(questions, 2); idx++){
            result = result + idx + ". " + questions[idxQ][idx] + RETOUR;
        }
        return result;
    }

    //Vérifie si la réponse rentrée est la bonne réponse
    boolean reponse(String[][] questions, String rep, int idxQ, int idxRep ){
        boolean result = false;
        if (equals(questions[idxQ][idxRep],rep)){
            result = true;
        }
        return result;
    }

    //Définit les vies en fonction du choix de l'utilisateur
    int choixVie (int choix){
        switch(choix){
            case 1:
                return 5;
            case 2:
                return 3;
            case 3:
                return 1;
        default:
            println("Erreur inconnue, refaites un choix");
            return choixVie(choixGlobal('3'));
        }
    }

    //Demande quel réponse l'utilisateur choisi
    int choixRep(){
        println("Rentrez une réponse parmis celles au dessus (1, 2 ,3 ou 4)");
        int nRep = choixGlobal('4');
        return nRep;
    }

    //Vérifie si le pseudo n'est pas trop long
    String choixPseudo(){
        String retour = "";
        while (length(retour) < 1 || length(retour) > 20){
            retour = readString();
        }
        return retour;
    }

    //Effectue un déplacement en fonction de la réponse
    void effectuerDeplacement(boolean reponse, String[] terrain, int[] iPersos){
        if (reponse){
            terrain[iPersos[0]+1] = terrain[iPersos[0]];
            terrain[iPersos[0]] = null;
            println("Bonne réponse !!! Vous avancez !");
            iPersos[0] = iPersos[0] + 1;
        }
        else {
            terrain[iPersos[1]-1] = terrain[iPersos[1]];
            terrain[iPersos[1]] = null;
            println("Mauvaise réponse, le méchant avance, attention !!!");
            iPersos[1] = iPersos[1] - 1;
        }
    }

    //Teste si c'est perdu
    boolean perdu(String[] terrain, int iMechant, int vies){
        boolean result = false;
        if ((length(terrain)-vies-1)== iMechant){
            result = true;
        }
        return result;
    }

    //Teste si c'est gagné
    boolean gagne(String[] terrain, int iHeros, int vies){
        boolean result = false;
        if ((length(terrain)-vies-1) == iHeros){
            result = true;
        }
        return result;
    } 

    //Affiche l'écran de victoire
    void printVictoire(){
        println(fileAsString("./ressources/victoire.txt"));
        delay(4000);
        clearScreen();
    }

    //Lance le jeu
    void jeu(){
        int taille = 11;
        int vies;
        int iMechant = taille-1;
        int iHeros = 0;
        int[] iPersos = new int[]{iHeros, iMechant};
        int idxQuestion = 0;
        String repCorrecte = "";
        Personnage heros;
        String[] terrain = new String[taille];
        printPersonnages();
        heros = personnageChoisi(choixGlobal('8'));
        println(RETOUR + "Vous avez choisi " + heros.nomPerso + RETOUR + RETOUR + RETOUR + heros.sprite + RETOUR +
                "Quel est le nom de votre " + heros.nomPerso + " ? (moins de 20 caractères)" + RETOUR);
        String pseudo = choixPseudo();
        clearScreen();
        println(fileAsString("./ressources/vie.txt"));
        vies = choixVie(choixGlobal('3'));
        initTerrain(terrain, vies, ">");
        String[][] questions = loadFichiers(heros); 
        randomQuestion(questions);
        while (!perdu(terrain, iPersos[1], vies) && !gagne(terrain, iPersos[0], vies) && idxQuestion < length(questions, 1)){
            clearScreen();
            println(toStringTerrain(terrain));
            println();
            repCorrecte = randomReponse(idxQuestion, questions);
            println(toStringQuestions(questions, idxQuestion));
            effectuerDeplacement(reponse(questions, repCorrecte, idxQuestion, choixRep()), terrain, iPersos);
            delay(2000);
            idxQuestion++;
        }
        clearScreen();
        if (gagne(terrain, iPersos[0], vies)){
            printVictoire();
        } else {
            println(fileAsString("./ressources/defaite.txt"));
            delay(4000);
            println(fileAsString("./ressources/defaiteBill.txt"));
            delay(1500);
        }
        saveClassement(loadClassement(), pseudo, heros.fichier, vies , iPersos);
    }

    void algorithm(){
        printAccueil();
        printMenu();
        int choix = choixGlobal('3');
        while (choix != 3){
            if (choix == 2){
                printClassement();
            }
            else {
                jeu();
            }
            printMenu();
            choix = choixGlobal('3');
        }
        clearScreen();
        println("Merci d'avoir joué, à bientôt !");
    }
}