package nc.pubimpl.pu.m21.cmp.m36d1.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.pu.m21.writeback.arap.OrderWriteBackForF3BP;
import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.ItemKeyMapping;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.pubitf.pu.m21.arap.mf3.IOrderWriteBackParaForF3;
import nc.pubitf.pu.m21.arap.mf3.OrderWriteBackParaForF3;
import nc.vo.cmp.apply.AggApplyVO;
import nc.vo.cmp.apply.ApplyBVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.PayPlanVO;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * ��������ƻ����ɵ��ʽ𸶿����뵥�ۻ�������仯���д������
 * 
 * @since 6.33
 * @version 2014-7-31 ����1:33:35
 * @author mengjian
 */
public class UpdatePaymoneyForF3Util {

  private static final UpdatePaymoneyForF3Util instance =
      new UpdatePaymoneyForF3Util();

  public static UpdatePaymoneyForF3Util getInstance() {
    return UpdatePaymoneyForF3Util.instance;
  }

  /**
   * ��Object����ת��ΪAggApplyVO����
   * 
   * @param object ��Ҫת����object����
   * @return AggApplyVO����
   */
  public AggApplyVO[] convertObjectToAggVos(Object object) {
    AggApplyVO[] aggVos = null;
    if (object == null) {
      return aggVos;
    }
    if (object.getClass().isArray()) {
      Object[] obj = (Object[]) object;
      aggVos = new AggApplyVO[obj.length];
      System.arraycopy(obj, 0, aggVos, 0, obj.length);
    }
    else {
      aggVos = new AggApplyVO[0];
      aggVos[0] = (AggApplyVO) object;
    }
    return aggVos;
  }

  /**
   * ���˳���Դ�ڲɹ���������ƻ��ĸ������뵥�ۺ�VO
   * 
   * @param aggVos
   * @return
   */
  public AggApplyVO[] getFilterAggVos(AggApplyVO[] aggVos) {
    if (ArrayUtils.isEmpty(aggVos)) {
      return null;
    }
    List<AggApplyVO> list = new ArrayList<AggApplyVO>();
    for (AggApplyVO aggVo : aggVos) {
      ApplyBVO[] bodyvos = (ApplyBVO[]) aggVo.getChildren(ApplyBVO.class);
      if (POBillType.Order.getCode().equals(bodyvos[0].getPk_srcbilltypeid())) {
        list.add(aggVo);
      }
    }
    return list.toArray(new AggApplyVO[list.size()]);
  }

  /**
   * ��֯��д����
   * 
   * @param aggVo ��ǰ�ۺ�vo
   * @param origAggVo ԭʼ�ۺ�vo
   * @return ��֯��Ļ�д����map
   */
  public Map<String, List<RewritePara>> getRewritePara(AggApplyVO[] aggVo,
      AggApplyVO[] origAggVo) {
    Map<String, List<RewritePara>> rwParaMap = null;
    BillRewriter billRewriter = this.getBillRewriter();
    if (ArrayUtils.isEmpty(aggVo)) {
      rwParaMap = billRewriter.splitForDelete(origAggVo);
    }
    else if (ArrayUtils.isEmpty(origAggVo)) {
      rwParaMap = billRewriter.splitForInsert(aggVo);
    }
    else {
      rwParaMap = billRewriter.splitForUpdate(aggVo, origAggVo);
    }
    return rwParaMap;
  }

  /**
   * �õ���д����VO����
   * 
   * @param paraList
   * @param currtypeMap
   * @return
   */
  public IOrderWriteBackParaForF3[] getWritebackVO(List<RewritePara> paraList,
      Map<String, String> currtypeMap) {
    IOrderWriteBackParaForF3[] reWritePara =
        new IOrderWriteBackParaForF3[paraList.size()];
    for (int i = 0; i < paraList.size(); i++) {
      OrderWriteBackParaForF3 para = new OrderWriteBackParaForF3();
      para.setM_hid(paraList.get(i).getCsrcid());
      para.setM_bid(paraList.get(i).getCsrcbid());
      para.setM_diffPayorgmny(paraList.get(i).getNnum());
      para.setM_diffPaymny(paraList.get(i).getNnum2());
      para.setM_currency(currtypeMap.get(paraList.get(i).getCsrcid()));
      reWritePara[i] = para;
    }
    return reWritePara;
  }

  /**
   * �ʽ𸶿����뵥��д�ɹ���������ƻ�
   * 
   * @param backVOs���ʽ𸶿����뵥��д��������ƻ�����VO����
   */
  public void writeBack(IOrderWriteBackParaForF3[] paras) {
    new OrderWriteBackForF3BP().writeback(paras);
  }

  /**
   * �õ���д�ɹ���������ƻ��Ĺ�����
   * 
   * @return ��д�ɹ���������ƻ��Ĺ�����ʵ��
   */
  private BillRewriter getBillRewriter() {
    ItemKeyMapping mapping = this.getItemMapping();
    BillRewriter billRewriter = new BillRewriter(mapping);
    billRewriter.addSRCHeadClazz(POBillType.Order.getCode(),
        OrderHeaderVO.class);
    billRewriter.addSRCItemClazz(POBillType.Order.getCode(), PayPlanVO.class);
    return billRewriter;
  }

  private ItemKeyMapping getItemMapping() {
    ItemKeyMapping mapping = new ItemKeyMapping();
    mapping.setVsrctypeKey(ApplyBVO.PK_SRCBILLTYPEID);
    mapping.setCsrcidKey(ApplyBVO.PK_SRCBILL);
    mapping.setCsrcbidKey(ApplyBVO.PK_SRCBILLROWID);
    // ������
    mapping.setNnumKey(ApplyBVO.PAYMNY);
    // ������(��֯����)
    mapping.setNnum2Key(ApplyBVO.OLCPAYMNY);
    return mapping;
  }
}
