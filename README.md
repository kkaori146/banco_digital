<h1>Banco Digital</h1>

<p>Projeto desenvolvido no bootcamp da Educ360 de Java, utilizando spring boot.</p>

<h2>Ferramentas Utilizadas</h2>

<p> - Spring Initializr</p>
<p> - H2 database </p>
<p> - Postman </p>

<h2>Dependências do Spring Initializr</h2>

<p> - Spring Data JDBC</p>
<p> - Spring Data JPA</p>
<p> - Spring Web </p>
<p> - H2 Database </p>

<h2>Configuração do Arquivo application.properties</h2>
<p><b> - Nome da aplicação:</b></p>
<p>spring.application.name=INSERIR O NOME DO BANCO</p>
<p><b> - JDBC URL em modo servidor:</b></p>
<p>spring.datasource.url=INSERIR O JDBC URL</p>
<p><b> - Usuário padrão do H2:</b></p>
<p>spring.datasource.username=sa</p>
<p><b> - Senha padrão do H2:</b></p>
<p>spring.datasource.password= </p>
<p><b> - Define o caminho para acesso via navegador:(ex. http://localhost:8080/h2-console)</b></p>
<p>spring.h2.console.path=/h2-console</p>
<p><b> - Habilita o console H2:</b></p>
<p>spring.h2.console.enabled=true</p>
<p><b> - Atualiza automaticamente o schema do banco conforme as entidades:</b></p>
<p>spring.jpa.hibernate.ddl-auto=update</p>

<h2>Testes Realizados no Postman: </h2>
<b> - ADICIONAR CLIENTE:</b>
<p>POST: localhost:8080/cliente/add </p>
<b> - LISTAR OS CLIENTES: </b>
<p>GET: localhost:8080/cliente/listAll</p>
<b> - DELETAR CLIENTES: </b>
<p>DELETE: localhost:8080/cliente/delete/ID</p>
<b> - ATUALIZAR OS DADOS DO CLIENTE: </b>
<p>PUT: localhost:8080/cliente/update/ID</p>
<b> - ADICIONAR CONTA:</b>
<p>POST: localhost:8080/contas/add?clienteId=ID&numero=NUMERODACONTA</p>
<b> - DETALHES DA CONTA:</b>
<p>GET: localhost:8080/contas/ID</p>
<b> - TRANSFERÊNCIA DE VALORES: </b>
<p>POST: localhost:8080/contas/ID/transferencia?destinoId=ID&valor=VALOR</p>
<b> - DEPÓSITO:</b>
<p>POST: localhost:8080/contas/ID/deposito?valor=VALOR</p>
<b> - CONSULTA DE SALDO: </b>
<p>GET: localhost:8080/contas/ID/saldo</p>
<b> - PIX:</b>
<p>POST: localhost:8080/contas/ID/pix?destinoId=ID&valor=VALOR</p>
<b> - SAQUE:</b>
<p>POST: localhost:8080/contas/ID/saque?valor=VALOR</p>
<b> - TAXA DE MANUTENÇÃO:</b>
<p>PUT: localhost:8080/contas/ID/manutencao?taxa=VALOR</p>
<b> - APLICAÇÃO DE RENDIMENTOS: </b>
<p>PUT: localhost:8080/contas/ID/rendimentos?percentual=VALOR</p>
<b> - EMISSÃO DE CARTÃO:</b>
<p>POST: localhost:8080/cartoes?clienteId=ID&tipo=DEBITO</p>
<p>POST: localhost:8080/cartoes?clienteId=ID&tipo=CREDITO</p>
<b> - LISTA DETALHES DO CARTÃO:</b>
<p>GET: localhost:8080/cartoes/ID</p>
<b> - PAGAMENTO DO CARTÃO:</b>
<p>POST: localhost:8080/cartoes/ID/pagamento?valor=VALOR&senha=SENHA</p>
<b> - ALTERAÇÃO DO LIMITE DO CARTÃO:</b>
<p>PUT: localhost:8080/cartoes/ID/limite?novoLimite=VALOR</p>
<b> - ATIVAR E DESATIVAR CARTÃO:</b>
<p>PUT: localhost:8080/cartoes/ID/status?ativo=true</p>
<p>PUT: localhost:8080/cartoes/ID/status?ativo=false</p>
<b> - ALTERAR SENHA DO CARTÃO:</b>
<p>PUT: localhost:8080/cartoes/ID/senha?novaSenha=SENHA</p>
<b> - CONSULTAR FATURA:</b>
<p>GET: localhost:8080/cartoes/ID/senha?novaSenha=SENHA</p>
<b> - PAGAMENTO DA FATURA DO CARTÃO:</b>
<p>POST: localhost:8080/cartoes/ID/fatura/pagamento?valor=VALOR</p>
<b> - ALTERAR O LIMITE DIÁRIO:</b>
<p>PUT: localhost:8080/cartoes/ID/limite-diario?novoLimiteDiario=VALOR</p>
