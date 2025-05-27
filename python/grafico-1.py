import pymysql
import pandas as pd
import matplotlib.pyplot as plt

conexao = pymysql.connect(
    host='localhost',
    user='root',
    password='1234',
    database='kommunityon'
)

# analisa densidade de solicitações por bairro
query = """
SELECT bairro, COUNT(*) AS total
FROM solicitacao
GROUP BY bairro;
"""

# le registros pra um dataframe
df = pd.read_sql(query, conexao)

conexao.close()

# configurações gráfico
plt.figure(figsize=(10,6))
plt.bar(df['bairro'], df['total'], color='skyblue')
plt.title('Demandas por Bairros')
plt.xlabel('Bairro')
plt.ylabel('Número de Solicitações')
plt.xticks(rotation=45)
plt.tight_layout()
plt.show()