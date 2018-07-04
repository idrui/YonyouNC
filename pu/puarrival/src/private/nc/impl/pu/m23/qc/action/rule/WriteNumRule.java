package nc.impl.pu.m23.qc.action.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.qc.pub.util.QCSysParamUtil;

/**
 * 
 * @description
 *            启用质检模块后，点击(报检)检验则更新到货单的累计报检主数量和累计合格主数量以及累计不合格主数量
 * @scene
 *      启用质检模块时，到货单检验
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-4-14 下午9:02:40
 * @author zhangshqb
 */
public class WriteNumRule implements IRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] vos) {
    String pk_org = vos[0].getHVO().getPk_org();
    if (SysInitGroupQuery.isQCEnabled()
        && UFBoolean.TRUE.equals(ValueUtils.getUFBoolean(QCSysParamUtil
            .getINI01(pk_org)))) {
      List<ArriveItemVO> volists = new ArrayList<ArriveItemVO>();
      for (ArriveVO arriveVO : vos) {
        ArriveItemVO[] bvos = arriveVO.getBVO();
        for (ArriveItemVO bvo : bvos) {
          UFDouble naccumstorenum = bvo.getNaccumstorenum();
          UFDouble naccumchecknum = bvo.getNaccumchecknum();
          // 将累计报检主数量和累计合格主数量以及累计不合格主数量清零
          bvo.setNaccumchecknum(UFDouble.ZERO_DBL);
          bvo.setNelignum(UFDouble.ZERO_DBL);
          bvo.setNnotelignum(UFDouble.ZERO_DBL);
          // 判断是否存在累计入库主数量，
          if (naccumstorenum != null
              && naccumstorenum.compareTo(UFDouble.ZERO_DBL) > 0) {
            bvo.setNnum(bvo.getNnum().sub(
                naccumchecknum == null ? UFDouble.ZERO_DBL : naccumchecknum));
            bvo.setNaccumstorenum(UFDouble.ZERO_DBL);
            String pk_material = bvo.getPk_material();
            String castunitid = bvo.getCastunitid();
            String changerate =
                MaterialPubService.queryMainMeasRateByMaterialAndMeasdoc(
                    pk_material, castunitid);
            UFDouble hsl = HslParseUtil.getUFDoubleHsl(changerate);
            // 如果换算率查不到，默认为1/1
            if (hsl == null) {
              bvo.setNastnum(bvo.getNnum());
            }
            else {
              UFDouble nastnum = bvo.getNnum().div(hsl);
              bvo.setNastnum(nastnum);
            }
          }
          else {
            volists.add(bvo);
          }
        }
      }
      // 更新到数据库
      VOUpdate<ArriveItemVO> voUpdate = new VOUpdate<ArriveItemVO>();
      if (volists.size() == 0) {
        return;
      }
      voUpdate.update(volists.toArray(new ArriveItemVO[0]), new String[] {
        ArriveItemVO.NACCUMCHECKNUM, ArriveItemVO.NELIGNUM,
        ArriveItemVO.NNOTELIGNUM
      });
    }
  }
}
