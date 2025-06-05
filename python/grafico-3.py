import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
import pymysql

# Conexão com o MySQL
conn = pymysql.connect(
    host='localhost',
    user='root',
    password='1234',
    database='kommunityon'
)

# Consulta
query = """
SELECT 
    s.bairro, 
    t.nome AS tag, 
    COUNT(*) AS ocorrencias
FROM solicitacao_tag st
JOIN tag t ON t.id = st.id_tag
JOIN solicitacao s ON s.id = st.id_solicitacao
GROUP BY s.bairro, t.nome
ORDER BY ocorrencias DESC;
"""

df = pd.read_sql(query, conn)

conn.close()

# Pivotar para heatmap
pivot_df = df.pivot(index='bairro', columns='tag', values='ocorrencias').fillna(0)

# Gráfico
plt.figure(figsize=(12, 8))
sns.heatmap(pivot_df, annot=True, fmt=".0f", cmap='YlGnBu')
plt.title('Ocorrência de Tags por Bairro')
plt.ylabel('Bairro')
plt.xlabel('Tag')
plt.tight_layout()
plt.show()