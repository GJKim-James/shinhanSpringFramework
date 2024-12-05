package com.shinhan.myapp.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect // @Pointcut + Advice
public class StopWatchAdvice {
	
//	@Pointcut("execution(* select*(..))")
	@Pointcut("within(com.shinhan.myapp.controller.EmpController)") // ��Ű���̸�/Ŭ�����̸�/�޼����̸�
	public void selectTimer() {
		
	}

	@Around("selectTimer()")
	public Object around(ProceedingJoinPoint jp) throws Throwable {
		// ��������(�־��� ��)
		System.out.println("********** " + jp.getSignature().getName() + " �޼ҵ� ȣ�� �� **********");
		StopWatch watch = new StopWatch("��� �ð�");
		watch.start();
		System.out.println("==================== before ====================");

		Object object = jp.proceed();
		System.out.println("==================== after ====================");

		// ��������(�־��� ��)
		System.out.println("********** " + jp.getSignature().getName() + " �޼ҵ� ȣ�� �� **********");
		watch.stop();
		System.out.println("�־��� ���� �ð� : " + watch.getTotalTimeMillis());
		System.out.println(watch.prettyPrint());

		return object;
	}

}
