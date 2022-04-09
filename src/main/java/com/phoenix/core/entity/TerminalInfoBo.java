package com.phoenix.core.entity;

public class TerminalInfoBo {
    private String id;
    private String hosCode;
    private String productId;
    private String pltId;
    private String machineCode;
    private String version;
    private String sversion;
    private String paramContent;
    private String sign;
    private String partnerId;

    public TerminalInfoBo() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHosCode() {
        return this.hosCode;
    }

    public void setHosCode(String hosCode) {
        this.hosCode = hosCode;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPltId() {
        return this.pltId;
    }

    public void setPltId(String pltId) {
        this.pltId = pltId;
    }

    public String getMachineCode() {
        return this.machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSversion() {
        return this.sversion;
    }

    public void setSversion(String sversion) {
        this.sversion = sversion;
    }

    public String getParamContent() {
        return this.paramContent;
    }

    public void setParamContent(String paramContent) {
        this.paramContent = paramContent;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPartnerId() {
        return this.partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }
}

