package nc.ui.pu.m25.service;

import nc.bs.framework.common.NCLocator;
import nc.pub.templet.converter.util.helper.ExceptionUtils;
import nc.pubift.pu.m25.ia.IInvoiceQueryForIAClosingCheck;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.parameter.ia.QueryParaVO;

/**
 * @description 关帐检查右表数据查询
 * @since 6.36
 * @time 2015-6-01 下午3:20:04
 * @author luojw
 */
public class ClosingCheckModelService {
  
  public static final String UNAPPROVE = "unapprove";
  
  public static final String UNSETTLE = "unsettle";
  
  private String type;
  
  public ClosingCheckModelService(){}
  
  public ClosingCheckModelService(String type) {
    this.type = type;
  }

  public InvoiceHeaderVO[] getInvoiceHeaderVO(QueryParaVO paraVo){
    IInvoiceQueryForIAClosingCheck service =
        NCLocator.getInstance().lookup(IInvoiceQueryForIAClosingCheck.class);
    InvoiceHeaderVO[] vos = null;
    try {
      // 获取未审批采购发票VO
      if(this.type.equals(UNAPPROVE)){
        vos = service.queryUnapprovedInvoice(paraVo);
      // 获取未结算采购发票VO
      }else if(this.type.equals(UNSETTLE)){
        vos = service.queryUnsettledInvoice(paraVo);
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrapException(e);
    }
    return vos;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
