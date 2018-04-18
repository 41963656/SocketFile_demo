import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestSocketClient {
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			System.out.println("generator Client " + i);
			if (i % 50 == 0) {
				Thread.sleep(300l);
			}
			new TestSocketClient().app(i);
		}
	}

	public void app(int i) {
		new TestClient(i).start();
	}

	class TestClient extends Thread {

		private int i;

		public TestClient(int i) {
			this.i = i;
		}

		public TestClient() {
			super();
			// TODO Auto-generated constructor stub
		}

		public TestClient(Runnable target, String name) {
			super(target, name);
			// TODO Auto-generated constructor stub
		}

		public TestClient(Runnable target) {
			super(target);
			// TODO Auto-generated constructor stub
		}

		public TestClient(String name) {
			super(name);
			// TODO Auto-generated constructor stub
		}

		public TestClient(ThreadGroup group, Runnable target, String name, long stackSize) {
			super(group, target, name, stackSize);
			// TODO Auto-generated constructor stub
		}

		public TestClient(ThreadGroup group, Runnable target, String name) {
			super(group, target, name);
			// TODO Auto-generated constructor stub
		}

		public TestClient(ThreadGroup group, Runnable target) {
			super(group, target);
			// TODO Auto-generated constructor stub
		}

		public TestClient(ThreadGroup group, String name) {
			super(group, name);
			// TODO Auto-generated constructor stub
		}

		public void run() {
			Socket socket = null;
			BufferedReader in = null;
			PrintWriter write = null;
			try {
				socket = new Socket("127.0.0.1", 11177);
				System.out.println("connection[" + i + "] successful.");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				// 由系统标准输入设备构造BufferedReader对象
				write = new PrintWriter(socket.getOutputStream());
				// 由Socket对象得到输出流，并构造PrintWriter对象
				// 3、获取输入流，并读取服务器端的响应信息
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				// 由Socket对象得到输入流，并构造相应的BufferedReader对象
				// String readline;
				// readline = br.readLine(); // 从系统标准输入读入一字符串
				// while (!readline.equals("end")) {
				// // 若从标准输入读入的字符串为 "end"则停止循环
				// write.println(readline);
				// // 将从系统标准输入读入的字符串输出到Server
				// write.flush();
				// // 刷新输出流，使Server马上收到该字符串
				// System.out.println("Client:" + readline);
				// // 在系统标准输出上打印读入的字符串
				// System.out.println("Server:" + in.readLine());
				// // 从Server读入一字符串，并打印到标准输出上
				// readline = br.readLine(); // 从系统标准输入读入一字符串
				// }
				// // 继续循环
				int i = 0;
				while (true) {
					int byt = (int) (Math.random() * (95 - 65 + 1) + 65);
					write.print((char) byt);
					if (i++ >= 200) {
						i = 0;
						write.println();
						write.flush();
						Thread.sleep(3000);
					}
				}
				// 4、关闭资源

			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeWrite(write);
				closeIn(in);
				closeSocket(socket);
			}
		}

		public void closeWrite(PrintWriter write) {
			if (write != null) {
				write.close();
			}
		}

		public void closeIn(BufferedReader in) {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void closeSocket(Socket s) {
			try {
				if (s != null) {
					s.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
