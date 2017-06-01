public class Counter {
	
	private int count = 0;
	private final String SETTINGS_FILE = "settings.txt";
	
	public Counter() {
		super();
		Integer count = new Integer(0);
		Serializer s = new Serializer();
		
		try {
			count = (Integer)s.deserializeToObject(SETTINGS_FILE);
		} catch (Exception e) {
		}
		
		this.count = count.intValue();
		
	}
	
	public void saveSettings() {
		Integer count = new Integer(this.count);
		Serializer s = new Serializer();
		
		try {
			s.serialize(count, SETTINGS_FILE);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public int getCount() {
		return count;
	}

	public void increment() {
		this.count++;
	}

	public void decrement() {
		this.count--;
	}
		
}
