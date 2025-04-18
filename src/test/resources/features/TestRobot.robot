*** Settings ***
Library    SeleniumLibrary

*** Test Cases ***
Vérifier Le Titre De La Page Example
    Open Browser    https://example.com    chrome
    Title Should Be    Example Domain
    Close Browser