PARA EL DNS

Metete como super

Colocate en la carpeta correcta 

cd -r /home/labsma/Desktop/Caso-estudio-Servicios-Audiovisuales/Servicio-IPTV-STREAMING/DNS

Añadimos los nuevos hosts

cp -r hosts /tmp/hosts

Lanza el dnsmasq

/usr/sbin/dnsmasq -C /etc/dnsmasq.d/dnsmasq.conf 

Copia el nuevo resolve 

cp -r resolv.conf /etc/resolv.conf

