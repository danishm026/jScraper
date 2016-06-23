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
	
	@AfterThrowing(pointcut="getCategoryLink() || getModelBasePage() || allPublicMethodsOfModelPageParser() || allPublicMethodsOfImagePageParser()",
					throwing="exception")
	public void logRetrievalFailure(JoinPoint joinPoint, Exception exception) {
		Parser parser = (Parser) joinPoint.getTarget();
		logger.error(parser.getClass().getSimpleName() + ": Error Retrieving URL: {}", parser.getUrl());
		logger.error(parser.getClass().getSimpleName() + ": Error: {}", exception.toString());
	}
	
	@Pointcut("execution(public String com.arc.jScraper.parsers.HomePageParser.getCategoryLink(char))")
	public void getCategoryLink() {}
	
	@Pointcut("execution(public String com.arc.jScraper.parsers.CategoryPageParser.getModelBasePage(String))")
	public void getModelBasePage() {}
	
	@Pointcut("execution(public * com.arc.jScraper.parsers.ModelPageParser.*())")
	public void allPublicMethodsOfModelPageParser() {}
	
	@Pointcut("execution(public * com.arc.jScraper.parsers.ImagePageParser.*())")
	public void allPublicMethodsOfImagePageParser(){}
}
