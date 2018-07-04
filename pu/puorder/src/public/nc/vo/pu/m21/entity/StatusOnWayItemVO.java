package nc.vo.pu.m21.entity;

import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单在途状态VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-4-14 上午08:37:01
 */
public class StatusOnWayItemVO extends SuperVO {

  /** 承运商 */
  public static final String CCARRIER = "ccarrier";

  /** 货柜号 */
  public static final String CCASECODE = "ccasecode";

  /** 到货港口 */
  public static final String CLANDPORT = "clandport";

  /** 装船港口 */
  public static final String CLOADPORT = "cloadport";

  /** 船次号 */
  public static final String CSHIPLINE = "cshipline";

  /** 船名 */
  public static final String CSHIPNAME = "cshipname";

  /** 单据日期 */
  public static final String DBILLDATE = "dbilldate";

  /** 计划到货日期 */
  public static final String DPLANARRVDATE = "dplanarrvdate";

  /** 计划到港日期 */
  public static final String DPLANFREIGHTDATE = "dplanfreightdate";

  /** dr */
  public static final String DR = "dr";

  /** 在途状态 */
  public static final String FONWAYSTATUS = "fonwaystatus";

  /** 是否已操作 */
  public static final String ISOPERATED = "isoperated";

  /** 最大可操作数量 */
  public static final String NMAXHANDLENUM = "nmaxhandlenum";

  /** 在途数量 */
  public static final String NONWAYNUM = "nonwaynum";

  /** 所属集团 */
  public static final String PK_GROUP = "pk_group";

  /** 采购订单 */
  public static final String PK_ORDER = "pk_order";

  /** 在途状态子子表 */
  public static final String PK_ORDER_B = "pk_order_b";

  /** 采购订单在途状态 */
  public static final String PK_ORDER_BB = "pk_order_bb";

  /** 采购组织版本信息 */
  public static final String PK_ORG = "pk_org";

  /** 采购组织 */
  public static final String PK_ORG_V = "pk_org_v";

  /** ts */
  public static final String TS = "ts";

  /** 自定义项1 */
  public static final String VBDEF1 = "vbdef1";

  /** 自定义项10 */
  public static final String VBDEF10 = "vbdef10";

  /** 自定义项11 */
  public static final String VBDEF11 = "vbdef11";

  /** 自定义项12 */
  public static final String VBDEF12 = "vbdef12";

  /** 自定义项13 */
  public static final String VBDEF13 = "vbdef13";

  /** 自定义项14 */
  public static final String VBDEF14 = "vbdef14";

  /** 自定义项15 */
  public static final String VBDEF15 = "vbdef15";

  /** 自定义项16 */
  public static final String VBDEF16 = "vbdef16";

  /** 自定义项17 */
  public static final String VBDEF17 = "vbdef17";

  /** 自定义项18 */
  public static final String VBDEF18 = "vbdef18";

  /** 自定义项19 */
  public static final String VBDEF19 = "vbdef19";

  /** 自定义项2 */
  public static final String VBDEF2 = "vbdef2";

  /** 自定义项20 */
  public static final String VBDEF20 = "vbdef20";

  /** 自定义项21 */
  public static final String VBDEF21 = "vbdef21";

  /** 自定义项22 */
  public static final String VBDEF22 = "vbdef22";

  /** 自定义项23 */
  public static final String VBDEF23 = "vbdef23";

  /** 自定义项24 */
  public static final String VBDEF24 = "vbdef24";

  /** 自定义项25 */
  public static final String VBDEF25 = "vbdef25";

  /** 自定义项26 */
  public static final String VBDEF26 = "vbdef26";

  /** 自定义项27 */
  public static final String VBDEF27 = "vbdef27";

  /** 自定义项28 */
  public static final String VBDEF28 = "vbdef28";

  /** 自定义项29 */
  public static final String VBDEF29 = "vbdef29";

  /** 自定义项3 */
  public static final String VBDEF3 = "vbdef3";

  /** 自定义项30 */
  public static final String VBDEF30 = "vbdef30";

  /** 自定义项31 */
  public static final String VBDEF31 = "vbdef31";

  /** 自定义项32 */
  public static final String VBDEF32 = "vbdef32";

  /** 自定义项33 */
  public static final String VBDEF33 = "vbdef33";

  /** 自定义项34 */
  public static final String VBDEF34 = "vbdef34";

  /** 自定义项35 */
  public static final String VBDEF35 = "vbdef35";

  /** 自定义项36 */
  public static final String VBDEF36 = "vbdef36";

  /** 自定义项37 */
  public static final String VBDEF37 = "vbdef37";

  /** 自定义项38 */
  public static final String VBDEF38 = "vbdef38";

  /** 自定义项39 */
  public static final String VBDEF39 = "vbdef39";

  /** 自定义项4 */
  public static final String VBDEF4 = "vbdef4";

  /** 自定义项40 */
  public static final String VBDEF40 = "vbdef40";

  /** 自定义项5 */
  public static final String VBDEF5 = "vbdef5";

  /** 自定义项6 */
  public static final String VBDEF6 = "vbdef6";

  /** 自定义项7 */
  public static final String VBDEF7 = "vbdef7";

  /** 自定义项8 */
  public static final String VBDEF8 = "vbdef8";

  /** 自定义项9 */
  public static final String VBDEF9 = "vbdef9";

  /** 单据编号 */
  public static final String VBILLCODE = "vbillcode";

  /** 表头自定义项1 */
  public static final String VHDEF1 = "vhdef1";

  /** 表头自定义项10 */
  public static final String VHDEF10 = "vhdef10";

  /** 表头自定义项11 */
  public static final String VHDEF11 = "vhdef11";

  /** 表头自定义项12 */
  public static final String VHDEF12 = "vhdef12";

  /** 表头自定义项13 */
  public static final String VHDEF13 = "vhdef13";

  /** 表头自定义项14 */
  public static final String VHDEF14 = "vhdef14";

  /** 表头自定义项15 */
  public static final String VHDEF15 = "vhdef15";

  /** 表头自定义项16 */
  public static final String VHDEF16 = "vhdef16";

  /** 表头自定义项17 */
  public static final String VHDEF17 = "vhdef17";

  /** 表头自定义项18 */
  public static final String VHDEF18 = "vhdef18";

  /** 表头自定义项19 */
  public static final String VHDEF19 = "vhdef19";

  /** 表头自定义项2 */
  public static final String VHDEF2 = "vhdef2";

  /** 表头自定义项20 */
  public static final String VHDEF20 = "vhdef20";

  /** 表头自定义项21 */
  public static final String VHDEF21 = "vhdef21";

  /** 表头自定义项22 */
  public static final String VHDEF22 = "vhdef22";

  /** 表头自定义项23 */
  public static final String VHDEF23 = "vhdef23";

  /** 表头自定义项24 */
  public static final String VHDEF24 = "vhdef24";

  /** 表头自定义项25 */
  public static final String VHDEF25 = "vhdef25";

  /** 表头自定义项26 */
  public static final String VHDEF26 = "vhdef26";

  /** 表头自定义项27 */
  public static final String VHDEF27 = "vhdef27";

  /** 表头自定义项28 */
  public static final String VHDEF28 = "vhdef28";

  /** 表头自定义项29 */
  public static final String VHDEF29 = "vhdef29";

  /** 表头自定义项3 */
  public static final String VHDEF3 = "vhdef3";

  /** 表头自定义项30 */
  public static final String VHDEF30 = "vhdef30";

  /** 表头自定义项31 */
  public static final String VHDEF31 = "vhdef31";

  /** 表头自定义项32 */
  public static final String VHDEF32 = "vhdef32";

  /** 表头自定义项33 */
  public static final String VHDEF33 = "vhdef33";

  /** 表头自定义项34 */
  public static final String VHDEF34 = "vhdef34";

  /** 表头自定义项35 */
  public static final String VHDEF35 = "vhdef35";

  /** 表头自定义项36 */
  public static final String VHDEF36 = "vhdef36";

  /** 表头自定义项37 */
  public static final String VHDEF37 = "vhdef37";

  /** 表头自定义项38 */
  public static final String VHDEF38 = "vhdef38";

  /** 表头自定义项39 */
  public static final String VHDEF39 = "vhdef39";

  /** 表头自定义项4 */
  public static final String VHDEF4 = "vhdef4";

  /** 表头自定义项40 */
  public static final String VHDEF40 = "vhdef40";

  /** 表头自定义项5 */
  public static final String VHDEF5 = "vhdef5";

  /** 表头自定义项6 */
  public static final String VHDEF6 = "vhdef6";

  /** 表头自定义项7 */
  public static final String VHDEF7 = "vhdef7";

  /** 表头自定义项8 */
  public static final String VHDEF8 = "vhdef8";

  /** 表头自定义项9 */
  public static final String VHDEF9 = "vhdef9";

  /** 对方订单行号 */
  public static final String VVENDORORDERROW = "vvendororderrow";

  private static final long serialVersionUID = -7350946154634335719L;

  /** 承运商 getter 方法 */
  public String getCcarrier() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.CCARRIER);
  }

  /** 货柜号 getter 方法 */
  public String getCcasecode() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.CCASECODE);
  }

  /** 到货港口 getter 方法 */
  public String getClandport() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.CLANDPORT);
  }

  /** 装船港口 getter 方法 */
  public String getCloadport() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.CLOADPORT);
  }

  /** 船次号 getter 方法 */
  public String getCshipline() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.CSHIPLINE);
  }

  /** 船名 getter 方法 */
  public String getCshipname() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.CSHIPNAME);
  }

  /** 单据日期 getter 方法 */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(StatusOnWayItemVO.DBILLDATE);
  }

  /** 计划到货日期 getter 方法 */
  public UFDate getDplanarrvdate() {
    return (UFDate) this.getAttributeValue(StatusOnWayItemVO.DPLANARRVDATE);
  }

  /** 计划到港日期 getter 方法 */
  public UFDate getDplanfreightdate() {
    return (UFDate) this.getAttributeValue(StatusOnWayItemVO.DPLANFREIGHTDATE);
  }

  /** dr getter 方法 */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(StatusOnWayItemVO.DR);
  }

  /** 在途状态 getter 方法 */
  public Integer getFonwaystatus() {
    return (Integer) this.getAttributeValue(StatusOnWayItemVO.FONWAYSTATUS);
  }

  /** 是否已操作 getter 方法 */
  public UFBoolean getIsoperated() {
    return (UFBoolean) this.getAttributeValue(StatusOnWayItemVO.ISOPERATED);
  }

  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta(PUEntity.M21_BB);
  }

  /** 最大可操作数量 getter 方法 */
  public UFDouble getNmaxhandlenum() {
    return (UFDouble) this.getAttributeValue(StatusOnWayItemVO.NMAXHANDLENUM);
  }

  /** 在途数量 getter 方法 */
  public UFDouble getNonwaynum() {
    return (UFDouble) this.getAttributeValue(StatusOnWayItemVO.NONWAYNUM);
  }

  /** 所属集团 getter 方法 */
  public String getPk_group() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.PK_GROUP);
  }

  /** 采购订单 getter 方法 */
  public String getPk_order() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.PK_ORDER);
  }

  /** 在途状态子子表 getter 方法 */
  public String getPk_order_b() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.PK_ORDER_B);
  }

  /** 采购订单在途状态 getter 方法 */
  public String getPk_order_bb() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.PK_ORDER_BB);
  }

  /** 采购组织版本信息 getter 方法 */
  public String getPk_org() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.PK_ORG);
  }

  /** 采购组织 getter 方法 */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.PK_ORG_V);
  }

  /** ts getter 方法 */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(StatusOnWayItemVO.TS);
  }

  /** 自定义项1 getter 方法 */
  public String getVbdef1() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF1);
  }

  /** 自定义项10 getter 方法 */
  public String getVbdef10() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF10);
  }

  /** 自定义项11 getter 方法 */
  public String getVbdef11() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF11);
  }

  /** 自定义项12 getter 方法 */
  public String getVbdef12() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF12);
  }

  /** 自定义项13 getter 方法 */
  public String getVbdef13() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF13);
  }

  /** 自定义项14 getter 方法 */
  public String getVbdef14() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF14);
  }

  /** 自定义项15 getter 方法 */
  public String getVbdef15() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF15);
  }

  /** 自定义项16 getter 方法 */
  public String getVbdef16() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF16);
  }

  /** 自定义项17 getter 方法 */
  public String getVbdef17() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF17);
  }

  /** 自定义项18 getter 方法 */
  public String getVbdef18() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF18);
  }

  /** 自定义项19 getter 方法 */
  public String getVbdef19() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF19);
  }

  /** 自定义项2 getter 方法 */
  public String getVbdef2() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF2);
  }

  /** 自定义项20 getter 方法 */
  public String getVbdef20() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF20);
  }

  /** 自定义项21 getter 方法 */
  public String getVbdef21() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF21);
  }

  /** 自定义项22 getter 方法 */
  public String getVbdef22() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF22);
  }

  /** 自定义项23 getter 方法 */
  public String getVbdef23() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF23);
  }

  /** 自定义项24 getter 方法 */
  public String getVbdef24() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF24);
  }

  /** 自定义项25 getter 方法 */
  public String getVbdef25() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF25);
  }

  /** 自定义项26 getter 方法 */
  public String getVbdef26() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF26);
  }

  /** 自定义项27 getter 方法 */
  public String getVbdef27() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF27);
  }

  /** 自定义项28 getter 方法 */
  public String getVbdef28() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF28);
  }

  /** 自定义项29 getter 方法 */
  public String getVbdef29() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF29);
  }

  /** 自定义项3 getter 方法 */
  public String getVbdef3() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF3);
  }

  /** 自定义项30 getter 方法 */
  public String getVbdef30() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF30);
  }

  /** 自定义项31 getter 方法 */
  public String getVbdef31() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF31);
  }

  /** 自定义项32 getter 方法 */
  public String getVbdef32() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF32);
  }

  /** 自定义项33 getter 方法 */
  public String getVbdef33() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF33);
  }

  /** 自定义项34 getter 方法 */
  public String getVbdef34() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF34);
  }

  /** 自定义项35 getter 方法 */
  public String getVbdef35() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF35);
  }

  /** 自定义项36 getter 方法 */
  public String getVbdef36() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF36);
  }

  /** 自定义项37 getter 方法 */
  public String getVbdef37() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF37);
  }

  /** 自定义项38 getter 方法 */
  public String getVbdef38() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF38);
  }

  /** 自定义项39 getter 方法 */
  public String getVbdef39() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF39);
  }

  /** 自定义项4 getter 方法 */
  public String getVbdef4() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF4);
  }

  /** 自定义项40 getter 方法 */
  public String getVbdef40() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF40);
  }

  /** 自定义项5 getter 方法 */
  public String getVbdef5() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF5);
  }

  /** 自定义项6 getter 方法 */
  public String getVbdef6() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF6);
  }

  /** 自定义项7 getter 方法 */
  public String getVbdef7() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF7);
  }

  /** 自定义项8 getter 方法 */
  public String getVbdef8() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF8);
  }

  /** 自定义项9 getter 方法 */
  public String getVbdef9() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBDEF9);
  }

  /** 单据编号 getter 方法 */
  public String getVbillcode() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VBILLCODE);
  }

  /** 表头自定义项1 getter 方法 */
  public String getVhdef1() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF1);
  }

  /** 表头自定义项10 getter 方法 */
  public String getVhdef10() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF10);
  }

  /** 表头自定义项11 getter 方法 */
  public String getVhdef11() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF11);
  }

  /** 表头自定义项12 getter 方法 */
  public String getVhdef12() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF12);
  }

  /** 表头自定义项13 getter 方法 */
  public String getVhdef13() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF13);
  }

  /** 表头自定义项14 getter 方法 */
  public String getVhdef14() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF14);
  }

  /** 表头自定义项15 getter 方法 */
  public String getVhdef15() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF15);
  }

  /** 表头自定义项16 getter 方法 */
  public String getVhdef16() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF16);
  }

  /** 表头自定义项17 getter 方法 */
  public String getVhdef17() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF17);
  }

  /** 表头自定义项18 getter 方法 */
  public String getVhdef18() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF18);
  }

  /** 表头自定义项19 getter 方法 */
  public String getVhdef19() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF19);
  }

  /** 表头自定义项2 getter 方法 */
  public String getVhdef2() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF2);
  }

  /** 表头自定义项20 getter 方法 */
  public String getVhdef20() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF20);
  }

  /** 表头自定义项21 getter 方法 */
  public String getVhdef21() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF21);
  }

  /** 表头自定义项22 getter 方法 */
  public String getVhdef22() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF22);
  }

  /** 表头自定义项23 getter 方法 */
  public String getVhdef23() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF23);
  }

  /** 表头自定义项24 getter 方法 */
  public String getVhdef24() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF24);
  }

  /** 表头自定义项25 getter 方法 */
  public String getVhdef25() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF25);
  }

  /** 表头自定义项26 getter 方法 */
  public String getVhdef26() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF26);
  }

  /** 表头自定义项27 getter 方法 */
  public String getVhdef27() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF27);
  }

  /** 表头自定义项28 getter 方法 */
  public String getVhdef28() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF28);
  }

  /** 表头自定义项29 getter 方法 */
  public String getVhdef29() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF29);
  }

  /** 表头自定义项3 getter 方法 */
  public String getVhdef3() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF3);
  }

  /** 表头自定义项30 getter 方法 */
  public String getVhdef30() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF30);
  }

  /** 表头自定义项31 getter 方法 */
  public String getVhdef31() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF31);
  }

  /** 表头自定义项32 getter 方法 */
  public String getVhdef32() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF32);
  }

  /** 表头自定义项33 getter 方法 */
  public String getVhdef33() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF33);
  }

  /** 表头自定义项34 getter 方法 */
  public String getVhdef34() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF34);
  }

  /** 表头自定义项35 getter 方法 */
  public String getVhdef35() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF35);
  }

  /** 表头自定义项36 getter 方法 */
  public String getVhdef36() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF36);
  }

  /** 表头自定义项37 getter 方法 */
  public String getVhdef37() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF37);
  }

  /** 表头自定义项38 getter 方法 */
  public String getVhdef38() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF38);
  }

  /** 表头自定义项39 getter 方法 */
  public String getVhdef39() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF39);
  }

  /** 表头自定义项4 getter 方法 */
  public String getVhdef4() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF4);
  }

  /** 表头自定义项40 getter 方法 */
  public String getVhdef40() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF40);
  }

  /** 表头自定义项5 getter 方法 */
  public String getVhdef5() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF5);
  }

  /** 表头自定义项6 getter 方法 */
  public String getVhdef6() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF6);
  }

  /** 表头自定义项7 getter 方法 */
  public String getVhdef7() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF7);
  }

  /** 表头自定义项8 getter 方法 */
  public String getVhdef8() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF8);
  }

  /** 表头自定义项9 getter 方法 */
  public String getVhdef9() {
    return (String) this.getAttributeValue(StatusOnWayItemVO.VHDEF9);
  }

  /** 对方订单行号 getter 方法 */
  public String getVvendororderrow() {
    return (String) this.getAttributeValue(OrderItemVO.VVENDORORDERROW);
  }

  /** 承运商 setter 方法 */
  public void setCcarrier(String ccarrier) {
    this.setAttributeValue(StatusOnWayItemVO.CCARRIER, ccarrier);
  }

  /** 货柜号 setter 方法 */
  public void setCcasecode(String ccasecode) {
    this.setAttributeValue(StatusOnWayItemVO.CCASECODE, ccasecode);
  }

  /** 到货港口 setter 方法 */
  public void setClandport(String clandport) {
    this.setAttributeValue(StatusOnWayItemVO.CLANDPORT, clandport);
  }

  /** 装船港口 setter 方法 */
  public void setCloadport(String cloadport) {
    this.setAttributeValue(StatusOnWayItemVO.CLOADPORT, cloadport);
  }

  /** 船次号 setter 方法 */
  public void setCshipline(String cshipline) {
    this.setAttributeValue(StatusOnWayItemVO.CSHIPLINE, cshipline);
  }

  /** 船名 setter 方法 */
  public void setCshipname(String cshipname) {
    this.setAttributeValue(StatusOnWayItemVO.CSHIPNAME, cshipname);
  }

  /** 单据日期 setter 方法 */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(StatusOnWayItemVO.DBILLDATE, dbilldate);
  }

  /** 计划到货日期 setter 方法 */
  public void setDplanarrvdate(UFDate dplanarrvdate) {
    this.setAttributeValue(StatusOnWayItemVO.DPLANARRVDATE, dplanarrvdate);
  }

  /** 计划到港日期 setter 方法 */
  public void setDplanfreightdate(UFDate dplanfreightdate) {
    this.setAttributeValue(StatusOnWayItemVO.DPLANFREIGHTDATE, dplanfreightdate);
  }

  /** dr setter 方法 */
  public void setDr(Integer dr) {
    this.setAttributeValue(StatusOnWayItemVO.DR, dr);
  }

  /** 在途状态 setter 方法 */
  public void setFonwaystatus(Integer fonwaystatus) {
    this.setAttributeValue(StatusOnWayItemVO.FONWAYSTATUS, fonwaystatus);
  }

  /** 是否已操作 setter 方法 */
  public void setIsoperated(UFBoolean isoperated) {
    this.setAttributeValue(StatusOnWayItemVO.ISOPERATED, isoperated);
  }

  /** 最大可操作数量 setter 方法 */
  public void setNmaxhandlenum(UFDouble nconfirmnum) {
    this.setAttributeValue(StatusOnWayItemVO.NMAXHANDLENUM, nconfirmnum);
  }

  /** 在途数量 setter 方法 */
  public void setNonwaynum(UFDouble nonwaynum) {
    this.setAttributeValue(StatusOnWayItemVO.NONWAYNUM, nonwaynum);
  }

  /** 所属集团 setter 方法 */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(StatusOnWayItemVO.PK_GROUP, pk_group);
  }

  /** 采购订单 setter 方法 */
  public void setPk_order(String pk_order) {
    this.setAttributeValue(StatusOnWayItemVO.PK_ORDER, pk_order);
  }

  /** 在途状态子子表 setter 方法 */
  public void setPk_order_b(String pk_order_b) {
    this.setAttributeValue(StatusOnWayItemVO.PK_ORDER_B, pk_order_b);
  }

  /** 采购订单在途状态 setter 方法 */
  public void setPk_order_bb(String pk_order_bb) {
    this.setAttributeValue(StatusOnWayItemVO.PK_ORDER_BB, pk_order_bb);
  }

  /** 采购组织版本信息 setter 方法 */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(StatusOnWayItemVO.PK_ORG, pk_org);
  }

  /** 采购组织 setter 方法 */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(StatusOnWayItemVO.PK_ORG_V, pk_org_v);
  }

  /** ts setter 方法 */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(StatusOnWayItemVO.TS, ts);
  }

  /** 自定义项1 setter 方法 */
  public void setVbdef1(String vbdef1) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF1, vbdef1);
  }

  /** 自定义项10 setter 方法 */
  public void setVbdef10(String vbdef10) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF10, vbdef10);
  }

  /** 自定义项11 setter 方法 */
  public void setVbdef11(String vbdef11) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF11, vbdef11);
  }

  /** 自定义项12 setter 方法 */
  public void setVbdef12(String vbdef12) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF12, vbdef12);
  }

  /** 自定义项13 setter 方法 */
  public void setVbdef13(String vbdef13) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF13, vbdef13);
  }

  /** 自定义项14 setter 方法 */
  public void setVbdef14(String vbdef14) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF14, vbdef14);
  }

  /** 自定义项15 setter 方法 */
  public void setVbdef15(String vbdef15) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF15, vbdef15);
  }

  /** 自定义项16 setter 方法 */
  public void setVbdef16(String vbdef16) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF16, vbdef16);
  }

  /** 自定义项17 setter 方法 */
  public void setVbdef17(String vbdef17) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF17, vbdef17);
  }

  /** 自定义项18 setter 方法 */
  public void setVbdef18(String vbdef18) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF18, vbdef18);
  }

  /** 自定义项19 setter 方法 */
  public void setVbdef19(String vbdef19) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF19, vbdef19);
  }

  /** 自定义项2 setter 方法 */
  public void setVbdef2(String vbdef2) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF2, vbdef2);
  }

  /** 自定义项20 setter 方法 */
  public void setVbdef20(String vbdef20) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF20, vbdef20);
  }

  /** 自定义项21 setter 方法 */
  public void setVbdef21(String vbdef21) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF21, vbdef21);
  }

  /** 自定义项22 setter 方法 */
  public void setVbdef22(String vbdef22) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF22, vbdef22);
  }

  /** 自定义项23 setter 方法 */
  public void setVbdef23(String vbdef23) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF23, vbdef23);
  }

  /** 自定义项24 setter 方法 */
  public void setVbdef24(String vbdef24) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF24, vbdef24);
  }

  /** 自定义项25 setter 方法 */
  public void setVbdef25(String vbdef25) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF25, vbdef25);
  }

  /** 自定义项26 setter 方法 */
  public void setVbdef26(String vbdef26) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF26, vbdef26);
  }

  /** 自定义项27 setter 方法 */
  public void setVbdef27(String vbdef27) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF27, vbdef27);
  }

  /** 自定义项28 setter 方法 */
  public void setVbdef28(String vbdef28) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF28, vbdef28);
  }

  /** 自定义项29 setter 方法 */
  public void setVbdef29(String vbdef29) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF29, vbdef29);
  }

  /** 自定义项3 setter 方法 */
  public void setVbdef3(String vbdef3) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF3, vbdef3);
  }

  /** 自定义项30 setter 方法 */
  public void setVbdef30(String vbdef30) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF30, vbdef30);
  }

  /** 自定义项31 setter 方法 */
  public void setVbdef31(String vbdef31) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF31, vbdef31);
  }

  /** 自定义项32 setter 方法 */
  public void setVbdef32(String vbdef32) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF32, vbdef32);
  }

  /** 自定义项33 setter 方法 */
  public void setVbdef33(String vbdef33) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF33, vbdef33);
  }

  /** 自定义项34 setter 方法 */
  public void setVbdef34(String vbdef34) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF34, vbdef34);
  }

  /** 自定义项35 setter 方法 */
  public void setVbdef35(String vbdef35) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF35, vbdef35);
  }

  /** 自定义项36 setter 方法 */
  public void setVbdef36(String vbdef36) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF36, vbdef36);
  }

  /** 自定义项37 setter 方法 */
  public void setVbdef37(String vbdef37) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF37, vbdef37);
  }

  /** 自定义项38 setter 方法 */
  public void setVbdef38(String vbdef38) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF38, vbdef38);
  }

  /** 自定义项39 setter 方法 */
  public void setVbdef39(String vbdef39) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF39, vbdef39);
  }

  /** 自定义项4 setter 方法 */
  public void setVbdef4(String vbdef4) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF4, vbdef4);
  }

  /** 自定义项40 setter 方法 */
  public void setVbdef40(String vbdef40) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF40, vbdef40);
  }

  /** 自定义项5 setter 方法 */
  public void setVbdef5(String vbdef5) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF5, vbdef5);
  }

  /** 自定义项6 setter 方法 */
  public void setVbdef6(String vbdef6) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF6, vbdef6);
  }

  /** 自定义项7 setter 方法 */
  public void setVbdef7(String vbdef7) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF7, vbdef7);
  }

  /** 自定义项8 setter 方法 */
  public void setVbdef8(String vbdef8) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF8, vbdef8);
  }

  /** 自定义项9 setter 方法 */
  public void setVbdef9(String vbdef9) {
    this.setAttributeValue(StatusOnWayItemVO.VBDEF9, vbdef9);
  }

  /** 单据编号 setter 方法 */
  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(StatusOnWayItemVO.VBILLCODE, vbillcode);
  }

  /** 表头自定义项1 setter 方法 */
  public void setVhdef1(String vhdef1) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF1, vhdef1);
  }

  /** 表头自定义项10 setter 方法 */
  public void setVhdef10(String vhdef10) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF10, vhdef10);
  }

  /** 表头自定义项11 setter 方法 */
  public void setVhdef11(String vhdef11) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF11, vhdef11);
  }

  /** 表头自定义项12 setter 方法 */
  public void setVhdef12(String vhdef12) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF12, vhdef12);
  }

  /** 表头自定义项13 setter 方法 */
  public void setVhdef13(String vhdef13) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF13, vhdef13);
  }

  /** 表头自定义项14 setter 方法 */
  public void setVhdef14(String vhdef14) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF14, vhdef14);
  }

  /** 表头自定义项15 setter 方法 */
  public void setVhdef15(String vhdef15) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF15, vhdef15);
  }

  /** 表头自定义项16 setter 方法 */
  public void setVhdef16(String vhdef16) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF16, vhdef16);
  }

  /** 表头自定义项17 setter 方法 */
  public void setVhdef17(String vhdef17) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF17, vhdef17);
  }

  /** 表头自定义项18 setter 方法 */
  public void setVhdef18(String vhdef18) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF18, vhdef18);
  }

  /** 表头自定义项19 setter 方法 */
  public void setVhdef19(String vhdef19) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF19, vhdef19);
  }

  /** 表头自定义项2 setter 方法 */
  public void setVhdef2(String vhdef2) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF2, vhdef2);
  }

  /** 表头自定义项20 setter 方法 */
  public void setVhdef20(String vhdef20) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF20, vhdef20);
  }

  /** 表头自定义项21 setter 方法 */
  public void setVhdef21(String vhdef21) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF21, vhdef21);
  }

  /** 表头自定义项22 setter 方法 */
  public void setVhdef22(String vhdef22) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF22, vhdef22);
  }

  /** 表头自定义项23 setter 方法 */
  public void setVhdef23(String vhdef23) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF23, vhdef23);
  }

  /** 表头自定义项24 setter 方法 */
  public void setVhdef24(String vhdef24) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF24, vhdef24);
  }

  /** 表头自定义项25 setter 方法 */
  public void setVhdef25(String vhdef25) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF25, vhdef25);
  }

  /** 表头自定义项26 setter 方法 */
  public void setVhdef26(String vhdef26) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF26, vhdef26);
  }

  /** 表头自定义项27 setter 方法 */
  public void setVhdef27(String vhdef27) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF27, vhdef27);
  }

  /** 表头自定义项28 setter 方法 */
  public void setVhdef28(String vhdef28) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF28, vhdef28);
  }

  /** 表头自定义项29 setter 方法 */
  public void setVhdef29(String vhdef29) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF29, vhdef29);
  }

  /** 表头自定义项3 setter 方法 */
  public void setVhdef3(String vhdef3) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF3, vhdef3);
  }

  /** 表头自定义项30 setter 方法 */
  public void setVhdef30(String vhdef30) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF30, vhdef30);
  }

  /** 表头自定义项31 setter 方法 */
  public void setVhdef31(String vhdef31) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF31, vhdef31);
  }

  /** 表头自定义项32 setter 方法 */
  public void setVhdef32(String vhdef32) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF32, vhdef32);
  }

  /** 表头自定义项33 setter 方法 */
  public void setVhdef33(String vhdef33) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF33, vhdef33);
  }

  /** 表头自定义项34 setter 方法 */
  public void setVhdef34(String vhdef34) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF34, vhdef34);
  }

  /** 表头自定义项35 setter 方法 */
  public void setVhdef35(String vhdef35) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF35, vhdef35);
  }

  /** 表头自定义项36 setter 方法 */
  public void setVhdef36(String vhdef36) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF36, vhdef36);
  }

  /** 表头自定义项37 setter 方法 */
  public void setVhdef37(String vhdef37) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF37, vhdef37);
  }

  /** 表头自定义项38 setter 方法 */
  public void setVhdef38(String vhdef38) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF38, vhdef38);
  }

  /** 表头自定义项39 setter 方法 */
  public void setVhdef39(String vhdef39) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF39, vhdef39);
  }

  /** 表头自定义项4 setter 方法 */
  public void setVhdef4(String vhdef4) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF4, vhdef4);
  }

  /** 表头自定义项40 setter 方法 */
  public void setVhdef40(String vhdef40) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF40, vhdef40);
  }

  /** 表头自定义项5 setter 方法 */
  public void setVhdef5(String vhdef5) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF5, vhdef5);
  }

  /** 表头自定义项6 setter 方法 */
  public void setVhdef6(String vhdef6) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF6, vhdef6);
  }

  /** 表头自定义项7 setter 方法 */
  public void setVhdef7(String vhdef7) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF7, vhdef7);
  }

  /** 表头自定义项8 setter 方法 */
  public void setVhdef8(String vhdef8) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF8, vhdef8);
  }

  /** 表头自定义项9 setter 方法 */
  public void setVhdef9(String vhdef9) {
    this.setAttributeValue(StatusOnWayItemVO.VHDEF9, vhdef9);
  }

  /** 对方订单行号 setter 方法 */
  public void setVvendororderrow(String vvendororderrow) {
    this.setAttributeValue(OrderItemVO.VVENDORORDERROW, vvendororderrow);
  }

}
