package utilities;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExecutorService {
	private ExecutorService executor;

	public ThreadExecutorService(int size) {
		// TODO Auto-generated constructor stub
		executor = Executors.newFixedThreadPool(size);
	}

	public void getWikiLinks() {
		ThreadRunner worker1 = null;
				worker1 = new ThreadRunner();
			 executor.execute(worker1);
		executor.shutdown();
		while (!executor.isTerminated()) {
		}
	}
}

	
	