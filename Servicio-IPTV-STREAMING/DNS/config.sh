echo "CONFIG DNS SERVER"
sudo su

cd /home/labsma/Desktop/Caso-estudio-Servicios-Audiovisuales/Servicio-IPTV-STREAMING/DNS
cp -r hosts /tmp/hosts
/usr/sbin/dnsmasq -C /etc/dnsmasq.d/dnsmasq.conf

