package com.currency.converter.convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.Query;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Persistent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@RestController
@RequestMapping("/")
public class CurrencyController {

	@PersistenceContext
	EntityManager manager;
	@Autowired
	CurrencyConversionDAO curr;

	public CurrencyController() {
		// TODO Auto-generated constructor stub
	}


//Mannually hitting server for scrapping
@GetMapping("/scrapping")
ResponseEntity<Response> getscrap() throws FailingHttpStatusCodeException, MalformedURLException, IOException{

//moneycontrol website we are using for scrapping
Document doc = Jsoup
              .connect("https://www.moneycontrol.com/mccode/currencies")
              .get();

curr.deleteAll();

for (Element div : doc.getElementsByClass("curdata")) {
 for (Element trElement : div.getElementsByTag("tr")) {

	 ArrayList<String> data =new ArrayList<String>();
	 for(Element td:trElement.getElementsByTag("td")) {
		 data.add(td.text());
		 
	 }
	 if(data.size()!=0)
		 //dataForDB.add(data);
		
	 	curr.save(new CurrencyConversion(data.get(0),data.get(1),data.get(2),data.get(3),data.get(4),data.get(5),data.get(6),data.get(7),data.get(8),data.get(9)));
     }
 	
}

		System.out.println("success");
	Response res = new Response("Success","1");
	return new ResponseEntity(res,HttpStatus.OK);
	
}

Map <String,String> CurrMap = new HashMap<String,String>();
Map <String,String> CurrMap1 = new HashMap<String,String>();

//Main API for conversion of currency after fetching details from database
@GetMapping("/getcur")
ResponseEntity<Response> getCurrency(@RequestParam(name="fromCurrency")String fromCurr, @RequestParam(name="toCurrency") String toCurr, @RequestParam("amount") double amount ){
	
	CurrMap.put("INR","1 Rupee =");
	CurrMap.put("USD","1 US $ =");
	CurrMap.put("EUR","1 Euro € =");
	CurrMap.put("GBP","1 UK £ =");
	CurrMap.put("AUD","1 Aus $ =");
	CurrMap.put("JPY","1 Japanese ¥ =");
	CurrMap.put("SGD","1 Singapore $=");
	CurrMap.put("CNY","1 Renminbi =");
	CurrMap.put("TWD","1 Taiwan $ =");
	
	CurrMap1.put("INR","Rupee");
	CurrMap1.put("USD","Us");
	CurrMap1.put("EUR","Euro");
	CurrMap1.put("GBP","UK");
	CurrMap1.put("AUD","Aus");
	CurrMap1.put("JPY","Japanese");
	CurrMap1.put("SGD","Singapore");
	CurrMap1.put("CNY","Renminbi");
	CurrMap1.put("TWD","Taiwan");

	
	javax.persistence.Query a =  manager.createQuery("Select c."+CurrMap1.get(toCurr)+ " from CurrencyConversion c where c.CurrencyName=?1");
	
	 a.setParameter(1,CurrMap.get(fromCurr));
	 String value = a.getSingleResult().toString();
	 
	BigDecimal value_in_one =new BigDecimal(value);
	BigDecimal result = new BigDecimal(amount);
	result=result.multiply(value_in_one);
	
	return new ResponseEntity(new Response("Success",result+""),HttpStatus.OK);
}

	
}
