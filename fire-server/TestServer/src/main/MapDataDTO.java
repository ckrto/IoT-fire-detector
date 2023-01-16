package main;

public class MapDataDTO {
	//private String strArr;
	private String beacon;
	
	private String[] strArr;
	
	public String[] getStrArr() {
		if(strArr != null) {
			String[] tempData = new String[strArr.length];
			System.arraycopy(strArr, 0, tempData, 0, strArr.length);
			return tempData;
		} else {
			return null;
		}
	}
	
	public void setData(String[] strArr) {
		if(strArr != null) {
			this.strArr = new String[strArr.length];
			System.arraycopy(strArr, 0, this.strArr, 0, strArr.length);
		} else {
			this.strArr = null;
		}
	}
//	private static int[][] strArr = {
//			{1,2,3,4,5},
//			{2,3,4,5,6}
//	};
//	
//	public static int[][] getStrArr() {
//		return strArr;
//	}
	

//	public String getStrArr() {
//		return strArr;
//	}
//
//	public void setStrArr(String strArr) {
//		this.strArr = strArr;
//	}

	
	
	public String getBeacon() {
		return beacon;
	}
	
	public void setBeacon(String beacon) {
		this.beacon = beacon;
	}

}
