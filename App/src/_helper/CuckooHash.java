package _helper;


class HashEntry<K,V> {
	public final K key;
	public final V value;
	public final int[] hashes;
	int hash;
	
	public HashEntry(K key, V value, int hashCount) {
		this.key = key;
		this.value = value;
		this.hashes = createHashes(key,hashCount);
		this.hash = 0;
	}

	public static <K> int[] createHashes(K key, int hashCount) {
		int[] res = new int[hashCount]; 
		int h = key.hashCode() & 0x7FFFFFFF;
		for (int i = 0; i < hashCount; i++) {
			res[i] = h;
			String novachave = String.format("%s%d", key, h);
			h = novachave.hashCode() & 0x7FFFFFFF;
		}
		return res;
	}
}

public class CuckooHash<K,V> {

	private final int hashCount = 2;
	
	private Object[] table = new Object[10];
	
	private void put(K key, V value) {
		HashEntry<K,V> entry = new HashEntry<>(key,value,hashCount);
		cuckoo(entry, 0);
	}

	
	@SuppressWarnings("unchecked")
	private void cuckoo(HashEntry<K, V> entry, int count) {
		if (count > 30) {
			rehash();
		}
		
		int h = entry.hashes[entry.hash];
		int i = h % table.length;
		
		if (table[i] == null) {
			table[i] = entry;
		} else {
			HashEntry<K,V> oldentry = (HashEntry<K,V>)table[i];

			if (entry.key.equals(oldentry.key))
				return;
			
			table[i] = entry;
			
			int hash = (oldentry.hash + 1) % hashCount;
			oldentry.hash = hash;
			cuckoo(oldentry, count + 1);
		}
	}


	private void rehash() {
		throw new IllegalStateException("Failed to rahsh.");
	}

	@SuppressWarnings("unchecked")
	private V get(K key) {
		int[] hashes = HashEntry.createHashes(key, hashCount);
		int a = 1;
		for (int h : hashes) {
			int i = h % table.length;
			System.out.println("Acesso " + a); a++;
			if (table[i] != null) {
				HashEntry<K,V> entry = (HashEntry<K,V>)table[i];
				if (entry.key.equals(key))
					return entry.value;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		CuckooHash<String,String> table = new CuckooHash<>();
		
		String[] ks = {"123","124","125","130","423","612", "123"};
		String[] vs = {"Rafael","Ivonei","Antonio",
				       "Guilherme","Marcelo","Aline", "TESTE"};
		
		for (int i = 0; i < ks.length; i++) {
			String key = ks[i], value = vs[i];
			System.out.println("Inserindo (" + key + " , " + value + " )");
			table.put(key,value);
		}

		for (int i = 0; i < ks.length; i++) {
			System.out.println(ks[i] + " - " + table.get(ks[i]));
		}
	}

}
