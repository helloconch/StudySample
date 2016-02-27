import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client {
	private static final int SERVER_PORT = 30000;
	private Socket socket;
	private PrintStream ps;
	private BufferedReader brServer;
	private BufferedReader keyIn;

	public static void main(String[] args) {
		Client client = new Client();
		client.init();
		client.readAndSend();
	}

	public void init() {
		try {
			// 初始化代表键盘的输入流
			keyIn = new BufferedReader(new InputStreamReader(System.in));
			// 连接到服务器
			socket = new Socket("127.0.0.1", SERVER_PORT);
			// 获取该Socket对应的输入流和输出流
			ps = new PrintStream(socket.getOutputStream());
			brServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String tip = "";
			// 采用循环不断的弹出对话框要求输入用户名
			while (true) {
				String userName = JOptionPane.showInputDialog(tip + "输入用户名");
				// 将用户输入的用户名的前后增加协议字符串
				ps.println(CommonProtocol.USER_ROUND + userName + CommonProtocol.USER_ROUND);
				// 读取服务器响应
				String result = brServer.readLine();
				// 如果用户重复,开始下次循环
				if (result.equals(CommonProtocol.NAME_REP)) {
					tip = "用户名重复!请重新";
					continue;
				}
				if (result.equals(CommonProtocol.LOGIN_SUCCESS)) {
					break;
				}
			}
		} catch (UnknownHostException e) {
			System.out.println("找不到遠程服务器,请查看远程服务器是否启动");
			closeRS();
			System.exit(1);
		} catch (IOException e) {
			System.out.println("网络异常，请重新登录");
			closeRS();
			System.out.println(1);
		}
		new Thread(new ClientT(brServer)).start();
	}

	// 定义一个读取键盘输出，并向网络发送的方法
	private void readAndSend() {
		try {
			// 不断读取键盘输入
			String line = null;
			while ((line = keyIn.readLine()) != null) {
				// 如果发送的信息中有冒号，并以//开头，则认为想发送私聊信息
				if (line.indexOf(":") > 0 && line.startsWith("//")) {
					line = line.substring(2);
					ps.println(CommonProtocol.PRIVATE_ROUND + line.split(":")[0] + CommonProtocol.SPLIT_SIGN + line.split(":")[1] + CommonProtocol.PRIVATE_ROUND);
				} else {
					ps.println(CommonProtocol.MSG_ROUND + line + CommonProtocol.MSG_ROUND);
				}
			}
		} catch (IOException e) {
			System.out.println("网络通信异常");
			closeRS();
			System.out.println(1);
		}
	}

	private void closeRS() {
		try {
			if (keyIn != null)
				keyIn.close();
			if (brServer != null)
				brServer.close();
			if (ps != null)
				ps.close();
			if (socket != null)
				socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
