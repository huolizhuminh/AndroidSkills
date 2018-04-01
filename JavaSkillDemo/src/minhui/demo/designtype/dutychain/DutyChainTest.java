
package minhui.demo.designtype.dutychain;

import minhui.demo.DemoInterface;

public class DutyChainTest implements DemoInterface {

	@Override
	public void startRun() {
    Process process = new Process();
    WhiteIntercept whiteIntercept = new WhiteIntercept();
    BlackIntercept blackIntercept = new BlackIntercept();
    process.addIntercept(whiteIntercept);
    process.addIntercept(blackIntercept);
    Request request = new Request();
    request.request="this is a response";
    process.process(request);
	}

}
