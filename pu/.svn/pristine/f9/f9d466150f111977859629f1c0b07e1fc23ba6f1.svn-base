package nc.vo.pu.onhand.entity;

import nc.vo.ic.onhand.entity.AbstractOnhandDlgHeadVO;
import nc.vo.ic.onhand.entity.OnhandDimVO;
import nc.vo.pub.JavaType;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * @description
 *              价格结算单
 * @scene
 *        价格结算单更新保存
 * @param 无
 * @since 6.3
 * @version 2015-1-7 下午4:29:21
 * @author luojw
 */

public class OnhandDlgPUHeaderVO extends AbstractOnhandDlgHeadVO {

  public static class OnhandDlgHeadVOMeta extends DataViewMeta {

    public OnhandDlgHeadVOMeta() {
      super();
      this.init();
    }

    protected void init() {
      this.add(OnhandDimVO.class);
    }
  }

  /** 行号 */
  public static final String CROWNO = "crowno";

  /** 主单位 */
  public static final String CUNITID = "cunitid";
  
  private static final long serialVersionUID = 946005018765065984L;

  private JavaType[] fixFieldJavaTypes = {
    JavaType.String, JavaType.String
  };

  private String[] fixFields = {
    OnhandDlgPUHeaderVO.CROWNO, OnhandDlgPUHeaderVO.CUNITID
  };

  @Override
  public void addFixAttributes(DataViewMeta dataViewMeta) {
    super.addFixAttributes(dataViewMeta);
    if (this.fixFields != null) {
      for (int i = 0; i < this.fixFields.length; i++) {
        dataViewMeta.add(DynamicAttribute.create(this.fixFields[i],
            this.fixFieldJavaTypes[i]));
      }
    }
  }

  /** 行号 */
  public String getCrowno() {
    return (String) this.getAttributeValue(OnhandDlgPUHeaderVO.CROWNO);
  }
  
  /** 主单位 */
  public String getCunitid() {
    return (String) this.getAttributeValue(OnhandDlgPUHeaderVO.CUNITID);
  }

  @Override
  public Class<? extends IDataViewMeta> getMetaDataClass() {
    return OnhandDlgHeadVOMeta.class;
  }

  /** 行号 */
  public void setCrowno(String crowno) {
    this.setAttributeValue(OnhandDlgPUHeaderVO.CROWNO, crowno);
  }
  
  /** 主单位 */
  public void setCunitid(String cunitid) {
    this.setAttributeValue(OnhandDlgPUHeaderVO.CUNITID, cunitid);
  }
}
