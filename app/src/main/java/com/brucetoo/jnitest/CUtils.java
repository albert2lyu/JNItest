package com.brucetoo.jnitest;

/**
 * Created by Bruce Too
 * On 3/18/16.
 * At 00:28
 */
public class CUtils {
    public native String getCString();

    /**
     * 在生成好***.h文件后 自定义cpp实现jni调用逻辑
     * 然后在.gradle文件中配置(当然可以在release或者debug模式下分别配不同的)
     *  ndk {
            moduleName "jnilib"  //so包名
            abiFilters "armeabi", "armeabi-v7a", "x86" //so支持的平台
     }

     最后make module的时候会在app/build/intermediates/ndk/debug/lib的目录下生成
     对应平台的so包(这些文件在mac下默认是隐藏的)
     */
    static {
       System.loadLibrary("jnilib");
    }
}
