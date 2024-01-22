# Héros du Savoir
===========

Développé par Nathan Marquis Lorenzo Flagothier
Contacts : nathan.marquis.etu@univ-lille.fr , lorenzo.flagothier.etu@univ-lille.fr

# Présentation des Héros du Savoir

<Les Héros du savoir est un jeu où vous pouvez incarner un personnage parmis 7 propositions qui représentent 6 matières différentes. Une fois que vous avez choisi votre personnage (chevalier pour histoire, archer pour géométrie, alchimiste pour science, barde pour anglais, carthographe pour géographie, princesse pour français et roi pour un mix de toutes les matières ) vous arrivez sur un plateau de jeu face à un méchant, et avec un crystal au milieu. Chaque bonne réponse vous fait avancer d'une case et chaque mauvaise réponse fait avancer l'ennemi. Le jeu s'arrête lorsque vous avez atteind le crystal.
Vous avez aussi le choix entre plusieurs difficultés (5 vies, 3 vies et 1 vie) pour rendre le challenge plus important. Les questions sont stockées dans un fichier CSV avec le nom de la matière, et il est tout a fait possible de rajouter des questions selon vos besoins.>

Des captures d'écran illustrant le fonctionnement du logiciel sont proposées dans le répertoire shots.

# Utilisation des Héros du savoir

Pour utiliser ce projet, nous vous recommandons une résolution d'écran minimale de 1680x1050, sinon votre affichage graphique risque d'être illisible.
Nous vous recommandons aussi de mettre votre terminal en plein écran pour éviter le problème ci-dessus également.
Afin d'utiliser le projet, il suffit de taper les commandes suivantes dans un terminal :

```
./compile.sh
```
Permet la compilation des fichiers présents dans 'src' et création des fichiers '.class' dans 'classes'

```
./run.sh
```
Permet le lancement du jeu
Si lorsque que vous essayez d'éxecuter le fichier, ça ne fonctionne pas, il vous faudra peut-être rendre votre fichier exécutable grace à ces commandes :

```
chmod u+x ./compile.sh
```
```
chmod u+x ./run.sh
```
Si vous souhaitez quittez le jeu en cours de partie (On ne le recommande pas), appuyez simultanément sur la touche 'CTRL' et 'C'
