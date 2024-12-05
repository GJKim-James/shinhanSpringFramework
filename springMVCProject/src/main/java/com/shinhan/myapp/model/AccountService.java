package com.shinhan.myapp.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED) // transaction 전파 규칙 설정
public class AccountService {
	
	@Autowired
	AccountDAOMybatis accountDAOMybatis;
	
	// 이체 서비스
	public void transferService() {
		int res1 = accountDAOMybatis.deposit();
		int res2 = accountDAOMybatis.withdraw();
		log.debug("[DEBUG] transferService 결과 : deposit()=" + res1 + ", withdraw()=" + res2);
		log.info("[INFO] transferService 결과 : deposit()=" + res1 + ", withdraw()=" + res2);
		log.warn("[WARN] transferService 결과 : deposit()=" + res1 + ", withdraw()=" + res2);
		log.error("[ERROR] transferService 결과 : deposit()=" + res1 + ", withdraw()=" + res2);
	}

}
