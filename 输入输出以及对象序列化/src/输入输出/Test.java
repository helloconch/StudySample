package 输入输出;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.io.StringReader;
import java.io.StringWriter;

public class Test {

	/**
	 * 每种输入，输出流可分为字节流和字符流
	 */
	public static void main(String[] args) throws Exception {
		// File类
		// ******************************
		// 访问文件和目录**********************************************
		// 以当前路径来创建一个File对象
		File file = new File(".");
		// 直接获取文件名，输出一点
		System.out.println(file.getName());
		// 获取相对的父路径可能出错，下面代码输出null
		System.out.println(file.getParent());
		// 获取绝对路径对应File对象
		System.out.println(file.getAbsoluteFile());
		System.out.println(file.getAbsolutePath());
		// 获取上一级路径
		System.out.println(file.getAbsoluteFile().getParent());
		// 在当前路径下创建一个临时文件
		File tmpFile = File.createTempFile("aaa", ".txt", file);
		// 指定当JVM退出时删除该文件
		tmpFile.deleteOnExit();
		// 以系统当前时间作为新文件名来创建新文件
		File newFile = new File(System.currentTimeMillis() + "");
		System.out.println("newFile对象是否存在" + newFile.exists());
		// newFile.createNewFile();
		// 創建一个目录
		newFile.mkdir();

		// 使用list方法来列出当前路径下的所有文件和路径
		String[] list = file.list();
		for (String fileName : list) {
			System.out.println("当前路径下的所有文件和路径:" + fileName);
		}
		// listRoots静态方法列出所有磁盘根路径
		File[] listRoots = File.listRoots();
		System.out.println("=系统所有的磁盘根路径===");
		for (File root : listRoots) {
			System.out.println(root);
		}

		// ********************************文件过滤器*****************************************
		File fileFilter = new File(".");
		String[] list2 = fileFilter.list(new MyFilenameFilter());
		for (String name : list2) {
			System.out.println("文件过滤器:" + name);
		}

		// *************流的分类**********************
		// 输入流:只能从中读取数据，而不能向其写出数据
		// 输出流:只能向其写出数据，而不能从中读取数据
		// Java中的输入流主要由InputStream和Reader作为基类
		// 输出流主要由OutputStream和Writer作为基类
		// 字节流和字符流
		// 字节流主要由InputStream和OutputStream作为基类
		// 字符流主要由Reader和Writer作为基类

		// InputStream和Reader都是抽象类，本身不能创建实例，但它们分别
		// 有一个用于读取文件的输入流:FileInputStream和FileReader,它们都是节点流，会直接和指定文件关联。
		// 创建字节输入流
		FileInputStream fis = new FileInputStream("Test.java");
		// 创建一个长度1024的竹筒
		byte[] bbuf = new byte[1024];
		// 用于保存实际读取的字节数
		int hasRead = 0;
		// 使用循环来重复取水过程
		while ((hasRead = fis.read(bbuf)) > 0) {
			// 取出竹筒里的水滴，将字节数组转换成字符串
			System.out.println(new String(bbuf, 0, hasRead));
		}
		fis.close();

		// 或字符流
		FileReader fr = new FileReader("Test.java");
		// 创建一个长度为32的竹筒
		char[] cbuf = new char[32];
		// 用于保存实际读取的字符数
		int hasCharRead = 0;
		while ((hasCharRead = fr.read(cbuf)) > 0) {
			System.out.println(new String(cbuf, 0, hasCharRead));
		}
		fr.close();

		// 输出流

		FileInputStream fis1 = null;
		FileOutputStream fos1 = null;
		// 创建字节输入流
		fis1 = new FileInputStream("Test.java");
		// 创建字节输出流
		fos1 = new FileOutputStream("newFile.txt");
		byte[] byte1 = new byte[32];
		int hasReadLen = 0;
		while ((hasReadLen = fis1.read(byte1)) > 0) {
			fos1.write(byte1, 0, hasReadLen);
		}

		if (fis1 != null)
			fis1.close();
		if (fos1 != null)
			fos1.close();

		// 如果直接输出字符串的内容，Writer会有更好的效果
		FileWriter fw = new FileWriter("poem.txt");
		fw.write("早安\r\n");
		fw.write("北京\r\n");
		if (fw != null)
			fw.close();

		// ******处理流来包装节点流*********
		// PrintStream处理流来包装输出流
		PrintStream ps = null;
		FileOutputStream fos2 = new FileOutputStream("test.txt");
		// 以PrintStream来包装FileOutputStream输出流
		ps = new PrintStream(fos2);
		// 使用PrintStream执行输出
		ps.println("普通字符串");
		// ps.println(Object);
		ps.close();// 关闭最上层的处理流，系统会自动关闭被该处理流包装的节点流

		// ***********使用字符串作为物理节点的字符输入、输出流的用法********************
		String src = "从明天起，做一个幸福的人\n" + "喂马，劈柴，周游世界\n";
		StringReader sr = new StringReader(src);
		char[] buffer = new char[32];
		int hasRead4 = 0;
		while ((hasRead4 = sr.read(buffer)) > 0) {
			System.out.println(new String(buffer, 0, hasRead4));
		}
		sr.close();
		// 使用StringWriter
		StringWriter sw = new StringWriter(20);
		sw.write("我有一只小棉羊");
		System.out.println("下面是sw的字符串节点里的内容");
		System.out.println(sw.toString());

		// *************转换流****************
		// 1.InputStreamReader将字节输入流转换成字符输入流
		// 2.OutStreamWriter将字节输出流转换成字符输出流
		BufferedReader br1 = null;
		InputStreamReader reader = new InputStreamReader(System.in);
		br1 = new BufferedReader(reader);
		String bufferContent = null;
		while ((bufferContent = br1.readLine()) != null) {
			if (buffer.equals("exit")) {
				System.exit(1);
			}
			// 打印读取的内容
			System.out.println("输入内容：" + buffer);
		}
		br1.close();

		// **************推回输入流******************
		// PushbackInputStream和PushbackReader
		// **************** 重定向标准输入/输出
		// setErr(PrintStream err)重定向"标准"错误输出流
		// setIn(InputStream in)重定向标准输入流
		// setOut(PrintStream out)重定向标准输出流
		// **************Java虚拟机读取其他进程的数据******************

		// ****************RandomAccessFile任意访问****即可以读文件，也可以写**********************
		// getFilePoniter()返回文件记录指针的当前位置
		// seek(long pos)将文件记录指针定位到pos位置

		// 使用RandomAccessFile来访问指定的中间部分数据
		RandomAccessFile raf = null;
		// 以只读方式打开一个RandomAccessFile对象
		raf = new RandomAccessFile("Test.java", "r");
		System.out.println("RandomAccessFile的文件指针的初始位置:" + raf.getFilePointer());
		// 移动raf的文件记录指针的位置
		raf.seek(300);
		byte[] bbuf3 = new byte[1024];
		int hasRead3 = 0;
		while ((hasRead3 = raf.read(bbuf3)) > 0) {
			System.out.println(new String(bbuf3, 0, hasRead3));
		}
		if (raf != null)
			raf.close();

		// RandomAccessFile向指定文件后追加内容
		// 以读写方式打开一个RandomAccessFile
		RandomAccessFile raf2 = new RandomAccessFile("test.txt", "rw");
		// 将记录指针移动到test.txt文件的最后
		raf2.seek(raf2.length());
		raf2.write("追加的内容".getBytes());
		if (raf2 != null)
			raf2.close();

		// RandomAccessFile实现向指定文件、指定位置插入内容的功能
		insert("test.txt", 45, "插入的内容\r\n");

	}

	public static void insert(String fileName, long pos, String content) throws Exception {
		RandomAccessFile raf3 = null;
		// 创建一个临时文件来保存插入点后的数据
		File tmp = File.createTempFile("tmp", null);
		FileOutputStream tmpOut = null;
		FileInputStream tmpIn = null;
		tmp.deleteOnExit();
		raf3 = new RandomAccessFile(fileName, "rw");
		tmpOut = new FileOutputStream(tmp);
		tmpIn = new FileInputStream(tmp);
		raf3.seek(pos);
		// 下面代码将插入点后的内容读入临时文件中保存
		byte[] bbuf = new byte[64];
		int hasRead = 0;
		while ((hasRead = raf3.read(bbuf)) > 0) {
			tmpOut.write(bbuf, 0, hasRead);
		}
		// 下面代码插入内容
		// 把文件记录指针重新定位到pos位置
		raf3.seek(pos);
		// 追加需要插入的内容
		raf3.write(content.getBytes());
		// 追加临时文件的内容
		while ((hasRead = tmpIn.read(bbuf)) > 0) {
			raf3.write(bbuf, 0, hasRead);
		}
		tmpIn.close();
		tmpOut.close();
		raf3.close();
	}

}

class MyFilenameFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String name) {
		// 如果文件名以.java结尾，或者文件对应一个路径，则返回true
		return name.endsWith(".java") || new File(name).isDirectory();
	}

}
