echo ------COMPILESERVER------

/usr/dist/jdk1.8.0_141/bin/javac -cp `echo ./vlcj-3.10.1/*.jar | tr ' ' ':'` *.java


echo ------EXECUTESERVER------
/usr/dist/jdk1.8.0_141/bin/java -cp .:`echo ./vlcj-3.10.1/*.jar | tr ' ' ':'` Server movie.mp4 dw11222.mp4
