package dynamiccurrencyrate

import org.joda.time.LocalDate;


import org.jsoup.nodes.*;
import org.jsoup.*;
import org.jsoup.select.*;


import java.text.*;

class CurrencyRateLoaderService {
	
	def loadCurRateFromInternet( String currencyCode , LocalDate requestedDate ){ 
		String url = "https://www.cbr.ru/currency_base/daily.aspx?date_req=" + requestedDate.toString('dd.MM.yyyy')
		Document doc = Jsoup.connect( url ).get()
		Elements trs = doc.select("table[class=data]").first().select("tr");
		def htmlText = null
		for( Element tr : trs ){ 
			Elements tds = tr.select("td")
			if ( tds.size() == 0 ){ 
				continue;
			}
			if ( tds.get(1).text() == currencyCode ){ 	
				DecimalFormat df = new DecimalFormat("#.#")
				DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance()
				dfs.setDecimalSeparator(',' as char)
				df.setDecimalFormatSymbols(dfs)	
				double loadedRate = df.parse(tds.last().text());
				return new CurRate( currencyCode:currencyCode , date:requestedDate, rate:loadedRate );
			}
		}
		return null;
	}

    synchronized def loadRate( String currencyCode , LocalDate date ) {
    	CurRate dbo = CurRate.findByCurrencyCodeAndDate( currencyCode , date )
		if ( dbo != null ){ 
			return dbo
		}else{
			CurRate curRate = loadCurRateFromInternet( currencyCode , date )
			if ( curRate != null ){ 
				curRate.save()
			}
			return curRate
		}
    }
}
