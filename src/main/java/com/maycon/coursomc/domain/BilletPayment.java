package com.maycon.coursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.maycon.coursomc.domain.enums.StatusPayment;

@Entity
public class BilletPayment extends Payment {
	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataVenc;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataPagto;

	public BilletPayment() {

	}

	public BilletPayment(Integer id, StatusPayment status, Order order, Date dataVenc, Date dataPagto) {
		super(id, status, order);
		this.dataPagto = dataPagto;
		this.dataVenc = dataVenc;
	}

	public Date getDataVenc() {
		return dataVenc;
	}

	public void setDataVenc(Date dataVenc) {
		this.dataVenc = dataVenc;
	}

	public Date getDataPagto() {
		return dataPagto;
	}

	public void setDataPagto(Date dataPagto) {
		this.dataPagto = dataPagto;
	}

}
