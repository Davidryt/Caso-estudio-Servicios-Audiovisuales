echo "CONFIGURANDO RUTAS PARA R3"

ip ro add 10.0.0.0/24 via 10.0.5.2
ip ro add 10.0.3.0/24 via 10.0.5.2
ip ro add 10.0.2.0/24 via 10.0.4.2
ip ro

cp -r /home/labsma/Desktop/Caso-estudio-Servicios-Audiovisuales/Servicio-IPTV-STREAMING/Router3/pimd.conf /tmp/pimd.conf
pimd -c /tmp/pimd.conf 