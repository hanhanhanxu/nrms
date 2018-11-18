package cn.itcast.pojo;

import cn.itcast.util.WebUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
//19个属性  去掉id   excel表格中18个可设置属性
public class ElectricityFees {
    private String  eleF_id;
    private String sitenum;
    private String sitename;
    private Float unitprice;//
    private Float balance;
    private Float predeposit;
    private Float meterrise;
    private Float meterstop;
    private Float eleconsumption;
    private Float loss;
    private Float taxation;
    private Float agriculturaleleadd;
    private Float bSARUnicom;
    private Float bSARMobile;
    private Float bSARTelecom;
    private Float totalcost;
    private String meterreading;
    private Float monthfolding;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date importdate;

    public ElectricityFees() {
    }

    public ElectricityFees(String sitenum, String sitename, Float unitprice, Float balance, Float predeposit, Float meterrise, Float meterstop, Float eleconsumption, Float loss, Float taxation, Float agriculturaleleadd, Float bSARUnicom, Float bSARMobile, Float bSARTelecom, Float totalcost, String meterreading, Float monthfolding, Date importdate) {
        this.sitenum = sitenum;
        this.sitename = sitename;
        this.unitprice = unitprice;
        this.balance = balance;
        this.predeposit = predeposit;
        this.meterrise = meterrise;
        this.meterstop = meterstop;
        this.eleconsumption = eleconsumption;
        this.loss = loss;
        this.taxation = taxation;
        this.agriculturaleleadd = agriculturaleleadd;
        this.bSARUnicom = bSARUnicom;
        this.bSARMobile = bSARMobile;
        this.bSARTelecom = bSARTelecom;
        this.totalcost = totalcost;
        this.meterreading = meterreading;
        this.monthfolding = monthfolding;
        this.importdate = importdate;
    }

    public String getEleF_id() {
        return eleF_id;
    }

    public void setEleF_id(String eleF_id) {
        this.eleF_id = eleF_id;
    }

    public String getSitenum() {
        return sitenum;
    }

    public void setSitenum(String sitenum) {
        this.sitenum = sitenum;
    }


    public String getSitename() {
        return sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    public Float getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(Float unitprice) {
        this.unitprice = unitprice;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Float getPredeposit() {
        return predeposit;
    }

    public void setPredeposit(Float predeposit) {
        this.predeposit = predeposit;
    }

    public Float getMeterrise() {
        return meterrise;
    }

    public void setMeterrise(Float meterrise) {
        this.meterrise = meterrise;
    }

    public Float getMeterstop() {
        return meterstop;
    }

    public void setMeterstop(Float meterstop) {
        this.meterstop = meterstop;
    }

    public Float getEleconsumption() {
        return eleconsumption;
    }

    public void setEleconsumption(Float eleconsumption) {
        this.eleconsumption = eleconsumption;
    }

    public Float getLoss() {
        return loss;
    }

    public void setLoss(Float loss) {
        this.loss = loss;
    }

    public Float getTaxation() {
        return taxation;
    }

    public void setTaxation(Float taxation) {
        this.taxation = taxation;
    }

    public Float getAgriculturaleleadd() {
        return agriculturaleleadd;
    }

    public void setAgriculturaleleadd(Float agriculturaleleadd) {
        this.agriculturaleleadd = agriculturaleleadd;
    }

    public Float getbSARUnicom() {
        return bSARUnicom;
    }

    public void setbSARUnicom(Float bSARUnicom) {
        this.bSARUnicom = bSARUnicom;
    }

    public Float getbSARMobile() {
        return bSARMobile;
    }

    public void setbSARMobile(Float bSARMobile) {
        this.bSARMobile = bSARMobile;
    }

    public Float getbSARTelecom() {
        return bSARTelecom;
    }

    public void setbSARTelecom(Float bSARTelecom) {
        this.bSARTelecom = bSARTelecom;
    }

    public Float getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(Float totalcost) {
        this.totalcost = totalcost;
    }

    public String getMeterreading() {
        return meterreading;
    }

    public void setMeterreading(String meterreading) {
        this.meterreading = meterreading;
    }

    public Float getMonthfolding() {
        return monthfolding;
    }

    public void setMonthfolding(Float monthfolding) {
        this.monthfolding = monthfolding;
    }

    public Date getImportdate() {
        return importdate;
    }

    public void setImportdate(Date importdate) {
        this.importdate = importdate;
    }
}
