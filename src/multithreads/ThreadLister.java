package multithreads;


import multithreads.CreateThreadGroups;

/**
 * List all thread groups and threads in each group in the JVM.
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts with Java - Eighth Edition
 * Copyright John Wiley & Sons - 2010.
 */

public class ThreadLister
{
	ThreadGroup rootThreadGroup = null;
 
	/* Comment Here */
        // The thread group method is defined to look at each thread
        // in a thread group and return its parent thread. 
	ThreadGroup getRootThreadGroup( ) {
    		if ( rootThreadGroup != null )
        		return rootThreadGroup;
    		ThreadGroup currentThreadGroup = Thread.currentThread( ).getThreadGroup( );
    		ThreadGroup parentThreadGroup;
    		while ( (parentThreadGroup = currentThreadGroup.getParent( )) != null )
        		currentThreadGroup = parentThreadGroup;

		rootThreadGroup = currentThreadGroup;

    		return rootThreadGroup;
	}

	/* Comment Here */
        // This method is defined to return an array of threads that are 
        // within a thread group.
	ThreadGroup[] getAllThreadGroups( ) {
    		final ThreadGroup root = getRootThreadGroup( );

		/* Comment Here */
   		int alloc = root.activeGroupCount( );

		/* Comment Here */
    		int n = 0;
    		ThreadGroup[] groups;
    		do {
			/* Comment Here */
        		alloc *= 2;
        		groups = new ThreadGroup[alloc];

			/* Comment Here */
        		n = root.enumerate(groups, true);
    		} while (n == alloc);

		   /* Comment Here */ 
    		ThreadGroup[] allGroups = new ThreadGroup[n+1];

		   /* Comment Here */
    		allGroups[0] = root;

		   /* Comment Here */
    		System.arraycopy( groups, 0, allGroups, 1, n );

    		return allGroups;
	}

	public static void main(String[] args) {
		
		new CreateThreadGroups();
		
		ThreadLister groups = new ThreadLister();

		/* Comment Here */
                // Since the create thread groups constructor was invoked,
                // new thread groups now exist. The array thread group stores 
                // the return of all the thread groups returned by the get all 
                // thread groups method. 
		ThreadGroup[] groupList = groups.getAllThreadGroups();

		/* Comment Here */
                // The control structure loops through the array of 
                // threads in the group list, 
		for (int i = 0; i < groupList.length; i++) {
			/* Comment Here */
                        // A list of threads is initializes the array size. 
                        // Number of thread groups is returned.
			Thread list[] = new Thread[groupList[i].activeCount() * 2];
			groupList[i].enumerate(list, false);
		
			/* Comment Here */
                        // For loop prints out each value of a thread in a list. 
			System.out.println(groupList[i].getName());
			for (int j = 0; j < list.length; j++) {
				if (list[j] != null)
					System.out.println("\t"+list[j].getName()+":"+list[j].getId()+":"+list[j].getState()+":"+list[j].isDaemon());
			}
		}
	}
}
