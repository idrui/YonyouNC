/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-31 下午06:33:21
 */
package nc.bs.pu.m20.maintain.rule.delete;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>制造计划定单“取消下达”功能
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-31 下午06:33:21
 */
public class RearOrderDelete implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] voArray) {
    // 制造计划定单维护增加“取消下达”功能， 目前生成请购单，删除该单不能取消下达
    // this.delete(voArray);

  }

  // private void delete(PraybillVO[] vos) {
  //
  // List<WriteBackVOForMM> deForMO = new ArrayList<WriteBackVOForMM>();
  // List<PoRewriteParamVO> deForPO = new ArrayList<PoRewriteParamVO>();
  //
  // for (PraybillVO vo : vos) {
  // int fpraysource = vo.getHVO().getFpraysource().intValue();
  // // 来源 0:计划订单 1:生产订单
  // if (EnumPraySource.MO.toInt() == fpraysource) {
  // this.fillDeForMO(vo, deForMO);
  // }
  // if (EnumPraySource.MRP.toInt() == fpraysource) {
  // this.fillDeForPO(vo, deForPO);
  // }
  // }
  // if (!CollectionUtils.isEmpty(deForMO)) {
  // INumRewritePara[] paras =
  // deForMO.toArray(new WriteBackVOForMM[deForMO.size()]);
  // IPublicMoService4PU serv =
  // NCLocator.getInstance().lookup(IPublicMoService4PU.class);
  // try {
  // serv.rewrite4DeletePrayOrder(paras);
  // }
  // catch (BusinessException e) {
  // ExceptionUtils.wrappException(e);
  // }
  // }
  // if (!CollectionUtils.isEmpty(deForMO)) {
  // PoRewriteParamVO[] paras =
  // deForPO.toArray(new PoRewriteParamVO[deForPO.size()]);
  // IPublicPoService4PU serv =
  // NCLocator.getInstance().lookup(IPublicPoService4PU.class);
  // try {
  // serv.rewrite4DeletePrayOrder(paras);
  // }
  // catch (BusinessException e) {
  // ExceptionUtils.wrappException(e);
  // }
  // }
  // }
  //
  // private void fillDeForMO(PraybillVO vo, List<WriteBackVOForMM> deForMO) {
  //
  // PraybillItemVO[] children = vo.getBVO();
  // for (PraybillItemVO child : children) {
  // String hid = child.getCsourceid();
  // String bid = null;
  // UFDouble assNum = child.getNastnum();
  // UFDouble nnum = child.getNnum();
  // WriteBackVOForMM wbvo = new WriteBackVOForMM(bid, hid, assNum, nnum);
  // deForMO.add(wbvo);
  // }
  //
  // }
  //
  // private void fillDeForPO(PraybillVO vo, List<PoRewriteParamVO> deForPO) {
  // PraybillItemVO[] children = vo.getBVO();
  // for (PraybillItemVO child : children) {
  // String hid = child.getCsourceid();
  // UFDouble nnum = child.getNnum();
  // // this.bid = bid;
  // // this.hid = hid;
  // // this.assNum = assNum;
  // // this.nnum = nnum;
  // PoRewriteParamVO wbvo = new PoRewriteParamVO();
  // wbvo.setCpoid(hid);
  // wbvo.setDeltaNprayNum(nnum);
  // deForPO.add(wbvo);
  // }
  // }
}
