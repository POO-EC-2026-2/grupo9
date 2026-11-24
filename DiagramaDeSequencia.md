# Ativade Lab 17/11 - UML - Diagrama de sequência

Este é um diagrma que apresenta a sequência dos processos do sistema de adoçao e sua interação com o ator adotante e o banco de dados.

````plantuml
@startuml 
actor Adotante  
participant SistemaDeAdocao  
participant Banco

Adotante -> SistemaDeAdocao : visualizarPet()  
SistemaDeAdocao -> Banco : BuscarpetsDisponiveis()  
Banco --> SistemaDeAdocao : listadePets  
SistemaDeAdocao --> Adotante : exibir Pets  

Adotante -> SistemaDeAdocao : selecionarPet(id)  
SistemaDeAdocao -> Banco : verificarPet(id)  
Banco --> SistemaDeAdocao : Pets disponíveis  
SistemaDeAdocao -> Banco : registrarSolicitacao(idAdotante, idPet)  
Banco --> SistemaDeAdocao : Solicitacao confirmada  
SistemaDeAdocao --> Adotante : Solicitacao enviada  
@enduml
````

![Diagrama UML]()
