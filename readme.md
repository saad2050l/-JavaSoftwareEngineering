Groupe:
- LAHLALI SAAD
- EL OTMANI RIAD

Copyright (C) 2016-2020
Contact: Denis.Conan[at]telecom-sudparis.eu

Structure du projet:
```
$ tree
.
├── cahierdescharges-chap.pdf
├── Code
│   ├── pom.xml
│   └── src
│       ├── main
│       │   └── java
│       │       └── eu
│       │           └── telecomsudparis
│       │               └── csc4102
│       │                   └── bebiloc
│       │                       ├── BeBiloc.java
│       │                       ├── Bureau.java
│       │                       ├── ConsommateurAnnulationAffectation.java
│       │                       ├── Employe.java
│       │                       ├── exception
│       │                       │   ├── BureauDejaExistant.java
│       │                       │   ├── BureauInexistant.java
│       │                       │   ├── CapaciteBureauErronee.java
│       │                       │   ├── CapaciteDePassageErronee.java
│       │                       │   ├── ConflitAvecOccupationDePassageMemeLieu.java
│       │                       │   ├── DateDebutAffectationAvantAujourdhui.java
│       │                       │   ├── DateDebutAffectationAvantDateEmbauche.java
│       │                       │   ├── DateDebutAffectationNull.java
│       │                       │   ├── DateEmbaucheNull.java
│       │                       │   ├── DateFinAffectationApresDateFinContrat.java
│       │                       │   ├── DateFinAffectationAvantDateDebutAffectation.java
│       │                       │   ├── DateFinAffectationNull.java
│       │                       │   ├── DateFinContratNonApresDateEmbauche.java
│       │                       │   ├── DateFinContratNull.java
│       │                       │   ├── DateNull.java
│       │                       │   ├── EmployeDejaExistant.java
│       │                       │   ├── EmployeInexistant.java
│       │                       │   ├── EmployeMaxPlaces.java
│       │                       │   ├── FonctionInconnue.java
│       │                       │   ├── FonctionNonAdaptee.java
│       │                       │   ├── LocalisationNullOuVide.java
│       │                       │   ├── MauvaisTypeOccupantBureau.java
│       │                       │   ├── OccupationDePassageAvecPlaceFixeMemeLieu.java
│       │                       │   ├── PasOccupationDansBureauPourEmployeADate.java
│       │                       │   ├── PasPlaceFixeDansBureauPourEmploye.java
│       │                       │   └── PlaceManquante.java
│       │                       ├── Fonction.java
│       │                       ├── Localisation.java
│       │                       ├── Notification.java
│       │                       ├── OccupationDePassage.java
│       │                       ├── PlaceDePassage.java
│       │                       ├── PlaceFixe.java
│       │                       ├── Place.java
│       │                       ├── RendreUnBureauS1.java
│       │                       ├── RendreUnBureauS2.java
│       │                       ├── RendreUnBureauS3.java
│       │                       ├── StrategieRendreBureau.java
│       │                       └── TypeFonction.java
│       └── test
│           └── java
│               └── eu
│                   └── telecomsudparis
│                       └── csc4102
│                           └── bebiloc
│                               ├── unitaires
│                               │   ├── TestAffecterEmploye.java
│                               │   ├── TestEmploye.java
│                               │   └── TestPlaceFixe.java
│                               └── validation
│                                   ├── TestAffecterPlaceDePassage.java
│                                   ├── TestAffecterPlaceFixe.java
│                                   ├── TestAjouterBureauPourNonPermanents.java
│                                   ├── TestAjouterBureauPourPermanents.java
│                                   ├── TestAjouterNonPermanent.java
│                                   ├── TestAjouterPermanent.java
│                                   ├── TestRendreBureau.java
│                                   └── TestRendreUnBureauS1.java
├── Modelisation
│   ├── DiagrammesDeCasDUtilisation
│   │   └── bebiloc_uml_diag_cas_utilisation.uxf
│   ├── DiagrammesDeClasses
│   │   └── bebiloc_uml_diag_classes.uxf
│   ├── DiagrammesDeMachineAEtats
│   │   ├── bebiloc_uml_diag_machine_a_etats_employe.uxf
│   │   └── bebiloc_uml_diag_machine_a_etats_placeFixe.uxf
│   ├── DiagrammesDeSequence
│   │   ├── bebiloc_uml_diag_sequence_ajouter_non_permanent.uxf
│   │   ├── bebiloc_uml_diag_sequence_ajouter_permanent.uxf
│   │   ├── DCURendreUnBureauStratégie1.uxf
│   │   ├── DCURendreUnBureauStratégie2.uxf
│   │   ├── DCURendreUnBureauStratégie3.uxf
│   │   ├── DSUC1.uxf
│   │   ├── DSUC2.uxf
│   │   └── DSUC3.uxf
│   ├── makefile
│   ├── modelisation.odt
│   ├── modelisation.pdf
│   ├── modelisation.tex
│   └── readme.md
├── readme.md
└── sun_checks_adapted_to_tsp_csc_pour_eclipse_2019_09.xml

================================================================================

Software prerequisites:
-----------------------
	1. JAVA Version >= 9.0
	   (https://openjdk.java.net/install/index.html)
	2. Maven Version >= 3.0.4
	   (http://maven.apache.org/)

Shell variables to set in your ~/.bashrc file:
----------------------------------------------
	1. if JAVA is not installed from an archive file,
```
export JAVA_HOME=<the root directory of your Java installation>
export CLASSPATH=$JAVA_HOME/lib
```

Before using the Maven module:
------------------------------
Modify the line 
```
 <artifactId>csc4102-saadlahlali_riadelotmani</artifactId>
```
in the file Code/pom.xml

Compilation and installation:
-----------------------------
	To compile and install the modules, execute the following command.
```
(cd Code; mvn install)
```
	Use the following options if you do not want to compile and execute
        the JUnit tests and do not want to generate the JavaDoc files.
        Then, the build process is much more rapid.
```
(cd Code; mvn install -Dmaven.test.skip=true -DskipTests -Dmaven.javadoc.skip=true -Dcheckstyle.skip)
```

In Eclipse:
-----------
	To load the project in Eclipse, either use the maven plugin (File >
	Import > Maven > Existing maven project), or create the Eclipse project
	using the following command and then create a Java project in Eclipse:
```
(cd Code; mvn eclipse:clean eclipse:eclipse)
```
