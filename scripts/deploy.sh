#!/bin/bash

BUILD_JAR=$(ls /home/ec2-user/e2e-boogle/build/libs/*.jar)
JAR_NAME=$(basename $BUILD_JAR)
echo "##### Build File Name: $JAR_NAME" >> /home/ec2-user/e2e-boogle/deploy.log

echo "##### Copy Build File" >> /home/ec2-user/e2e-boogle/deploy.log
DEPLOY_PATH=/home/ec2-user/e2e-boogle/
cp $BUILD_JAR $DEPLOY_PATH

echo "##### Check currently running application PID" >> /home/ec2-user/e2e-boogle/deploy.log
CURRENT_PID=$(pgrep -f $JAR_NAME)

if [ -z $CURRENT_PID ]
then
  echo "##### Since there are no applications currently running, it will not terminate." >> /home/ec2-user/e2e-boogle/deploy.log
else
  echo ">>> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

DEPLOY_JAR=$DEPLOY_PATH$JAR_NAME
echo "##### Deploy DEPLOY_JAR"    >> /home/ec2-user/e2e-boogle/deploy.log
nohup java -jar $DEPLOY_JAR --spring.config.location=/home/ec2-user/e2e-boogle-config/application-prod.yml >> /home/ec2-user/deploy.log 2>/home/ec2-user/e2e-boogle/deploy_err.log &
