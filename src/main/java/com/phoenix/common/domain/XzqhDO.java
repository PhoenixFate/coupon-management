package com.phoenix.common.domain;

import java.io.Serializable;

public class XzqhDO implements Serializable {
    private String qhdm;

    private String mc;

    private String pyjm;

    private String cj;

    private static final long serialVersionUID = 1L;

    public String getQhdm() {
        return qhdm;
    }

    public void setQhdm(String qhdm) {
        this.qhdm = qhdm == null ? null : qhdm.trim();
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc == null ? null : mc.trim();
    }

    public String getPyjm() {
        return pyjm;
    }

    public void setPyjm(String pyjm) {
        this.pyjm = pyjm == null ? null : pyjm.trim();
    }

    public String getCj() {
        return cj;
    }

    public void setCj(String cj) {
        this.cj = cj == null ? null : cj.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        XzqhDO other = (XzqhDO) that;
        return (this.getQhdm() == null ? other.getQhdm() == null : this.getQhdm().equals(other.getQhdm()))
            && (this.getMc() == null ? other.getMc() == null : this.getMc().equals(other.getMc()))
            && (this.getPyjm() == null ? other.getPyjm() == null : this.getPyjm().equals(other.getPyjm()))
            && (this.getCj() == null ? other.getCj() == null : this.getCj().equals(other.getCj()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getQhdm() == null) ? 0 : getQhdm().hashCode());
        result = prime * result + ((getMc() == null) ? 0 : getMc().hashCode());
        result = prime * result + ((getPyjm() == null) ? 0 : getPyjm().hashCode());
        result = prime * result + ((getCj() == null) ? 0 : getCj().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", qhdm=").append(qhdm);
        sb.append(", mc=").append(mc);
        sb.append(", pyjm=").append(pyjm);
        sb.append(", cj=").append(cj);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}