<h1>Banco Digital</h1>

<p>Projeto desenvolvido no bootcamp da Educ360 de Java, utilizando spring boot.</p><br>

<div align="left"> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" height="40" alt="Java logo"/> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" height="40" alt="Spring Boot logo"/> <img src="https://www.svgrepo.com/show/373845/h2.svg" height="40" alt="H2 logo"/> </div>

<h2>Ferramentas Utilizadas</h2>
<p>- Spring Initializr</p>
<p>- H2 database</p>
<p>- Postman</p><br>

<h2>Dependências do Spring Initializr</h2>
<p>- Spring Data JDBC</p>
<p>- Spring Data JPA</p>
<p>- Spring Web</p>
<p>- H2 Database</p><br>

<h2>Configuração do Arquivo application.properties</h2>
<h4>- Nome da aplicação:</h4>
<p>spring.application.name=INSERIR O NOME DO BANCO</p><br>

<h4>- JDBC URL em modo servidor:</h4>
<p>spring.datasource.url=INSERIR O JDBC URL</p><br>

<h4>- Usuário padrão do H2:</h4>
<p>spring.datasource.username=sa</p><br>

<h4>- Senha padrão do H2:</h4>
<p>spring.datasource.password=</p><br>

<h4>- Define o caminho para acesso via navegador:(ex. http://localhost:8080/h2-console)</h4>
<p>spring.h2.console.path=/h2-console</p><br>

<h4>- Habilita o console H2:</h4>
<p>spring.h2.console.enabled=true</p><br>

<h4>- Atualiza automaticamente o schema do banco conforme as entidades:</h4>
<p>spring.jpa.hibernate.ddl-auto=update</p><br>

<h2>Testes Realizados no Postman:</h2>
<h4>- ADICIONAR CLIENTE:</h4>
<p>➕POST: localhost:8080/cliente/add</p><br>

<h4>- LISTAR OS CLIENTES:</h4>
<p>📥GET: localhost:8080/cliente/listAll</p><br>

<h4>- DELETAR CLIENTES:</h4>
<p>❌DELETE: localhost:8080/cliente/delete/ID</p><br>

<h4>- ATUALIZAR OS DADOS DO CLIENTE:</h4>
<p>♻️PUT: localhost:8080/cliente/update/ID</p><br>

<h4>- ADICIONAR CONTA:</h4>
<p>➕POST: localhost:8080/contas/add?clienteId=ID&numero=NUMERODACONTA</p><br>

<h4>- DETALHES DA CONTA:</h4>
<p>📥GET: localhost:8080/contas/ID</p><br>

<h4>- TRANSFERÊNCIA DE VALORES:</h4>
<p>➕POST: localhost:8080/contas/ID/transferencia?destinoId=ID&valor=VALOR</p><br>

<h4>- DEPÓSITO:</h4>
<p>➕POST: localhost:8080/contas/ID/deposito?valor=VALOR</p><br>

<h4>- CONSULTA DE SALDO:</h4>
<p>📥GET: localhost:8080/contas/ID/saldo</p><br>

<h4>- PIX:</h4>
<p>➕POST: localhost:8080/contas/ID/pix?destinoId=ID&valor=VALOR</p><br>

<h4>- SAQUE:</h4>
<p>➕POST: localhost:8080/contas/ID/saque?valor=VALOR</p><br>

<h4>- TAXA DE MANUTENÇÃO:</h4>
<p>♻️PUT: localhost:8080/contas/ID/manutencao?taxa=VALOR</p><br>

<h4>- APLICAÇÃO DE RENDIMENTOS:</h4>
<p>♻️PUT: localhost:8080/contas/ID/rendimentos?percentual=VALOR</p><br>

<h4>- EMISSÃO DE CARTÃO:</h4>
<p>➕POST: localhost:8080/cartoes?clienteId=ID&tipo=DEBITO</p><br>

<p>➕POST: localhost:8080/cartoes?clienteId=ID&tipo=CREDITO</p><br>

<h4>- LISTA DETALHES DO CARTÃO:</h4>
<p>📥GET: localhost:8080/cartoes/ID</p><br>

<h4>- PAGAMENTO DO CARTÃO:</h4>
<p>➕POST: localhost:8080/cartoes/ID/pagamento?valor=VALOR&senha=SENHA</p><br>

<h4>- ALTERAÇÃO DO LIMITE DO CARTÃO:</h4>
<p>♻️PUT: localhost:8080/cartoes/ID/limite?novoLimite=VALOR</p><br>

<h4>- ATIVAR E DESATIVAR CARTÃO:</h4>
<p>♻️PUT: localhost:8080/cartoes/ID/status?ativo=true</p><br>

<p>♻️PUT: localhost:8080/cartoes/ID/status?ativo=false</p><br>

<h4>- ALTERAR SENHA DO CARTÃO:</h4>
<p>♻️PUT: localhost:8080/cartoes/ID/senha?novaSenha=SENHA</p><br>

<h4>- CONSULTAR FATURA:</h4>
<p>📥GET: localhost:8080/cartoes/ID/senha?novaSenha=SENHA</p><br>

<h4>- PAGAMENTO DA FATURA DO CARTÃO:</h4>
<p>➕POST: localhost:8080/cartoes/ID/fatura/pagamento?valor=VALOR</p><br>

<h4>- ALTERAR O LIMITE DIÁRIO:</h4>
<p>♻️PUT: localhost:8080/cartoes/ID/limite-diario?novoLimiteDiario=VALOR</p>
