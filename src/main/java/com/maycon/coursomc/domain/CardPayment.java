package com.maycon.coursomc.domain;

import javax.persistence.Entity;

import com.maycon.coursomc.domain.enums.StatusPayment;

@Entity
public class CardPayment extends Payment{
	private static final long serialVersionUID = 1L;
	
	private Integer nrParcelas;

	
	public CardPayment() {
		
	}


	public CardPayment(Integer id, StatusPayment status, Order order, Integer nrParcelas) {
		super(id, status, order);
		this.nrParcelas = nrParcelas;

	}


	public Integer getNrParcelas() {
		return nrParcelas;
	}


	public void setNrParcelas(Integer nrParcelas) {
		this.nrParcelas = nrParcelas;
	}
	
	
}
