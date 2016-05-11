@echo off
SET /P Ruta=Escriba la ruta de la crpeta NetBeansProjects:
CD dist
COPY Tprog.jar %Ruta%\Tarea2\libs\Logica.jar
ECHO Archivo creado en %Ruta%\Tarea2\libs
pause
exit