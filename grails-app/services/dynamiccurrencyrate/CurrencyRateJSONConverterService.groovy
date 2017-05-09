package dynamiccurrencyrate

import grails.transaction.Transactional

@Transactional
class CurrencyRateJSONConverterService {

	def convertRateToJson( CurRate rate ){ 
		return '[Date.UTC('+ 
					rate.date.getYear()        + ',' + 
					( rate.date.getMonthOfYear() - 1 )  + ',' + 
					rate.date.getDayOfMonth()   + ') , ' + rate.rate + ' ]'
	}
	
	def convertRatesToJson( Collection rates ){ 
		StringBuilder sb = new StringBuilder();
		sb.append( '[' )
		boolean b = false
		for( CurRate rate : rates ){ 
			if ( !b ){ 
				b = true;
			}else{ 
				sb.append(',')
			}
			sb.append( convertRateToJson( rate ) )
		}
		sb.append(']')
		return sb.toString().encodeAsJSON()
	}
}
