/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-21 ����07:21:19
 */
package nc.vo.pu.est.entity.m4t;

import java.util.HashMap;
import java.util.Map;

import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.m4201.enumeration.EnumToAPFlag;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ڳ��ݹ�������ݹ���Ϣ�ı�ͷVO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-10-21 ����07:21:19
 */
public class InitialBillGoodsEstVO extends GoodsEstVO {

  private static final long serialVersionUID = 3697691054683515736L;

  // �ֶ�ӳ��map
  private Map<String, String> attNameMap = null;

  /**
   * �ֶβ�ͬ����Ҫӳ��
   * 
   * @return
   */
  public Map<String, String> getAttNameMap() {
    if (this.attNameMap == null) {
      this.attNameMap = new HashMap<String, String>();
      // �ۼƽ�������
      this.attNameMap.put(GoodsEstVO.NACCUMSETTLENUM,
          InitialEstItemVO.NACCSETTLENUM);
      // �ݹ�Ӧ�����
      this.attNameMap.put(GoodsEstVO.FTOAPFLAG, InitialEstItemVO.BESTIMATEAP);
      // �����
      this.attNameMap.put(GoodsEstVO.PK_STOCKPS_B,
          InitialEstItemVO.PK_INITIALEST_B);
      // TODO ������ ��������������ͬ���ֶ�
    }
    return this.attNameMap;
  }

  @Override
  public Object getAttributeValue(String name) {
    String realName = this.getAttNameMap().get(name);
    realName = StringUtil.isEmptyWithTrim(realName) ? name : realName;
    // ע�⣺�ڳ����ݹ�Ӧ�����Ϊboolean����ת��Ϊö�ٹ�������
    if (InitialEstItemVO.BESTIMATEAP.equals(realName)) {
      return UFBoolean.TRUE.equals(super.getAttributeValue(realName)) ? EnumToAPFlag.EstimateToAP
          .toInteger() : EnumToAPFlag.NotToAP.toInteger();
    }
    return super.getAttributeValue(realName);
  }

  @Override
  public String getBillType() {
    return POBillType.InitEstimate.getCode();
  }

  @Override
  public IDataViewMeta getMetaData() {
    // ������ ������ʱʹ�ñ�ͷ�����ȫ����Ϣ���Ժ���������ѯֻ��ע���ݹ���Ϣ
    return DataViewMetaFactory.getInstance()
        .getBillViewMeta(InitialEstVO.class);
  }

  @Override
  public void setAttributeValue(String name, Object value) {
    String realName = this.getAttNameMap().get(name);
    realName = StringUtil.isEmptyWithTrim(realName) ? name : realName;
    super.setAttributeValue(realName, value);
  }

}
