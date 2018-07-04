/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-20 ����09:59:29
 */
package nc.pubimpl.pu.m25.pu.settle.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IFilterRule;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.pub.InvoiceVOUtil;
import nc.vo.pu.m25trantype.entity.InvcTranTypeVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * ���ݽ������͹��˽�������Զ���Ӧ���ķ�Ʊ
 * @scene
 * ����(ƥ��)���Զ���Ӧ��
 * @param
 * ��
 *
 * @since 6.3
 * @version 2014-10-22 ����3:47:58
 * @author zhangshqb
 */
public class SettleAutoSendAPFilterRule implements IFilterRule<InvoiceVO> {

  @Override
  public InvoiceVO[] process(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return vos;
    }
    Map<String, InvcTranTypeVO> ttcodeMap = InvoiceVOUtil.getTranstype(vos);
    if (MapUtils.isEmpty(ttcodeMap)) {
      return null;
    }
    List<InvoiceVO> filterVos = new ArrayList<InvoiceVO>();
    for (InvoiceVO vo : vos) {
      String ttcode = vo.getParentVO().getVtrantypecode();
      InvcTranTypeVO ttVo = ttcodeMap.get(ttcode);
      if (this.isCanAutoSendAP(vo, ttVo)) {
        filterVos.add(vo);
      }
    }
    return filterVos.toArray(new InvoiceVO[filterVos.size()]);
  }

  private boolean isCanAutoSendAP(InvoiceVO vo, InvcTranTypeVO ttVo) {
    // ��������ⷢƱ����һ����Ӧ����������������
    if (ValueUtils.getBoolean(vo.getParentVO().getBvirtual())) {
      return true;
    }
    if (null == ttVo) {
      return false;
    }
    /**
     * �ɹ���Ʊ��������Ƿ��Զ���Ӧ�����ɽ����������ԡ���������Զ���Ӧ���������������ѡ�ˣ�
     * ��ɹ���Ʊ���б�����ȫ��������ϣ����Զ���Ӧ����
     * ���û�й�ѡ����ʹȫ���������Ҳ���Զ���Ӧ������Ҫͨ����Ӧ����ť�ֹ�������Ӧ����
     */
    if (!ValueUtils.getBoolean(ttVo.getBsendpay())) {
      return false;
    }
    for (InvoiceItemVO item : vo.getChildrenVO()) {
      // wuxla v61
      // UFDouble mny = MathTool.nvl(item.getNmny());
      UFDouble ncalcostmny = MathTool.nvl(item.getNcalcostmny());
      UFDouble settleMny = MathTool.nvl(item.getNaccumsettmny());
      if (!ncalcostmny.equals(settleMny)) {
        return false;// δ����������ܴ�Ӧ��
      }
    }
    return true;
  }

}
