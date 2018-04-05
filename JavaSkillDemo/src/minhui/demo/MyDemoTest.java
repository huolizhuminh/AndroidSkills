
package minhui.demo;

import minhui.demo.arithmatic.ArithMatic1;
import minhui.demo.arithmatic.ArithMatic3;
import minhui.demo.arithmatic.ArithMatic4;
import minhui.demo.arithmatic.ArithMatic5;
import minhui.demo.arithmatic.ArithMatic7;
import minhui.demo.arithmatic.ArithMatic8;
import minhui.demo.designtype.abstractfactroy.AbstractFactoryTest;
import minhui.demo.designtype.adapter.AdapterTest;
import minhui.demo.designtype.appearance.AppearanceTest;
import minhui.demo.designtype.bridge.BridgeDemo;
import minhui.demo.designtype.combination.CombinationTest;
import minhui.demo.designtype.command.CommandTest;
import minhui.demo.designtype.decorator.DecoratorTest;
import minhui.demo.designtype.dutychain.DutyChainTest;
import minhui.demo.designtype.enjoyelement.EnjoymentTest;
import minhui.demo.designtype.factroymethod.FactoryMethodTest;
import minhui.demo.designtype.iterator.IteartorTest;
import minhui.demo.designtype.prototype.ProtoTypeTest;
import minhui.demo.designtype.proxy.ProxyTest;
import minhui.demo.designtype.state.ContextTest;
import minhui.demo.designtype.templatemethod.TemplateMethodTest;
import minhui.demo.designtype.visitor.VisitorTest;
import minhui.demo.mysodu.generator.MySoduGenerator;
import minhui.demo.mysodusolve.MySoduSolve;
import minhui.demo.sort.HeapSortDemo;
import minhui.demo.sort.InSertSort;
import minhui.demo.sort.MaoPaoSort;
import minhui.demo.sort.MergeSort;
import minhui.demo.sort.QuickSort;

public class MyDemoTest {

	public static void main(String[] args) {
		DemoInterface test7= new MySoduGenerator();
	//	DemoInterface test7= new MySoduSolve();
		test7.startRun();
	}

}
