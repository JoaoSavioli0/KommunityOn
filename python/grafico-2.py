import pymysql
import pandas as pd
import plotly.express as px

conexao = pymysql.connect(
    host='localhost',
    user='root',
    password='1234',
    database='kommunityon'
)

# pega a data de abertura e conclusao das solicitacoes
query = """
SELECT 
    id, 
    data_abertura AS 'Dia Inicio', 
    data_conclusao AS 'Dia Concluido'
FROM 
    solicitacao
WHERE
    data_conclusao IS NOT NULL 
ORDER BY 
    'Dia Inicio';
"""

# le registros pra um dataframe
df = pd.read_sql(query, conexao)

conexao.close()

# criar uma nova coluna para a duração da solicitação
df['Duração'] = (df['Dia Concluido'] - df['Dia Inicio']).dt.days

# criar o gráfico de Gantt
fig = px.timeline(
    df, 
    x_start='Dia Inicio', 
    x_end='Dia Concluido', 
    y='id',  # Usando o ID como a variável para o eixo Y
    title="Duração das Solicitações - Abertura e Conclusão",
    labels={"id": "Solicitação", "Dia Inicio": "Data de Abertura", "Dia Concluido": "Data de Conclusão"}
)

# configurando o grafico
fig.update_layout(
    xaxis_title="Data",
    yaxis_title="Solicitação",
    showlegend=False
)

fig.show()