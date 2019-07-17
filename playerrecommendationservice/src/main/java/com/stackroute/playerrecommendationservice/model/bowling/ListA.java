package com.stackroute.playerrecommendationservice.model.bowling;

public class ListA {

	private String tenper;
	private String fiveper;
	private String fourper;
	private String sr;
	private String econ;
	private String ave;
	private String bbm;
	private String bbi;
	private String wkts;
	private String runs;
	private String balls;
	private String inns;
	private String mat;
	
	
	public ListA(String tenper, String fiveper, String fourper, String sr, String econ, String ave, String bbm,
			String bbi, String wkts, String runs, String balls, String inns, String mat) {
		super();
		this.tenper = tenper;
		this.fiveper = fiveper;
		this.fourper = fourper;
		this.sr = sr;
		this.econ = econ;
		this.ave = ave;
		this.bbm = bbm;
		this.bbi = bbi;
		this.wkts = wkts;
		this.runs = runs;
		this.balls = balls;
		this.inns = inns;
		this.mat = mat;
	}
	public String getTenper() {
		return tenper;
	}
	public void setTenper(String tenper) {
		this.tenper = tenper;
	}
	public String getFiveper() {
		return fiveper;
	}
	public void setFiveper(String fiveper) {
		this.fiveper = fiveper;
	}
	public String getFourper() {
		return fourper;
	}
	public void setFourper(String fourper) {
		this.fourper = fourper;
	}
	public String getSr() {
		return sr;
	}
	public void setSr(String sr) {
		this.sr = sr;
	}
	public String getEcon() {
		return econ;
	}
	public void setEcon(String econ) {
		this.econ = econ;
	}
	public String getAve() {
		return ave;
	}
	public void setAve(String ave) {
		this.ave = ave;
	}
	public String getBbm() {
		return bbm;
	}
	public void setBbm(String bbm) {
		this.bbm = bbm;
	}
	public String getBbi() {
		return bbi;
	}
	public void setBbi(String bbi) {
		this.bbi = bbi;
	}
	public String getWkts() {
		return wkts;
	}
	public void setWkts(String wkts) {
		this.wkts = wkts;
	}
	public String getRuns() {
		return runs;
	}
	public void setRuns(String runs) {
		this.runs = runs;
	}
	public String getBalls() {
		return balls;
	}
	public void setBalls(String balls) {
		this.balls = balls;
	}
	public String getInns() {
		return inns;
	}
	public void setInns(String inns) {
		this.inns = inns;
	}
	public String getMat() {
		return mat;
	}
	public void setMat(String mat) {
		this.mat = mat;
	}
	@Override
	public String toString() {
		return "ListA [tenper=" + tenper + ", fiveper=" + fiveper + ", fourper=" + fourper + ", sr=" + sr + ", econ="
				+ econ + ", ave=" + ave + ", bbm=" + bbm + ", bbi=" + bbi + ", wkts=" + wkts + ", runs=" + runs
				+ ", balls=" + balls + ", inns=" + inns + ", mat=" + mat + "]";
	}


}
