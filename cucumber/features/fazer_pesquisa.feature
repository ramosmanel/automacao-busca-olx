#language: pt
  Funcionalidade: Pesquisar Produto
    Como um usuário deslogado
    Eu quero pesquisar um produto e seleciona-lo
    Para entao compra-lo

  Contexto:
    Dado que estou na pagina inicial da olx
    Quando nao me encontro logado

  @pesquisarproduto
  Esquema do Cenario: Deve mostrar todos os produtos com base no produto que eu pesquisei
    E pesquiso o produto <nomeProduto> na barra de pesquisa
    Entao eu escolho o produto <nomeProduto> no <indiceProduto> para compra-lo
    Exemplos:
    |    nomeProduto    | indiceProduto |
    |     "Skate"       |       3       |
    |  "Playstation 5"  |       2       |
    |    "Notebook"     |      16       |
    |"Panela de pressão"|      41       |

   @validandopesquisas
   Esquema do Cenario: Deve mostrar os resultados de pesquisa
   condizentes com o termo de busca
     E pesquiso pelo produto <nomeProduto> na barra de pesquisa
     Entao o produto <nomeProduto> deve estar presente no resultado da busca
     E eu clico na segunda aba
     Entao o produto <nomeProduto> deve estar presente no resultado da busca
     E eu clico na quinta aba
     Entao o produto <nomeProduto> deve estar presente no resultado da busca
     E eu clico na oitava aba
     Entao o produto <nomeProduto> deve estar presente no resultado da busca
     Exemplos:
       |    nomeProduto    |
       |     "Skate"       |
       |  "Playstation 5"  |
       |    "Notebook"     |
       |     "Controle"    |
