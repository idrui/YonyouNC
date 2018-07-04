/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-18 下午06:13:30
 */
package nc.vo.pu.est.util;

import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pubapp.calculator.data.IRelationForItems;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>暂估单价金额计算字段名称集(暂估表头，货物暂估)
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-18 下午06:13:30
 */
public class EstRelationCalcItem implements IRelationForItems {

  @Override
  public String getBillDate() {
    return GoodsEstVO.DTOCOSTAPDATE;
  }

  @Override
  public String getCastunitidKey() {
    //
    return null;
  }

  @Override
  public String getCcurrencyidKey() {
    return GoodsEstVO.CCURRENCYID;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pubapp.calculator.data.RelationItemForCal#getCorigcurrencyidKey()
   */
  @Override
  public String getCorigcurrencyidKey() {
    return GoodsEstVO.PK_ESTCURRENCY;
  }

  @Override
  public String getCqtcurrencyidKey() {
    //
    return null;
  }

  @Override
  public String getCqtunitidKey() {
    //
    return GoodsEstVO.CUNITID;

  }

  @Override
  public String getCunitidKey() {
    return GoodsEstVO.CUNITID;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pubapp.calculator.data.RelationItemForCal#getFtaxtypeflagKey()
   */
  @Override
  public String getFtaxtypeflagKey() {
    return GoodsEstVO.FESTTAXTYPE;
  }

  @Override
  public String getNaskqtorigpriceKey() {
    //
    return null;
  }

  @Override
  public String getNaskqtorigtaxprcKey() {
    //
    return "null-naskqtorigtaxprc";
  }

  @Override
  public String getNaskqtpriceKey() {
    //
    return null;
  }

  @Override
  public String getNaskqttaxpriceKey() {
    //
    return null;
  }

  @Override
  public String getNassistnumKey() {
    //
    return null;
  }

  @Override
  public String getNcaltaxmnyKey() {
    // wuxla V61
    return GoodsEstVO.NESTCALTAXMNY;
  }

  @Override
  public String getNchangerateKey() {
    //
    return null;
  }

  @Override
  public String getNcostmnyKey() {
    return GoodsEstVO.NESTCALCOSTMNY;
  }

  @Override
  public String getNcostpriceKey() {
    //
    return null;
  }

  @Override
  public String getNdeductibletaxKey() {
    return GoodsEstVO.NESTNOSUBTAX;
  }

  @Override
  public String getNdeductibleTaxrateKey() {
    return GoodsEstVO.NESTNOSUBTAXRATE;
  }

  @Override
  public String getNdiscountKey() {
    return null;
  }

  @Override
  public String getNdiscountrateKey() {
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pubapp.calculator.data.RelationItemForCal#getNexchangerateKey()
   */
  @Override
  public String getNexchangerateKey() {
    return GoodsEstVO.NESTEXHGRATE;
  }

  @Override
  public String getNglobalexchgrateKey() {
    return "null-nglobalexchgrate";
  }

  @Override
  public String getNglobalmnyKey() {
    //
    return null;
  }

  @Override
  public String getNglobaltaxmnyKey() {
    //
    return null;
  }

  @Override
  public String getNgroupexchgrateKey() {
    //
    return "null-ngroupexchgrate";
  }

  @Override
  public String getNgroupmnyKey() {
    //
    return null;
  }

  @Override
  public String getNgrouptaxmnyKey() {
    //
    return null;
  }

  @Override
  public String getNitemdiscountrateKey() {
    //
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pubapp.calculator.data.RelationItemForCal#getNmnyKey()
   */
  @Override
  public String getNmnyKey() {
    return GoodsEstVO.NESTMNY;
  }

  @Override
  public String getNnetpriceKey() {
    // return null;
    return GoodsEstVO.NESTPRICE;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pubapp.calculator.data.RelationItemForCal#getNnumKey()
   */
  @Override
  public String getNnumKey() {
    return GoodsEstVO.NESTNUM;
  }

  @Override
  public String getNorigdiscountKey() {
    return "null-norigdiscount";
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pubapp.calculator.data.RelationItemForCal#getNorigmnyKey()
   */
  @Override
  public String getNorigmnyKey() {
    return GoodsEstVO.NESTOMNY;
  }

  @Override
  public String getNorignetpriceKey() {
    return GoodsEstVO.NESTOPRICE;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pubapp.calculator.data.RelationItemForCal#getNorigpriceKey()
   */
  @Override
  public String getNorigpriceKey() {
    return GoodsEstVO.NESTOPRICE;
  }

  // /**
  // * 父类方法重写
  // *
  // * @see nc.vo.pubapp.calculator.data.RelationItemForCal#getNorigtaxKey()
  // */
  // @Override
  // public String getNorigtaxKey() {
  // return GoodsEstVO.NESTOTAXMNY;
  // }

  @Override
  public String getNorigtaxKey() {
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pubapp.calculator.data.RelationItemForCal#getNorigtaxmnyKey()
   */
  @Override
  public String getNorigtaxmnyKey() {
    return GoodsEstVO.NESTOTOTALMNY;
  }

  @Override
  public String getNorigtaxnetpriceKey() {
    return GoodsEstVO.NESTOTAXPRICE;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pubapp.calculator.data.RelationItemForCal#getNorigtaxpriceKey()
   */
  @Override
  public String getNorigtaxpriceKey() {
    return GoodsEstVO.NESTOTAXPRICE;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pubapp.calculator.data.RelationItemForCal#getNpriceKey()
   */
  @Override
  public String getNpriceKey() {
    return GoodsEstVO.NESTPRICE;
  }

  @Override
  public String getNqtnetpriceKey() {
    //
    return null;
  }

  @Override
  public String getNqtorignetpriceKey() {
    //
    return null;
  }

  @Override
  public String getNqtorigpriceKey() {
    //
    return null;
  }

  @Override
  public String getNqtorigtaxnetprcKey() {
    //
    return null;
  }

  @Override
  public String getNqtorigtaxpriceKey() {
    //
    return null;
  }

  @Override
  public String getNqtpriceKey() {
    //
    return null;
  }

  @Override
  public String getNqttaxnetpriceKey() {
    //
    return null;
  }

  @Override
  public String getNqttaxpriceKey() {
    //
    return null;
  }

  @Override
  public String getNqtunitnumKey() {
    //
    return null;
  }

  @Override
  public String getNqtunitrateKey() {
    //
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pubapp.calculator.data.RelationItemForCal#getNtaxKey()
   */
  @Override
  public String getNtaxKey() {
    return GoodsEstVO.NESTTAXMNY;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pubapp.calculator.data.RelationItemForCal#getNtaxmnyKey()
   */
  @Override
  public String getNtaxmnyKey() {
    return GoodsEstVO.NESTTOTALMNY;
  }

  @Override
  public String getNtaxnetpriceKey() {
    return GoodsEstVO.NESTTAXPRICE;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pubapp.calculator.data.RelationItemForCal#getNtaxpriceKey()
   */
  @Override
  public String getNtaxpriceKey() {
    return GoodsEstVO.NESTTAXPRICE;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pubapp.calculator.data.RelationItemForCal#getNtaxrateKey()
   */
  @Override
  public String getNtaxrateKey() {
    return GoodsEstVO.NESTTAXRATE;
  }

  @Override
  public String getNtotalnumKey() {
    //
    return null;
  }

  @Override
  public String getNtotalorigmnyKey() {
    //
    return null;
  }

  @Override
  public String getNtotalorigtaxmnyKey() {
    //
    return null;
  }

  @Override
  public String getPk_group() {
    return GoodsEstVO.PK_GROUP;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pubapp.calculator.data.RelationItemForCal#getPk_org()
   */
  @Override
  public String getPk_org() {
    return GoodsEstVO.PK_FINANCEORG;
  }

  @Override
  public String getQualifiedNumKey() {
    //
    return null;
  }

  @Override
  public String getUnQualifiedNumKey() {
    //
    return null;
  }

  @Override
  public void setBillDate(String key) {
    //

  }

  @Override
  public void setCastunitidKey(String key) {
    //

  }

  @Override
  public void setCcurrencyidKey(String key) {
    //

  }

  @Override
  public void setCorigcurrencyidKey(String key) {
    //

  }

  @Override
  public void setCqtcurrencyidKey(String key) {
    //

  }

  @Override
  public void setCqtunitidKey(String key) {
    //

  }

  @Override
  public void setCunitidKey(String key) {
    //

  }

  @Override
  public void setFtaxtypeflagKey(String key) {
    //

  }

  @Override
  public void setNaskqtorigpriceKey(String key) {
    //

  }

  @Override
  public void setNaskqtorigtaxprcKey(String key) {
    //

  }

  @Override
  public void setNaskqtpriceKey(String key) {
    //

  }

  @Override
  public void setNaskqttaxpriceKey(String key) {
    //

  }

  @Override
  public void setNassistnumKey(String key) {
    //

  }

  @Override
  public void setNcaltaxmnyKey(String key) {

  }

  @Override
  public void setNchangerateKey(String key) {
    //

  }

  @Override
  public void setNcostmnyKey(String key) {
    //

  }

  @Override
  public void setNcostpriceKey(String key) {
    //

  }

  @Override
  public void setNdeductibletaxKey(String key) {

  }

  @Override
  public void setNdeductibleTaxrateKey(String key) {

  }

  @Override
  public void setNdiscountKey(String key) {
    //

  }

  @Override
  public void setNdiscountrateKey(String key) {
    //

  }

  @Override
  public void setNexchangerateKey(String key) {
    //

  }

  @Override
  public void setNglobalexchgrateKey(String key) {
    //

  }

  @Override
  public void setNglobalmnyKey(String key) {
    //

  }

  @Override
  public void setNglobaltaxmnyKey(String key) {
    //

  }

  @Override
  public void setNgroupexchgrateKey(String key) {
    //

  }

  @Override
  public void setNgroupmnyKey(String key) {
    //

  }

  @Override
  public void setNgrouptaxmnyKey(String key) {
    //

  }

  @Override
  public void setNitemdiscountrateKey(String key) {
    //

  }

  @Override
  public void setNmnyKey(String key) {
    //

  }

  @Override
  public void setNnetpriceKey(String key) {
    //

  }

  @Override
  public void setNorigdiscountKey(String key) {
    //

  }

  @Override
  public void setNorigmnyKey(String key) {
    //

  }

  @Override
  public void setNorignetpriceKey(String key) {
    //

  }

  @Override
  public void setNorigpriceKey(String key) {
    //

  }

  @Override
  public void setNorigtaxKey(String key) {
    //

  }

  @Override
  public void setNorigtaxmnyKey(String key) {
    //

  }

  @Override
  public void setNorigtaxnetpriceKey(String key) {
    //

  }

  @Override
  public void setNorigtaxpriceKey(String key) {
    //

  }

  @Override
  public void setNpriceKey(String key) {
    //

  }

  @Override
  public void setNqtnetpriceKey(String key) {
    //

  }

  @Override
  public void setNqtorignetpriceKey(String key) {
    //

  }

  @Override
  public void setNqtorigpriceKey(String key) {
    //

  }

  @Override
  public void setNqtorigtaxnetprcKey(String key) {
    //

  }

  @Override
  public void setNqtorigtaxpriceKey(String key) {
    //

  }

  @Override
  public void setNqtpriceKey(String key) {
    //

  }

  @Override
  public void setNqttaxnetpriceKey(String key) {
    //

  }

  @Override
  public void setNqttaxpriceKey(String key) {
    //

  }

  @Override
  public void setNqtunitnumKey(String key) {
    //

  }

  @Override
  public void setNqtunitrateKey(String key) {
    //

  }

  @Override
  public void setNtaxKey(String key) {
    //

  }

  @Override
  public void setNtaxmnyKey(String key) {
    //

  }

  @Override
  public void setNtaxnetpriceKey(String key) {
    //

  }

  @Override
  public void setNtaxpriceKey(String key) {
    //

  }

  @Override
  public void setNtaxrateKey(String key) {
    //

  }

  @Override
  public void setNtotalnumKey(String key) {
    //

  }

  @Override
  public void setNtotalorigmnyKey(String key) {
    //

  }

  @Override
  public void setNtotalorigtaxmnyKey(String key) {
    //

  }

  @Override
  public void setnumKey(String key) {
    //
  }

  @Override
  public void setPk_group(String key) {
    //
  }

  @Override
  public void setPk_org(String key) {
    //

  }

  @Override
  public void setQualifiedNumKey(String key) {
    //

  }

  @Override
  public void setUnQualifiedNumKey(String key) {
    //

  }

}
