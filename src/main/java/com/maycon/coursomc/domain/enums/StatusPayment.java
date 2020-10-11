package com.maycon.coursomc.domain.enums;

public enum StatusPayment {
	
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	
	private int cod;
	private String desc;
	
	private StatusPayment(int cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public static StatusPayment toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for (StatusPayment x : StatusPayment.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Invalid id: " + cod);
	}

}
