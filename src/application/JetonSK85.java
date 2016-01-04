package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class JetonSK85 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private List<Integer> tab;
	private List<Integer> fa;
	
	@Override
	public String toString() {
		return "JetonSK85 [tab=" + tab + ", fa=" + fa + "]";
	}
	
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
