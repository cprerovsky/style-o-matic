package at.docstoegprer.android;

public class Outfit {
	
	private String outfitname;
	
	private int accessoriesPostion;
	private int topPosition;
	private int bottomPosition;
	private int shoesPosition;
	

	public Outfit(String outfitname, int accessoriesPostion, 
			int topPostion, int bottomPostion, int shoesPosition){
		this.outfitname = outfitname;
		this.accessoriesPostion = accessoriesPostion;
		this.topPosition = topPostion;
		this.bottomPosition = bottomPostion;
		this.shoesPosition = shoesPosition;
	}

	public String getSaveLine(){
		return outfitname + ";" + accessoriesPostion + ";" + 
					topPosition + ";" + bottomPosition + ";" + shoesPosition + "\n";
	}

	public String getOutfitname() {
		return outfitname;
	}


	public int getAccessoriesPostion() {
		return accessoriesPostion;
	}


	public int getTopPosition() {
		return topPosition;
	}


	public int getBottomPosition() {
		return bottomPosition;
	}


	public int getShoesPosition() {
		return shoesPosition;
	}
	
	
}
