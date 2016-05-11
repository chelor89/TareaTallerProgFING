sh ws.sh
ant
mv  dist/QuickOrder.war misc/apache-tomcat-7.0.57/webapps
cd misc/apache-tomcat-7.0.57/bin
chmod +x *
sh catalina.sh run