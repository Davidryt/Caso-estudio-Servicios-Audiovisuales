echo "Config IPTV"


mkdir /var/www/apache2

cd /home/labsma/Desktop/Caso-estudio-Servicios-Audiovisuales/Servicio-IPTV-STREAMING/IPTV

cp guia_iptv.xmltv /var/www/apache2
cp /var/www/index.html /var/www/apache2

cd /etc/apache2/
mkdir sites-available
mkdir sites-enabled

cp /home/labsma/Desktop/Caso-estudio-Servicios-Audiovisuales/Servicio-IPTV-STREAMING/IPTV/apache2.conf sites-available
cd sites-available
a2ensite apache2.conf

service apache2 reload