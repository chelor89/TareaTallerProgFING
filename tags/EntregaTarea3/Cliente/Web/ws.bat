path=build/generated-sources/jax-ws/
mkdir %path%
cd %path%
wsimport -keep http://Marcelo-PC:9128/publicadorRestaurantes?wsdl
wsimport -keep http://Marcelo-PC:9128/publicadorPerfil?wsdl
wsimport -keep http://Marcelo-PC:9128/publicadorRegistro?wsdl
wsimport -keep http://Marcelo-PC:9128/publicadorImagenes?wsdl
wsimport -keep http://Marcelo-PC:9128/publicadorBusqueda?wsdl
