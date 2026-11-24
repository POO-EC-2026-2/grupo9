@startuml  
actor Adotante  
participant SistemaDeAdoção  
participant Banco

Adotante -> SistemaDeAdoção : visualizarPet()  
SistemaDeAdoção -> Banco : BuscarpetsDisponiveis()  
Banco --> SistemaDeAdoção : listadePets  
SistemaDeAdoção --> Adotante : exibir Pets  

Adotante -> SistemaDeAdoção : selecionarPet(id)  
SistemaDeAdoção -> Banco : verificarPet(id)  
Banco --> SistemaDeAdoção : Pets disponíveis  
SistemaDeAdoção -> Banco : registrarSolicitação(idAdotante, idPet)  
Banco --> SistemaDeAdoção : Solicitação confirmada  
SistemaDeAdoção --> Adotante : Solicitação enviada  
@enduml
