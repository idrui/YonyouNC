/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-25 下午01:30:24
 */
package nc.vo.pu.m4202.entity.outsrv;

import nc.vo.pu.m4202.entity.VmiFIHeaderVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>库存消耗汇总查询暂估信息VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-9-25 下午01:30:24
 */
public class VMIEstInfoVO extends SuperVO {

  private static final long serialVersionUID = 4494066789551610327L;

  /** 是否已经暂估（包括只记录暂估数据，但未传存货核算；如有新需求再调整） **/
  public UFBoolean getBEstimated() {
    boolean estflag =
        (null == this.getNestnum())
            || UFDouble.ZERO_DBL.equals(this.getNestnum());
    return UFBoolean.valueOf(!estflag);
  }

  /** 是否结算完成 getter 方法 */
  public UFBoolean getBsettlefinish() {
    return (UFBoolean) this.getAttributeValue(VmiFIHeaderVO.BSETTLEFINISH);
  }

  /** 暂估日期 getter 方法 */
  public UFDate getDtocostapdate() {
    return (UFDate) this.getAttributeValue(VmiFIHeaderVO.DTOCOSTAPDATE);
  }

  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta(PUEntity.VMIFI_H);
  }

  /** 累计回冲暂估成本金额 getter 方法 */
  public UFDouble getNaccestcostwashmny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NACCESTCOSTWASHMNY);
  }

  /** 累计结算数量 getter 方法 */
  public UFDouble getNaccumsettlenum() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NACCUMSETTLENUM);
  }

  /** 本币无税金额 getter 方法 */
  public UFDouble getNestmny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTMNY);
  }

  /** 暂估主数量 getter 方法 */
  public UFDouble getNestnum() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTNUM);
  }

  /** 暂估单价 getter 方法 */
  public UFDouble getNestprice() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTPRICE);
  }

  /** 消耗汇总财务辅标识 getter 方法 */
  public String getPk_stockps() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_STOCKPS);
  }

  /** 是否结算完成 setter 方法 */
  public void setBsettlefinish(UFBoolean bsettlefinish) {
    this.setAttributeValue(VmiFIHeaderVO.BSETTLEFINISH, bsettlefinish);
  }

  /** 暂估日期 setter 方法 */
  public void setDtocostapdate(UFDate dtocostapdate) {
    this.setAttributeValue(VmiFIHeaderVO.DTOCOSTAPDATE, dtocostapdate);
  }

  /** 累计回冲暂估成本金额 setter 方法 */
  public void setNaccestcostwashmny(UFDouble naccestcostwashmny) {
    this.setAttributeValue(VmiFIHeaderVO.NACCESTCOSTWASHMNY, naccestcostwashmny);
  }

  /** 累计结算数量 setter 方法 */
  public void setNaccumsettlenum(UFDouble naccumsettlenum) {
    this.setAttributeValue(VmiFIHeaderVO.NACCUMSETTLENUM, naccumsettlenum);
  }

  /** 本币无税金额 setter 方法 */
  public void setNestmny(UFDouble nestmny) {
    this.setAttributeValue(VmiFIHeaderVO.NESTMNY, nestmny);
  }

  /** 暂估主数量 setter 方法 */
  public void setNestnum(UFDouble nestnum) {
    this.setAttributeValue(VmiFIHeaderVO.NESTNUM, nestnum);
  }

  /** 暂估单价 setter 方法 */
  public void setNestprice(UFDouble nestprice) {
    this.setAttributeValue(VmiFIHeaderVO.NESTPRICE, nestprice);
  }

  /** 消耗汇总财务主标识，也即消耗汇总表头ID setter 方法 */
  public void setPk_stockps_b(String pk_stockps_b) {
    this.setAttributeValue(VmiFIHeaderVO.PK_STOCKPS_B, pk_stockps_b);
  }

}
