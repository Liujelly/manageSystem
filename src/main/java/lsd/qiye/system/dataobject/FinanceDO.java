package lsd.qiye.system.dataobject;

import java.time.LocalDateTime;

public class FinanceDO {
    private LocalDateTime gmtCreated;
    private String id;
    private long companyDep;
    private long onlinePay;
    private long manualDep;
    private long arppu;
    private long manualDed;
    private long withdrawals;
    private long fundFlow;
    private long income;
    private long netProfit;
    private String fillPerson;

    public LocalDateTime getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(LocalDateTime gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCompanyDep() {
        return companyDep;
    }

    public void setCompanyDep(long companyDep) {
        this.companyDep = companyDep;
    }

    public long getOnlinePay() {
        return onlinePay;
    }

    public void setOnlinePay(long onlinePay) {
        this.onlinePay = onlinePay;
    }

    public long getManualDep() {
        return manualDep;
    }

    public void setManualDep(long manualDep) {
        this.manualDep = manualDep;
    }

    public long getArppu() {
        return arppu;
    }

    public void setArppu(long arppu) {
        this.arppu = arppu;
    }

    public long getManualDed() {
        return manualDed;
    }

    public void setManualDed(long manualDed) {
        this.manualDed = manualDed;
    }

    public long getWithdrawals() {
        return withdrawals;
    }

    public void setWithdrawals(long withdrawals) {
        this.withdrawals = withdrawals;
    }

    public long getFundFlow() {
        return fundFlow;
    }

    public void setFundFlow(long fundFlow) {
        this.fundFlow = fundFlow;
    }

    public long getIncome() {
        return income;
    }

    public void setIncome(long income) {
        this.income = income;
    }

    public long getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(long netProfit) {
        this.netProfit = netProfit;
    }

    public String getFillPerson() {
        return fillPerson;
    }

    public void setFillPerson(String fillPerson) {
        this.fillPerson = fillPerson;
    }
}
