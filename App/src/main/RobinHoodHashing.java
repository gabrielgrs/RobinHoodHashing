package main;

class HashElement {
	public final String key;
	public final String value;
	public HashElement(String k, String v) {
		key = k;
		value = v;
	}
}

public class RobinHoodHashing<K,V> {

        private HashElement[] table = new HashElement[10];
    
	private void put(String key, String value) {
		int hk = hash(key);
		System.out.println("Key: " + key + "  - H(k): " + hk);
		
		int i = hk % table.length;
		
		for (int j = 0; j < table.length; j++) {
			// linear probing
			//i = (i + j) % table.length;
			// quadratic probing
			//i = (i + j*j) % table.length;
			//i = (i + (1 << j)) % table.length;
			// double hashing
			int hk2 = hash(String.format("%s%d", key, hk));
			System.out.println("H1 = " + hk + " - H2 = " + hk2);
			i = (hk + j*hk2) % table.length;
			
			i = i < 0 ? -1*i : i;
			
			System.out.println("Testando na posicao: " + i);
			if (table[i] == null || table[i].key.equals(key)) {	
				table[i] = new HashElement(key,value);
				break;
			}
		}
	}
        
	private String get(String key) {
		int hk = hash(key);
		int i = hk % table.length;

		for (int j = 0; j < table.length; j++) {
			// linear probing
			i = (i + j) % table.length;
			if (table[i] != null && table[i].key.equals(key)) {	
				return table[i].value;
			}
		}
		return null;
	}
        
	public int hash(String key) {
		int prime = 31;
		for (char c : key.toCharArray()) {
			prime = (prime + c) << 4 + (c & 0x0F);
		}
		return prime;
	}
        
}
