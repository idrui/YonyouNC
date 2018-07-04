/**
 * $�ļ�˵��$
 *
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-27 ����08:37:08
 */
package nc.impl.pu.m20.action;

import nc.bs.pu.m20.maintain.PraybillInsertBP;
import nc.impl.pu.m20.plugin.ActionPlugInPoint;
import nc.impl.pu.m20.rule.ChkPrayVONotNullRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pubapp.pattern.log.TimeLog;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b> ����action��
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-1-27 ����08:37:08
 */
public class PraybillInsertAction {

  public PraybillVO[] insert(PraybillVO[] praybillVos) {
    if (ArrayUtils.isEmpty(praybillVos)) {
      return null;
    }
    TimeLog.logStart();

    AroundProcesser<PraybillVO> processer =
        new AroundProcesser<PraybillVO>(ActionPlugInPoint.InsertAction);
    // ����Action�е�ִ��ǰ/�����
    this.addBeforeRule(processer);

    processer.before(praybillVos);

    PraybillInsertBP action = new PraybillInsertBP();
    PraybillVO[] vos = action.insert(praybillVos);

    processer.after(praybillVos);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004020_0", "04004020-0052")/* @res "ҵ����־:��̨���뵥������" */);
    return vos;

  }

  private void addBeforeRule(AroundProcesser<PraybillVO> processer) {
    // ��ӹ���˳���ܸ��ģ���
    // ������ȷ�Լ��
    processer.addBeforeRule(new ChkPrayVONotNullRule());
  }

}
