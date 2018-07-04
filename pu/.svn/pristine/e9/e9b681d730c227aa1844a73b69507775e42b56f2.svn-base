
package nc.vo.pu.m4t.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 * 单据日期检查:期初暂估单的入库日期不能晚于系统启用日期
 * @scene
 * 期初暂估单保存
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-31 上午10:00:05
 * @author guoyk
 */

public class InitialEstBillDateChkRule implements IRule<InitialEstVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(InitialEstVO[] vos) {

    for (InitialEstVO vo : vos) {
      UFDate startDate =
          PUSysParamUtil.getINI02BeforeDate(vo.getHeader().getPk_org());
      if (startDate == null) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004000_0", "04004000-0122")/*
                                                                     * @res
                                                                     * "采购期初日期（参数INI02）未设置！"
                                                                     */);
        return;
      }
      // 取期初日期的前一天
      UFDate dbilldate = vo.getHeader().getDbilldate();
      if (dbilldate.compareTo(startDate.asLocalEnd()) > 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004060_0", "04004060-0203", null,
                new String[] {
                  dbilldate.toLocalString(), startDate.toLocalString()
                })/*
                   * 期初暂估单的入库日期不能晚于采购期初日期的前一天
                   * ！
                   */);
      }
    }

  }
}
