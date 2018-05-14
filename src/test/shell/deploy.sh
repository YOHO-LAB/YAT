#!/bin/bash
#mvn -v 查看java版本，要和pom里java版本一致，若不一致，yum install java-1.8.0-openjdk*

CODE_PATH=/opt/code
TOMCAT_PATH=/opt/tomcat-yat

mkdir -p $CODE_PATH
cd $CODE_PATH
if [[ -d $CODE_PATH/yat ]];then
  cd $CODE_PATH/yat
  git reset --hard origin/master
  git pull
else
  git clone git@git.xxx.cn:test/yat.git
fi
cd $CODE_PATH/yat
mvn clean package

ps -ef | grep "$TOMCAT_PATH" | grep -v grep | cut -c 9-15 | xargs kill -9

mv $CODE_PATH/yat/target/yat.war $TOMCAT_PATH/webapps/yat.war
rm -rf $TOMCAT_PATH/webapps/yat

sh $TOMCAT_PATH/bin/startup.sh