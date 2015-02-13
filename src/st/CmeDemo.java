package st;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * ConcurrentModificationException using a single thread
 */
public class CmeDemo {
	public static void main(String[] args) {
		List<String> words = new ArrayList<String>(Arrays.asList("apple", "orange", "pear", "peach", "apricot"));
		for (Iterator<String> it = words.iterator();it.hasNext();) {
			String w = it.next();
			if (w.startsWith("o")) {
				it.remove();
			}
		}
	}
}
