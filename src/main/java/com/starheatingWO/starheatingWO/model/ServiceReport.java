package com.starheatingWO.starheatingWO.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class ServiceReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobSiteName;
    private String billingName;
    private String jobSiteAddress;
    private String billingAddress;
    private Date date;
    private String customerPO;
    private String equipmentMake;
    private String model;
    private String serialNumber;
    private BigDecimal gasPressureInlet;
    private BigDecimal gasPressureWC;
    private BigDecimal gasPressureManifold;
    private Boolean coTest;
    private Integer coReadings;
    private BigDecimal totalMaterial;
    private BigDecimal totalLabour;
    private BigDecimal travelCharges;
    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal total;
    private String recommendations;
    private String email;
    private boolean coTestCompleted;
    private String reasonForCall;
    private boolean heatExchangerWashed;
    private boolean heatExchangerCleaned;
    private boolean heatExchangerInspected;
    private boolean refractoryTested;
    private boolean refractorySet;
    private boolean operatorTested;
    private String operatorSet;
    private boolean highLimitTested;
    private String highLimitSet;
    private boolean waterPressureTested;
    private String waterPressureSet;
    private boolean flowSwitchTested;
    private boolean pressureSwitchTested;

    private int quantity;
    private String material;
    private BigDecimal unitPrice;
    private boolean exported = false; //

    // Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobSiteName() {
        return jobSiteName;
    }

    public void setJobSiteName(String jobSiteName) {
        this.jobSiteName = jobSiteName;
    }

    public String getBillingName() {
        return billingName;
    }

    public void setBillingName(String billingName) {
        this.billingName = billingName;
    }

    public String getJobSiteAddress() {
        return jobSiteAddress;
    }

    public void setJobSiteAddress(String jobSiteAddress) {
        this.jobSiteAddress = jobSiteAddress;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustomerPO() {
        return customerPO;
    }

    public void setCustomerPO(String customerPO) {
        this.customerPO = customerPO;
    }

    public String getEquipmentMake() {
        return equipmentMake;
    }

    public void setEquipmentMake(String equipmentMake) {
        this.equipmentMake = equipmentMake;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public BigDecimal getGasPressureInlet() {
        return gasPressureInlet;
    }

    public void setGasPressureInlet(BigDecimal gasPressureInlet) {
        this.gasPressureInlet = gasPressureInlet;
    }

    public BigDecimal getGasPressureWC() {
        return gasPressureWC;
    }

    public void setGasPressureWC(BigDecimal gasPressureWC) {
        this.gasPressureWC = gasPressureWC;
    }

    public BigDecimal getGasPressureManifold() {
        return gasPressureManifold;
    }

    public void setGasPressureManifold(BigDecimal gasPressureManifold) {
        this.gasPressureManifold = gasPressureManifold;
    }

    public Boolean getCoTest() {
        return coTest;
    }

    public void setCoTest(Boolean coTest) {
        this.coTest = coTest;
    }

    public Integer getCoReadings() {
        return coReadings;
    }

    public void setCoReadings(Integer coReadings) {
        this.coReadings = coReadings;
    }

    public BigDecimal getTotalMaterial() {
        return totalMaterial;
    }

    public void setTotalMaterial(BigDecimal totalMaterial) {
        this.totalMaterial = totalMaterial;
    }

    public BigDecimal getTotalLabour() {
        return totalLabour;
    }

    public void setTotalLabour(BigDecimal totalLabour) {
        this.totalLabour = totalLabour;
    }

    public BigDecimal getTravelCharges() {
        return travelCharges;
    }

    public void setTravelCharges(BigDecimal travelCharges) {
        this.travelCharges = travelCharges;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isCoTestCompleted() {
        return coTestCompleted;
    }

    public void setCoTestCompleted(boolean coTestCompleted) {
        this.coTestCompleted = coTestCompleted;
    }

    public String getReasonForCall() {
        return reasonForCall;
    }

    public void setReasonForCall(String reasonForCall) {
        this.reasonForCall = reasonForCall;
    }

    public boolean isHeatExchangerWashed() {
        return heatExchangerWashed;
    }

    public void setHeatExchangerWashed(boolean heatExchangerWashed) {
        this.heatExchangerWashed = heatExchangerWashed;
    }

    public boolean isHeatExchangerCleaned() {
        return heatExchangerCleaned;
    }

    public void setHeatExchangerCleaned(boolean heatExchangerCleaned) {
        this.heatExchangerCleaned = heatExchangerCleaned;
    }

    public boolean isHeatExchangerInspected() {
        return heatExchangerInspected;
    }

    public void setHeatExchangerInspected(boolean heatExchangerInspected) {
        this.heatExchangerInspected = heatExchangerInspected;
    }

    public boolean isRefractoryTested() {
        return refractoryTested;
    }

    public void setRefractoryTested(boolean refractoryTested) {
        this.refractoryTested = refractoryTested;
    }

    public boolean isRefractorySet() {
        return refractorySet;
    }

    public void setRefractorySet(boolean refractorySet) {
        this.refractorySet = refractorySet;
    }

    public boolean isOperatorTested() {
        return operatorTested;
    }

    public void setOperatorTested(boolean operatorTested) {
        this.operatorTested = operatorTested;
    }

    public String isOperatorSet() {
        return operatorSet;
    }

    public void setOperatorSet(String operatorSet) {
        this.operatorSet = operatorSet;
    }

    public boolean isHighLimitTested() {
        return highLimitTested;
    }

    public void setHighLimitTested(boolean highLimitTested) {
        this.highLimitTested = highLimitTested;
    }

    public boolean isWaterPressureTested() {
        return waterPressureTested;
    }

    public void setWaterPressureTested(boolean waterPressureTested) {
        this.waterPressureTested = waterPressureTested;
    }

    public boolean isFlowSwitchTested() {
        return flowSwitchTested;
    }

    public void setFlowSwitchTested(boolean flowSwitchTested) {
        this.flowSwitchTested = flowSwitchTested;
    }

    public boolean isPressureSwitchTested() {
        return pressureSwitchTested;
    }

    public void setPressureSwitchTested(boolean pressureSwitchTested) {
        this.pressureSwitchTested = pressureSwitchTested;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String isHighLimitSet() {
        return highLimitSet;
    }

    public void setHighLimitSet(String highLimitSet) {
        this.highLimitSet = highLimitSet;
    }

    public String isWaterPressureSet() {
        return waterPressureSet;
    }

    public void setWaterPressureSet(String waterPressureSet) {
        this.waterPressureSet = waterPressureSet;
    }

    public boolean isExported() {
        return exported;
    }

    public void setExported(boolean exported) {
        this.exported = exported;
    }
}
