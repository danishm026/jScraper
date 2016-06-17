package com.arc.jScraper.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arc.jScraper.parsers.Parser;

@Aspect
public class LoggingAspect {
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	@AfterThrowing(pointcut="getCategoryLink() || getModelBasePage()")
	public void logRetrievalFailure(JoinPoint joinPoint) {
		Parser parser = (Parser) joinPoint.getTarget();
		logger.error("Error Retrieving URL: {}", parser.getUrl());
	}
	
	@Pointcut("execution(public String com.arc.jScraper.parsers.HomePageParser.getCategoryLink(char))")
	public void getCategoryLink() {}
	
	@Pointcut("execution(public String com.arc.jScraper.parsers.CategoryPageParser.getModelBasePage(String))")
	public void getModelBasePage() {}
}
