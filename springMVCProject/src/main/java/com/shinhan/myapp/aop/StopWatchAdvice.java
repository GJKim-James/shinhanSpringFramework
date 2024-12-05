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
	@Pointcut("within(com.shinhan.myapp.controller.EmpController)") // 패키지이름/클래스이름/메서드이름
	public void selectTimer() {
		
	}

	@Around("selectTimer()")
	public Object around(ProceedingJoinPoint jp) throws Throwable {
		// 보조업무(주업무 전)
		System.out.println("********** " + jp.getSignature().getName() + " 메소드 호출 전 **********");
		StopWatch watch = new StopWatch("계산 시간");
		watch.start();
		System.out.println("==================== before ====================");

		Object object = jp.proceed();
		System.out.println("==================== after ====================");

		// 보조업무(주업무 후)
		System.out.println("********** " + jp.getSignature().getName() + " 메소드 호출 후 **********");
		watch.stop();
		System.out.println("주업무 수행 시간 : " + watch.getTotalTimeMillis());
		System.out.println(watch.prettyPrint());

		return object;
	}

}
