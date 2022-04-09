package com.phoenix.system.domain;

import java.io.Serializable;

public class ReserveChannelStatisticDO implements Serializable {
    private String id;

    private String channelCode;

    private String channelName;

    private String orgCode;

    private Integer reserveCount;

    private Integer refundCount;

    private Integer violateCount;

    private String statisticDate;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode == null ? null : channelCode.trim();
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public Integer getReserveCount() {
        return reserveCount;
    }

    public void setReserveCount(Integer reserveCount) {
        this.reserveCount = reserveCount;
    }

    public Integer getRefundCount() {
        return refundCount;
    }

    public void setRefundCount(Integer refundCount) {
        this.refundCount = refundCount;
    }

    public Integer getViolateCount() {
        return violateCount;
    }

    public void setViolateCount(Integer violateCount) {
        this.violateCount = violateCount;
    }

    public String getStatisticDate() {
        return statisticDate;
    }

    public void setStatisticDate(String statisticDate) {
        this.statisticDate = statisticDate == null ? null : statisticDate.trim();
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
        ReserveChannelStatisticDO other = (ReserveChannelStatisticDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getChannelCode() == null ? other.getChannelCode() == null : this.getChannelCode().equals(other.getChannelCode()))
            && (this.getChannelName() == null ? other.getChannelName() == null : this.getChannelName().equals(other.getChannelName()))
            && (this.getOrgCode() == null ? other.getOrgCode() == null : this.getOrgCode().equals(other.getOrgCode()))
            && (this.getReserveCount() == null ? other.getReserveCount() == null : this.getReserveCount().equals(other.getReserveCount()))
            && (this.getRefundCount() == null ? other.getRefundCount() == null : this.getRefundCount().equals(other.getRefundCount()))
            && (this.getViolateCount() == null ? other.getViolateCount() == null : this.getViolateCount().equals(other.getViolateCount()))
            && (this.getStatisticDate() == null ? other.getStatisticDate() == null : this.getStatisticDate().equals(other.getStatisticDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getChannelCode() == null) ? 0 : getChannelCode().hashCode());
        result = prime * result + ((getChannelName() == null) ? 0 : getChannelName().hashCode());
        result = prime * result + ((getOrgCode() == null) ? 0 : getOrgCode().hashCode());
        result = prime * result + ((getReserveCount() == null) ? 0 : getReserveCount().hashCode());
        result = prime * result + ((getRefundCount() == null) ? 0 : getRefundCount().hashCode());
        result = prime * result + ((getViolateCount() == null) ? 0 : getViolateCount().hashCode());
        result = prime * result + ((getStatisticDate() == null) ? 0 : getStatisticDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", channelCode=").append(channelCode);
        sb.append(", channelName=").append(channelName);
        sb.append(", orgCode=").append(orgCode);
        sb.append(", reserveCount=").append(reserveCount);
        sb.append(", refundCount=").append(refundCount);
        sb.append(", violateCount=").append(violateCount);
        sb.append(", statisticDate=").append(statisticDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}