import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class MyClient {
	private static final String address = "127.0.0.1";
	private static final int port = 30000;

	public static void main(String[] args) throws Exception {
		Socket socket = new Socket(address, port);
		// 设置10秒之后即认为超时(网络连接，读取操作超过合理时间之后)
		//对应SocketTimeoutException异常
//		socket.setSoTimeout(10000); 
		//连接超时
		// Socket socket=new Socket();
		// socket.connect(new InetSocketAddress(address, port), 10000);
		// 客户端启动ClientThread线程不断读取来自服务器的数据
		new Thread(new ClientThread(socket)).start();
		// 获取该Socket对应的输出流
		PrintStream ps = new PrintStream(socket.getOutputStream());
		String line = null;
		// 不断读取键盘输入
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while ((line = br.readLine()) != null) {
			// 将用户的键盘输入内容写入Socket对应的输出流
			ps.println(line);
		}
	}

}
