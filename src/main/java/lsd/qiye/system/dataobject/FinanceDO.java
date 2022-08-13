package lsd.qiye.system.dataobject;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class FinanceDO {
    private String gmtCreated;
    private String id;
    private double companyDep;
    private double onlinePay;
    private double manualDep;
    private double arppu;
    private double manualDed;
    private double fundFlow;
    private double withdrawals;
    private double income;
    private double netProfit;
    private String fillPerson;

    public String getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(String gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getCompanyDep() {
        return companyDep;
    }

    public void setCompanyDep(double companyDep) {
        this.companyDep = companyDep;
    }

    public double getOnlinePay() {
        return onlinePay;
    }

    public void setOnlinePay(double onlinePay) {
        this.onlinePay = onlinePay;
    }

    public double getManualDep() {
        return manualDep;
    }

    public void setManualDep(double manualDep) {
        this.manualDep = manualDep;
    }

    public double getArppu() {
        return arppu;
    }

    public void setArppu(double arppu) {
        this.arppu = arppu;
    }

    public double getManualDed() {
        return manualDed;
    }

    public void setManualDed(double manualDed) {
        this.manualDed = manualDed;
    }

    public double getFundFlow() {
        return fundFlow;
    }

    public void setFundFlow(double fundFlow) {
        this.fundFlow = fundFlow;
    }

    public double getWithdrawals() {
        return withdrawals;
    }

    public void setWithdrawals(double withdrawals) {
        this.withdrawals = withdrawals;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(double netProfit) {
        this.netProfit = netProfit;
    }

    public String getFillPerson() {
        return fillPerson;
    }

    public void setFillPerson(String fillPerson) {
        this.fillPerson = fillPerson;
    }
}
