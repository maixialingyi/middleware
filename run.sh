#!/usr/bin/env bash
# ------------------------------------
# 项目运行脚本
# -----------------------------------

set -e

CUSTOM_JVM_OFFLINE="-server
                    -Dfile.encoding=UTF-8
                    -Dsun.jnu.encoding=UTF-8
                    -Djava.net.preferIPv6Addresses=false
                    -Djava.io.tmpdir=/tmp
                    -Duser.timezone=GMT+08
                    -Xmx6g
                    -Xms6g
                    -XX:MetaspaceSize=128m
                    -XX:MaxMetaspaceSize=384m
                    -XX:SurvivorRatio=14
                    -XX:NewRatio=1
                    -XX:+HeapDumpOnOutOfMemoryError
                    -XX:+DisableExplicitGC
                    -XX:+PrintFlagsFinal
                    -XX:+PrintGCDetails
                    -XX:+PrintHeapAtGC
                    -XX:+PrintTenuringDistribution
                    -XX:+PrintGCApplicationStoppedTime
                    -XX:+PrintGCDateStamps
                    -XX:+PrintGCTimeStamps
                    -XX:+PrintCommandLineFlags
                    -XX:+UseConcMarkSweepGC
                    -XX:+UseParNewGC
                    -XX:ParallelCMSThreads=4
                    -XX:+CMSClassUnloadingEnabled
                    -XX:+UseCMSCompactAtFullCollection
                    -XX:CMSFullGCsBeforeCompaction=1
                    -XX:CMSInitiatingOccupancyFraction=72
                    -Xdebug -Xrunjdwp:transport=dt_socket,suspend=n,server=y,address=8413
                    -javaagent:/opt/meituan/qa_test/jacocoagent.jar=output=tcpserver,port=6300,address=*,excludes=com.dianping.*"

CUSTOM_JVM_ONLINE=" -server
                    -Dfile.encoding=UTF-8
                    -Dsun.jnu.encoding=UTF-8
                    -Djava.net.preferIPv6Addresses=false
                    -Djava.io.tmpdir=/tmp
                    -Duser.timezone=GMT+08
                    -Xmx6g
                    -Xms6g
                    -XX:MetaspaceSize=128m
                    -XX:MaxMetaspaceSize=384m
                    -XX:SurvivorRatio=14
                    -XX:NewRatio=1
                    -XX:+HeapDumpOnOutOfMemoryError
                    -XX:+DisableExplicitGC
                    -XX:+PrintFlagsFinal
                    -XX:+PrintGCDetails
                    -XX:+PrintHeapAtGC
                    -XX:+PrintTenuringDistribution
                    -XX:+PrintGCApplicationStoppedTime
                    -XX:+PrintGCDateStamps
                    -XX:+PrintGCTimeStamps
                    -XX:+PrintCommandLineFlags
                    -XX:+UseConcMarkSweepGC
                    -XX:+UseParNewGC
                    -XX:ParallelCMSThreads=4
                    -XX:+CMSClassUnloadingEnabled
                    -XX:+UseCMSCompactAtFullCollection
                    -XX:CMSFullGCsBeforeCompaction=1
                    -XX:CMSInitiatingOccupancyFraction=72"


# 环境变量
NOW_DATE=`date +%Y%m%d%H%M`
HOST_NAME=`hostname`
HOST_IP=`hostname -i | awk -F ' ' '{if(NF==1) print $1;else print $2 end}'`
JAVA_HOME=/usr/local/java8
LOG_HOME=/var/sankuai/logs

DEFAULT_JVM_LOG_ARGS="  -Xloggc:$LOG_HOME/$SERVER_NAME.gc.log.$NOW_DATE
                        -XX:ErrorFile=$LOG_HOME/$SERVER_NAME.vmerr.log.$NOW_DATE
                        -XX:HeapDumpPath=$LOG_HOME/$SERVER_NAME.heaperr.log.$NOW_DATE"


WORKDIR=/opt/meituan/apps/$SERVER_NAME/work

JAR_NAME=`ls -t $WORKDIR/*.jar | grep $SERVER_NAME | head -1 | awk -F/ '{print $NF}'`

if [ -z $VM_LOG ]; then
    VM_LOG="$DEFAULT_JVM_LOG_ARGS"
fi

if [ "$ENVIRONMENT" = "prod" ]; then
    JVM_ARGS="$CUSTOM_JVM_ONLINE"
else
    JVM_ARGS="$CUSTOM_JVM_OFFLINE"
fi

echo "#=========================开始部署=========================#"
echo "--------------------------部署参数--------------------------"
echo "ENVIRONMENT=$ENVIRONMENT"
echo "WORKDIR=$WORKDIR"
echo "SERVER_NAME=$SERVER_NAME"
echo "APP_KEY=$APP_KEY"
echo "JAR_NAME=$JAR_NAME"
echo "LOG_HOME=$LOG_HOME"
echo "VM_LOG=$VM_LOG"
echo "JVM_ARGS=$JVM_ARGS"

exec $JAVA_HOME/jre/bin/java \
     $VM_LOG $JVM_ARGS \
     -Denvironment=$ENVIRONMENT -Dspring.profiles.active=$ENVIRONMENT \
     -Dapp.key=$APP_KEY -Dapp.host=$HOST_NAME -Dapp.ip=$HOST_IP \
     -jar $WORKDIR/$JAR_NAME 2>&1

echo "#结束部署#"
