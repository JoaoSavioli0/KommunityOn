
# 🏛️ KON - Ouvidoria Municipal

O KommunityOn é um sistema web dinâmico que permite aos cidadãos de um município abrir solicitações e sugestões para a prefeitura. Os usuários podem interagir com as solicitações de outros cidadãos por meio de likes e comentários, ajudando a destacar as demandas mais relevantes.

## 📸 Screenshots

![Home Page](https://github.com/user-attachments/assets/94674c84-f89e-4a30-95c1-ffa0e48ac891)

Página inicial

![Solicitação](https://github.com/user-attachments/assets/d1469d78-4e1b-4e84-85bb-c128548886f6)

Página da solicitação

## 🧵 Stack utilizada

**Front-end:** Vue.js, TailwindCSS

**Back-end:** SpringBoot + MySQL (JPA)


## 📱 Funcionalidades

- Cadastro e Login de usuários
- Abertura de solicitações para a prefeitura
- Interação com solicitações de outros cidadãos (likes e comentários)
- Edição de perfil (nome, email, imagem de usuário)
- Recuperação de senha (código de recuperação simulado como envio por email/telefone)
- Restrições na criação de solicitações e interações (regras de negócio)
- Permissões de administrador

## 📜 Regras de Negócio
### Likes e Interações

- Cada usuário começa com 2 interações livres;
- Após utilizar uma interação, ela só ficará disponível novamente após 7 dias;
- As duas interações possuem intervalos independentes entre si.

### Solicitações

- O usuário só poderá abrir uma nova soliitação se não tiver nenhuma solicitação em aberto, ou se a sua solicitação aberta possuir mais de 20 likes;


## 🚀 Execute Localmente

📌 Pré-requisitos
Antes de iniciar, certifique-se de ter os seguintes itens instalados:

- Node.js e npm/yarn
- Java JDK 17+
- MySQL

### Clonar o Repositório
```bash
    git clone https://github.com/JoaoSavioli0/KommunityOn.git
```
    
### Baixar as dependências do Back-End
```bash
    cd KommunityOn # Pasta do diretório
    cd kommunityon # Pasta dos arquivos Back-End

    ./mvnw clean install  # Para Linux/Mac
    mvnw.cmd clean install  # Para Windows 
```

### Inicie a API
```bash
    ./mvnw spring-boot:run  # Para Linux/Mac
    mvnw.cmd spring-boot:run  # Para Windows
```

### Baixar as dependências do Front-End
```bash
    cd kommunityon # Pasta do diretório
    cd kon #Pasta do Front-End

    npm install  # Ou yarn install
```

### Inicie o servidor
```bash
    npm run serve # Ou yarn serve
```
> O servidor será iniciado no endereço http://localhost:9091

## 🛠️ Melhorias Futuras

- Sistema de notificação para atualizações de solicitações;
- Relatórios de solicitações mais populares;
- Mecanismo de moderação de conteúdo.

