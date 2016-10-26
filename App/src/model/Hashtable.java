package model;

public class Hashtable {
	private HashElement[] table = new HashElement[10];
	private int count = 0;

	public void insert(String key, String value) {
		if (count > table.length){
			rehash();
		}
		
		int hash = hash(key);
		HashElement entry = new HashElement(key, value, hash);
		int index = hash & 0x7ffffff% table.length;
		
		while (table[index] != null){
			if (entry.getProbe() < table[index].getProbe()){
				swap(entry, table[index]);
			} else {
				entry.incrementProbe();
			}
			index = index + 1 % table.length;
		}
		table[index] = entry;
		count ++;
	}
	
	public HashElement get(String key) {
		int hash = hash(key);
		int i = hash & 0x7ffffff % table.length;
		int probe = 0;
		
		while (count < table.length && probe <= table[i].getProbe()) {
			if(table[i].getHash() == hash && table[i].getKey() == key){
				return table[i];
			}		
			i = i + 1 % table.length;
			probe++;
		}
		return null;	
	}
	
	public void remove(String key){
		int hash = hash(key);
		int i = hash & 0x7ffffff % table.length;
		int probe = 0;
		
		while (count < table.length && probe <= table[i].getProbe()) {
			if(table[i].getHash() == hash && table[i].getKey() == key){
				table[i] = null;
			}		
			// index = index + 1 % table.length;
			// probe++;
		}
                count--;
	}
	
	private void swap(HashElement entry, HashElement elements) {
		HashElement aux = entry;
		entry = elements;
		elements = aux;		
	}
	
	private int hash(String key) {
		int prime = 31;
		for (char c : key.toCharArray()) {
			prime = (prime + c) << 4 + (c & 0x0F);
		}
		return prime;
	}
	
	private void rehash() {
		throw new IllegalStateException("Do rehash");		
	}

}
