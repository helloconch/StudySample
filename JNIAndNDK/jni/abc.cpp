#include <jni.h>
extern "C"
jstring
Java_com_example_abc_JNIInterface_getAPPUrl(JNIEnv* env,jobject thiz) {
	//return (*env)->NewStringUTF(env,"http://www.baidu.com"); //c
	return env->NewStringUTF("http://www.baidu.com");
}
//第一行代码：引入jni.h头文件
//第二行代码：因为这里用的c ,所以要extern "C" C是大小的
//第三行代码：刚才写的JNIInterface类的getAppUrl方法位于com.loveplusplus.hellojni这个包下，所以这里有一个固定的写法：Java_包名_类名_方法名。这和其它教程里面讲的 使用javah 生成头文件其实是一回事。JNIEnv* env,jobject thiz是固定传入的参数。
//第四行代码：这行注释掉的代码是c语言返回字符串的写法
//第五行代码：返回一个字符串
