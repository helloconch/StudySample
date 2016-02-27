import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class StartCallable {

	public static void main(String[] args) {
		// 创建Callable对象
		RtnThread rt = new RtnThread();
		// 使用FutrueTask来包装Callable对象
		FutureTask<Integer> task = new FutureTask<Integer>(rt);
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + " i=" + i);
			if (i == 20) {
				// 实质还是以Callable对象来创建，并启动线程
				new Thread(task, "有返回值的线程").start();
			}

		}

		try {
			System.out.println("子线程的返回值:" + task.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

}
