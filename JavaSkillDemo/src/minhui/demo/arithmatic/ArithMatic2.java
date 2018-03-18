
package minhui.demo.arithmatic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import minhui.demo.DemoInterface;
import minhui.demo.Utils;

/**
 * 1、假设淘宝一天有5亿条成交数据，求出销量最高的100个商品并给出算法的时间复杂度。 利用堆排序
 */
public class ArithMatic2 implements DemoInterface {
	class ProductSell implements Comparable {
		String ID;
		int sells;

		@Override
		public int hashCode() {
			return ID.hashCode();
		}

		@Override
		public int compareTo(Object o) {
			if (o == null) {
				return -1;
			}
			ProductSell compareSell = (ProductSell) o;
			return sells - compareSell.sells;
		}
	}

	@Override
	public void startRun() {
		HashMap<String, ProductSell> Datas = new HashMap(20000);
		ProductSell sell;
		for (; (sell = getData()) != null;) {
			ProductSell lastSell = Datas.get(sell.ID);
			if (lastSell == null) {
				lastSell = new ProductSell();
				lastSell.ID = sell.ID;
				lastSell.sells = sell.sells;
				Datas.put(sell.ID, lastSell);
			} else {
				lastSell.sells = lastSell.sells + sell.sells;
			}
		}
		ProductSell[] sellSum = new ProductSell[Datas.size()];
		Iterator<Entry<String, ProductSell>> iterator = Datas.entrySet().iterator();
		int current = 0;
		while (iterator.hasNext()) {
			sellSum[current] = iterator.next().getValue();
			current++;
		}
		Utils.sortData(sellSum,100);
	}

	private ProductSell getData() {
		return null;
	}

}
