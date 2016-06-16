package com.arc.jScraper.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.arc.jScraper.parsers.Parser;

@Aspect
public class LoggingAspect {
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	@AfterThrowing(pointcut="execution(public String com.arc.jScraper.parsers.HomePageParser.getCategoryLink(char))")
	public void logRetrievalFailure(JoinPoint joinPoint) {
		Parser parser = (Parser) joinPoint.getTarget();
		logger.error("Error Retrieving URL: {}", parser.getUrl());
	}
}
