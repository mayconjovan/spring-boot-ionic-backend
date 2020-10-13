package com.maycon.coursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.maycon.coursomc.domain.BilletPayment;

@Service
public class BilletService {
	
	public void fillBilletPayment(BilletPayment pgto, Date instantOfOrder) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instantOfOrder);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pgto.setDataVenc(cal.getTime());
	}
}
