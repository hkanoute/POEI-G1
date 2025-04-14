Feature: Connexion

  Background:
    Given Je suis sur la page de login

  Scenario: Connexion réussi avec des identifiants valides
    Then Je remplis le champ username avec la valeur "john"
    And Je remplie le champ password avec la valeur "demo"
    And Je clique sur le bouton me connecter
    And Je suis connecté


  Scenario: Connexion échoué avec un mot de passe incorrect
    Then Je remplis le champ username avec la valeur "standard_user"
    And Je remplie le champ password avec la valeur "secret_sauce_not_ok"
    And Je clique sur le bouton me connecter
    Then Je reçois un message d'erreur