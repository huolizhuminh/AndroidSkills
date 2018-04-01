
package minhui.demo.designtype.state;

public class FinishState extends IState {

	public FinishState(Context context) {
		super(context);
		System.out.println("FinishState build");
	}

	@Override
	public void back() {
        System.out.println("FinishState back");
        context.setState(new ProcessState(context));
	}

	@Override
	public void forward() {
		System.out.println("FinishState can not forward");
	}

}
