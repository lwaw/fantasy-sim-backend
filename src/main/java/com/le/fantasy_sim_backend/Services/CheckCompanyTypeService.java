package com.le.fantasy_sim_backend.Services;

import org.springframework.stereotype.Service;

@Service
public class CheckCompanyTypeService {

	public boolean checkTypes(String type) {
		
		switch(type) {
		case "rawWeapon": return true;
		case "rawFood": return true;
		case "q1Food": return true;
		case "q1Weapon": return true;
		default: return false;
		}
	} 
}
