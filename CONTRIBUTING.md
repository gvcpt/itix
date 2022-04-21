# ITIX

## Composantes

### itix-app-front-end

- projet angular d'affichage d'informations diverses
- pour le démarrer, lancer la config Angular CLI-server
- il récupère les données stockées en bdd par le module batch et exposées via le service itix-rest-spark

### itix-batch

- projet batch qui lit les données historiques depuis le fichier spi_matches ou spi_matches_latest et le stocke en base
- il produit aussi un fichier xls en sortie avec des stats de base
- il se lance via la config Run Batch avec les options suivantes:
- ==> createGlobalxGClassement : création du classement par xG+, xG- et DxG (Power ranking)
- ==> createClassementByTeam : création d'un classement de xG+, xG- et DxG d'une équipe particulière
- ==> createClassementByLeagueSeason : création d'un classement de xG+, xG- et DxG d'une saison en particulier

TODO: à partir des deux classements ci dessus projecter les résultats de matchs à venir Comment faire: pour les deux équipes qui se rencontrent produire un
premier index global de xG depuis le power ranking et un deuxième index spécifique aux deux équipes à partir du deuxième classement. Puis comparer les deux
index en y applicant un traitment d'AI ?

### itix-core

- projet qui contient les differentes configs, notamment le modèle et les services utilisés dans les autre projets

### itix-rest

- projet qui expose via des WS REST les données récupérées via le projet itix-batch pour qu'elles soient appelées et affichées par le front end
- TODO: résoudre NPE lors de l'appel du bean via le front-end

### itix-rest-spark

- exposition des api d'accès à la bd via rest spark
- execution via la méthode main() et accès via  http://localhost:4567/getMatches
- NB: à cause de l'erreur suivante:
  // Access to XMLHttpRequest at 'http://localhost:8080/greeting' from origin 'http://localhost:4200' has been blocked by CORS policy: No '
  Access-Control-Allow-Origin' header is present on the requested resource. le front end ne marche que sous Mozilla/Chrome en utilisant l'extension CORS
  Everywhere (Firefox) ou CORS unblock (Chrome)

### itixReact

- front end pour affichage en ReactJs
- pour le démarrer lancer la config npm ReacJs front-end (ou faire un npm start dans le répertoire)
- activer l'extension CorsE enable dans le navigateur pour bypasser le problème de Cross Origin

### itix-express

- front end pour affichage en NodeJs Express
- pour le démarrer lancer la config Node Express front-end
- pas besoind de l'extension CorsE

// TODO

1. ajouter des dao pour récupérer les matchs selon un ou plusieurs critères (année, league id, etc)
2. afficher un championnat à la fois (trié par année, serie etc) 
