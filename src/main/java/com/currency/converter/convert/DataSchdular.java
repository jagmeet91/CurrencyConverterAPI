package com.currency.converter.convert;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DataSchdular {

	public DataSchdular() {
		// TODO Auto-generated constructor stub
	}
	@Autowired
	CurrencyConversionDAO curr;

@Scheduled(fixedRate=3600000)
void ScheduledData() throws IOException {
	
	Document doc = Jsoup
            .connect("https://www.moneycontrol.com/mccode/currencies")
            .get();

	curr.deleteAll();
ArrayList<ArrayList<String>> dataForDB = new ArrayList<ArrayList<String>>();

for (Element div : doc.getElementsByClass("curdata")) {
for (Element trElement : div.getElementsByTag("tr")) {
// skip header "tr"s and process only data "tr"s
//  if (trElement.hasClass("tab-data1")) {

//       StringJoiner tdj = new StringJoiner(",");
//       
//       for (Element tdElement : trElement.getElementsByTag("td")) {
//           tdj.add(tdElement.text());
//       }
// System.out.println(tdj);

	 ArrayList<String> data =new ArrayList<String>();
	 for(Element td:trElement.getElementsByTag("td")) {
		 data.add(td.text());
		 
	 }
	 if(data.size()!=0)
	 	curr.save(new CurrencyConversion(data.get(0),data.get(1),data.get(2),data.get(3),data.get(4),data.get(5),data.get(6),data.get(7),data.get(8),data.get(9)));
   }
	
}
	System.out.println("success");

}
}
