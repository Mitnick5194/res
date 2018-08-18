#! /bin/bash
NAME=res
UPLOAD_NAME=res-1.0.0

BASE_PATH=/var/www/$NAME
TOMCAT_HOME=/home/ajie/tomcat
USER_DIR=/var/www/$NAME/$UPLOAD_NAME
TOMCAT_USER_DIR=$TOMCAT_HOME/webapps/$NAME
TOMCAT_WEBAPPS=$TOMCAT_HOME/webapps

if [ ! -d $USER_DIR ] ; then
	echo $USER_DIR/$NAME not exit
	exit 1
fi

if [ -d $BASE_PATH/${NAME}.old ];then
	echo deletting ${NAME}.old...
fi

$TOMCAT_HOME/bin/shutdown.sh

mv  $TOMCAT_USER_DIR $BASE_PATH/${res}.old

mv $USER_DIR $TOMCAT_WEBAPPS

# rename because maven build project is carried version info
mv $UPLOAD_NAME $NAME

for file in $BASE_PATH
do
	if test -f $file;then
		cp $file $TOMCAT_USER_DIR/WEB-INF/classes
	fi
done


