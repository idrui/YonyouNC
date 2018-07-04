package nc.bs.pu.m23.maintain.rule;

import java.util.List;
import java.util.Map;

import nc.bs.pu.m23.writeback.pu.m23.Writeback23For23BP;
import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.ItemKeyMapping;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.enumeration.EnumOperate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SCBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * ���ڵ������˻����˻�����д��Դ����������
 * �˴���ͬ�ڵ��͵Ļ�д����Ϊ�ǻ�дͬ���͵ĵ��ݣ����Դ˴�ֱ�Ӵ���û������ӿ�
 * @scene
 * 
 * @param
 * ��
 *
 * @since 6.3
 * @version 2012-8-10 ����03:34:27
 * @author lixyp
 */


public class WriteBackFor23Rule implements ICompareRule<ArriveVO> {

  private EnumOperate operateType = null;

  public WriteBackFor23Rule(EnumOperate operateType) {
    this.operateType = operateType;
  }

  @Override
  public void process(ArriveVO[] vos, ArriveVO[] originVos) {
    try {
      if (ArrayUtils.isEmpty(vos)) {
        return;
      }

      // ���ж������������ڶ������˻������ı��涼��������߼�������BP���У��ж������û����Դ����������ֱ�ӷ��أ����Զ���������û��Ӱ�졣
      Map<String, List<RewritePara>> rewriteParas =
          this.getRewriterParas(vos, originVos);
      List<RewritePara> poPara = rewriteParas.get(POBillType.Order.getCode());
      List<RewritePara> scPara = rewriteParas.get(SCBillType.Order.getCode());
      if (poPara != null) {
        new Writeback23For23BP().writeBack(poPara);
      }
      else if (scPara != null) {
        new Writeback23For23BP().writeBack(scPara);
      }

      // �����Դʱ��������ڵ������˻��ĳ�������Ϊ��ԴTS�Ե��ǵ�������TS������յĻ���������д����ʱ�ᱨ������
      this.clearSourceTs(vos);
    }
    catch (Exception e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
  }

  /**
   * �����ԴTS��ֻ��Ի��ڵ������˻��ĳ�����
   * 
   * @param vos
   */
  private void clearSourceTs(ArriveVO[] vos) {
    for (ArriveVO vo : vos) {
      for (ArriveItemVO itemVo : vo.getBVO()) {
        if (itemVo.getCsourcearrivebid() != null) {
          itemVo.setSourcets(null);
          itemVo.setSourcebts(null);
        }
      }
    }
  }

  /**
   * ʹ�û�д���ߣ���ȡ��д������
   * 
   * @param vos ��VO
   * @param originVos ԭʼVO
   * @return
   */
  private Map<String, List<RewritePara>> getRewriterParas(ArriveVO[] vos,
      ArriveVO[] originVos) {
    ItemKeyMapping keymapping = new ItemKeyMapping();
    // ��Դ�������ͣ���Ȼ���ڵ������˻�������Ȼ��¼���Ƕ���������Ƚ����⡣
    keymapping.setVsrctypeKey(ArriveItemVO.CSOURCETYPECODE);
    // ��Դ������
    keymapping.setCsrcidKey(ArriveItemVO.CSOURCEARRIVEID);
    // ��Դ��������ϸ
    keymapping.setCsrcbidKey(ArriveItemVO.CSOURCEARRIVEBID);
    // ����������
    keymapping.setNnumKey(ArriveItemVO.NNUM);
    keymapping.setSrcTSKey(ArriveItemVO.SOURCETS);
    keymapping.setSrcbTSKey(ArriveItemVO.SOURCEBTS);

    BillRewriter tool = new BillRewriter(keymapping);
    tool.addSRCHeadClazz(POBillType.Arrive.getCode(), ArriveHeaderVO.class);
    tool.addSRCItemClazz(POBillType.Arrive.getCode(), ArriveItemVO.class);

    if (EnumOperate.ADD.equals(this.operateType)) {
      return tool.splitForInsert(vos);
    }
    else if (EnumOperate.DELETE.equals(this.operateType)) {
      return tool.splitForDelete(vos);
    }
    else {
      return tool.splitForUpdate(vos, originVos);
    }
  }
}
