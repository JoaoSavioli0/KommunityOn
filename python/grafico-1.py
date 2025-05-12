import pymysql
import pandas as pd
import matplotlib.pyplot as plt

# Conexão com o MySQL
conexao = pymysql.connect(
    host='localhost',
    user='root',
    password='1234',
    database='kommunityon'  # nome do seu banco
)

# Consulta
query = """
SELECT bairro, COUNT(*) AS total
FROM solicitacao
GROUP BY bairro;
"""

# Leitura dos dados em um DataFrame
df = pd.read_sql(query, conexao)

# Fechar a conexão
conexao.close()

# Plotar gráfico de barras
plt.figure(figsize=(10,6))
plt.bar(df['bairro'], df['total'], color='skyblue')
plt.title('Demandas por Bairros')
plt.xlabel('Bairro')
plt.ylabel('Número de Solicitações')
plt.xticks(rotation=45)
plt.tight_layout()
plt.show()