
package minhui.demo.designtype.state;

public abstract class IState {
	Context context;

	public IState(Context context) {
		this.context = context;
	}

	public abstract void back();

	public abstract void forward();

}
