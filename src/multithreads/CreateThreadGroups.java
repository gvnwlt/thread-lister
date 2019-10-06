package multithreads;

/**
 * A program that tests your implementation that
 * lists all thread groups 
 * - and threads within each group -
 * in the JVM.
 */


public class CreateThreadGroups
{
	public CreateThreadGroups() {
		/* Comment Here */
                // Three thread groups are created. The first to are children of the
                // main thread and the last (theta) is a child of alpha. 
		ThreadGroup alpha = new ThreadGroup("alpha");
		ThreadGroup beta = new ThreadGroup("beta");
		ThreadGroup theta = new ThreadGroup(alpha, "theta");

		/* Comment Here */
                // Threads are started here with the thread command. 
                // Each thread created is created under its parent thread in 
                // a tree like fashion. 
		(new Thread(alpha, new GroupWorker())).start();
		(new Thread(alpha, new GroupWorker())).start();
		(new Thread(alpha, new GroupWorker())).start();
		(new Thread(beta, new GroupWorker())).start();
		(new Thread(theta, new GroupWorker())).start();
		(new Thread(theta, new GroupWorker())).start();
	}

	class GroupWorker implements Runnable
	{
		public void run() {
			while (true) {
				try {
					Thread.sleep(1000);
					for (int i = 0; i < 1000000; i++)
						;
				}
				catch (InterruptedException ie) { }
			}
		}
	}
}
