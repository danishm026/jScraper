package com.arc.jScraper.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arc.jScraper.pages.Page;

@Aspect
public class LoggingAspect {
	private static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	@AfterThrowing(pointcut="execution(public void com.arc.jScraper.pages.Page.retrieveDocument())", throwing="exception")
	public void logDocumentRetrivalFailure(JoinPoint joinPoint,  Exception exception) {
			Page page = (Page) joinPoint.getTarget();
			logger.error("Error retriveing page with URL: {}", page.getUrl());
	}
}
