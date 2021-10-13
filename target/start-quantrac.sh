#!/bin/sh
# The main program to run
MS_HOME=/opt/spring_app/backend # <--- EDIT THIS LINE
MS_JAR=tea-quantrac-app-backend-0.0.1-SNAPSHOT.jar  # <--- EDIT THIS LINE
cd $MS_HOME
# Get the java command
#
if [ -z "$JAVACMD" ] ; then 
  if [ -n "$JAVA_HOME"  ] ; then
    if [ -x "$JAVA_HOME/jre/sh/java" ] ; then 
      JAVACMD="$JAVA_HOME/jre/sh/java"
    else
      JAVACMD="$JAVA_HOME/bin/java"
    fi
  else
    JAVACMD=`which java 2> /dev/null`
    if [ -z "$JAVACMD" ] ; then 
      JAVACMD=java
    fi
  fi
fi
MS_PID=`ps fax|grep java|grep "${MS_JAR}"|awk '{print $1}'`
if [ -n "$MS_PID"  ] ; then
  echo "Kill -9 $MS_PID"
  kill -9 $MS_PID
fi
# Run the programme
#
$JAVACMD -Xms256m -Xmx1024m -jar $MS_JAR --spring.config.location=file:/opt/spring_app/backend/config/tea-quantrac-app-backend.yml &





