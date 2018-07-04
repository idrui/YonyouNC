/**
 *
 */
package nc.bs.pu.m25.maintain.rule.save;

import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 * 直运类检查
 * @scene
 * 保存的BP
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午3:25:24
 * @author zhangshqb
 */
public class DirectTranTypeChkRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    for (InvoiceVO vo : vos) {
      String pk_stockorg = vo.getParentVO().getPk_stockorg();
      if (!StringUtil.isEmptyWithTrim(pk_stockorg)) {
        continue;
      }
      Set<String> ordertypes =
          CirVOUtil.getDistinctFieldSet(vo.getChildrenVO(),
              InvoiceItemVO.VORDERTRANTYPE);
      if ((null == ordertypes) || (0 == ordertypes.size())) {
        continue;
      }
      Map<String, PoTransTypeVO> ordertypeMap =
          this.getOrdertype(ordertypes.toArray(new String[ordertypes.size()]));
      if ((null == ordertypeMap) || (0 == ordertypeMap.size())) {
        continue;
      }
      for (String type : ordertypes) {
        PoTransTypeVO typevo = ordertypeMap.get(type);
        if (ValueUtils.getBoolean(typevo.getBdirect())) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0022")/*@res "直运类业务,要求必须有库存组织才能保存，请输入库存组织！"*/);
        }
      }
    }
  }

  private Map<String, PoTransTypeVO> getOrdertype(String[] ordertypes) {
    IPoTransTypeQuery srv =
        NCLocator.getInstance().lookup(IPoTransTypeQuery.class);
    try {
      return srv.queryAttrByTypes(ordertypes, new String[] {
        PoTransTypeVO.BDIRECT
      });
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

}