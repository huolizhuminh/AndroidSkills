
package minhui.demo.designtype.prototype;

public class IPHeader implements Cloneable {
	public String ipHeaderData;

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
