path=build/generated-sources/jax-ws/
mkdir -p $path
cd $path
source /$HOME/config.properties
 wsimport -keep http://$hostName:$port/publicadorBusqueda?wsdl
 wsimport -keep http://$hostName:$port/publicadorRegistro?wsdl
 wsimport -keep http://$hostName:$port/publicadorPerfil?wsdl
 wsimport -keep http://$hostName:$port/publicadorRestaurantes?wsdl
 wsimport -keep http://$hostName:$port/publicadorImagenes?wsdl
 wsimport -keep http://$hostName:$port/publicadorMovil?wsdl
