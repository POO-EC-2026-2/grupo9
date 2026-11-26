# Frontend (JavaFX)

Telas do adotante feitas em Java com JavaFX.

## Como rodar

Precisa só do JDK 21. O Maven é baixado automaticamente pelo wrapper na primeira execução.

```
cd frontend
.mvnw javafx:run      # Windows (PowerShell)
./mvnw javafx:run      # Linux/Mac
```

Também dá para abrir a pasta `frontend` como projeto Maven no IntelliJ/VS Code e rodar a classe `com.grupo9.adocao.Launcher`.

## Telas

- **Tela inicial do adotante** (`TelaInicialAdotante`): pets em blocos, três por linha, com imagem, nome, espécie, sexo, porte e idade. Com mais pets, aparece a barra de rolagem.
- **Perfil do pet** (`PerfilPet`): abre ao clicar num bloco. Mostra mais informações, a descrição e os botões **Adotar** e **Voltar**.

## Imagens

Coloque as fotos em `src/main/resources/imagens/` e informe o caminho no pet, por exemplo `"/imagens/thor.jpg"`. Também funciona com URL (`https://...`). Sem imagem, aparece um bloco com a inicial do nome.

## Integração com o backend

As telas usam a interface `PetService`. Por enquanto, `PetServiceMemoria` traz dados de exemplo. Quando o backend estiver pronto, basta criar uma implementação de `PetService` que use o backend e trocar em `App`.
