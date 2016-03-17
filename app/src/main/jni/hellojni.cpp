//
// Created by brucetoo on 3/18/16.
//
#include "com_brucetoo_jnitest_CUtils.h"

JNIEXPORT jstring JNICALL Java_com_brucetoo_jnitest_CUtils_getCString
  (JNIEnv *env, jobject obj)
{
    return env->NewStringUTF( "I'm from JNI Compiled with ABI .");
}