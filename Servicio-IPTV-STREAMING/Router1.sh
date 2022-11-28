echo "CONFIGURANDO RUTAS PARA R1"

ip ro add 10.0.1.0/24 via 10.0.5.1
ip ro add 10.0.2.0/24 via 10.0.3.2
ip ro add 10.0.4.0/24 via 10.0.3.2
ip ro