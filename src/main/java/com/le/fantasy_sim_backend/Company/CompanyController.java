package com.le.fantasy_sim_backend.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.le.fantasy_sim_backend.Currency.ICurrencyRepository;
import com.le.fantasy_sim_backend.Services.CheckCompanyTypeService;
import com.le.fantasy_sim_backend.Services.SubtractCurrencyService;
import com.le.fantasy_sim_backend.Services.UserIsAuthorizedChecksService;
import com.le.fantasy_sim_backend.UserCharacter.IUserCharacterRepository;
import com.le.fantasy_sim_backend.UserCharacter.UserCharacter;

@RestController
@CrossOrigin(maxAge = 3600)
public class CompanyController {
	
	@Autowired
	private ICurrencyRepository currencyRepo;
	
	@Autowired
	ICompanyRepository companyRepo;
	
	@Autowired
	IUserCharacterRepository userCharacterRepo;
	
	@Autowired
	private UserIsAuthorizedChecksService userIsAuthorizedChecksService;
	
	@Autowired
	private SubtractCurrencyService subtractCurrencyService;
	
	@Autowired
	private CheckCompanyTypeService checkCompanyTypeService;
	
	@PostMapping(value = "Company/create")
	public CreateCompanyResponseDTO createCompany(@RequestHeader("Authentication") String token, CreateCompanyRequestDTO dto) {
		
		if(!userIsAuthorizedChecksService.userIsAuthorizedChecks(dto.getUserCharacterId(), token)) {
			return new CreateCompanyResponseDTO(false, "login error");
		}
		
		if(!checkCompanyTypeService.checkTypes(dto.getType())) {
			return new CreateCompanyResponseDTO(false, "Illegal company type");
		}
		
		if(subtractCurrencyService.subtractCurrency(currencyRepo.findByName("gold").get().getId(), dto.getUserCharacterId(), 10.00)) {
			Company company = new Company();
			companyRepo.save(company);
			
			UserCharacter userCharacter = userCharacterRepo.findById(dto.getUserCharacterId()).get();
			
			company.setName(dto.getName());
			company.setOwner(userCharacter);
			company.setType(dto.getType());
			company.setLocationNation(userCharacter.getLocationNation());
			company.setLocationRegion(userCharacter.getLocationRegion());
			companyRepo.save(company);
			
			return new CreateCompanyResponseDTO(true, "");
		}else {
			return new CreateCompanyResponseDTO(false, "Not enough currency in inventory");
		}
		
		
	}
}
