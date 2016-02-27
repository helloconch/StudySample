public enum SeasonEnum {
	SPRING, SUMMER, FALL, WINTER, SSS("春天");
	public String name;
	public int a;

	private SeasonEnum() {
	}

	private SeasonEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
