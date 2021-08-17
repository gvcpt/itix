# ITIX

## Composantes

### itix-app-front-end

- projet angular d'affichage d'informations diverses
- pour le démarrer, lancer la config Angular CLI-server
- pour l'instant il cherche les infos depuis un url de test, mais à terme il sera branché au module itix-rest

### itix-batch

- projet batch qui lit les données historiques depuis le fichier spi_matches ou spi_matches_latest et le stocke en base
- il produit aussi un fichier xls en sortie avec des stats de base
- il se lance via la config Run Batch

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
