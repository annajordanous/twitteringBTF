package hosebirding;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.twitter.hbc.core.event.Event;

public class hbcGetAccess {

	BlockingQueue<String> msgQueue;
	BlockingQueue<Event> eventQueue;
	
	private void setUpBlockingQueues()  {
		msgQueue = new LinkedBlockingQueue<String>(100000);
		eventQueue = new LinkedBlockingQueue<Event>(1000);

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
