API que simula um forum com um CRUD simples  utilizando Spring Boot, JPA e Maven

Rota de acesso localhost:8080/topicos

Método GET na rota localhost:8080/topicos sem passar parametros irá trazer todos os topicos cadastrados 
no banco.

Método GET na rota localhost:8080/topicos?nomeCurso=Spring Boot traz todos os topicos relacionados ao curso
Spring Boot

Método POST na rota localhost:8080/topicos enviando esse json {'titulo': 'Duvida', 'mensagem': 'Projeto não compila', 'nomeCurso' : 'Spring Boot'}
irá cadastrar um novo topico. Seguindo algumas regras de validação, titulo não pode ser vazio e não pode ser menor que 
5 caracteres, mensagem não pode ter menos de 10 caracteres  e nome do curso não pode ser Nulo

Método GET na rota localhost:8080/topicos/id  irá trazer os detalhes do topico que correspodem a ao id passado

Método PUT na rota localhost:8080/topicos/id enviando esse json {"titulo":"Novo Titulo", "mensagem":"Nova mensagem de teste"}
irá atualizar o topico que corresponder ao id passado na URL

Método DELETE  na rota localhost:8080/topicos/id   irá deletar o topico que corresponde ao id passado na URL

o Banco de dados utilizados é o H2 que é um banco em memória, na pasta resource existe um arquivo data.sql que já inserem
algum dados no banco para realizar testes.

OBS: na raiz do projeto existem um arquivo request.py que consume essa API para poder realizar os testes