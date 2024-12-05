package com.shinhan.myapp.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED) // transaction ���� ��Ģ ����
public class AccountService {
	
	@Autowired
	AccountDAOMybatis accountDAOMybatis;
	
	// ��ü ����
	public void transferService() {
		int res1 = accountDAOMybatis.deposit();
		int res2 = accountDAOMybatis.withdraw();
		log.debug("[DEBUG] transferService ��� : deposit()=" + res1 + ", withdraw()=" + res2);
		log.info("[INFO] transferService ��� : deposit()=" + res1 + ", withdraw()=" + res2);
		log.warn("[WARN] transferService ��� : deposit()=" + res1 + ", withdraw()=" + res2);
		log.error("[ERROR] transferService ��� : deposit()=" + res1 + ", withdraw()=" + res2);
	}

}
