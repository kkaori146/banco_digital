<h1>Banco Digital</h1>

<p>Projeto desenvolvido no bootcamp da Educ360 de Java, utilizando spring boot.</p>

<h2>Ferramentas Utilizadas</h2>
<p>- Spring Initializr</p>
<p>- H2 database</p>
<p>- Postman</p>

<h2>Dependências do Spring Initializr</h2>
<p>- Spring Data JDBC</p>
<p>- Spring Data JPA</p>
<p>- Spring Web</p>
<p>- H2 Database</p>

<h2>Configuração do Arquivo application.properties</h2>
<h4>- Nome da aplicação:</h4>
<p>spring.application.name=INSERIR O NOME DO BANCO</p>

<h4>- JDBC URL em modo servidor:</h4>
<p>spring.datasource.url=INSERIR O JDBC URL</p>

<h4>- Usuário padrão do H2:</h4>
<p>spring.datasource.username=sa</p>

<h4>- Senha padrão do H2:</h4>
<p>spring.datasource.password=</p>

<h4>- Define o caminho para acesso via navegador:(ex. http://localhost:8080/h2-console)</h4>
<p>spring.h2.console.path=/h2-console</p>

<h4>- Habilita o console H2:</h4>
<p>spring.h2.console.enabled=true</p>

<h4>- Atualiza automaticamente o schema do banco conforme as entidades:</h4>
<p>spring.jpa.hibernate.ddl-auto=update</p>

<h2>Testes Realizados no Postman:</h2>
<h4>- ADICIONAR CLIENTE:</h4>
<p>➕POST: localhost:8080/cliente/add</p>

<h4>- LISTAR OS CLIENTES:</h4>
<p>📥GET: localhost:8080/cliente/listAll</p>

<h4>- DELETAR CLIENTES:</h4>
<p>❌DELETE: localhost:8080/cliente/delete/ID</p>

<h4>- ATUALIZAR OS DADOS DO CLIENTE:</h4>
<p>♻️PUT: localhost:8080/cliente/update/ID</p>

<h4>- ADICIONAR CONTA:</h4>
<p>➕POST: localhost:8080/contas/add?clienteId=ID&numero=NUMERODACONTA</p>

<h4>- DETALHES DA CONTA:</h4>
<p>📥GET: localhost:8080/contas/ID</p>

<h4>- TRANSFERÊNCIA DE VALORES:</h4>
<p>➕POST: localhost:8080/contas/ID/transferencia?destinoId=ID&valor=VALOR</p>

<h4>- DEPÓSITO:</h4>
<p>➕POST: localhost:8080/contas/ID/deposito?valor=VALOR</p>

<h4>- CONSULTA DE SALDO:</h4>
<p>📥GET: localhost:8080/contas/ID/saldo</p>

<h4>- PIX:</h4>
<p>➕POST: localhost:8080/contas/ID/pix?destinoId=ID&valor=VALOR</p>

<h4>- SAQUE:</h4>
<p>➕POST: localhost:8080/contas/ID/saque?valor=VALOR</p>

<h4>- TAXA DE MANUTENÇÃO:</h4>
<p>♻️PUT: localhost:8080/contas/ID/manutencao?taxa=VALOR</p>

<h4>- APLICAÇÃO DE RENDIMENTOS:</h4>
<p>♻️PUT: localhost:8080/contas/ID/rendimentos?percentual=VALOR</p>

<h4>- EMISSÃO DE CARTÃO:</h4>
<p>➕POST: localhost:8080/cartoes?clienteId=ID&tipo=DEBITO</p>

<p>➕POST: localhost:8080/cartoes?clienteId=ID&tipo=CREDITO</p>

<h4>- LISTA DETALHES DO CARTÃO:</h4>
<p>📥GET: localhost:8080/cartoes/ID</p>

<h4>- PAGAMENTO DO CARTÃO:</h4>
<p>➕POST: localhost:8080/cartoes/ID/pagamento?valor=VALOR&senha=SENHA</p>

<h4>- ALTERAÇÃO DO LIMITE DO CARTÃO:</h4>
<p>♻️PUT: localhost:8080/cartoes/ID/limite?novoLimite=VALOR</p>

<h4>- ATIVAR E DESATIVAR CARTÃO:</h4>
<p>♻️PUT: localhost:8080/cartoes/ID/status?ativo=true</p>

<p>♻️PUT: localhost:8080/cartoes/ID/status?ativo=false</p>

<h4>- ALTERAR SENHA DO CARTÃO:</h4>
<p>♻️PUT: localhost:8080/cartoes/ID/senha?novaSenha=SENHA</p>

<h4>- CONSULTAR FATURA:</h4>
<p>📥GET: localhost:8080/cartoes/ID/senha?novaSenha=SENHA</p>

<h3>- PAGAMENTO DA FATURA DO CARTÃO:</h3>
<p>➕POST: localhost:8080/cartoes/ID/fatura/pagamento?valor=VALOR</p>

<h3>- ALTERAR O LIMITE DIÁRIO:</h3>
<p>♻️PUT: localhost:8080/cartoes/ID/limite-diario?novoLimiteDiario=VALOR</p>
