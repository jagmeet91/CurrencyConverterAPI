package com.currency.converter.convert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



@Entity
@Table(name="Currency_Conversion")
public class CurrencyConversion {

	public CurrencyConversion( String currencyName, String rupee, String us, String euro,
			String uK, String aus, String japanese, String singapore, String renminbi, String taiwan) {
		super();
		
		CurrencyName = currencyName;
		Rupee = rupee;
		Us = us;
		Euro = euro;
		UK = uK;
		Aus = aus;
		Japanese = japanese;
		Singapore = singapore;
		Renminbi = renminbi;
		Taiwan = taiwan;
	}

	public CurrencyConversion() {
		// TODO Auto-generated constructor stub
	}
	@NotNull
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="currencyId")
	int currencyId;
	
	@Column(name=("Currency_Name"))
	String CurrencyName ;
	
	@Column(name=("Rupee"))
	String Rupee ;
	
	@Column(name=("Us"))
	String Us ;
	
	@Column(name=("Euro"))
	String Euro ;
	
	@Column(name=("UK"))
	String UK ;
	
	@Column(name=("Aus"))
	String Aus ;
	
	@Column(name=("Japanese"))
	String Japanese ;
	
	@Column(name=("Singapore"))
	String Singapore ;
	
	@Column(name=("Renminbi"))
	String Renminbi ;
	
	@Column(name=("Taiwan"))
	String Taiwan ;

	public int getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}

	public String getCurrencyName() {
		return CurrencyName;
	}

	public void setCurrencyName(String currencyName) {
		CurrencyName = currencyName;
	}

	public String getRupee() {
		return Rupee;
	}

	public void setRupee(String rupee) {
		Rupee = rupee;
	}

	public String getUs() {
		return Us;
	}

	public void setUs(String us) {
		Us = us;
	}

	public String getEuro() {
		return Euro;
	}

	public void setEuro(String euro) {
		Euro = euro;
	}

	public String getUK() {
		return UK;
	}

	public void setUK(String uK) {
		UK = uK;
	}

	public String getAus() {
		return Aus;
	}

	public void setAus(String aus) {
		Aus = aus;
	}

	public String getJapanese() {
		return Japanese;
	}

	public void setJapanese(String japanese) {
		Japanese = japanese;
	}

	public String getSingapore() {
		return Singapore;
	}

	public void setSingapore(String singapore) {
		Singapore = singapore;
	}

	public String getRenminbi() {
		return Renminbi;
	}

	public void setRenminbi(String renminbi) {
		Renminbi = renminbi;
	}

	public String getTaiwan() {
		return Taiwan;
	}

	public void setTaiwan(String taiwan) {
		Taiwan = taiwan;
	}
}
