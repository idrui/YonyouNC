/**
 * $�ļ�˵��$
 *
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-17 ����09:14:05
 */
package nc.bs.pu.m21.writeback.pu.rule;

import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21.pu.m23.IOrderWriteBackParaFor23;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ۼƵ����������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-1-17 ����09:14:05
 */
public class AccArrNumChkRule implements IRule<OrderViewVO> {
  private IOrderWriteBackParaFor23[] wbVos;

  public AccArrNumChkRule(IOrderWriteBackParaFor23[] wbVos) {
    this.wbVos = wbVos;
  }

  @Override
  public void process(OrderViewVO[] views) {
    if (ArrayUtils.isEmpty(views)) {
      return;
    }
    Map<String, OrderViewVO> viewMap = CirVOUtil.createKeyVOMap(views);
    for (IOrderWriteBackParaFor23 wbVo : this.wbVos) {
      OrderViewVO view = viewMap.get(wbVo.getBID());
      // �ۼƵ���(���)�����붩���������
      this.checkArrNum(view);
      this.checkSign(view, wbVo);
      // �ۼƵ������ۼ��˻����ۼ����֮���ϵ���
    }
  }

  private void checkArrNum(OrderViewVO view) {
    if (UFBoolean.TRUE.equals(view.getBrefwhenreturn())) {
      return;
    }
    UFDouble nnum = view.getNnum();
    UFDouble naccumarrvnum = view.getNaccumarrvnum();
    UFDouble nbackarrvnum = view.getNbackarrvnum();
    UFDouble naccbacknum =
        MathTool.add(view.getNbackarrvnum(), view.getNbackstorenum());
    if (MathTool.compareTo(nnum, UFDouble.ZERO_DBL) > 0) {
      if (MathTool.compareTo(nbackarrvnum, naccumarrvnum) > 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0144")/*
                                                                     * @res
                                                                     * "���ڶ����ۼ��˻������������ۼƵ������������У����飡"
                                                                     */);
      }
    }
    else {
      if (MathTool.absCompareTo(naccbacknum, nnum) > 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0142")/*
                                                                     * @res
                                                                     * "���ڶ������ۼ��˻��������ۼ��˿�����֮�ͳ��������������У����飡"
                                                                     */);
      }
    }
  }

  private void checkSign(OrderViewVO view, IOrderWriteBackParaFor23 wbVo) {
    if (!wbVo.isReturn() || UFBoolean.TRUE.equals(view.getBrefwhenreturn())) {
      if (MathTool.isDiffSign(view.getNnum(), view.getNaccumarrvnum())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0145")/*
                                                                     * @res
                                                                     * "���ڶ����ۼƵ����������붩������������һ�µ��У����飡"
                                                                     */);
      }
    }
    // else {
    // if (MathTool.compareTo(view.getNaccumarrvnum(), UFDouble.ZERO_DBL) < 0) {
    // ExceptionUtils.wrappBusinessException("�ۼƵ���������������㣡");
    // }
    // }
  }
}
