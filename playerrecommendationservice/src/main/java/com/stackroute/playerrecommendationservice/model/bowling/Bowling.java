package com.stackroute.playerrecommendationservice.model.bowling;

import com.stackroute.playerrecommendationservice.model.bowling.FirstClass;
import com.stackroute.playerrecommendationservice.model.bowling.ListA;
import com.stackroute.playerrecommendationservice.model.bowling.Odis;
import com.stackroute.playerrecommendationservice.model.bowling.T20s;
import com.stackroute.playerrecommendationservice.model.bowling.TestMs;

public class Bowling {

	private ListA listA;
	private FirstClass firstClass;
	private Odis odis;
	private TestMs testms;
	private T20s t20s;
	
	public Bowling(ListA listA, FirstClass firstClass, Odis odis, TestMs testms, T20s t20s) {
		super();
		this.listA = listA;
		this.firstClass = firstClass;
		this.odis = odis;
		this.testms = testms;
		this.t20s = t20s;
	}
	public ListA getListA() {
		return listA;
	}
	public void setListA(ListA listA) {
		this.listA = listA;
	}
	public FirstClass getFirstClass() {
		return firstClass;
	}
	public void setFirstClass(FirstClass firstClass) {
		this.firstClass = firstClass;
	}
	public Odis getOdis() {
		return odis;
	}
	public void setOdis(Odis odis) {
		this.odis = odis;
	}
	public TestMs gettestms() {
		return testms;
	}
	public void settestms(TestMs testms) {
		this.testms = testms;
	}
	public T20s getT20s() {
		return t20s;
	}
	public void setT20s(T20s t20s) {
		this.t20s = t20s;
	}
	@Override
	public String toString() {
		return "Bowling [listA=" + listA + ", firstClass=" + firstClass + ", odis=" + odis + ", testms=" + testms
				+ ", t20s=" + t20s + "]";
	}
	
}
