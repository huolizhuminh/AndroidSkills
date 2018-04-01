
package minhui.demo.designtype.adapter;

public class ObjectAdapter implements Level {
    private LowImplement lowimplement=new LowImplement();
	@Override
	public String getHigh() {
		// TODO Auto-generated method stub
		return "ObjectAdaptergetHigh";
	}

	@Override
	public String getLow() {
		// TODO Auto-generated method stub
		return lowimplement.getLow();
	}

}
