# TesteBackEnd
Teste Projeto Spring


1- Criei a Classe/Interface Repository que seria a persistência/JPA
2- Na classe Entities, anotei com a anotação @entity e coloquei um @id com uma sequência pra quando rodar o projeto já criar a tabela no banco de dados
3-Criei o pacote e classes controllers. Neste caso, criei um controller com 2 end points, o primeiro para testar o funcionando do Spring na url. E segundo pra testar o banco de dados do spring JPA.
4- No arquivo aplication.properties coloquei as propriedades do banco de dados.
5 - No pom.xml que seria o maven coloquei algumas anotações a mais para  rodar o spring.
6 - O segundo controller que fiz, a idéia seria a seguinte ao meu entender, intercepta um arquivo e neste end- point especificamente passa o arquivo pro método da classe  Service que já estava neste código para ler o Excel. Eu já trabalhei com a biblioteca Poi, acho mais fácil para manipular excel, mais é questão de costume.
7 - Utilizei o eclipse para abrir o projeto e tive que instalar o plugin do Lombok para reconhecer.
8- Criei uma classe helper
