#! /bin/bash
NAME=res
UPLOAD_NAME=res-1.0.0

TOMCAT_HOME=/home/ajie/tomcat
USER_DIR=/var/www/$NAME/$UPLOAD_NAME
PROJECT_DIR=$TOMCAT_HOME/webapps/$NAME

if [ ! -d $USER_DIR ] ; then
	echo $USER_DIR/$NAME not exit
	exit 1
fi

$TOMCAT_HOME/bin/shutdown.sh
rm -rf $PROJECT_NAME/META-INF
mv $USER_DIR/META-INF $PROJECT_DIR

rm -rf  $PROJECT_DIR/$NAME
mv $USER_DIR/$NAME $PROJECT_DIR

mv $USER_DIR/WEB-INF/*.* $PROJECT_DIR/WEB-INF/

rm -rf $PROJECT_DIR/WEB-INF/lib
mv $USER_DIR/WEB-INF/lib $PROJECT_DIR/WEB-INF/

rm -rf $PROJECT_DIR/WEB-INF/classes/com
mv $USER_DIR/WEB-INF/classes/com /$PROJECT_DIR/WEB-INF/classes/
$TOMCAT_HOME/bin/startup.sh

echo done.
exit 0



