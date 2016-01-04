package application;

import java.util.ArrayList;
import java.util.List;

public class JetonSK85 {

	private List<Integer> tab;
	private List<Integer> fa;
	
	public JetonSK85(int size) {
		this.fa = new ArrayList<Integer>();
		this.tab = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			tab.add(0);
		}
	}

	public List<Integer> getTab() {
		return tab;
	}

	public void setTab(List<Integer> tab) {
		this.tab = tab;
	}

	public List<Integer> getFa() {
		return fa;
	}

	public void setFa(List<Integer> fa) {
		this.fa = fa;
	}
	
	public void addToFa(int i) {
		this.fa.add(i);
	}
	
	public int getFirstFaAndRemove() {
		int i = this.fa.remove(0);
		return i;
	}

}
