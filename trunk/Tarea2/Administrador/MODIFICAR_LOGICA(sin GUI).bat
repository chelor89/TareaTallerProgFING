@echo off
SET /P Ruta=Escriba la ruta de la crpeta NetBeansProjects:
CD build\classes
jar cf Logica.jar controladores dataTypes entidades Fabrica interfaces manejadores
COPY Logica.jar %Ruta%\Web\libs
ECHO Archivo creado en %Ruta%\Web\libs
pause
exit