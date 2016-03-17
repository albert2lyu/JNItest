#!/bin/sh
export ProjectPath=$(cd "../$(dirname "$1")"; pwd)
export TargetClassName="com.brucetoo.jnitest.CUtils"

export SourceFile="${ProjectPath}/app/src/main/java"
export TargetPath="${ProjectPath}/app/src/main/jni"

cd "${SourceFile}"
javah -d ${TargetPath} -classpath "${SourceFile}" "${TargetClassName}"
echo -d ${TargetPath} -classpath "${SourceFile}" "${TargetClassName}"

##生成.h文件的脚本 只需要修改对应的路径 然后cd到指定autojavah.sh的目录  然后 sh autojavah.sh 就能生成代码