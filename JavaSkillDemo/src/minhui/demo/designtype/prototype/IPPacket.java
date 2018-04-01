
package minhui.demo.designtype.prototype;

public class IPPacket implements Cloneable{
	IPHeader ipHeader;
	TCPHeader tcpHeader;
	@Override
	protected Object clone() throws CloneNotSupportedException {
		IPPacket tcpPacket = new IPPacket();
		tcpPacket.ipHeader=(IPHeader) ipHeader.clone();
		tcpPacket.tcpHeader=(TCPHeader) tcpHeader.clone();
		return tcpPacket;
	}
}
