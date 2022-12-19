echo "CONFIG DNS SERVER"

cd /home/labsma/Desktop/Caso-estudio-Servicios-Audiovisuales/Servicio-IPTV-STREAMING/DNS
cp -r hosts /tmp/hosts
killall dnsmasq
/usr/sbin/dnsmasq -C /etc/dnsmasq.d/dnsmasq.conf
