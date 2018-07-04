package nc.bs.pu.m27.feesettle.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.pu.m27.feesettle.util.FeeSettleDataContext;
import nc.bs.pu.m27.feesettle.util.FeeSettlePrivateUtil;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.est.entity.FeeEstDistVO;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.SettleFeeAllotDetailVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * @description
 * ���·��ý����̯��ϸһЩ��Ϣ
 * (���㵥����������������һ�η����ݹ������־���������ڵĽ��㵥��ID��)
 * ��д�ݹ��������ϵ�һ�ν���Ľ��㵥ID
 * @scene
 * ȡ�����ý���,���ý���
 * @param
 * bdosettle ���㣬����ȡ������
 * datactx ����������
 *
 * @since 6.3
 * @version 2014-10-22 ����4:02:26
 * @author zhangshqb
 */
public class SettleDetailAndEstInfoSetRule implements IRule<SettleBillVO> {

  // ���㣬����ȡ������
  private boolean bdosettle;

  // ����������
  private FeeSettleDataContext datactx;

  public SettleDetailAndEstInfoSetRule(boolean bdosettle,
      FeeSettleDataContext datactx) {
    this.bdosettle = bdosettle;
    this.datactx = datactx;
  }

  @Override
  public void process(SettleBillVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    if (this.bdosettle) {
      this.writeForDoFeeSettle(vos);
    }
    else {
      this.writeForCancelFeeSettle(vos);
    }
  }

  private void fillupHidAndBid(SettleBillVO[] vos,
      MapList<String, SettleFeeAllotDetailVO> details) {
    for (SettleBillVO vo : vos) {
      for (SettleBillItemVO item : vo.getChildrenVO()) {
        String stockbid = item.getPk_stock_b();
        List<SettleFeeAllotDetailVO> relateDetails = details.get(stockbid);
        if (relateDetails == null || relateDetails.size() == 0) {
          continue;
        }
        // ����㵥�����ӱ������
        String hid = item.getPk_settlebill();
        String bid = item.getPk_settlebill_b();
        for (SettleFeeAllotDetailVO bbvo : relateDetails) {
          bbvo.setPk_settlebill(hid);
          bbvo.setPk_settlebill_b(bid);

          // ��Ӧ�ķ������ڵĽ�����ID
          String invoicebid = bbvo.getPk_invoice_b();
          if (StringUtils.isEmpty(invoicebid)) {
            continue;
          }
          String oppofeeid = this.findOppofeeid(vo.getChildrenVO(), invoicebid);
          bbvo.setPk_oppofee_b(oppofeeid);
        }
      }
    }
  }

  private String findOppofeeid(SettleBillItemVO[] childrenVO, String invoicebid) {
    if (ArrayUtils.isEmpty(childrenVO) || StringUtils.isEmpty(invoicebid)) {
      return null;
    }
    for (SettleBillItemVO item : childrenVO) {
      if (!invoicebid.equals(item.getPk_invoice_b())) {
        continue;
      }
      return item.getPk_settlebill_b();
    }
    return null;
  }

  private void updateFirstSettleInfo(
      Map<String, ArrayList<FeeEstVO>> feeEstMpLst) {
    MapList<String, SettleFeeAllotDetailVO> feeStlMpLst =
        this.datactx.getNoneSavedAllotDetails();
    if (null == feeStlMpLst || feeStlMpLst.size() == 0) {
      return;
    }
    for (Entry<String, List<SettleFeeAllotDetailVO>> entry : feeStlMpLst
        .entrySet()) {
      for (SettleFeeAllotDetailVO feestlVo : entry.getValue()) {
        feestlVo.setBestfirstsettle(UFBoolean.FALSE);// �ȳ�ʼ��Ϊfalse
      }
    }
    if (null == feeEstMpLst || feeEstMpLst.size() == 0) {
      return;
    }
    List<FeeEstVO> updFeeEstLst = new ArrayList<FeeEstVO>();
    for (Entry<String, ArrayList<FeeEstVO>> entry : feeEstMpLst.entrySet()) {
      String stockbid = entry.getKey();
      for (FeeEstVO festVo : entry.getValue()) {
        List<SettleFeeAllotDetailVO> feeStlLst = feeStlMpLst.get(stockbid);
        String firstOppbid = null;
        String marvid = festVo.getPk_srcfeematerial();
        for (SettleFeeAllotDetailVO feeStlVo : feeStlLst) {
          String stlMarvid = feeStlVo.getPk_srcmaterial();
          String oppbid = feeStlVo.getPk_oppofee_b();
          if (null == firstOppbid && marvid.equals(stlMarvid)
              || null != firstOppbid && firstOppbid.equals(oppbid)) {
            firstOppbid = oppbid;
            feeStlVo.setBestfirstsettle(UFBoolean.TRUE);
            festVo.setPk_firstsettle_b(firstOppbid);
            festVo.setPk_firstsettle(feeStlVo.getPk_settlebill());
          }
        }
        if (null != firstOppbid) {
          festVo.setStatus(VOStatus.UPDATED);
          updFeeEstLst.add(festVo);
        }
      }
    }

    if (updFeeEstLst.size() > 0) {
      FeeEstVO[] festVos =
          new ListToArrayTool<FeeEstVO>().convertToArray(updFeeEstLst);
      new VOUpdate<FeeEstVO>().update(festVos, new String[] {
        FeeEstVO.PK_FIRSTSETTLE, FeeEstVO.PK_FIRSTSETTLE_B
      });
    }
  }

  private void writeForCancelFeeSettle(SettleBillVO[] vos) {
    // ��ȡ���ý��㵥
    List<SettleBillVO> feevos = FeeSettlePrivateUtil.findNeedFeeSettleVO(vos);
    if (feevos == null || feevos.size() == 0) {
      return;
    }
    // �����ݹ���ϸ
    FeeEstVO[] bbItems = this.datactx.getEstBbItems();
    if (bbItems == null) {
      return;
    }
    // ��շ����ݹ���ϸ������¼�ĵ�һ�η��ý����Ӧ��ID
    for (FeeEstVO fifee : bbItems) {
      fifee.setPk_firstsettle(null);
      fifee.setPk_firstsettle_b(null);
      fifee.setStatus(VOStatus.UPDATED);
    }
    String[] names = new String[2];
    names[0] = FeeEstVO.PK_FIRSTSETTLE;
    names[1] = FeeEstVO.PK_FIRSTSETTLE_B;
    new VOUpdate<FeeEstVO>().update(bbItems, names);
  }

  private void writeForDoFeeSettle(SettleBillVO[] vos) {
    MapList<String, SettleFeeAllotDetailVO> details =
        this.datactx.getNoneSavedAllotDetails();
    if (null == details || details.size() == 0) {
      return;
    }

    // ���÷�̯��ϸ��һЩ����
    this.fillupHidAndBid(vos, details);
    // ��ȡ���ý��㵥
    List<SettleBillVO> feevos = FeeSettlePrivateUtil.findNeedFeeSettleVO(vos);
    if (feevos == null || feevos.size() == 0) {
      return;
    }
    // �����ݹ���ϸ�� �����ݹ���̯��ϸ
    FeeEstVO[] bbItems = this.datactx.getEstBbItems();
    FeeEstDistVO[] bbbItems = this.datactx.getEstBbbItems();
    if (bbbItems == null || bbItems == null) {
      return;
    }
    // <��ⵥ�������������ݹ�VO�б�>
    HashMap<String, ArrayList<FeeEstVO>> m45estvos =
        CirVOUtil.getFieldVOList(bbItems, FeeEstVO.PK_STOCKPS_B);

    // ���·��ý�����ϸ���Ƿ��һ�ν���ı�ʶ���ݹ���һ�ν���ID��
    this.updateFirstSettleInfo(m45estvos);

  }
}
