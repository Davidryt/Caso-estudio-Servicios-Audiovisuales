echo ------COMPILECLIENT------

/usr/dist/jdk1.8.0_141/bin/javac -cp `echo ./vlcj-3.10.1/*.jar | tr ' ' ':'` *.java


echo ------EXECUTECLIENT------
sudo -u labsma /usr/dist/jdk1.8.0_141/bin/java -cp .:`echo ./vlcj-3.10.1/*.jar | tr ' ' ':'` Client
