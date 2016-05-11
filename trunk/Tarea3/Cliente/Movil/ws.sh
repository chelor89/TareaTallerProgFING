path=build/generated-sources/jax-ws/
mkdir -p $path
cd $path
source /$HOME/config.properties
 wsimport -keep http://$hostName:$port/publicadorMovil?wsdl

