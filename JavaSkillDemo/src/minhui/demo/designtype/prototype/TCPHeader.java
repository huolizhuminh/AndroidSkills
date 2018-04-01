
package minhui.demo.designtype.prototype;

public class TCPHeader implements Cloneable {
   public String tcpData;
   @Override
   public Object clone() throws CloneNotSupportedException {
   	return super.clone();
   }
}
