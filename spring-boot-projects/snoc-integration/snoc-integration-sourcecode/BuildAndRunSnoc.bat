@echo off
echo This script prepared by Vinayak-Mahadev

REM snoc-common module
start cmd.exe /c  "CD ./snoc-common/ && mvn clean install -U && exit"
echo snoc-common builded
pause

REM snoc-entities module
start cmd.exe /c  "CD ./snoc-entity-generator && mvn clean install -U && exit"
echo snoc-entity-generator builded
pause

REM snoc-entities module
start cmd.exe /c  "CD ./snoc-entities-auto && mvn clean install -U && exit"
echo snoc-entities-auto builded
pause

REM snoc-entities module
start cmd.exe /c  "CD ./snoc-beans && mvn clean install -U && exit"
echo snoc-beans builded
pause

REM snoc-entity-interface module
start cmd.exe /c  "CD ./snoc-entity-interface && mvn clean install -U && exit"
echo snoc-entity-interface builded
pause

REM snoc-entity-service module
start cmd.exe /c  "CD ./snoc-entity-service && mvn clean install -U && exit"
echo snoc-entity-service builded
pause

REM Run snoc-entity-service module
start cmd.exe /k  "CD ./snoc-entity-service && mvn spring-boot:run -Drun.arguments=--server.port=9090 && exit"
echo snoc-entity-service running at 8085 before building snoc-entity-service-client
pause

REM snoc-entity-service-client module
start cmd.exe /k  "CD ./snoc-entity-service-client && mvn clean install -U -s ./../settings.xml && exit"
echo snoc-entity-service-client builded
pause

REM snoc-scheduler module
start cmd.exe /k  "CD ./snoc-scheduler && mvn clean install -U && exit"
echo snoc-scheduler builded
pause
                                                           
REM snoc-scheduler module
start cmd.exe /k  "CD ./snoc-scheduler && mvn spring-boot:run -Drun.arguments=--server.port=8080 -s ./../settings.xml&& exit"
echo snoc-entity-scheduler running at 8080


