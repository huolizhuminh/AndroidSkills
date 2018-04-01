
package minhui.demo.designtype.prototype;

import minhui.demo.DemoInterface;

public class ProtoTypeTest implements DemoInterface {

	@Override
	public void startRun() {
		IPPacket ipPacket = new IPPacket();
		ipPacket.ipHeader = new IPHeader();
		ipPacket.ipHeader.ipHeaderData = "testipHeader";
		ipPacket.tcpHeader = new TCPHeader();
		ipPacket.tcpHeader.tcpData = "testtcpData";
		try {
			IPPacket clone = (IPPacket) ipPacket.clone();
			System.out.println("clone"+clone.ipHeader.ipHeaderData+clone.tcpHeader.tcpData);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
