Ce fichier contient et contiendra des remarques de suivi sur votre
projet tant sur la modélisation que sur la programmation. Un nouveau
suivi est indiqué par une nouvelle section datée.

Certaines remarques demandent des actions de votre part, vous les
repérerez par une case à cocher.

- []  Action (à réaliser) 

Merci de nous indiquer que vous avez pris en compte la remarque en
cochant la case. N'hésitez pas à écrire dans ce fichier et à nous
exposer votre point de vue.

- [x] Action (réalisée)
    - RÉPONSE et éventuelles remarques de votre part, 


# Suivi du mer. 20 janv. 2021 13:36:22 CET
Denisse Munante

- [] ATTENTION ! Le seul fichier que je regarde est le pdf. Votre pdf n'a pas
été modifié, je n'ai donc rien à regarder... Pour le prochain suivi, veillez à
ce que le pdf soit à jour.

# 28.01

(je n'ai regardé pour l'instant que les cas d'utilisation de priorité haute et les tests de validation attachés)

diag. des cas d'utilisation
- attention aux redondances, e.g., "affecter à un bureau le nombre de places fixes" et "changer le nombre de places fixes d'un bureau".
toute fonction de la façade devra être codée, et moins on code, plus on est sur de la correction du programme.
- les cas d'utilisation affectation d'une place fixe/de passage son ambigü.
- utilisez des verbes dans les cas d'utilisation

cas utilisation "ajouter un employé"
- il est redondant d'écrire fonction du permanent bien formée et fonction du permament \in {direction, ..}

test validation "ajouter un employé permanent"
- à mon sens, il y a un seul test si la fonction du permanent est invalide, et non 5.

test validation "ajouter un employé non-permanent"
- remarques similaires au test précédent

diag. de classes
- diagramme partiel mais correct
- la flêche en pointillée n'a pas de signification en UML (hormis le commentaire)

diag de seq. "ajouter un employé non-permanent"
- la description textuelle apparait deux fois, mais il n'y a pas de diag. UML

diag de seq. "affecter un employé à une place fixe"
- identifiant -> identifiantEmployé
- "si non manager, le nombre de place fixe de l’employé vérifié (null)" à détailler
- id -> idEmployé dans la figure
- "vérifier les conditions restantes" est vague
- placesFixesActuel_les_
- la condition optionelle du dernier fragment n'est pas correcte à mon sens
- addFixes -> nom étrange

# 18.02

Fig.4 ce n'est pas le bon diagramme, ni le bon libélé

DSUC1. 
- alg. OK 
- DS. OK (on pourrait être plus spécifique dans "ajouterALaCollection")

DSUC2.
- alg. OK
- DS
  - [] pourquoi passez vous (id,b) au constructeur d'une place (fixe ou non)?

DSUC3.
- alg. 
  - précondition "non manager, le nombre de place fixe de l’employé sur les deux campus (null)"
	- [] je ne comprends pas le sens de "null" (même remarque pour la suivante)
  - [] il faut signaler à la place qu'elle est occupée
  - logique OK, hormis les points mentionnés plus haut
- SC
  - [] l'appel à isManager doit être demandé par la façade
  - [] st<=2 & (!m | st<=1)  
  
- Diag. machines à états PlaceFixe
  - [] le passage de libre à occupé (ou vice-versa= doit se faire suite à un appel de méthode
  - [] vérifier la concordance de ce diagramme avec les diagrammes de classe et de séquence
