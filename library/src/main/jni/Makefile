jni:
	@echo "编译之前，请务必指定jni.h以及jni_md.h的实际运行目录"
	g++ curldroid.cpp -I /usr/lib/jdk/include -I /usr/lib/jdk/include/linux -lcurl  -fPIC -shared -o libcurl4java.so
install:
	@echo "执行install（安装so文件）操作之前，请务必指定java.library.path目录，通过System.getProperty(\"java.library.path\")可以获取"
	cp -f libcurl4java.so /usr/lib/libcurl4java.so
uninstall:
	@echo "执行uninstall（卸载so文件）操作之前，请务必指定java.library.path目录，通过System.getProperty(\"java.library.path\")可以获取"
	rm -f  /usr/lib/libcurl4java.so
clean:
	@echo "删除所有的链接文件以及所有的目标文件，可能需要root权限"
	rm -f *.o
	rm -f *.so
	rm -f /usr/lib/ibcurldroid.so