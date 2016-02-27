package 文件锁;

import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class Test {

	/**
	 * lock和tryLock 当lock试图锁定某个文件时，如果无法得到文件锁，程序将一直阻塞。 而tryLock是直接返回而不是阻塞
	 * Lock(long position,long size,boolean shared) Lock(long position,long
	 * size,boolean shared) shared为true时，表面该锁是一个共享锁，它将允许多个进程来读取该文件
	 * ，但阻止其他进程获得对该文件的排它锁。 当为false时候，表面该锁是一个排它锁，它将锁定对该文件的读写。
	 * 
	 * FileLock的release()方法释放文件锁
	 * 
	 */
	public static void main(String[] args) throws Exception {
		FileChannel channel = new FileOutputStream("a.txt").getChannel();
		// 使用非阻塞方式对指定文件加锁
		FileLock lock = channel.tryLock();
		Thread.sleep(5000);
		lock.release();
		if (channel != null)
			channel.close();
	}

}
