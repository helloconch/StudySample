public class TestEnum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SeasonEnum season = Enum.valueOf(SeasonEnum.class, "SPRING");
		System.out.println(season.getName());
		season.name = "che";
		season.a = 0;
		System.out.println(season + "---" + season.name + "  " + season.a);
		for (SeasonEnum s : SeasonEnum.values()) {
			System.out.println(s);
		}
		SeasonEnum s = Enum.valueOf(SeasonEnum.class, "SSS");
		System.out.println(s.getName());
		// 强制系统进行垃圾回收
		System.gc();
		Runtime.getRuntime().gc();
	}

	public void judge(SeasonEnum s) {
		switch (s) {
		case SPRING:
			break;
		case SUMMER:
			break;
		case FALL:
			break;
		case WINTER:
			break;
		}
	}
}
