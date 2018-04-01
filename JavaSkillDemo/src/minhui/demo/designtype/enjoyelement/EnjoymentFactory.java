
package minhui.demo.designtype.enjoyelement;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class EnjoymentFactory {
	Queue<WhiteEnjoment> whieteQueue = new ArrayBlockingQueue<>(6);

	public synchronized WhiteEnjoment getWhiteEnjoyMent() {
		WhiteEnjoment enjoyment = whieteQueue.poll();
		if (enjoyment == null) {
			enjoyment = new WhiteEnjoment();
		}
		return enjoyment;
	}

	public synchronized void releaseWhiteEnjoyMent(WhiteEnjoment enjoment) {
		whieteQueue.offer(enjoment);
	}
}
