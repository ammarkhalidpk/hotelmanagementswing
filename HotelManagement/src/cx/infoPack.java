package cx;

//信息封装
public class infoPack {
	private int mNum;
	private String mType;
	private String mPlace;
	private float mPrice;
	private String mInfo;

	public infoPack(int Num, String Type, String Place, float Price, String Info) {
		mNum = Num;
		mType = Type;
		mPlace = Place;
		mPrice = Price;
		mInfo = Info;
	}

	public int getNum() {
		return (mNum);
	}

	public String getType() {
		return (mType);
	}

	public String getPlace() {
		return (mPlace);
	}

	public float getPrice() {
		return (mPrice);
	}

	public String getInfo() {
		return (mInfo);
	}

	/*
	 * public String toString() { String str = "学号：" + mId + "\n"; str += "姓名：" +
	 * mName + "\n"; str += "性别：" + mSex + "\n"; str += "年龄：" + mAge + "\n";
	 * return (str); }
	 */
}