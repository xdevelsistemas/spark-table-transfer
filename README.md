# Spark Table Transfer
Aplicação feita em cima do Spark para transferência de tabelas de bancos de dados.

[![Circle CI](https://circleci.com/gh/xdevelsistemas/spark-table-transfer/tree/publish.svg?style=svg)](https://circleci.com/gh/xdevelsistemas/spark-table-transfer/tree/publish)

# Obs:
No momento suporta apenas bancos de dados Postgres

# Como utilizar:
Rodar com os seguintes argumentos:

```bash
java -jar tabletransfer originalHost originalDatabase originalUser originalPassword originalTable destinationHost destinationTable destinationUser destinationPasswordl destinationTable
```
