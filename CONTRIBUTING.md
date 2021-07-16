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
- TODO: configurer le mode d'exposition des ws REST (jar, war, etc)