package nc.vo.pu.m21.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by shangguoqiang on 2017-03-06 09:06:22
 */
@XmlRootElement(name = "datarow")//攀钢销售系统销售订单
public class MsgXSAGHT0001 {
    String orderNumPf;   //订单号_PF  长度:8  
    String mdOrderNumPf;   //买断订单号  长度:10  
    String erpOrderNumPf;   //ERP合同号  长度:40  
    String orderStatusPf;   //订单状态_PF  长度:1  
    String orderCertNo;   //订货凭据  长度:30  
    String orderTypeCode;   //订单性质代码  长度:3  
    String expFlag;   //内外销标志  长度:1  
    String orderPeriodPf;   //订货期别_PF  长度:4  
    String resoPeriodPf;   //资源期别_PF  长度:4  
    String saleNetworkPf;   //销售网络  长度:1  
    String prodClassCode;   //产品大类代码  长度:1  
    String companyCodePf;   //公司别_PF  长度:2  
    String tradeMode;   //贸易方式  长度:1  
    String settleMode;   //结算方式代码  长度:1  
    String signSitePf;   //履约地点代码_PF  长度:1  
    String priceFormulaMode;   //计价方式  长度:1  
    String rmaNoPf;   //异议号_PF  长度:6  
    String contInDate;   //合约录入日期  长度:8  
    String contInPerson;   //合约录入人  长度:8  
    String orderModiMark;   //订单变更中标志  长度:1  
    String orderUserCodePf;   //订货用户代码_PF  长度:6  
    String orderUserCnamePf;   //订货用户中文名称_PF  长度:60  
    BigDecimal orderUserAddrNum;   //订货用户地址码  长度:2  
    String orderUserAddrNamePf;   //订货用户地址_PF  长度:60  
    String settleUserNum;   //需方用户代码  长度:6  
    String settleUserNamePf;   //需方用户名称_PF  长度:60  
    BigDecimal settUserAddrNum;   //需方用户地址码  长度:2  
    String settUserAddrPf;   //需方用户地址_PF  长度:60  
    BigDecimal settUserAcctNum;   //需方用户帐户序号  长度:2  
    String settUserAcctPf;   //需方用户帐户_PF  长度:30  
    BigDecimal contOrderNum;   //合约子项数量  长度:2  
    BigDecimal contTotWt;   //合约总重量  长度:12.3  
    BigDecimal contTotAmt;   //合约总金额  长度:14.2  
    BigDecimal contTrnpTotAmt;   //合约运费总金额  长度:12.2  
    BigDecimal contDepositNum;   //合约押金量  长度:12.3  
    BigDecimal contDepositAmt;   //合约押金金额  长度:14.2  
    BigDecimal contPrepayRate;   //合约定金比例  长度:6.4  
    BigDecimal contPrepayLackAmt;   //合约应收定金额  长度:14.2  
    BigDecimal taxRatePf;   //税率_PF  长度:6.4  
    
    String orderNo;   //合同号  长度:10  
    String mdOrderNo;   //买断订单子项号  长度:10  
    String erpOrderNo;   //ERP合同子项号  长度:40  
    BigDecimal orderModiNum;   //合同变更次数  长度:2  
    String orderLastSeqNo;   //合同子项序号  长度:2  
    String saleId;   //销售业务员工号  长度:8  
    String recCreateTime;   //记录创建时间  长度:14  
    String partOrderFlag;   //套裁订单标志  长度:1  
    BigDecimal cutNum;   //纵切条数  长度:2  
    String orderItemStatusCode;   //销售订单子项状态  长度:2  
    String orderItemModiMark;   //订单子项变更中标记  长度:1  
    String modiCause;   //变更原因  长度:100  
    String modiDate;   //变更日期  长度:8  
    String modiOperPerson;   //合同变更操作人  长度:8  
    String modiType;   //变更性质  长度:1  
    String finUserNum;   //最终用户代码  长度:6  
    String finUserName;   //最终用户名称  长度:60  
    String finUserTrade;   //最终用户的行业代码  长度:2  
    String userCredit;   //用户信誉度  长度:1  
    String consignNum;   //收货用户代码  长度:6  
    String consignNamePf;   //收货用户名称_PF  长度:60  
    BigDecimal cnsgAddressNum;   //收货用户地址码  长度:2  
    String cnsgAddressPf;   //收货用户地址_PF  长度:60  
    String consignNum1;   //收货用户代码1  长度:6  
    String consignName1Pf;   //收货用户名称1_PF  长度:60  
    BigDecimal cnsgAddressNum1;   //收货用户地址码1  长度:2  
    String cnsgAddress1Pf;   //收货用户地址1_PF  长度:60  
    String trnpTitleCode;   //运费发票抬头代码  长度:1  
    String trnpTitleText;   //运费发票抬头  长度:40  
    String psr;   //产品规范码  长度:11  
    String apn;   //最终用途  长度:4  
    String msc;   //冶金规范码  长度:6  
    String newProductFlag;   //新试产品标志  长度:1  
    String productDscr;   //产品名称  长度:30  
    String shopsignPf;   //牌号_PF  长度:20  
    String steelStd;   //产品标准  长度:60  
    String capabYnFlag;   //性能是否要求标志  长度:1  
    String rainCoatFlag;   //加盖雨布标志  长度:1  
    String qcType;   //质保书类型_PF  长度:2  
    BigDecimal certiNum;   //质保书份数  长度:2  
    String packingCode;   //包装代码_PF  长度:2  
    String orderPriority;   //订单优先级  长度:1  
    String metFeetFlag;   //公英制标志  长度:1  
    BigDecimal orderThick;   //订货厚  长度:7.3  
    BigDecimal orderWidthPf;   //订货宽  长度:4  
    BigDecimal orderLenLow;   //订货长度下限  长度:7  
    BigDecimal orderLenUp;   //订货长度上限  长度:7  
    String thickEng;   //英制厚度  长度:15  
    String widthEng;   //英制宽度  长度:15  
    String lengthMinEng;   //英制长度下限  长度:15  
    String lengthMaxEng;   //英制长度上限  长度:15  
    String weightMethod;   //计重方式  长度:1  
    String orderUnit;   //订货计量单 位  长度:1  
    BigDecimal orderWtPf;   //订货重量_PF  长度:12.3  
    BigDecimal preAlcaWtPf;   //预合同分配量_PF  长度:12.3  
    BigDecimal orderPiece;   //订货件数  长度:4  
    BigDecimal unitWtUpPf;   //单件最大重量_PF  长度:9.3  
    BigDecimal unitWtLowPf;   //单件最小重量_PF  长度:9.3  
    BigDecimal delivyTolUpper;   //公差上限  长度:2  
    BigDecimal delivyTolLower;   //公差下限  长度:3  
    BigDecimal coilInsideDimPf;   //内径_PF  长度:4  
    BigDecimal shortSizeRatePf;   //订货短尺率_PF  长度:5.2  
    BigDecimal shortSizeLowPf;   //订货短尺下限_PF  长度:7  
    BigDecimal shortSizeUpPf;   //订货短尺上限_PF  长度:7  
    BigDecimal shortSize1;   //短尺长度1  长度:7  
    BigDecimal shortSize2;   //短尺长度2  长度:7  
    BigDecimal shortSize3;   //短尺长度3  长度:7  
    BigDecimal shortSize4;   //短尺长度4  长度:7  
    BigDecimal shortSize5;   //短尺长度5  长度:7  
    String deliveryDateChr;   //约定交货日期  长度:8  
    String delivyPeriodPf;   //交货期别_PF  长度:4  
    String deliveryWeekMark;   //按周交货标志  长度:1  
    String orderReadyDate;   //订单备妥日期  长度:8  
    String launchTime;   //下发时间  长度:14  
    String launchId;   //下发人工号  长度:8  
    String launchFirstTime;   //初次下发时间  长度:14  
    String launchFirstId;   //初次下发人工号  长度:8  
    BigDecimal versionNumPf;   //版本号_PF  长度:2  
    String orderPriceMode;   //合同计价方式  长度:1  
    String priceBaseDate;   //价格执行基准日  长度:8  
    BigDecimal saleUnitPrice;   //销售 单价  长度:9.2  
    String agreemtCode;   //协议价格单号  长度:10  
    BigDecimal agreePrice;   //协议价格  长度:9.2  
    BigDecimal orderAmtPf;   //订单金额_PF  长度:14.2  
    String saleRemark;   //销售备注  长度:100  
    String a_flag;   //A项标志  长度:1  
    String userSpecDesc;   //用户质量特殊要求  长度:200  
    String drewMode;   //开票方式  长度:1  
    BigDecimal depositN;   //押金数  长度:3  
    BigDecimal depositAmtPf;   //押金金额_PF  长度:14.2  
    String transType;   //运输类型  长度:1  
    String trnpModeCodePf;   //运输方式代码_PF  长度:2  
    String deliveryPlaceCodePf;   //终到站港代码_PF  长度:8  
    String deliveryPlaceName;   //终到站港描述  长度:60  
    String deliveryPlaceCode1Pf;   //终到站港代码1_PF  长度:8  
    String deliveryPlaceName1;   //终到站港描述1  长度:60  
    String privateRouteCodePf;   //专用线代码_PF  长度:5  
    String privateRouteNamePf;   //专用线描述_PF  长度:60  
    BigDecimal trnpPricePf;   //运费单价_PF  长度:9.2  
    BigDecimal trnpAmtPf;   //运费金额_PF  长度:14.2  
    String guideMeasureAq;   //镀层厚度计量方式  长度:1  
    String edgeType;   //切边形态  长度:1  
    String orderTechnicDes;   //合同单技术参数描述  长度:200  
    String orderByprodDegreeCode;   //合同副产品等级代码  长度:3  
    String weightPlate;   //锌层重量  长度:10  
    String grainCode;   //粒度代码  长度:2  
    BigDecimal patternSalient;   //纹高  长度:6.3  
    String sectionCode;   //截面代码  长度:10  
    String sectionDesc;   //截面描述  长度:30  
    String recognCode;   //产品标识代码  长度:2  
    String endLineCode;   //终到站省份  长度:4  
    String endLineName;   //终到站地区别  长度:2  
    String transProdCode;   //运输产品大类  长度:1  
    String unloadPoint;   //卸车地点  长度:60  
    String priceMode;   //定价模式  长度:1  
    String prodCode;   //销售品种  长度:20  
    String straragemMark;   //客商合作关系等级  长度:1  
    String priceModeCname;   //定价模式名称  长度:10  
    String prodCodeCname;   //定价模式名称  长度:30  
    String straragemMarkCname;   //客商合作关系等级名称  长度:10  
    String invoiceType;   //营改增发票类型  长度:1  
    String invoiceTypeName;   //发票类型说明  长度:40  
    String userChineseName;   //受票方名称  长度:200  
    String taxNum;   //纳税人识别号  长度:30  
    String addressLocationc;   //地址  长度:200  
    String bankBranchName;   //开户银行  长度:200  
    String accountNum;   //账号  长度:30  
    String telNum;   //电话  长度:20  

    public MsgXSAGHT0001() {
    }

    public MsgXSAGHT0001(
            String orderNumPf, String mdOrderNumPf, String erpOrderNumPf, String orderStatusPf, String orderCertNo, String orderTypeCode, String expFlag, String orderPeriodPf, String resoPeriodPf, String saleNetworkPf, String prodClassCode, String companyCodePf, String tradeMode, String settleMode, String signSitePf, String priceFormulaMode, String rmaNoPf, String contInDate, String contInPerson, String orderModiMark, String orderUserCodePf, String orderUserCnamePf, BigDecimal orderUserAddrNum, String orderUserAddrNamePf, String settleUserNum, String settleUserNamePf, BigDecimal settUserAddrNum, String settUserAddrPf, BigDecimal settUserAcctNum, String settUserAcctPf, BigDecimal contOrderNum, BigDecimal contTotWt, BigDecimal contTotAmt, BigDecimal contTrnpTotAmt, BigDecimal contDepositNum, BigDecimal contDepositAmt, BigDecimal contPrepayRate, BigDecimal contPrepayLackAmt, BigDecimal taxRatePf, String orderNo, String mdOrderNo, String erpOrderNo, BigDecimal orderModiNum, String orderLastSeqNo, String saleId, String recCreateTime, String partOrderFlag, BigDecimal cutNum, String orderItemStatusCode, String orderItemModiMark, String modiCause, String modiDate, String modiOperPerson, String modiType, String finUserNum, String finUserName, String finUserTrade, String userCredit, String consignNum, String consignNamePf, BigDecimal cnsgAddressNum, String cnsgAddressPf, String consignNum1, String consignName1Pf, BigDecimal cnsgAddressNum1, String cnsgAddress1Pf, String trnpTitleCode, String trnpTitleText, String psr, String apn, String msc, String newProductFlag, String productDscr, String shopsignPf, String steelStd, String capabYnFlag, String rainCoatFlag, String qcType, BigDecimal certiNum, String packingCode, String orderPriority, String metFeetFlag, BigDecimal orderThick, BigDecimal orderWidthPf, BigDecimal orderLenLow, BigDecimal orderLenUp, String thickEng, String widthEng, String lengthMinEng, String lengthMaxEng, String weightMethod, String orderUnit, BigDecimal orderWtPf, BigDecimal preAlcaWtPf, BigDecimal orderPiece, BigDecimal unitWtUpPf, BigDecimal unitWtLowPf, BigDecimal delivyTolUpper, BigDecimal delivyTolLower, BigDecimal coilInsideDimPf, BigDecimal shortSizeRatePf, BigDecimal shortSizeLowPf, BigDecimal shortSizeUpPf, BigDecimal shortSize1, BigDecimal shortSize2, BigDecimal shortSize3, BigDecimal shortSize4, BigDecimal shortSize5, String deliveryDateChr, String delivyPeriodPf, String deliveryWeekMark, String orderReadyDate, String launchTime, String launchId, String launchFirstTime, String launchFirstId, BigDecimal versionNumPf, String orderPriceMode, String priceBaseDate, BigDecimal saleUnitPrice, String agreemtCode, BigDecimal agreePrice, BigDecimal orderAmtPf, String saleRemark, String a_flag, String userSpecDesc, String drewMode, BigDecimal depositN, BigDecimal depositAmtPf, String transType, String trnpModeCodePf, String deliveryPlaceCodePf, String deliveryPlaceName, String deliveryPlaceCode1Pf, String deliveryPlaceName1, String privateRouteCodePf, String privateRouteNamePf, BigDecimal trnpPricePf, BigDecimal trnpAmtPf, String guideMeasureAq, String edgeType, String orderTechnicDes, String orderByprodDegreeCode, String weightPlate, String grainCode, BigDecimal patternSalient, String sectionCode, String sectionDesc, String recognCode, String endLineCode, String endLineName, String transProdCode, String unloadPoint, String priceMode, String prodCode, String straragemMark, String priceModeCname, String prodCodeCname, String straragemMarkCname, String invoiceType, String invoiceTypeName, String userChineseName, String taxNum, String addressLocationc, String bankBranchName, String accountNum, String telNum
    ) {
        this.orderNumPf = orderNumPf;
        this.mdOrderNumPf = mdOrderNumPf;
        this.erpOrderNumPf = erpOrderNumPf;
        this.orderStatusPf = orderStatusPf;
        this.orderCertNo = orderCertNo;
        this.orderTypeCode = orderTypeCode;
        this.expFlag = expFlag;
        this.orderPeriodPf = orderPeriodPf;
        this.resoPeriodPf = resoPeriodPf;
        this.saleNetworkPf = saleNetworkPf;
        this.prodClassCode = prodClassCode;
        this.companyCodePf = companyCodePf;
        this.tradeMode = tradeMode;
        this.settleMode = settleMode;
        this.signSitePf = signSitePf;
        this.priceFormulaMode = priceFormulaMode;
        this.rmaNoPf = rmaNoPf;
        this.contInDate = contInDate;
        this.contInPerson = contInPerson;
        this.orderModiMark = orderModiMark;
        this.orderUserCodePf = orderUserCodePf;
        this.orderUserCnamePf = orderUserCnamePf;
        this.orderUserAddrNum = orderUserAddrNum;
        this.orderUserAddrNamePf = orderUserAddrNamePf;
        this.settleUserNum = settleUserNum;
        this.settleUserNamePf = settleUserNamePf;
        this.settUserAddrNum = settUserAddrNum;
        this.settUserAddrPf = settUserAddrPf;
        this.settUserAcctNum = settUserAcctNum;
        this.settUserAcctPf = settUserAcctPf;
        this.contOrderNum = contOrderNum;
        this.contTotWt = contTotWt;
        this.contTotAmt = contTotAmt;
        this.contTrnpTotAmt = contTrnpTotAmt;
        this.contDepositNum = contDepositNum;
        this.contDepositAmt = contDepositAmt;
        this.contPrepayRate = contPrepayRate;
        this.contPrepayLackAmt = contPrepayLackAmt;
        this.taxRatePf = taxRatePf;
        this.orderNo = orderNo;
        this.mdOrderNo = mdOrderNo;
        this.erpOrderNo = erpOrderNo;
        this.orderModiNum = orderModiNum;
        this.orderLastSeqNo = orderLastSeqNo;
        this.saleId = saleId;
        this.recCreateTime = recCreateTime;
        this.partOrderFlag = partOrderFlag;
        this.cutNum = cutNum;
        this.orderItemStatusCode = orderItemStatusCode;
        this.orderItemModiMark = orderItemModiMark;
        this.modiCause = modiCause;
        this.modiDate = modiDate;
        this.modiOperPerson = modiOperPerson;
        this.modiType = modiType;
        this.finUserNum = finUserNum;
        this.finUserName = finUserName;
        this.finUserTrade = finUserTrade;
        this.userCredit = userCredit;
        this.consignNum = consignNum;
        this.consignNamePf = consignNamePf;
        this.cnsgAddressNum = cnsgAddressNum;
        this.cnsgAddressPf = cnsgAddressPf;
        this.consignNum1 = consignNum1;
        this.consignName1Pf = consignName1Pf;
        this.cnsgAddressNum1 = cnsgAddressNum1;
        this.cnsgAddress1Pf = cnsgAddress1Pf;
        this.trnpTitleCode = trnpTitleCode;
        this.trnpTitleText = trnpTitleText;
        this.psr = psr;
        this.apn = apn;
        this.msc = msc;
        this.newProductFlag = newProductFlag;
        this.productDscr = productDscr;
        this.shopsignPf = shopsignPf;
        this.steelStd = steelStd;
        this.capabYnFlag = capabYnFlag;
        this.rainCoatFlag = rainCoatFlag;
        this.qcType = qcType;
        this.certiNum = certiNum;
        this.packingCode = packingCode;
        this.orderPriority = orderPriority;
        this.metFeetFlag = metFeetFlag;
        this.orderThick = orderThick;
        this.orderWidthPf = orderWidthPf;
        this.orderLenLow = orderLenLow;
        this.orderLenUp = orderLenUp;
        this.thickEng = thickEng;
        this.widthEng = widthEng;
        this.lengthMinEng = lengthMinEng;
        this.lengthMaxEng = lengthMaxEng;
        this.weightMethod = weightMethod;
        this.orderUnit = orderUnit;
        this.orderWtPf = orderWtPf;
        this.preAlcaWtPf = preAlcaWtPf;
        this.orderPiece = orderPiece;
        this.unitWtUpPf = unitWtUpPf;
        this.unitWtLowPf = unitWtLowPf;
        this.delivyTolUpper = delivyTolUpper;
        this.delivyTolLower = delivyTolLower;
        this.coilInsideDimPf = coilInsideDimPf;
        this.shortSizeRatePf = shortSizeRatePf;
        this.shortSizeLowPf = shortSizeLowPf;
        this.shortSizeUpPf = shortSizeUpPf;
        this.shortSize1 = shortSize1;
        this.shortSize2 = shortSize2;
        this.shortSize3 = shortSize3;
        this.shortSize4 = shortSize4;
        this.shortSize5 = shortSize5;
        this.deliveryDateChr = deliveryDateChr;
        this.delivyPeriodPf = delivyPeriodPf;
        this.deliveryWeekMark = deliveryWeekMark;
        this.orderReadyDate = orderReadyDate;
        this.launchTime = launchTime;
        this.launchId = launchId;
        this.launchFirstTime = launchFirstTime;
        this.launchFirstId = launchFirstId;
        this.versionNumPf = versionNumPf;
        this.orderPriceMode = orderPriceMode;
        this.priceBaseDate = priceBaseDate;
        this.saleUnitPrice = saleUnitPrice;
        this.agreemtCode = agreemtCode;
        this.agreePrice = agreePrice;
        this.orderAmtPf = orderAmtPf;
        this.saleRemark = saleRemark;
        this.a_flag = a_flag;
        this.userSpecDesc = userSpecDesc;
        this.drewMode = drewMode;
        this.depositN = depositN;
        this.depositAmtPf = depositAmtPf;
        this.transType = transType;
        this.trnpModeCodePf = trnpModeCodePf;
        this.deliveryPlaceCodePf = deliveryPlaceCodePf;
        this.deliveryPlaceName = deliveryPlaceName;
        this.deliveryPlaceCode1Pf = deliveryPlaceCode1Pf;
        this.deliveryPlaceName1 = deliveryPlaceName1;
        this.privateRouteCodePf = privateRouteCodePf;
        this.privateRouteNamePf = privateRouteNamePf;
        this.trnpPricePf = trnpPricePf;
        this.trnpAmtPf = trnpAmtPf;
        this.guideMeasureAq = guideMeasureAq;
        this.edgeType = edgeType;
        this.orderTechnicDes = orderTechnicDes;
        this.orderByprodDegreeCode = orderByprodDegreeCode;
        this.weightPlate = weightPlate;
        this.grainCode = grainCode;
        this.patternSalient = patternSalient;
        this.sectionCode = sectionCode;
        this.sectionDesc = sectionDesc;
        this.recognCode = recognCode;
        this.endLineCode = endLineCode;
        this.endLineName = endLineName;
        this.transProdCode = transProdCode;
        this.unloadPoint = unloadPoint;
        this.priceMode = priceMode;
        this.prodCode = prodCode;
        this.straragemMark = straragemMark;
        this.priceModeCname = priceModeCname;
        this.prodCodeCname = prodCodeCname;
        this.straragemMarkCname = straragemMarkCname;
        this.invoiceType = invoiceType;
        this.invoiceTypeName = invoiceTypeName;
        this.userChineseName = userChineseName;
        this.taxNum = taxNum;
        this.addressLocationc = addressLocationc;
        this.bankBranchName = bankBranchName;
        this.accountNum = accountNum;
        this.telNum = telNum;


    }

    @XmlElement(name = "orderNumPf")
    public String getOrderNumPf() {
        return orderNumPf;
    }

    public void setOrderNumPf(String orderNumPf) {
        this.orderNumPf = orderNumPf;
    }

    @XmlElement(name = "mdOrderNumPf")
    public String getMdOrderNumPf() {
        return mdOrderNumPf;
    }

    public void setMdOrderNumPf(String mdOrderNumPf) {
        this.mdOrderNumPf = mdOrderNumPf;
    }

    @XmlElement(name = "erpOrderNumPf")
    public String getErpOrderNumPf() {
        return erpOrderNumPf;
    }

    public void setErpOrderNumPf(String erpOrderNumPf) {
        this.erpOrderNumPf = erpOrderNumPf;
    }

    @XmlElement(name = "orderStatusPf")
    public String getOrderStatusPf() {
        return orderStatusPf;
    }

    public void setOrderStatusPf(String orderStatusPf) {
        this.orderStatusPf = orderStatusPf;
    }

    @XmlElement(name = "orderCertNo")
    public String getOrderCertNo() {
        return orderCertNo;
    }

    public void setOrderCertNo(String orderCertNo) {
        this.orderCertNo = orderCertNo;
    }

    @XmlElement(name = "orderTypeCode")
    public String getOrderTypeCode() {
        return orderTypeCode;
    }

    public void setOrderTypeCode(String orderTypeCode) {
        this.orderTypeCode = orderTypeCode;
    }

    @XmlElement(name = "expFlag")
    public String getExpFlag() {
        return expFlag;
    }

    public void setExpFlag(String expFlag) {
        this.expFlag = expFlag;
    }

    @XmlElement(name = "orderPeriodPf")
    public String getOrderPeriodPf() {
        return orderPeriodPf;
    }

    public void setOrderPeriodPf(String orderPeriodPf) {
        this.orderPeriodPf = orderPeriodPf;
    }

    @XmlElement(name = "resoPeriodPf")
    public String getResoPeriodPf() {
        return resoPeriodPf;
    }

    public void setResoPeriodPf(String resoPeriodPf) {
        this.resoPeriodPf = resoPeriodPf;
    }

    @XmlElement(name = "saleNetworkPf")
    public String getSaleNetworkPf() {
        return saleNetworkPf;
    }

    public void setSaleNetworkPf(String saleNetworkPf) {
        this.saleNetworkPf = saleNetworkPf;
    }

    @XmlElement(name = "prodClassCode")
    public String getProdClassCode() {
        return prodClassCode;
    }

    public void setProdClassCode(String prodClassCode) {
        this.prodClassCode = prodClassCode;
    }

    @XmlElement(name = "companyCodePf")
    public String getCompanyCodePf() {
        return companyCodePf;
    }

    public void setCompanyCodePf(String companyCodePf) {
        this.companyCodePf = companyCodePf;
    }

    @XmlElement(name = "tradeMode")
    public String getTradeMode() {
        return tradeMode;
    }

    public void setTradeMode(String tradeMode) {
        this.tradeMode = tradeMode;
    }

    @XmlElement(name = "settleMode")
    public String getSettleMode() {
        return settleMode;
    }

    public void setSettleMode(String settleMode) {
        this.settleMode = settleMode;
    }

    @XmlElement(name = "signSitePf")
    public String getSignSitePf() {
        return signSitePf;
    }

    public void setSignSitePf(String signSitePf) {
        this.signSitePf = signSitePf;
    }

    @XmlElement(name = "priceFormulaMode")
    public String getPriceFormulaMode() {
        return priceFormulaMode;
    }

    public void setPriceFormulaMode(String priceFormulaMode) {
        this.priceFormulaMode = priceFormulaMode;
    }

    @XmlElement(name = "rmaNoPf")
    public String getRmaNoPf() {
        return rmaNoPf;
    }

    public void setRmaNoPf(String rmaNoPf) {
        this.rmaNoPf = rmaNoPf;
    }

    @XmlElement(name = "contInDate")
    public String getContInDate() {
        return contInDate;
    }

    public void setContInDate(String contInDate) {
        this.contInDate = contInDate;
    }

    @XmlElement(name = "contInPerson")
    public String getContInPerson() {
        return contInPerson;
    }

    public void setContInPerson(String contInPerson) {
        this.contInPerson = contInPerson;
    }

    @XmlElement(name = "orderModiMark")
    public String getOrderModiMark() {
        return orderModiMark;
    }

    public void setOrderModiMark(String orderModiMark) {
        this.orderModiMark = orderModiMark;
    }

    @XmlElement(name = "orderUserCodePf")
    public String getOrderUserCodePf() {
        return orderUserCodePf;
    }

    public void setOrderUserCodePf(String orderUserCodePf) {
        this.orderUserCodePf = orderUserCodePf;
    }

    @XmlElement(name = "orderUserCnamePf")
    public String getOrderUserCnamePf() {
        return orderUserCnamePf;
    }

    public void setOrderUserCnamePf(String orderUserCnamePf) {
        this.orderUserCnamePf = orderUserCnamePf;
    }

    @XmlElement(name = "orderUserAddrNum")
    public BigDecimal getOrderUserAddrNum() {
        return orderUserAddrNum;
    }

    public void setOrderUserAddrNum(BigDecimal orderUserAddrNum) {
        this.orderUserAddrNum = orderUserAddrNum;
    }

    @XmlElement(name = "orderUserAddrNamePf")
    public String getOrderUserAddrNamePf() {
        return orderUserAddrNamePf;
    }

    public void setOrderUserAddrNamePf(String orderUserAddrNamePf) {
        this.orderUserAddrNamePf = orderUserAddrNamePf;
    }

    @XmlElement(name = "settleUserNum")
    public String getSettleUserNum() {
        return settleUserNum;
    }

    public void setSettleUserNum(String settleUserNum) {
        this.settleUserNum = settleUserNum;
    }

    @XmlElement(name = "settleUserNamePf")
    public String getSettleUserNamePf() {
        return settleUserNamePf;
    }

    public void setSettleUserNamePf(String settleUserNamePf) {
        this.settleUserNamePf = settleUserNamePf;
    }

    @XmlElement(name = "settUserAddrNum")
    public BigDecimal getSettUserAddrNum() {
        return settUserAddrNum;
    }

    public void setSettUserAddrNum(BigDecimal settUserAddrNum) {
        this.settUserAddrNum = settUserAddrNum;
    }

    @XmlElement(name = "settUserAddrPf")
    public String getSettUserAddrPf() {
        return settUserAddrPf;
    }

    public void setSettUserAddrPf(String settUserAddrPf) {
        this.settUserAddrPf = settUserAddrPf;
    }

    @XmlElement(name = "settUserAcctNum")
    public BigDecimal getSettUserAcctNum() {
        return settUserAcctNum;
    }

    public void setSettUserAcctNum(BigDecimal settUserAcctNum) {
        this.settUserAcctNum = settUserAcctNum;
    }

    @XmlElement(name = "settUserAcctPf")
    public String getSettUserAcctPf() {
        return settUserAcctPf;
    }

    public void setSettUserAcctPf(String settUserAcctPf) {
        this.settUserAcctPf = settUserAcctPf;
    }

    @XmlElement(name = "contOrderNum")
    public BigDecimal getContOrderNum() {
        return contOrderNum;
    }

    public void setContOrderNum(BigDecimal contOrderNum) {
        this.contOrderNum = contOrderNum;
    }

    @XmlElement(name = "contTotWt")
    public BigDecimal getContTotWt() {
        return contTotWt;
    }

    public void setContTotWt(BigDecimal contTotWt) {
        this.contTotWt = contTotWt;
    }

    @XmlElement(name = "contTotAmt")
    public BigDecimal getContTotAmt() {
        return contTotAmt;
    }

    public void setContTotAmt(BigDecimal contTotAmt) {
        this.contTotAmt = contTotAmt;
    }

    @XmlElement(name = "contTrnpTotAmt")
    public BigDecimal getContTrnpTotAmt() {
        return contTrnpTotAmt;
    }

    public void setContTrnpTotAmt(BigDecimal contTrnpTotAmt) {
        this.contTrnpTotAmt = contTrnpTotAmt;
    }

    @XmlElement(name = "contDepositNum")
    public BigDecimal getContDepositNum() {
        return contDepositNum;
    }

    public void setContDepositNum(BigDecimal contDepositNum) {
        this.contDepositNum = contDepositNum;
    }

    @XmlElement(name = "contDepositAmt")
    public BigDecimal getContDepositAmt() {
        return contDepositAmt;
    }

    public void setContDepositAmt(BigDecimal contDepositAmt) {
        this.contDepositAmt = contDepositAmt;
    }

    @XmlElement(name = "contPrepayRate")
    public BigDecimal getContPrepayRate() {
        return contPrepayRate;
    }

    public void setContPrepayRate(BigDecimal contPrepayRate) {
        this.contPrepayRate = contPrepayRate;
    }

    @XmlElement(name = "contPrepayLackAmt")
    public BigDecimal getContPrepayLackAmt() {
        return contPrepayLackAmt;
    }

    public void setContPrepayLackAmt(BigDecimal contPrepayLackAmt) {
        this.contPrepayLackAmt = contPrepayLackAmt;
    }

    @XmlElement(name = "taxRatePf")
    public BigDecimal getTaxRatePf() {
        return taxRatePf;
    }

    public void setTaxRatePf(BigDecimal taxRatePf) {
        this.taxRatePf = taxRatePf;
    }

    @XmlElement(name = "orderNo")
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @XmlElement(name = "mdOrderNo")
    public String getMdOrderNo() {
        return mdOrderNo;
    }

    public void setMdOrderNo(String mdOrderNo) {
        this.mdOrderNo = mdOrderNo;
    }

    @XmlElement(name = "erpOrderNo")
    public String getErpOrderNo() {
        return erpOrderNo;
    }

    public void setErpOrderNo(String erpOrderNo) {
        this.erpOrderNo = erpOrderNo;
    }

    @XmlElement(name = "orderModiNum")
    public BigDecimal getOrderModiNum() {
        return orderModiNum;
    }

    public void setOrderModiNum(BigDecimal orderModiNum) {
        this.orderModiNum = orderModiNum;
    }

    @XmlElement(name = "orderLastSeqNo")
    public String getOrderLastSeqNo() {
        return orderLastSeqNo;
    }

    public void setOrderLastSeqNo(String orderLastSeqNo) {
        this.orderLastSeqNo = orderLastSeqNo;
    }

    @XmlElement(name = "saleId")
    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    @XmlElement(name = "recCreateTime")
    public String getRecCreateTime() {
        return recCreateTime;
    }

    public void setRecCreateTime(String recCreateTime) {
        this.recCreateTime = recCreateTime;
    }

    @XmlElement(name = "partOrderFlag")
    public String getPartOrderFlag() {
        return partOrderFlag;
    }

    public void setPartOrderFlag(String partOrderFlag) {
        this.partOrderFlag = partOrderFlag;
    }

    @XmlElement(name = "cutNum")
    public BigDecimal getCutNum() {
        return cutNum;
    }

    public void setCutNum(BigDecimal cutNum) {
        this.cutNum = cutNum;
    }

    @XmlElement(name = "orderItemStatusCode")
    public String getOrderItemStatusCode() {
        return orderItemStatusCode;
    }

    public void setOrderItemStatusCode(String orderItemStatusCode) {
        this.orderItemStatusCode = orderItemStatusCode;
    }

    @XmlElement(name = "orderItemModiMark")
    public String getOrderItemModiMark() {
        return orderItemModiMark;
    }

    public void setOrderItemModiMark(String orderItemModiMark) {
        this.orderItemModiMark = orderItemModiMark;
    }

    @XmlElement(name = "modiCause")
    public String getModiCause() {
        return modiCause;
    }

    public void setModiCause(String modiCause) {
        this.modiCause = modiCause;
    }

    @XmlElement(name = "modiDate")
    public String getModiDate() {
        return modiDate;
    }

    public void setModiDate(String modiDate) {
        this.modiDate = modiDate;
    }

    @XmlElement(name = "modiOperPerson")
    public String getModiOperPerson() {
        return modiOperPerson;
    }

    public void setModiOperPerson(String modiOperPerson) {
        this.modiOperPerson = modiOperPerson;
    }

    @XmlElement(name = "modiType")
    public String getModiType() {
        return modiType;
    }

    public void setModiType(String modiType) {
        this.modiType = modiType;
    }

    @XmlElement(name = "finUserNum")
    public String getFinUserNum() {
        return finUserNum;
    }

    public void setFinUserNum(String finUserNum) {
        this.finUserNum = finUserNum;
    }

    @XmlElement(name = "finUserName")
    public String getFinUserName() {
        return finUserName;
    }

    public void setFinUserName(String finUserName) {
        this.finUserName = finUserName;
    }

    @XmlElement(name = "finUserTrade")
    public String getFinUserTrade() {
        return finUserTrade;
    }

    public void setFinUserTrade(String finUserTrade) {
        this.finUserTrade = finUserTrade;
    }

    @XmlElement(name = "userCredit")
    public String getUserCredit() {
        return userCredit;
    }

    public void setUserCredit(String userCredit) {
        this.userCredit = userCredit;
    }

    @XmlElement(name = "consignNum")
    public String getConsignNum() {
        return consignNum;
    }

    public void setConsignNum(String consignNum) {
        this.consignNum = consignNum;
    }

    @XmlElement(name = "consignNamePf")
    public String getConsignNamePf() {
        return consignNamePf;
    }

    public void setConsignNamePf(String consignNamePf) {
        this.consignNamePf = consignNamePf;
    }

    @XmlElement(name = "cnsgAddressNum")
    public BigDecimal getCnsgAddressNum() {
        return cnsgAddressNum;
    }

    public void setCnsgAddressNum(BigDecimal cnsgAddressNum) {
        this.cnsgAddressNum = cnsgAddressNum;
    }

    @XmlElement(name = "cnsgAddressPf")
    public String getCnsgAddressPf() {
        return cnsgAddressPf;
    }

    public void setCnsgAddressPf(String cnsgAddressPf) {
        this.cnsgAddressPf = cnsgAddressPf;
    }

    @XmlElement(name = "consignNum1")
    public String getConsignNum1() {
        return consignNum1;
    }

    public void setConsignNum1(String consignNum1) {
        this.consignNum1 = consignNum1;
    }

    @XmlElement(name = "consignName1Pf")
    public String getConsignName1Pf() {
        return consignName1Pf;
    }

    public void setConsignName1Pf(String consignName1Pf) {
        this.consignName1Pf = consignName1Pf;
    }

    @XmlElement(name = "cnsgAddressNum1")
    public BigDecimal getCnsgAddressNum1() {
        return cnsgAddressNum1;
    }

    public void setCnsgAddressNum1(BigDecimal cnsgAddressNum1) {
        this.cnsgAddressNum1 = cnsgAddressNum1;
    }

    @XmlElement(name = "cnsgAddress1Pf")
    public String getCnsgAddress1Pf() {
        return cnsgAddress1Pf;
    }

    public void setCnsgAddress1Pf(String cnsgAddress1Pf) {
        this.cnsgAddress1Pf = cnsgAddress1Pf;
    }

    @XmlElement(name = "trnpTitleCode")
    public String getTrnpTitleCode() {
        return trnpTitleCode;
    }

    public void setTrnpTitleCode(String trnpTitleCode) {
        this.trnpTitleCode = trnpTitleCode;
    }

    @XmlElement(name = "trnpTitleText")
    public String getTrnpTitleText() {
        return trnpTitleText;
    }

    public void setTrnpTitleText(String trnpTitleText) {
        this.trnpTitleText = trnpTitleText;
    }

    @XmlElement(name = "psr")
    public String getPsr() {
        return psr;
    }

    public void setPsr(String psr) {
        this.psr = psr;
    }

    @XmlElement(name = "apn")
    public String getApn() {
        return apn;
    }

    public void setApn(String apn) {
        this.apn = apn;
    }

    @XmlElement(name = "msc")
    public String getMsc() {
        return msc;
    }

    public void setMsc(String msc) {
        this.msc = msc;
    }

    @XmlElement(name = "newProductFlag")
    public String getNewProductFlag() {
        return newProductFlag;
    }

    public void setNewProductFlag(String newProductFlag) {
        this.newProductFlag = newProductFlag;
    }

    @XmlElement(name = "productDscr")
    public String getProductDscr() {
        return productDscr;
    }

    public void setProductDscr(String productDscr) {
        this.productDscr = productDscr;
    }

    @XmlElement(name = "shopsignPf")
    public String getShopsignPf() {
        return shopsignPf;
    }

    public void setShopsignPf(String shopsignPf) {
        this.shopsignPf = shopsignPf;
    }

    @XmlElement(name = "steelStd")
    public String getSteelStd() {
        return steelStd;
    }

    public void setSteelStd(String steelStd) {
        this.steelStd = steelStd;
    }

    @XmlElement(name = "capabYnFlag")
    public String getCapabYnFlag() {
        return capabYnFlag;
    }

    public void setCapabYnFlag(String capabYnFlag) {
        this.capabYnFlag = capabYnFlag;
    }

    @XmlElement(name = "rainCoatFlag")
    public String getRainCoatFlag() {
        return rainCoatFlag;
    }

    public void setRainCoatFlag(String rainCoatFlag) {
        this.rainCoatFlag = rainCoatFlag;
    }

    @XmlElement(name = "qcType")
    public String getQcType() {
        return qcType;
    }

    public void setQcType(String qcType) {
        this.qcType = qcType;
    }

    @XmlElement(name = "certiNum")
    public BigDecimal getCertiNum() {
        return certiNum;
    }

    public void setCertiNum(BigDecimal certiNum) {
        this.certiNum = certiNum;
    }

    @XmlElement(name = "packingCode")
    public String getPackingCode() {
        return packingCode;
    }

    public void setPackingCode(String packingCode) {
        this.packingCode = packingCode;
    }

    @XmlElement(name = "orderPriority")
    public String getOrderPriority() {
        return orderPriority;
    }

    public void setOrderPriority(String orderPriority) {
        this.orderPriority = orderPriority;
    }

    @XmlElement(name = "metFeetFlag")
    public String getMetFeetFlag() {
        return metFeetFlag;
    }

    public void setMetFeetFlag(String metFeetFlag) {
        this.metFeetFlag = metFeetFlag;
    }

    @XmlElement(name = "orderThick")
    public BigDecimal getOrderThick() {
        return orderThick;
    }

    public void setOrderThick(BigDecimal orderThick) {
        this.orderThick = orderThick;
    }

    @XmlElement(name = "orderWidthPf")
    public BigDecimal getOrderWidthPf() {
        return orderWidthPf;
    }

    public void setOrderWidthPf(BigDecimal orderWidthPf) {
        this.orderWidthPf = orderWidthPf;
    }

    @XmlElement(name = "orderLenLow")
    public BigDecimal getOrderLenLow() {
        return orderLenLow;
    }

    public void setOrderLenLow(BigDecimal orderLenLow) {
        this.orderLenLow = orderLenLow;
    }

    @XmlElement(name = "orderLenUp")
    public BigDecimal getOrderLenUp() {
        return orderLenUp;
    }

    public void setOrderLenUp(BigDecimal orderLenUp) {
        this.orderLenUp = orderLenUp;
    }

    @XmlElement(name = "thickEng")
    public String getThickEng() {
        return thickEng;
    }

    public void setThickEng(String thickEng) {
        this.thickEng = thickEng;
    }

    @XmlElement(name = "widthEng")
    public String getWidthEng() {
        return widthEng;
    }

    public void setWidthEng(String widthEng) {
        this.widthEng = widthEng;
    }

    @XmlElement(name = "lengthMinEng")
    public String getLengthMinEng() {
        return lengthMinEng;
    }

    public void setLengthMinEng(String lengthMinEng) {
        this.lengthMinEng = lengthMinEng;
    }

    @XmlElement(name = "lengthMaxEng")
    public String getLengthMaxEng() {
        return lengthMaxEng;
    }

    public void setLengthMaxEng(String lengthMaxEng) {
        this.lengthMaxEng = lengthMaxEng;
    }

    @XmlElement(name = "weightMethod")
    public String getWeightMethod() {
        return weightMethod;
    }

    public void setWeightMethod(String weightMethod) {
        this.weightMethod = weightMethod;
    }

    @XmlElement(name = "orderUnit")
    public String getOrderUnit() {
        return orderUnit;
    }

    public void setOrderUnit(String orderUnit) {
        this.orderUnit = orderUnit;
    }

    @XmlElement(name = "orderWtPf")
    public BigDecimal getOrderWtPf() {
        return orderWtPf;
    }

    public void setOrderWtPf(BigDecimal orderWtPf) {
        this.orderWtPf = orderWtPf;
    }

    @XmlElement(name = "preAlcaWtPf")
    public BigDecimal getPreAlcaWtPf() {
        return preAlcaWtPf;
    }

    public void setPreAlcaWtPf(BigDecimal preAlcaWtPf) {
        this.preAlcaWtPf = preAlcaWtPf;
    }

    @XmlElement(name = "orderPiece")
    public BigDecimal getOrderPiece() {
        return orderPiece;
    }

    public void setOrderPiece(BigDecimal orderPiece) {
        this.orderPiece = orderPiece;
    }

    @XmlElement(name = "unitWtUpPf")
    public BigDecimal getUnitWtUpPf() {
        return unitWtUpPf;
    }

    public void setUnitWtUpPf(BigDecimal unitWtUpPf) {
        this.unitWtUpPf = unitWtUpPf;
    }

    @XmlElement(name = "unitWtLowPf")
    public BigDecimal getUnitWtLowPf() {
        return unitWtLowPf;
    }

    public void setUnitWtLowPf(BigDecimal unitWtLowPf) {
        this.unitWtLowPf = unitWtLowPf;
    }


    @XmlElement(name = "delivyTolUpper")
    public BigDecimal getDelivyTolUpper() {
        return delivyTolUpper;
    }

    public void setDelivyTolUpper(BigDecimal delivyTolUpper) {
        this.delivyTolUpper = delivyTolUpper;
    }

    @XmlElement(name = "delivyTolLower")
    public BigDecimal getDelivyTolLower() {
        return delivyTolLower;
    }

    public void setDelivyTolLower(BigDecimal delivyTolLower) {
        this.delivyTolLower = delivyTolLower;
    }

    @XmlElement(name = "coilInsideDimPf")
    public BigDecimal getCoilInsideDimPf() {
        return coilInsideDimPf;
    }

    public void setCoilInsideDimPf(BigDecimal coilInsideDimPf) {
        this.coilInsideDimPf = coilInsideDimPf;
    }

    @XmlElement(name = "shortSizeRatePf")
    public BigDecimal getShortSizeRatePf() {
        return shortSizeRatePf;
    }

    public void setShortSizeRatePf(BigDecimal shortSizeRatePf) {
        this.shortSizeRatePf = shortSizeRatePf;
    }

    @XmlElement(name = "shortSizeLowPf")
    public BigDecimal getShortSizeLowPf() {
        return shortSizeLowPf;
    }

    public void setShortSizeLowPf(BigDecimal shortSizeLowPf) {
        this.shortSizeLowPf = shortSizeLowPf;
    }

    @XmlElement(name = "shortSizeUpPf")
    public BigDecimal getShortSizeUpPf() {
        return shortSizeUpPf;
    }

    public void setShortSizeUpPf(BigDecimal shortSizeUpPf) {
        this.shortSizeUpPf = shortSizeUpPf;
    }

    @XmlElement(name = "shortSize1")
    public BigDecimal getShortSize1() {
        return shortSize1;
    }

    public void setShortSize1(BigDecimal shortSize1) {
        this.shortSize1 = shortSize1;
    }

    @XmlElement(name = "shortSize2")
    public BigDecimal getShortSize2() {
        return shortSize2;
    }

    public void setShortSize2(BigDecimal shortSize2) {
        this.shortSize2 = shortSize2;
    }

    @XmlElement(name = "shortSize3")
    public BigDecimal getShortSize3() {
        return shortSize3;
    }

    public void setShortSize3(BigDecimal shortSize3) {
        this.shortSize3 = shortSize3;
    }

    @XmlElement(name = "shortSize4")
    public BigDecimal getShortSize4() {
        return shortSize4;
    }

    public void setShortSize4(BigDecimal shortSize4) {
        this.shortSize4 = shortSize4;
    }

    @XmlElement(name = "shortSize5")
    public BigDecimal getShortSize5() {
        return shortSize5;
    }

    public void setShortSize5(BigDecimal shortSize5) {
        this.shortSize5 = shortSize5;
    }

    @XmlElement(name = "deliveryDateChr")
    public String getDeliveryDateChr() {
        return deliveryDateChr;
    }

    public void setDeliveryDateChr(String deliveryDateChr) {
        this.deliveryDateChr = deliveryDateChr;
    }

    @XmlElement(name = "delivyPeriodPf")
    public String getDelivyPeriodPf() {
        return delivyPeriodPf;
    }

    public void setDelivyPeriodPf(String delivyPeriodPf) {
        this.delivyPeriodPf = delivyPeriodPf;
    }

    @XmlElement(name = "deliveryWeekMark")
    public String getDeliveryWeekMark() {
        return deliveryWeekMark;
    }

    public void setDeliveryWeekMark(String deliveryWeekMark) {
        this.deliveryWeekMark = deliveryWeekMark;
    }

    @XmlElement(name = "orderReadyDate")
    public String getOrderReadyDate() {
        return orderReadyDate;
    }

    public void setOrderReadyDate(String orderReadyDate) {
        this.orderReadyDate = orderReadyDate;
    }

    @XmlElement(name = "launchTime")
    public String getLaunchTime() {
        return launchTime;
    }

    public void setLaunchTime(String launchTime) {
        this.launchTime = launchTime;
    }

    @XmlElement(name = "launchId")
    public String getLaunchId() {
        return launchId;
    }

    public void setLaunchId(String launchId) {
        this.launchId = launchId;
    }

    @XmlElement(name = "launchFirstTime")
    public String getLaunchFirstTime() {
        return launchFirstTime;
    }

    public void setLaunchFirstTime(String launchFirstTime) {
        this.launchFirstTime = launchFirstTime;
    }

    @XmlElement(name = "launchFirstId")
    public String getLaunchFirstId() {
        return launchFirstId;
    }

    public void setLaunchFirstId(String launchFirstId) {
        this.launchFirstId = launchFirstId;
    }

    @XmlElement(name = "versionNumPf")
    public BigDecimal getVersionNumPf() {
        return versionNumPf;
    }

    public void setVersionNumPf(BigDecimal versionNumPf) {
        this.versionNumPf = versionNumPf;
    }

    @XmlElement(name = "orderPriceMode")
    public String getOrderPriceMode() {
        return orderPriceMode;
    }

    public void setOrderPriceMode(String orderPriceMode) {
        this.orderPriceMode = orderPriceMode;
    }

    @XmlElement(name = "priceBaseDate")
    public String getPriceBaseDate() {
        return priceBaseDate;
    }

    public void setPriceBaseDate(String priceBaseDate) {
        this.priceBaseDate = priceBaseDate;
    }

    @XmlElement(name = "saleUnitPrice")
    public BigDecimal getSaleUnitPrice() {
        return saleUnitPrice;
    }

    public void setSaleUnitPrice(BigDecimal saleUnitPrice) {
        this.saleUnitPrice = saleUnitPrice;
    }

    @XmlElement(name = "agreemtCode")
    public String getAgreemtCode() {
        return agreemtCode;
    }

    public void setAgreemtCode(String agreemtCode) {
        this.agreemtCode = agreemtCode;
    }

    @XmlElement(name = "agreePrice")
    public BigDecimal getAgreePrice() {
        return agreePrice;
    }

    public void setAgreePrice(BigDecimal agreePrice) {
        this.agreePrice = agreePrice;
    }

    @XmlElement(name = "orderAmtPf")
    public BigDecimal getOrderAmtPf() {
        return orderAmtPf;
    }

    public void setOrderAmtPf(BigDecimal orderAmtPf) {
        this.orderAmtPf = orderAmtPf;
    }

    @XmlElement(name = "saleRemark")
    public String getSaleRemark() {
        return saleRemark;
    }

    public void setSaleRemark(String saleRemark) {
        this.saleRemark = saleRemark;
    }

    @XmlElement(name = "a_flag")
    public String getA_flag() {
        return a_flag;
    }

    public void setA_flag(String a_flag) {
        this.a_flag = a_flag;
    }

    @XmlElement(name = "userSpecDesc")
    public String getUserSpecDesc() {
        return userSpecDesc;
    }

    public void setUserSpecDesc(String userSpecDesc) {
        this.userSpecDesc = userSpecDesc;
    }

    @XmlElement(name = "drewMode")
    public String getDrewMode() {
        return drewMode;
    }

    public void setDrewMode(String drewMode) {
        this.drewMode = drewMode;
    }

    @XmlElement(name = "depositN")
    public BigDecimal getDepositN() {
        return depositN;
    }

    public void setDepositN(BigDecimal depositN) {
        this.depositN = depositN;
    }

    @XmlElement(name = "depositAmtPf")
    public BigDecimal getDepositAmtPf() {
        return depositAmtPf;
    }

    public void setDepositAmtPf(BigDecimal depositAmtPf) {
        this.depositAmtPf = depositAmtPf;
    }

    @XmlElement(name = "transType")
    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    @XmlElement(name = "trnpModeCodePf")
    public String getTrnpModeCodePf() {
        return trnpModeCodePf;
    }

    public void setTrnpModeCodePf(String trnpModeCodePf) {
        this.trnpModeCodePf = trnpModeCodePf;
    }

    @XmlElement(name = "deliveryPlaceCodePf")
    public String getDeliveryPlaceCodePf() {
        return deliveryPlaceCodePf;
    }

    public void setDeliveryPlaceCodePf(String deliveryPlaceCodePf) {
        this.deliveryPlaceCodePf = deliveryPlaceCodePf;
    }

    @XmlElement(name = "deliveryPlaceName")
    public String getDeliveryPlaceName() {
        return deliveryPlaceName;
    }

    public void setDeliveryPlaceName(String deliveryPlaceName) {
        this.deliveryPlaceName = deliveryPlaceName;
    }

    @XmlElement(name = "deliveryPlaceCode1Pf")
    public String getDeliveryPlaceCode1Pf() {
        return deliveryPlaceCode1Pf;
    }

    public void setDeliveryPlaceCode1Pf(String deliveryPlaceCode1Pf) {
        this.deliveryPlaceCode1Pf = deliveryPlaceCode1Pf;
    }

    @XmlElement(name = "deliveryPlaceName1")
    public String getDeliveryPlaceName1() {
        return deliveryPlaceName1;
    }

    public void setDeliveryPlaceName1(String deliveryPlaceName1) {
        this.deliveryPlaceName1 = deliveryPlaceName1;
    }

    @XmlElement(name = "privateRouteCodePf")
    public String getPrivateRouteCodePf() {
        return privateRouteCodePf;
    }

    public void setPrivateRouteCodePf(String privateRouteCodePf) {
        this.privateRouteCodePf = privateRouteCodePf;
    }

    @XmlElement(name = "privateRouteNamePf")
    public String getPrivateRouteNamePf() {
        return privateRouteNamePf;
    }

    public void setPrivateRouteNamePf(String privateRouteNamePf) {
        this.privateRouteNamePf = privateRouteNamePf;
    }

    @XmlElement(name = "trnpPricePf")
    public BigDecimal getTrnpPricePf() {
        return trnpPricePf;
    }

    public void setTrnpPricePf(BigDecimal trnpPricePf) {
        this.trnpPricePf = trnpPricePf;
    }

    @XmlElement(name = "trnpAmtPf")
    public BigDecimal getTrnpAmtPf() {
        return trnpAmtPf;
    }

    public void setTrnpAmtPf(BigDecimal trnpAmtPf) {
        this.trnpAmtPf = trnpAmtPf;
    }

    @XmlElement(name = "guideMeasureAq")
    public String getGuideMeasureAq() {
        return guideMeasureAq;
    }

    public void setGuideMeasureAq(String guideMeasureAq) {
        this.guideMeasureAq = guideMeasureAq;
    }

    @XmlElement(name = "edgeType")
    public String getEdgeType() {
        return edgeType;
    }

    public void setEdgeType(String edgeType) {
        this.edgeType = edgeType;
    }

    @XmlElement(name = "orderTechnicDes")
    public String getOrderTechnicDes() {
        return orderTechnicDes;
    }

    public void setOrderTechnicDes(String orderTechnicDes) {
        this.orderTechnicDes = orderTechnicDes;
    }

    @XmlElement(name = "orderByprodDegreeCode")
    public String getOrderByprodDegreeCode() {
        return orderByprodDegreeCode;
    }

    public void setOrderByprodDegreeCode(String orderByprodDegreeCode) {
        this.orderByprodDegreeCode = orderByprodDegreeCode;
    }

    @XmlElement(name = "weightPlate")
    public String getWeightPlate() {
        return weightPlate;
    }

    public void setWeightPlate(String weightPlate) {
        this.weightPlate = weightPlate;
    }

    @XmlElement(name = "grainCode")
    public String getGrainCode() {
        return grainCode;
    }

    public void setGrainCode(String grainCode) {
        this.grainCode = grainCode;
    }

    @XmlElement(name = "patternSalient")
    public BigDecimal getPatternSalient() {
        return patternSalient;
    }

    public void setPatternSalient(BigDecimal patternSalient) {
        this.patternSalient = patternSalient;
    }

    @XmlElement(name = "sectionCode")
    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    @XmlElement(name = "sectionDesc")
    public String getSectionDesc() {
        return sectionDesc;
    }

    public void setSectionDesc(String sectionDesc) {
        this.sectionDesc = sectionDesc;
    }

    @XmlElement(name = "recognCode")
    public String getRecognCode() {
        return recognCode;
    }

    public void setRecognCode(String recognCode) {
        this.recognCode = recognCode;
    }

    @XmlElement(name = "endLineCode")
    public String getEndLineCode() {
        return endLineCode;
    }

    public void setEndLineCode(String endLineCode) {
        this.endLineCode = endLineCode;
    }

    @XmlElement(name = "endLineName")
    public String getEndLineName() {
        return endLineName;
    }

    public void setEndLineName(String endLineName) {
        this.endLineName = endLineName;
    }

    @XmlElement(name = "transProdCode")
    public String getTransProdCode() {
        return transProdCode;
    }

    public void setTransProdCode(String transProdCode) {
        this.transProdCode = transProdCode;
    }

    @XmlElement(name = "unloadPoint")
    public String getUnloadPoint() {
        return unloadPoint;
    }

    public void setUnloadPoint(String unloadPoint) {
        this.unloadPoint = unloadPoint;
    }

    @XmlElement(name = "priceMode")
    public String getPriceMode() {
        return priceMode;
    }

    public void setPriceMode(String priceMode) {
        this.priceMode = priceMode;
    }

    @XmlElement(name = "prodCode")
    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    @XmlElement(name = "straragemMark")
    public String getStraragemMark() {
        return straragemMark;
    }

    public void setStraragemMark(String straragemMark) {
        this.straragemMark = straragemMark;
    }

    @XmlElement(name = "priceModeCname")
    public String getPriceModeCname() {
        return priceModeCname;
    }

    public void setPriceModeCname(String priceModeCname) {
        this.priceModeCname = priceModeCname;
    }

    @XmlElement(name = "prodCodeCname")
    public String getProdCodeCname() {
        return prodCodeCname;
    }

    public void setProdCodeCname(String prodCodeCname) {
        this.prodCodeCname = prodCodeCname;
    }

    @XmlElement(name = "straragemMarkCname")
    public String getStraragemMarkCname() {
        return straragemMarkCname;
    }

    public void setStraragemMarkCname(String straragemMarkCname) {
        this.straragemMarkCname = straragemMarkCname;
    }

    @XmlElement(name = "invoiceType")
    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    @XmlElement(name = "invoiceTypeName")
    public String getInvoiceTypeName() {
        return invoiceTypeName;
    }

    public void setInvoiceTypeName(String invoiceTypeName) {
        this.invoiceTypeName = invoiceTypeName;
    }

    @XmlElement(name = "userChineseName")
    public String getUserChineseName() {
        return userChineseName;
    }

    public void setUserChineseName(String userChineseName) {
        this.userChineseName = userChineseName;
    }

    @XmlElement(name = "taxNum")
    public String getTaxNum() {
        return taxNum;
    }

    public void setTaxNum(String taxNum) {
        this.taxNum = taxNum;
    }

    @XmlElement(name = "addressLocationc")
    public String getAddressLocationc() {
        return addressLocationc;
    }

    public void setAddressLocationc(String addressLocationc) {
        this.addressLocationc = addressLocationc;
    }

    @XmlElement(name = "bankBranchName")
    public String getBankBranchName() {
        return bankBranchName;
    }

    public void setBankBranchName(String bankBranchName) {
        this.bankBranchName = bankBranchName;
    }

    @XmlElement(name = "accountNum")
    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    @XmlElement(name = "telNum")
    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }


}

