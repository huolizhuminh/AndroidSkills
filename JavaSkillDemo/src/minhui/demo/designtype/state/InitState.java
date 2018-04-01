
package minhui.demo.designtype.state;

public class InitState extends IState {

	public InitState(Context context) {
		super(context);
		System.out.println("InitState build");
	}

	@Override
	public void back() {
		System.out.println("InitState can not back");
	}

	@Override
	public void forward() {
		System.out.println("InitState forward");
		context.setState(new ProcessState(context));
	}

}
