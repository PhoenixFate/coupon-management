package com.phoenix.core.constant;

public interface HosServiceUrlConst {
    String GET_SCANORDER_URL = "common-interface/scanorder/getScanOrderUrl";

    public interface ACCOUNT {
        String HOS_ACCT_QUERY = "phoenix-front/hismz/getHosAccountInfo";
        String HOS_ACCT_TRANS_QUERY = "phoenix-front/hismz/getHosAccountTransInfo";
        String HOS_ACC_RECHARGE_SUMMARY = "phoenix-front/hismz/hosAccountRechargeSummary";
        String HOS_ACC_RECHARGE_COMFIRM = "phoenix-front/hismz/hosAccountRechargeConfirm";
        String HOS_ACCT_OPEN = "phoenix-front/hismz/openHosAccount";
        String HOS_ACCT_CANCEL_OPEN = "phoenix-front/hismz/cancelHosAccount";
        String HOS_ACCT_CONSUM = "phoenix-front/hismz/hosAccountConsum";
        String HOS_ACCT_CANCEL_CONSUM = "phoenix-front/hismz/hosAccountCancelConsum";
        String Get_HOS_ACCT_BUSINESS_STATUS = "phoenix-front/hismz/getHosAccountBusinessStatus";
        String MODIFY_PWD = "phoenix-front/hismz/hosAccountModifyPwd";
    }

    public interface BILLS {
        String GET_CHECKLEDGER_BANK_INFO = "phoenix-front/checkLedger/getBankTransInfo";
        String GET_CHECKLEDGER_HIS_INFO = "phoenix-front/checkLedger/getHisTransInfo";
        String GET_CHECKLEDGER_YBHIS_INFO = "phoenix-front/checkLedger/getHisYbTransInfo";
        String GET_CHECKLEDGER_MZHOSACC_INFO = "phoenix-front/checkLedger/getMzHosAccTransInfo";
        String GET_CHECKLEDGER_ZYHOSACC_INFO = "phoenix-front/checkLedger/getZyHosAccTransInfo";
        String GET_CHECKLEDGER_YZT_ACCOUNT_CHECK = "phoenix-front/checkLedger/yztAccountCheck";
        String GET_BANKTRANS_INFO = "phoenix-front/checkLedger/getBankTransInfo";
        String DOWNLOAD_BANKTRANSINFO_TOLOCAL = "phoenix-front/checkLedger/downloadBankTransInfoToLocal";
        String GET_HIS_TRANSINFO = "phoenix-front/checkLedger/getHisTransInfo";
        String DOWNLOAD_HIS_TRANSINFO_TOLOCAL = "phoenix-front/checkLedger/downloadHisTransInfoToLocal";
        String GET_ISSUERSCARD_TRANSINFO = "phoenix-front/checkLedger/getIssuersCardTransInfo";
        String DOWNLOAD_ISSUERSCARD_TRANSINFO_TOLOCAL = "phoenix-front/checkLedger/downloadIssuersCardTransInfoToLocal";
        String GET_MZHOSACC_TRANSINFO = "phoenix-front/checkLedger/getMzHosAccTransInfo";
        String DOWNLOAD_MZHOSACC_TRANSINFO_TOLOCAL = "phoenix-front/checkLedger/downloadMzHosAccTransInfoToLocal";
        String GET_ZYHOSACC_TRANSINFO = "phoenix-front/checkLedger/getZyHosAccTransInfo";
        String GET_ZYHOSACCTREALTIME_TRANSINFO = "phoenix-front/checkLedger/getZyHosAcctRealTimeTransInfo";
        String DOWNLOAD_ZYHOSACC_TRANSINFO_TOLOCAL = "phoenix-front/checkLedger/downloadZyHosAccTransInfoToLocal";
        String GET_UNIONPAY_HIS_BILLS = "phoenix-front/checkLedger/getUnionPayHisBills";
    }

    public interface CALLBACKURL {
        String PAYMENT_CALLBACK_URL = "phoenix-hospital/registration/zzPrenosPayment";
        String REGISTER_CALLBACK_URL = "phoenix-hospital/registration/zzRegisterPayment";
        String ONACCOUNT_RECHARG_CALLBACK_URL = "phoenix-hospital/oneAcct/oneAcctRechargeOrderConfirm";
        String CARDISSUERS_CALLBACK_URL = "phoenix-hospital/selfcard/selfCardOrderPayment";
        String MZRECHARGE_CALLBACK_URL = "phoenix-hospital/zzHosAcct/zzHosAcctRechargeConfirm";
        String ZYRECHARGE_CALLBACK_URL = "phoenix-hospital/inhos/zzPaymentHosDeposit";
    }

    public interface CALLBACKURL_NEW {
        String PAYMENT_CALLBACK_URL = "phoenix-hospital/offline/registration/zzPrenosPayment";
        String REGISTER_CALLBACK_URL = "phoenix-hospital/offline/registration/zzRegisterPayment";
        String ONACCOUNT_RECHARG_CALLBACK_URL = "phoenix-hospital/oneAcct/oneAcctRechargeOrderConfirm";
        String CARDISSUERS_CALLBACK_URL = "phoenix-hospital/offline/selfcard/selfCardOrderPayment";
        String MZRECHARGE_CALLBACK_URL = "phoenix-hospital/offline/zzHosAcct/zzHosAcctRechargeConfirm";
        String ZYRECHARGE_CALLBACK_URL = "phoenix-hospital/offline/inhos/zzPaymentHosDeposit";
    }

    public interface INHOS {
        String INHOS_ACC_RECHARGE_SUMMARY = "phoenix-front/hiszy/inHosAcctRechargeSummary";
        String INHOS_ACC_RECHARGE_COMFIRM = "phoenix-front/hiszy/inHosAcctRechargeConfirm";
        String GET_PATIENT_INFO_LIST = "phoenix-front/hiszy/getRechargePatientInfoList";
        String GET_AREA_INFO = "phoenix-front/hiszy/getAreaInfo";
        String GET_USER_ZY_INFO = "phoenix-front/hiszy/getInPatientInfo";
        String GET_USER_ZY_INFO_LIST = "phoenix-front/hiszy/getInPatientInfo";
        String GET_USER_ZY_RECORD_LIST = "phoenix-front/hiszy/getInPatientRecord";
        String GET_ZY_BILL_DETAIL = "phoenix-front/hiszy/getInPatientBillDetail";
        String GET_ZY_BILL_LIST = "phoenix-front/hiszy/getInPatientBill";
        String GET_ZY_DAY_BILL = "phoenix-front/hiszy/getZyDayBill";
        String GET_ZY_DAY_BILL_DETAIL = "phoenix-front/hiszy/getZyDayBillDetail";
        String GET_HIS_ZY_TRANS_INFO = "phoenix-front/hiszy/getHisZyTransInfo";
        String GET_IN_HOS_ACCT_INFO = "phoenix-front/hiszy/getInHosAcctInfo";
        String GET_IN_HOS_ACCT_RECHARGE_MAKEUP = "phoenix-front/hiszy/inHosAcctRechargeMakeup";
        String GET_UNDERLINE_ZY_DEPOSITRESULT = "phoenix-front/inhos/getUnderlineZyDepositResult";
        String GET_ZY_BUSINESS_STATUS = "phoenix-front/hiszy/getHisZyBusinessStatus";
    }

    public interface INVOICE {
        String LOGGEDRECEIPT = "phoenix-front/receipt/loggedReceipt";
        String GETMACHINEINVOICENO = "phoenix-front/receipt/getMachineInvoiceNo";
        String GETREGISTERRECEIPT = "phoenix-front/receipt/getRegisterReceipt";
        String GETPAYMENTRECEIPT = "phoenix-front/receipt/getPayMentReceipt";
        String FLAGINVOICEPRINTED = "phoenix-front/receipt/flagInvoicePrinted";
        String GETALLRECEIPTNOPRINT = "phoenix-front/receipt/getAllReceiptNoPrint";
        String GETMAINTAINSECTIONNO = "phoenix-front/receipt/getMaintainSectionNo";
        String DELSECTIONNO = "phoenix-front/receipt/delSectionNo";
        String GETMAKEINVOICEINFO = "phoenix-front/receipt/getMakeInvoiceInfo";
        String DELMAKEINVOICERESULT = "phoenix-front/receipt/delMakeInvoiceResult";
        String GETFPINVOICEINFO = "phoenix-front/receipt/getFpInvoiceInfo";
        String DEALFPINVOICEINFO = "phoenix-front/receipt/dealFpInvoiceInfo";
        String UPDATEFPINVOICE = "phoenix-front/receipt/updateFpInvoice";
    }

    public interface ONEACCOUNT {
        String GET_ONE_ACCOUNT_AMOUNT = "phoenix-hospital/oneAcct/oneAcctAmountQuery";
        String OPEN_ONE_ACCOUNT = "phoenix-hospital/oneAcct/openOneAcct";
        String ONE_ACCOUNT_RECHARGECONFIRM = "phoenix-hospital/oneAcct/oneAcctRechargeConfirm";
        String ONE_ACCOUNT_CLEAN_MACHINE = "phoenix-hospital/oneAcct/oneAccCleanMachine";
    }

    public interface OTHERS {
        String GET_CLEAN_MACHINE_INFO = "phoenix-front/cleanmachine/getCleanMachineInfo";
        String CLEAN_MACHINE = "phoenix-front/cleanmachine/cleanMachine";
        String GET_MEDICAL_FEETYPE = "phoenix-front/medicalfeequery/getMedicalFeeType";
        String GET_MEDICAL_FEETYPE_DETAIL = "phoenix-front/medicalfeequery/getMedicalFeeTypeDetail";
        String GET_AREA_CASCADE = "phoenix-front/patient/getAreaCascade";
        String GET_ELECTRONIC_PRESCRIPTION = "phoenix-front/prescript/getElectronicPrescription";
        String SET_ELECTRONIC_PRESCRIPTION_PRINTFLAG = "phoenix-front/prescript/setElectronicPrescriptionPrintFlag";
        String GET_MEDICAL_RECORD = "phoenix-front/medicalRecord/zzGetMedicalRecord";
        String SET_MEDICAL_RECORD_PRINTFLAG = "phoenix-front/medicalRecord/zzSetMedicalRecordPrintFlag";
        String GET_MEDICAL_RECORD_DETAIL = "phoenix-front/medicalRecord/zzGetMedicalRecordDetail";
        String GET_OPERATOR_CODE = "phoenix-front/msst/getOperatorCode";
        String GET_RELATION_SHIP = "phoenix-front/patient/getRelationShip";
        String GET_HOS_SIGN_INFO = "phoenix-front/others/getHosSignInfo";
        String GET_REGISTER_QUEUEINFO = "phoenix-front/hismz/getRegisterQueueInfo";
        String GET_PAUSED_SCHEDULE = "phoenix-front/hismz/getPausedSchedule";
        String GET_GUIDECLINIC_INFO = "phoenix-front/hismz/getGuideClinicInfo";
        String GET_OUT_SETTLE_INVOICE = "phoenix-front/hismz/getOutSettleInvoice";
        String PRT_OUT_SETTLE_INVOICE = "phoenix-front/hismz/prtOutSettleInvoice";
        String GET_MZ_TRANS_RECORD = "phoenix-front/hismz/getMzTransRecord";
        String GET_ORDER_BUSINESSS_TATUS = "phoenix-front/others/getOrderBusinesssSatus";
        String GET_DO_SIGN_URL = "phoenix-front/others/doSign";
        String GET_CANCEL_SIGN_URL = "phoenix-front/others/cancelSign";
        String GET_SIGN_INFO_URL = "phoenix-front/others/getSignInfo";
        String GET_VERI_CODE_URL = "phoenix-front/others/getVeriCode";
        String MODIFY_PHONE_INFO_URL = "phoenix-front/others/modifyPhone";
    }

    public interface RECEIPT {
        String GET_MZ_PATIENT_INFO = "phoenix-front/hismz/getRegistrationInfo";
        String REG_MZ_PATIENT_INFO = "phoenix-front/hismz/citizenCardRegister";
        String REG_MZ_CHECK_SFZ = "phoenix-front/hismz/regMzCheckSfz";
        String REG_MZ_CHECK_PID = "phoenix-front/hismz/regMzCheckPid";
        String REG_MZ_UNBINDING = "phoenix-front/hismz/regMzUnBinding";
        String ISSUE_CARD = "phoenix-front/hismz/issueCard";
        String MODIFY_PATIENT = "phoenix-front/hismz/modifyPatient";
        String ISSUE_CARD_SUMMARY = "phoenix-front/hismz/issueCardSummary";
        String GET_HEALTH_CARD_INFO = "phoenix-front/hismz/getHealthCardInfo";
        String GET_HEALTH_CARD_TOKEN = "phoenix-front/hismz/getHealthCardToken";
        String GET_HEALTH_CARD_VALID = "phoenix-front/hismz/getHealthCardValid";
        String HISMZ_CHECK_SFZ = "phoenix-front/hismz/checkSfz";
        String SELFCARD_ISSUECARD = "phoenix-front/selfcard/issueCard";
        String QRYPATI_MOBILE_STATUS = "phoenix-front/hismz/qryPatiMobileStatus";
        String GET_CARDNO_BY_MEENO = "phoenix-front/hismz/getCardNoByMeeNo";
        String GET_REALNAME_INFO = "phoenix-front/hismz/getRealNameInfo";
    }

    public interface REGISTRATION {
        String SEARCH_PRENOS = "phoenix-front/hismz/getPrenosInfo";
        String REGISTER_SUMMARY = "phoenix-front/hismz/registerSummary";
        String REGISTER_COMFIRM = "phoenix-front/hismz/registerConfirm";
        String PAYMENT_SUMMARY = "phoenix-front/hismz/paymentSummary";
        String PAYMENT_COMFIRM = "phoenix-front/hismz/paymentConfirm";
        String TJ_PAYMENT_SUMMARY = "phoenix-front/hismz/tjPaymentSummary";
        String TJ_PAYMENT_COMFIRM = "phoenix-front/hismz/tjPaymentConfirm";
        String GET_CARD_INFO = "phoenix-front/hismz/getCardInfo";
        String GET_HIS_BUSINESS_STATUS = "phoenix-front/hismz/getHisBusinessStatus";
        String GET_RESERVATE_CONFIRM_INFO = "phoenix-front/hismz/getReservateConfirmInfo";
        String REGISTER_REFUND = "phoenix-front/hismz/registerRefund";
        String HISMZ_GETCARDINFO = "phoenix-front/hismz/getCardInfo";
        String HISMZ_GETRESERVATECONFIRMINFO = "phoenix-front/hismz/getReservateConfirmInfo";
        String GET_WECHAT_REG_AND_PAY = "phoenix-front/hismz/getWechatRegAndPay";
        String REGISTER_CONFIRM_WECHAT = "phoenix-front/hismz/registerConfirmWechat";
        String PAYMENT_CONFIRM_WECHAT = "phoenix-front/hismz/paymentConfirmWechat";
        String GET_EMC_SCHEDULE_DEPT = "phoenix-front/hismz/getEmcScheduleInfo";
    }

    public interface REPORT {
        String FRONT_GET_NOTPRINT_REPORTINFO = "phoenix-front/report/getNotPrintReportInfo";
        String FRONT_UPDATE_PRINTFLAG = "phoenix-front/report/updatePrintFlag";
        String FRONT_GET_HOS_REPORTINFO = "phoenix-front/lismz/getHosReportInfo";
        String FRONT_GET_HOS_REPORTDETAIL = "phoenix-front/lismz/getHosReportDetail";
        String FRONT_GET_HOS_PACS_REPORTINFO = "phoenix-front/lismz/getHosPacsReportInfo";
        String FRONT_GET_HOS_PACS_REPORTINFODETAIL = "phoenix-front/lismz/getHosPacsReportInfoDetail";
        String FRONT_GET_PRINTED_REPORTINFO = "phoenix-front/report/getPrintedReportInfo";
    }

    public interface RESERVATION {
        String PRE_REGISTER_CANCEL = "phoenix-front/hismz/preRegisterCancel";
        String PRE_REGISTER_CONFIRM = "phoenix-front/hismz/preRegisterConfirm";
        String PRE_REGISTER_SUMMARY = "phoenix-front/hismz/preRegisterSummary";
    }

    public interface SCHEDULE {
        String PRE_REGISTER_SCHEDUE = "phoenix-front/hismz/getPreRegisterSchedue";
        String PRE_ALL_REGISTER_SCHEDUE = "phoenix-front/hismz/getAllPreRegisterSchedue";
        String PRE_REGISTER_TDSCHEDUE = "phoenix-front/hismz/getRegisterSchedueInfo";
        String PRE_ALL_REGISTER_TDSCHEDUE = "phoenix-front/hismz/getAllRegisterSchedueInfo";
    }

    public interface SRVINHOS {
        String SEARCH_HOS_AREA_INFO = "phoenix-hospital/online/inhos/zzSearchHosAreaInfo";
        String GET_INHOS_ACCT_INFO = "phoenix-hospital/online/inhos/getInHosAcctInfo";
        String GET_INPATIENT_BILL = "phoenix-hospital/online/inhos/getInPatientBill";
        String GET_INPATIENT_BILL_DETAIL = "phoenix-hospital/online/inhos/getInPatientBillDetail";
        String SEARCH_RECHARGE_PATIENT_INFO = "phoenix-hospital/online/inhos/zzSearchRechargePatientInfo";
        String GET_INPATIENT_RECORD = "phoenix-hospital/online/inhos/getInPatientRecord";
        String SEARCH_RECHARGE_PATIENT_BY_AREAINFO = "phoenix-hospital/online/inhos/zzSearchRechargePatientByAreaInfo";
        String GET_ZY_DAY_BILL = "phoenix-hospital/online/inhos/zzGetZyDayBill";
        String GET_ZY_DAY_BILL_DETAIL = "phoenix-hospital/online/inhos/zzGetZyDayBillDetail";
        String PAYMENT_HOS_DEPOSIT = "phoenix-hospital/online/inhos/zzPaymentHosDeposit";
        String HOS_ACC_HOS_DEPOSIT_SUMMARY = "phoenix-hospital/online/inhos/zzHosAccHosDepositSummary";
        String INHOS_ACCT_RECHARGE_MAKEUP = "phoenix-hospital/offline/inhos/inHosAcctRechargeMakeup";
    }

    public interface SRVREGISTRATION {
        String PRENOS_PAYMENT = "phoenix-hospital/online/registration/zzPrenosPayment";
        String PRENOS_SUMMARY = "phoenix-hospital/online/registration/zzPrenosSummary";
        String REGISTER_PAYMENT = "phoenix-hospital/online/registration/zzRegisterPayment";
        String REGISTER_REFUND = "phoenix-hospital/online/registration/registerRefund";
        String REGISTER_SUMMARY = "phoenix-hospital/online/registration/zzRegisterSummary";
    }

    public interface SRVRESERVATION {
        String PRE_REGISTER_CANCEL = "phoenix-hospital/online/zzPreRegister/zzPreRegisterCancel";
        String PRE_REGISTER_CONFIRM = "phoenix-hospital/online/zzPreRegister/zzPreRegisterConfirm";
        String RESERVATE_SUMMARY = "phoenix-hospital/online/zzPreRegister/zzReservateSummary";
    }

    public interface SRV_ACCOUNT {
        String SRV_HOS_ACCT_QUERY = "phoenix-hospital/online/zzHosAcct/zzAcctQuery";
        String SRV_HOS_ACCT_CANCEL = "phoenix-hospital/online/zzHosAcct/zzCancelHosAcct";
        String SRV_GET_HOS_ACCT_BUSINESS_STATUS = "phoenix-hospital/online/zzHosAcct/getYnzhBusinessStatus";
        String SRV_HOS_ACCT_TRANS_QUERY = "phoenix-hospital/online/zzHosAcct/zzHosAcctTransQuery";
        String SRV_HOS_ACCT_CANCEL_CONSUM = "phoenix-hospital/online/zzHosAcct/zzHosAcctConsumCancel";
        String SRV_HOS_ACCT_CONSUM = "phoenix-hospital/online/zzHosAcct/zzHosAcctConsum";
        String SRV_HOS_ACC_RECHARGE_COMFIRM = "phoenix-hospital/online/zzHosAcct/zzHosAcctRechargeConfirm";
        String SRV_HOS_ACC_RECHARGE_SUMMARY = "phoenix-hospital/online/zzHosAcct/zzHosAcctRechargeOrder";
        String SRV_HOS_ACCT_OPEN = "phoenix-hospital/online/zzHosAcct/zzOpenHosAcct";
    }

    public interface SRV_RECEIPT {
        String SRV_REG_MZ_CHECK_SFZ = "phoenix-hospital/online/patient/zzRegCheckSfz";
        String SRV_REG_MZ_PATIENT_INFO = "phoenix-hospital/online/patient/zzRegPatient";
        String SRV_GET_MZ_PATIENT_INFO = "phoenix-hospital/online/patient/zzGetPatient";
    }

    public interface SRV_REGISTRATION {
        String SRV_SEARCH_PRENOS = "phoenix-hospital/online/registration/zzSearchPrenosInfo";
        String SRV_GET_CARD_INFO = "phoenix-hospital/online/registration/getCardInfo";
        String SRV_GET_HIS_BUSINESS_STATUS = "phoenix-hospital/online/registration/getHisBusinessStatus";
    }

    public interface SRV_REPORT {
        String SRV_GET_NOTPRINT_REPORTINFO = "phoenix-front/offline/report/getNotPrintReportInfos";
        String SRV_UPDATE_PRINTFLAG = "phoenix-front/offline/report/updateLisPrintFlag";
        String SRV_GET_HOS_REPORTINFO = "phoenix-front/offline/report/getHosReportInfo";
        String SRV_GET_HOS_REPORTDETAIL = "phoenix-front/offline/report/getHosReportDetail";
        String SRV_GET_HOS_PACS_REPORTINFO = "phoenix-front/offline/report/getHosPacsReportInfo";
        String SRV_GET_HOS_PACS_REPORTINFODETAIL = "phoenix-front/offline/report/getHosPacsReportInfoDetail";
        String SRV_GET_PRINTED_REPORTINFO = "phoenix-front/offline/report/getPrintedReportInfo";
    }

    public interface SRV_SELFMACHINE {
        String SRV_GETSELFMACHINEINFO = "phoenix-hospital/offline/selfmac/getSelfMachineInfo";
    }

    public interface payCenter {
        String PAY_FATHERSON_ORDER_URL = "paycenter/payorder/fathersonOrder";
        String PAY_SINGLE_ORDER_URL = "paycenter/payorder/singleOrder";
    }
}
