package nc.ui.pu.pub.util;

import java.util.List;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pu.pub.rule.BusitypeFillRule;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.scmpub.res.billtype.IBillType;
import nc.vo.uif2.LoginContext;

import org.apache.commons.lang.StringUtils;

/**
 * ҵ�����������������ڣ�����֯�л���������ť������ʱȷ��ҵ������
 * 
 * @since 6.0
 * @version 2011-8-5 ����11:06:08
 * @author zhaoyha
 */
public class BusitypeSetter {

  private IKeyValue billHelper;

  private IBillType bt;

  private LoginContext context;

  /**
   * 
   * @param bt ��������
   * @param billHelper ���ݵķ�װ��
   * @param context LoginContex
   */
  public BusitypeSetter(IBillType bt, IKeyValue billHelper, LoginContext context) {
    super();
    this.bt = bt;
    this.billHelper = billHelper;
    this.context = context;
  }

  /**
   * ���Ƶ���ʱ����
   * 
   * @param rccRuleLst
   */
  public void copySet(List<IPURemoteCallCombinator> rccRuleLst) {
    // ����������
    String transtype = TrantypeFuncUtils.getTrantype(this.context);
    // ֻ�н������ͷ����Ľڵ㣬������ȷ�����̣���Ϊ��ʱ���ܲ��ᴥ�����ͱ༭���п���ȷ����������
    if (StringUtils.isNotBlank(transtype)) {
      // ע��Զ�̵���
      BusitypeFillRule bizRule =
          new BusitypeFillRule(this.billHelper, this.bt.getCode());
      bizRule.prepare();
      rccRuleLst.add(bizRule);
    }

  }

  /**
   * �����ư�ťʱ����
   * 
   * @param rccRuleLst
   */
  public void manualAddSet(List<IPURemoteCallCombinator> rccRuleLst) {
    String transtype = TrantypeFuncUtils.getTrantype(this.context);
    String pk_org = this.context.getPk_org();
    String pk_busitype =
        (String) this.billHelper.getHeadValue(PUQueryConst.PK_BUSITYPE);
    // ֻ�н������ͷ����Ľڵ㣬������ȷ�����̣���Ϊ��ʱ���ܲ��ᴥ�����ͱ༭���п���ȷ����������
    // �˴������ж������ֶ��Ƿ��Ѿ���ֵ������Ѿ����˿��Բ����ң�����������֣�
    // ���õ��޸Ļ����ģ�����ʱ�Ѿ���������֯�༭�¼�������Ҳ����ͬ�Ĺ���
    if (StringUtils.isNotBlank(transtype) && StringUtils.isNotBlank(pk_org)
        && StringUtils.isBlank(pk_busitype)) {
      BusitypeFillRule biz =
          new BusitypeFillRule(this.billHelper, this.bt.getCode());
      biz.prepare();
      rccRuleLst.add(biz);
    }

  }

  /**
   * ����֯�л�ʱ����
   * 
   * @param rccRuleLst
   */
  public void orgChgSet(List<IPURemoteCallCombinator> rccRuleLst) {
    String transtype = TrantypeFuncUtils.getTrantype(this.context);
    String pk_org = this.context.getPk_org();
    // ֻ�н������ͷ����Ľڵ㣬������ȷ�����̣���Ϊ��ʱ���ܲ��ᴥ�����ͱ༭���п���ȷ����������
    if (StringUtils.isNotBlank(transtype) && StringUtils.isNotBlank(pk_org)) {
      BusitypeFillRule biz =
          new BusitypeFillRule(this.billHelper, this.bt.getCode());
      biz.prepare();
      rccRuleLst.add(biz);
    }
  }
}
