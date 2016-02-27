import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MyServer {
	// 定义保存所有Socket的ArrayList
	public static ArrayList<Socket> socketList = new ArrayList<Socket>();
	// 使用Map对象保存每个客户名字和对应输出流之间的对应关系
	public static SessionMap<String, PrintStream> clients = new SessionMap<String, PrintStream>();

	private static final int port = 30000;

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
			while (true) {
				// 此行会阻塞，将一直等待别人链接
				Socket s = serverSocket.accept();
//				socketList.add(s);
				// 每当客户端连接后启动一条ServerThread线程为该客户端服务
				new Thread(new ServerThread(s)).start();
			}
		} catch (IOException e) {
			System.out.println("服务器启动失败,是否端口" + port + "已经被占用");
		} finally {
			try {
				if (serverSocket != null) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.exit(1);
		}
	}
}
