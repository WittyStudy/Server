#!/usr/bin/env bash

REPOSITORY=/opt/testapp
cd $REPOSITORY

APP_NAME=study-app
JAR_NAME=$(ls $REPOSITORY/build/libs/ | grep '.jar' | tail -n 1)
JAR_PATH=$REPOSITORY/build/libs/$JAR_NAME
ENC_PASS=$(echo $ENC_PASS)

CURRENT_PID=$(pgrep -f $APP_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> 종료할것 없음." >> deploy.log
else
  echo "> kill -9 $CURRENT_PID" >> deploy.log
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> $JAR_PATH 배포" >> deploy.log
nohup java -jar -Djasypt_encryptor_password=$ENC_PASS $JAR_PATH >> deploy.log &