# /bin/bash
NAME=res
UPLOAD_NAME=res-1.0.0

BASE_PATH=/var/www/$NAME
TOMCAT_HOME=/home/ajie/tomcat
USER_DIR=/var/www/$NAME/$UPLOAD_NAME
TOMCAT_USER_DIR=$TOMCAT_HOME/webapps/$NAME
TOMCAT_WEBAPPS=$TOMCAT_HOME/webapps

#判断有没有上传文件
if [ ! -d $USER_DIR ] ; then
	echo $USER_DIR/$NAME not exit
	exit 1
fi

#删除旧的备份
if [ -d $BASE_PATH/${NAME}.old ];then
	rm -rf $BASE_PATH/${NAME}.old
	echo deletting ${NAME}.old...
fi

#关闭tomcat
$TOMCAT_HOME/bin/shutdown.sh

#将原来的项目打包备份，作版本异常回滚使用
if [ -d  $TOMCAT_USER_DIR ];then
	mv  $TOMCAT_USER_DIR $BASE_PATH/${NAME}.old
	echo "mv $TOMCAT_USER_DIR to $BASE_PATH/${NAME}.old"
	
fi

#将上传的项目重命名
mv $USER_DIR $BASE_PATH/$NAME

#将项目移到tomcat下的webapps
mv $BASE_PATH/$NAME $TOMCAT_WEBAPPS/
echo "mv $BASE_PATH/$NAME to  $TOMCAT_WEBAPPS/"

#将配置文件复制到项目中 如果项目中的配置有改变 那么需要手动在/var/www/项目名/下面找到对应的配置进行修改
for file in `ls $BASE_PATH`
do
	if test -f $file;then
		cp -f $file $TOMCAT_USER_DIR/WEB-INF/classes
		echo "cp $file to $TOMCAT_USER_DIR/WEB-INF/classes"
	fi
done

#重启tomcat
$TOMCAT_HOME/bin/startup.sh
echo "done"
