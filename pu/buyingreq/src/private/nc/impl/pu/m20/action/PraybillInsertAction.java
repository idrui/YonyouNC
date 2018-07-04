/**
 * $文件说明$
 *
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-27 下午08:37:08
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
 * <b>本类主要完成以下功能：</b> 保存action类
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-1-27 下午08:37:08
 */
public class PraybillInsertAction {

  public PraybillVO[] insert(PraybillVO[] praybillVos) {
    if (ArrayUtils.isEmpty(praybillVos)) {
      return null;
    }
    TimeLog.logStart();

    AroundProcesser<PraybillVO> processer =
        new AroundProcesser<PraybillVO>(ActionPlugInPoint.InsertAction);
    // 加入Action中的执行前/后规则
    this.addBeforeRule(processer);

    processer.before(praybillVos);

    PraybillInsertBP action = new PraybillInsertBP();
    PraybillVO[] vos = action.insert(praybillVos);

    processer.after(praybillVos);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004020_0", "04004020-0052")/* @res "业务日志:后台插入单据数据" */);
    return vos;

  }

  private void addBeforeRule(AroundProcesser<PraybillVO> processer) {
    // 添加规则顺序不能更改！！
    // 参数正确性检查
    processer.addBeforeRule(new ChkPrayVONotNullRule());
  }

}
