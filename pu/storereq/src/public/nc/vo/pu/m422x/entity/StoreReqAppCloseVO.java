/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-28 ����08:11:13
 */
package nc.vo.pu.m422x.entity;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.pubapp.pattern.model.tool.BillComposite;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-28 ����08:11:13
 */
public class StoreReqAppCloseVO extends AbstractDataView {

  private static final long serialVersionUID = 8082000881084072191L;

  public static StoreReqAppVO[] getBillVO(StoreReqAppCloseVO[] views) {
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

  /** �Ƿ�ر� getter ���� */
  public UFBoolean getBclose() {
    return (UFBoolean) this.getAttributeValue(StoreReqAppItemVO.BCLOSE);
  }

  /** ����״̬ getter ���� */
  public Integer getFbillstatus() {
    return (Integer) this.getAttributeValue(StoreReqAppHeaderVO.FBILLSTATUS);
  }

  /**
   * ���෽����д
   * 
   * @see nc.vo.pubapp.pattern.model.entity.view.AbstractDataView#getMetaData()
   */
  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getBillViewMeta(
        StoreReqAppVO.class);
  }

  /** �����֯���°汾 getter ���� */
  public String getPk_org() {
    return (String) this.getAttributeValue(StoreReqAppHeaderVO.PK_ORG);
  }

  /** �Ƿ�ر� setter ���� */
  public void setBclose(UFBoolean bclose) {
    this.setAttributeValue(StoreReqAppItemVO.BCLOSE, bclose);
  }

  /** ����״̬ setter ���� */
  public void setFbillstatus(Integer fbillstatus) {
    this.setAttributeValue(StoreReqAppHeaderVO.FBILLSTATUS, fbillstatus);
  }

  /** �����֯���°汾 setter ���� */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(StoreReqAppHeaderVO.PK_ORG, pk_org);
  }
}
