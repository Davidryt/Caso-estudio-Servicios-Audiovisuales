echo "CONFIGURANDO RUTAS PARA R2"

ip ro add 10.0.1.0/24 via 10.0.4.1
ip ro add 10.0.0.0/24 via 10.0.3.1
ip ro add 10.0.5.0/24 via 10.0.3.1
ip ro