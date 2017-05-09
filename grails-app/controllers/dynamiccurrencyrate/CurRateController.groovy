package dynamiccurrencyrate

import org.joda.time.LocalDate;

class CurRateController {

	def currencyRateLoaderService;
	def currencyRateJSONConverterService;
	

	static class Row{ 
		LocalDate date;
		CurRate usdRate;
		CurRate eurRate;
	}
	
    def index() {
    	def rates = new ArrayList();
    	def usds = new ArrayList();
    	def eurs = new ArrayList();
    	
    	CurRate maxUsdRate = null;
    	CurRate minUsdRate = null;
    	CurRate maxEurRate = null;
    	CurRate minEurRate = null;
		for( int i = 30; i >= 0; --i ){
			def date = LocalDate.now().minusDays(i)
			def uRate = currencyRateLoaderService.loadRate('USD',date)
			if ( uRate != null ){
				maxUsdRate = maxUsdRate == null || maxUsdRate.rate < uRate.rate ? uRate : maxUsdRate 
				minUsdRate = minUsdRate == null || minUsdRate.rate > uRate.rate ? uRate : minUsdRate 
				usds.add( uRate )
			}
			def eRate = currencyRateLoaderService.loadRate('EUR',date)
			if ( eRate != null ){ 
				maxEurRate = maxEurRate == null || maxEurRate.rate < eRate.rate ? eRate : maxEurRate 
				minEurRate = minEurRate == null || minEurRate.rate > eRate.rate ? eRate : minEurRate 
				eurs.add( eRate )
			}
			rates.add( new Row( date: date, usdRate: uRate, eurRate: eRate) )
		} 	
   		def eurSeries = currencyRateJSONConverterService.convertRatesToJson(eurs)
    	def usdSeries = currencyRateJSONConverterService.convertRatesToJson(usds)	
    	
    	render( view: "curRate", 
    		model: [
    			rates: rates, 
    			maxUsdRate:maxUsdRate , minUsdRate:minUsdRate , 
    			maxEurRate:maxEurRate , minEurRate:minEurRate , 
    			eurSeries: eurSeries, usdSeries: usdSeries] )
    }
}
