package com.stackroute.playerrecommendationservice.model;

public class Batting {

	private ListA lista;
	private FirstClass firstclass;
	private Odis odis;
	private TestMs testms;
	private T20s t20s;
	
	
	public Batting(ListA lista, FirstClass firstclass, Odis odis, TestMs testms, T20s t20s) {
		super();
		this.lista = lista;
		this.firstclass = firstclass;
		this.odis = odis;
		this.testms = testms;
		this.t20s = t20s;
	}
	
	public ListA getLista() {
		return lista;
	}
	public void setLista(ListA lista) {
		this.lista = lista;
	}
	public FirstClass getFirstclass() {
		return firstclass;
	}
	public void setFirstclass(FirstClass firstclass) {
		this.firstclass = firstclass;
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
		return "Batting [lista=" + lista + ", firstclass=" + firstclass + ", odis=" + odis + ", testms=" + testms
				+ ", t20s=" + t20s + "]";
	}
	


}
