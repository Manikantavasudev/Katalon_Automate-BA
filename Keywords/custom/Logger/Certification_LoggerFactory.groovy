package custom.Logger

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import org.slf4j.LoggerFactory;
import org.slf4j.ILoggerFactory
//import org.slf4j.Logger

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.FileAppender

import internal.GlobalVariable

public class Certification_LoggerFactory {
	private static Logger logger = LoggerFactory.getLogger(CustomLoggerFactory.class);

	public static final CLASSNAME_COMMENT_KEYWORD = 'com.kms.katalon.core.keyword.builtin.CommentKeyword'

	/**
	 * @return Logger as defined by the following logback config
	 * <PRE>
	 * <configuration>
	 *   <appender name="STDOUT-msgOnly" class="ch.qos.logback.core.ConsoleAppender">
	 *     <encoder>
	 *       <pattern>%msg%n</pattern>
	 *     </encoder>
	 *   </appender>
	 *   <appender name="FILE" class="ch.qos.logback.core.FileAppender">
	 *     <file>testFile.log</file>
	 *     <append>true</append>
	 *     <!-- set immediateFlush to false for much higher logging throughput -->
	 *     <immediateFlush>true</immediateFlush>
	 *     <!-- encoders are assigned the type
	 *          ch.qos.logback.classic.encoder.PatternLayoutEncoder by default -->
	 *     <encoder>
	 *       <pattern>%-4relative [%thread] %-5level %logger{35} - %msg%n</pattern>
	 *     </encoder>
	 *   </appender>
	 *   <logger name="com.kms.katalon.core.keyword.builtin.CommentKeyword" level="info" additivity="false">
	 *     <appender-ref ref="STDOUT-msgOnly" />
	 *     <appender-ref ref="FILE" />
	 *   </logger>
	 * </configuration>
	 * </PRE>
	 */
	@Keyword
	public static Logger getLogger4CommentKeyword() {
		LoggerContext logCtx = ((ch.qos.logback.classic.Logger)logger).getLoggerContext()
		//
		PatternLayoutEncoder msgOnlyEncoder = new PatternLayoutEncoder()
		msgOnlyEncoder.setContext(logCtx)
		msgOnlyEncoder.setPattern("%msg%n")
		msgOnlyEncoder.start()
		//
		PatternLayoutEncoder fileEncoder = new PatternLayoutEncoder()
		fileEncoder.setContext(logCtx)
		fileEncoder.setPattern("%-4relative [%thread] %-5level %logger{35} - %msg%n")
		fileEncoder.start()
		//
		ConsoleAppender msgOnlyAppender = new ConsoleAppender()
		msgOnlyAppender.setContext(logCtx)
		msgOnlyAppender.setName("STDOUT-msgOnly")
		msgOnlyAppender.setEncoder(msgOnlyEncoder)
		msgOnlyAppender.start()
		//
		FileAppender fileAppender = new FileAppender()
		fileAppender.setContext(logCtx)
		fileAppender.setName("FILE")
		fileAppender.setFile("certification_file_validator.log")
		fileAppender.setAppend(true)
		fileAppender.setImmediateFlush(true)
		fileAppender.setEncoder(fileEncoder)
		fileAppender.start()
		//
		Logger logger = logCtx.getLogger(CLASSNAME_COMMENT_KEYWORD)
		logger.additive = false
		logger.level = Level.INFO
		logger.addAppender(msgOnlyAppender)
		logger.addAppender(fileAppender)
		//
		return logger
	}

	@Keyword
	public static void customizeLogger4CommentKeyword() {
		LoggerFactory.metaClass.static.getLogger = { String name ->
			if (name =~ /com\.kms\.katalon\.core\.keyword\.builtin\.CommentKeyword/) {
				return getLogger4CommentKeyword()
			} else {
				ILoggerFactory iLoggerFactory = LoggerFactory.getILoggerFactory()
				return iLoggerFactory.getLogger(name);
			}
		}
		org.slf4j.Logger log = LoggerFactory.getLogger("com.kms.katalon.core.keyword.builtin.CommentKeyword")
	}

}
