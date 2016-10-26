package model;


public class HashElement {
	private final String key;
	private String value;
	private int hash;
	private int probe;
	
	public HashElement(String key, String value, int hash) {
		this.key = key;
		this.value = value;
		this.hash = hash;
	}
	

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public int getHash() {
		return hash;
	}
	
	public int getProbe() {
		return probe;
	}

	
	public void setValue(String value) {
		this.value = value;
	}

	public void setHash(int hashCode) {
		this.hash = hashCode;
	}
	
	public void setProbe(int probe) {
		this.probe = probe;
	}
	
	public void incrementProbe(){
		this.probe++;
	}
	
	public void decrementProbe(){
		this.probe--;
	}
}
