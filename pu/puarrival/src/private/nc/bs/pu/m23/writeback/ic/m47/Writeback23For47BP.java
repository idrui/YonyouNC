package nc.bs.pu.m23.writeback.ic.m47;

import nc.bs.pu.m23.plugin.ArriveBPPlugInPoint;
import nc.bs.pu.m23.writeback.ic.rule.ChkBCreateFaCardRule;
import nc.bs.pu.m23.writeback.ic.rule.ChkBStoreNumRule;
import nc.bs.pu.m23.writeback.ic.rule.UpdateBBStoreNumRule;
import nc.bs.pu.m23.writeback.ic.rule.UpdateViewStoreNumRule;
import nc.bs.pu.m23.writeback.ic.rule.WriteAccumLetgoInnumRule;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubitf.pu.m23.ic.m47.IWriteback23For47Para;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveViewVO;
import nc.vo.pu.m23.utils.ArrivePublicUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��д���������ۼ����������ί�мӹ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-13 ����10:47:37
 */
public class Writeback23For47BP {

  /**
   * ����������������д���������ۼ����������ί�мӹ���ⵥ
   * <p>
   * <b>����˵��</b>
   * 
   * @param paraArray��д��������
   *          <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-13 ����10:18:08
   */
  public void writeback(IWriteback23For47Para[] paras) {
    if (ArrayUtils.isEmpty(paras)) {
      return;
    }
    // ����д�������Ĳ���
    this.checkWritePara(paras);

    // ��ѯ������������ͼVO
    String[] bidArray = ArrivePublicUtil.getWriteParaBidArray(paras);
    ViewQuery<ArriveViewVO> q = new ViewQuery<ArriveViewVO>(ArriveViewVO.class);
    q.setSharedHead(true);
    ArriveViewVO[] oldViewVOArray = q.query(bidArray);

    // ���ִ��ҵ�����
    ArriveBPPlugInPoint point = ArriveBPPlugInPoint.Writeback23For47BP;
    AroundProcesser<ArriveViewVO> pc = new AroundProcesser<ArriveViewVO>(point);
    this.addBeforeFinalRule(pc, paras);
    this.addAfterRule(pc, paras);

    pc.before(oldViewVOArray);

    // �־û�
    String[] names = new String[3];
    names[0] = ArriveItemVO.NACCUMSTORENUM;
    names[1] = ArriveItemVO.BLETGOSTATE;
    names[2] = ArriveItemVO.NACCUMLETGOINNUM;
    ViewUpdate<ArriveViewVO> bo = new ViewUpdate<ArriveViewVO>();
    oldViewVOArray = bo.update(oldViewVOArray, ArriveItemVO.class, names);

    pc.after(oldViewVOArray);
  }

  private void addAfterRule(AroundProcesser<ArriveViewVO> processer,
      IWriteback23For47Para[] paras) {
    // �����ӱ����������ݲ���
    processer.addAfterRule(new ChkBStoreNumRule(UFBoolean.valueOf(paras[0]
        .isNumUserConfirm())));
  }

  private void addBeforeFinalRule(AroundProcesser<ArriveViewVO> processer,
      IWriteback23For47Para[] paras) {
    // ���������ⵥ�ĵ��������Ƿ�����Ѿ������ʲ���Ƭ
    processer.addBeforeFinalRule(new ChkBCreateFaCardRule());

    // ���µ��������ӱ���ۼ��������
    processer.addBeforeFinalRule(new UpdateBBStoreNumRule(paras));

    // ������ͼVO�е��ۼ��������
    processer.addBeforeFinalRule(new UpdateViewStoreNumRule(paras));

    // ��д�������е����ۼ��������
    processer.addBeforeFinalRule(new WriteAccumLetgoInnumRule(paras));
  }

  private void checkWritePara(IWriteback23For47Para[] paras) {
    for (IWriteback23For47Para para : paras) {
      if (StringUtils.isEmpty(para.getHID())) {
        String message =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
                "04004040-0092")/* @res "��д������ʱ�����еĵ�������ͷ����������Ϊ�գ����飡" */;
        ExceptionUtils.wrappBusinessException(message);
      }
      if (StringUtils.isEmpty(para.getBID())) {
        String message =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
                "04004040-0093")/* @res "��д������ʱ�����еĵ�������������������Ϊ�գ����飡" */;
        ExceptionUtils.wrappBusinessException(message);
      }
    }
  }
}
