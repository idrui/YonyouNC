/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-25 下午08:27:50
 */
package nc.vo.pu.m20.rule;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单保存时请购日期、需求日期、建议订货日期检查规则
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-25 下午08:27:50
 */
public class ChkDate {
  /**
   * 方法功能描述：请购日期、需求日期、建议订货日期检查规则。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 下午04:27:00
   */
  public void checkDate(PraybillVO vo) {
    PraybillItemVO[] bvos = vo.getBVO();
    for (PraybillItemVO bvo : bvos) {
      // 需求日期
      UFDate reqdate = bvo.getDreqdate();
      if (null != reqdate) {
        reqdate = reqdate.asBegin();
      }
      // 建议订货日期
      UFDate suggestdate = bvo.getDsuggestdate();
      if (null != suggestdate) {
        suggestdate = suggestdate.asBegin();
      }

      String sRowNo = bvo.getCrowno();
      if (null != suggestdate && null != reqdate && suggestdate.after(reqdate)) {
        String errorMsg =
            NCLangRes4VoTransl.getNCLangRes().getStrByID("4004020_0",
                "04004020-0093", null, new String[] {
                  sRowNo
                })/* 表体行{0}:建议订货日期必须小于需求日期! */;
        ExceptionUtils.wrappBusinessException(errorMsg);
      }

      // yangtian修改的一个bug，要求前台询问请购日期是否可以大于需求日期，
      // 所以将后台的这个判断先删掉，否则前台用户要求可以，后台却抛异常，将造成不一致。
    }
  }
}
