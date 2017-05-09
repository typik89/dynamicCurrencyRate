import grails.plugins.metadata.GrailsPlugin
import org.grails.gsp.compiler.transform.LineNumber
import org.grails.gsp.GroovyPage
import org.grails.web.taglib.*
import org.grails.taglib.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_dynamicCurrencyRate_curRatecurRate_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/curRate/curRate.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('stylesheet','asset',4,['src':("bootstrap.css")],-1)
printHtmlPart(1)
invokeTag('javascript','asset',5,['src':("bootstrap.js")],-1)
printHtmlPart(1)
invokeTag('javascript','asset',6,['src':("jquery-2.2.0.min.js")],-1)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',12,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
for( rate in (rates) ) {
printHtmlPart(5)
expressionOut.print(rate.date)
printHtmlPart(6)
expressionOut.print(rate.usdRate == maxUsdRate ? 'red' : ( rate.usdRate == minUsdRate ? 'blue' : 'none' ))
printHtmlPart(7)
expressionOut.print(rate.usdRate?.rate)
printHtmlPart(6)
expressionOut.print(rate.eurRate == maxEurRate ? 'red' : ( rate.eurRate == minEurRate ? 'blue' : 'none' ))
printHtmlPart(7)
expressionOut.print(rate.eurRate?.rate)
printHtmlPart(8)
}
printHtmlPart(9)
createTagBody(2, {->
printHtmlPart(10)
expressionOut.print(usdSeries)
printHtmlPart(11)
expressionOut.print(eurSeries)
printHtmlPart(12)
})
invokeTag('javascript','g',87,[:],2)
printHtmlPart(13)
})
invokeTag('captureBody','sitemesh',92,[:],1)
printHtmlPart(14)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1494286252493L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
