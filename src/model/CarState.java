package model;

public class CarState {
	
	
	
	public class GPRMC {
		public String utc;
		public String locateState;
		public double latitude;
		public String NorS;
		public double longitude;
		public String EorW;
		public String speed;
		public String direction;
		public String date;
		public String declination;
		public String mDirection;
		public String separator;
		public String check;
		
		
		GPRMC(String orgString){
			String org[] = orgString.split(",");
			this.utc = org[0];
			this.locateState = org[1];
			this.latitude = Double.parseDouble(org[2]);
			this.NorS = org[3];
			this.longitude = Double.parseDouble(org[4]);
			this.EorW = org[5];
			this.speed = org[6];
			this.direction = org[7];
			this.date = org[8];
			this.declination = org[9];
			this.mDirection = org[10];
			this.separator = org[11].substring(0,0);
			this.check = org[11].substring(1, 2);

		}
		
	}
	
	public GPRMC gprmc;
	public String posAccuracy;
	public String height;
	public String portState;
	public String voltage;
	public String baseStation;
	public String gsmStrength;
	public String distant;
	
	
	
	public CarState(String orgString){
		String[] org = orgString.split("\\|");
		this.gprmc = new GPRMC(org[0]);
		this.posAccuracy = org[1];
		this.height	= org[2];
		this.portState	= org[3];
		this.voltage = org[4];
		this.baseStation = org[5];
		this.gsmStrength = org[6];
		this.distant = org[7];
		
		
	}
	
	
	
	
	
	
	

}
