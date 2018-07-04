package nc.pubitf.pu.m25.ap.f1;

import java.util.List;
import java.util.Map;

import nc.vo.arap.termitem.PaymentDateVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;

/**
 * 采购发票给应付单的查询服务
 * 
 * @since 6.0
 * @version 2011-9-1 下午1:24:05
 * @author zhaoyha
 */
public interface IInvoiceQuery4F1 {

  /**
   * 根据财务的收付协议起算日期VO，查询对应的业务（单据）日期
   * 
   * @param pdvoList 要查询付款协议的VO列表
   * @return
   *         Map{key=应付单来源bid(nc.vo.arap.termitem.PaymentDateVO.itemid)+日期类型枚举名称
   *         （
   *         nc.vo .arap.termitem.PaymentDateVO.AccountDate.name()）,<br>
   *         value=相应的业务（单据）日期(可能为null)}
   */
  Map<String, UFDate> queryPaytermDate(List<PaymentDateVO> pdvoList)
      throws BusinessException;

}
