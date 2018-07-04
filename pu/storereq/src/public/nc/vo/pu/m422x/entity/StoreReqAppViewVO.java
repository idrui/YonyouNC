package nc.vo.pu.m422x.entity;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.pubapp.pattern.model.tool.BillComposite;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>物资需求申请单视图VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-9-15 上午11:15:28
 */
public class StoreReqAppViewVO extends AbstractDataView {

  private static final long serialVersionUID = 5508564754340511569L;

  /** 由视图VO得到物资需求申请VO **/
  public static StoreReqAppVO[] getStoreReqAppVO(AbstractDataView[] views) {
    if (ArrayUtils.isEmpty(views)) {
      return null;
    }
    List<StoreReqAppHeaderVO> headers = new ArrayList<StoreReqAppHeaderVO>();
    List<StoreReqAppItemVO> items = new ArrayList<StoreReqAppItemVO>();
    for (AbstractDataView view : views) {
      headers.add((StoreReqAppHeaderVO) view.getVO(StoreReqAppHeaderVO.class));
      items.add((StoreReqAppItemVO) view.getVO(StoreReqAppItemVO.class));
    }

    BillComposite<StoreReqAppVO> bc =
        new BillComposite<StoreReqAppVO>(StoreReqAppVO.class);
    StoreReqAppVO tempVO = new StoreReqAppVO();
    bc.append(tempVO.getMetaData().getParent(),
        headers.toArray(new StoreReqAppHeaderVO[headers.size()]));
    bc.append(tempVO.getMetaData().getVOMeta(StoreReqAppItemVO.class),
        items.toArray(new StoreReqAppItemVO[items.size()]));
    return bc.composite();
    // return (StoreReqAppVO[]) AggVOUtil.createBillVO(headers
    // .toArray(new StoreReqAppHeaderVO[headers.size()]), items
    // .toArray(new StoreReqAppItemVO[items.size()]), StoreReqAppVO.class);
  }

  /** 是否关闭 getter 方法 */
  public UFBoolean getBclose() {
    return (UFBoolean) this.getAttributeValue(StoreReqAppItemVO.BCLOSE);
  }

  /** 行号 getter 方法 */
  public String getCrowno() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.CROWNO);
  }

  /** 取得表头VO **/
  public StoreReqAppHeaderVO getHead() {
    return (StoreReqAppHeaderVO) this.getVO(StoreReqAppHeaderVO.class);
  }

  /** 取得表体VO **/
  public StoreReqAppItemVO getItem() {
    return (StoreReqAppItemVO) this.getVO(StoreReqAppItemVO.class);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pubapp.pattern.model.entity.view.AbstractDataView#getMetaData()
   */
  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getBillViewMeta(
        StoreReqAppVO.class);
  }

  /** 累计请购主数量 **/
  public UFDouble getNaccumbuyreqnum() {
    return (UFDouble) this.getAttributeValue(StoreReqAppItemVO.NACCUMBUYREQNUM);
  }

  /** 累计出库数量 **/
  public UFDouble getNaccuoutnum() {
    return (UFDouble) this.getAttributeValue(StoreReqAppItemVO.NACCUOUTNUM);
  }

  /** 累计申请出库主数量 getter 方法 */
  public UFDouble getNaccuoutreqnum() {
    return (UFDouble) this.getAttributeValue(StoreReqAppItemVO.NACCUOUTREQNUM);
  }

  /** 数量 **/
  public UFDouble getNassistnum() {
    return (UFDouble) this.getAttributeValue(StoreReqAppItemVO.NASTNUM);
  }

  /** 可请购主数量 getter 方法 */
  public UFDouble getNcanbuyreqnnum() {
    return (UFDouble) this.getAttributeValue(StoreReqAppItemVO.NCANBUYREQNNUM);
  }

  /** 主数量 **/
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(StoreReqAppItemVO.NNUM);
  }

  /** 本币价税合计 **/
  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(StoreReqAppItemVO.NTAXMNY);
  }

  /** 主本币含税单价 **/
  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(StoreReqAppItemVO.NTAXPRICE);
  }

  /** 物料版本信息 getter 方法 */
  public String getPk_material() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.PK_MATERIAL);
  }

  /** 库存组织最新版本 getter 方法 */
  public String getPk_org() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.PK_ORG);
  }

  /** 物资需求申请单明细 getter 方法 */
  public String getPk_storereq_b() {
    return (String) this.getAttributeValue(StoreReqAppItemVO.PK_STOREREQ_B);
  }

  /** 申请单号 getter 方法 */
  public String getVbillcode() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.VBILLCODE);
  }

  /** 是否关闭 setter 方法 */
  public void setBclose(UFBoolean bclose) {
    this.setAttributeValue(StoreReqAppItemVO.BCLOSE, bclose);
  }

  public void setHead(StoreReqAppHeaderVO head) {
    this.setVO(head);
  }

  public void setItem(StoreReqAppItemVO item) {
    this.setVO(item);
  }

  /** 累计请购主数量 **/
  public void setNaccumbuyreqnum(UFDouble naccumbuyreqnum) {
    this.setAttributeValue(StoreReqAppItemVO.NACCUMBUYREQNUM, naccumbuyreqnum);
  }

  /** 累计出库数量 **/
  public void setNaccuoutnum(UFDouble naccuoutnum) {
    this.setAttributeValue(StoreReqAppItemVO.NACCUOUTNUM, naccuoutnum);
  }

  /** 累计申请出库主数量 setter 方法 */
  public void setNaccuoutreqnum(UFDouble naccuoutreqnum) {
    this.setAttributeValue(StoreReqAppItemVO.NACCUOUTREQNUM, naccuoutreqnum);
  }

  /** 数量 **/
  public void setNassistnum(UFDouble nassistnum) {
    this.setAttributeValue(StoreReqAppItemVO.NASTNUM, nassistnum);
  }

  /** 可请购主数量 setter 方法 */
  public void setNcanbuyreqnnum(String ncanbuyreqnnum) {
    this.setAttributeValue(StoreReqAppItemVO.NCANBUYREQNNUM, ncanbuyreqnnum);
  }

  /** 主数量 **/
  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(StoreReqAppItemVO.NNUM, nnum);
  }

  /** 本币价税合计 **/
  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(StoreReqAppItemVO.NTAXMNY, ntaxmny);
  }

  /** 主本币含税单价 **/
  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(StoreReqAppItemVO.NTAXPRICE, ntaxprice);
  }

}
