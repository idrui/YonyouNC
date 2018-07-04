package nc.pubimpl.pu.m20.mmdp.altc;

import nc.pubitf.pu.m20.mmdp.altc.IUpdateParyOrderService;

/**
 * 场景描述 变更处理按钮触发
 * 业务逻辑 根据单据主键和变更建议更新请购单
 * 
 * 2015年7月24日 wangweir 生产V65版本不需要此接口，并且生产没有接口代码此处先注释 Begin
 * end
 * 
 * @since 6.36
 * @version 2015-2-9 下午9:35:51
 * @author mengjian
 */
public class UpdateParyOrderServiceImpl implements IUpdateParyOrderService {

  // @Override
  // public void updateBill(UpdateParam[] params) throws BusinessException {
  // List<PraybillItemVO> bodyList = new ArrayList<PraybillItemVO>();
  // List<String> hidList = new ArrayList<String>();
  // Map<String, ChangeDetail[]> itemMap = new HashMap<String,
  // ChangeDetail[]>();
  // Map<String, PraybillVO> headMap = new HashMap<String, PraybillVO>();
  // if (null == params) {
  // return;
  // }
  // for (UpdateParam param : params) {
  // if (!hidList.contains(param.getId())) {
  // hidList.add(param.getId());
  // }
  // itemMap.put(param.getBid(), param.getChangedetails());
  // }
  // BillQuery<PraybillVO> query = new BillQuery<PraybillVO>(PraybillVO.class);
  // PraybillVO[] orivos = query.query(hidList.toArray(new String[0]));
  // List<PraybillVO> updateList = new ArrayList<PraybillVO>();
  // // 抽取出有变更的表体数据
  // for (PraybillVO updatevo : orivos) {
  // headMap.put(updatevo.getHVO().getPk_praybill(), updatevo);
  // PraybillVO newupvo = (PraybillVO) updatevo.clone();
  // updateList.add(newupvo);
  // PraybillItemVO[] items = newupvo.getBVO();
  // for (PraybillItemVO item : items) {
  // // 表体无变更
  // if (null == itemMap.get(item.getPk_praybill_b())) {
  // continue;
  // }
  // if (!bodyList.contains(item)) {
  // bodyList.add(item);
  // }
  // }
  // }
  // // 处理表体变更数据
  // this.processBodyData(bodyList, itemMap);
  //
  // PraybillVO[] updatevos = updateList.toArray(new PraybillVO[0]);
  // // 联动计算数量、单价、金额等
  // this.calculate(updatevos);
  // // 更新单据，自由态走维护单据，审批态走修订单据
  // this.updateOrder(updatevos, headMap);
  // }
  //
  // /**
  // * 联动计算
  // */
  // private void calculate(PraybillVO[] updateVos) {
  // RelationCalculate calculate = new RelationCalculate();
  // for (PraybillVO updatevo : updateVos) {
  // // 联动计算数量、单价、金额等
  // calculate.calculate(updatevo, PraybillItemVO.NNUM);
  // }
  // }
  //
  // /**
  // * 方法功能描述：处理表体变更数据
  // */
  // private void processBodyData(List<PraybillItemVO> bodyList,
  // Map<String, ChangeDetail[]> itemMap) {
  // for (PraybillItemVO body : bodyList) {
  // body.setStatus(VOStatus.UPDATED);
  // ChangeDetail[] changeItems = itemMap.get(body.getPk_praybill_b());
  // for (ChangeDetail changeitem : changeItems) {
  // String filedname = changeitem.getFieldPath();
  // // 只处理数量及日期变更
  // if (filedname.equals("pk_praybill_b." + PraybillItemVO.NNUM)) {
  // UFDouble nnum = ValueUtils.getUFDouble(changeitem.getValue());
  // UFDouble orinnum =
  // ValueUtils.getUFDouble(changeitem.getOriginValue());
  // // 变更前的值与数据中不一致
  // if (!orinnum.equals(body.getNnum())) {
  // ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
  // .getNCLangRes().getStrByID("4004000_0", "04004000-0154")/*
  // * @res
  // * "所修改的数据已经存在变化，不可执行变更。"
  // */);
  // }
  // body.setNnum(nnum);
  // }
  // if (filedname.equals("pk_praybill_b." + PraybillItemVO.DREQDATE)) {
  // UFDate dplandate = ValueUtils.getUFDate(changeitem.getValue());
  // UFDate oridate = ValueUtils.getUFDate(changeitem.getOriginValue());
  // // 日期，变更前的值与数据中不一致
  // if (!oridate.equals(body.getDreqdate())) {
  // ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
  // .getNCLangRes().getStrByID("4004000_0", "04004000-0154")/*
  // * @res
  // * "所修改的数据已经存在变化，不可执行变更。"
  // */);
  // }
  // body.setDreqdate(dplandate);
  // }
  // }
  // }
  // }
  //
  // /**
  // * 更新单据，自由态走维护单据，审批态走修订单据
  // */
  // private void updateOrder(PraybillVO[] updatevos,
  // Map<String, PraybillVO> headMap) {
  // List<PraybillVO> updateList = new ArrayList<PraybillVO>();// 自由态，维护单据
  // List<PraybillVO> updateOriList = new ArrayList<PraybillVO>();
  // List<PraybillVO> reviseList = new ArrayList<PraybillVO>();// 审批态，修改订单据
  // List<PraybillVO> reviseOriList = new ArrayList<PraybillVO>();
  // for (PraybillVO vo : updatevos) {
  // PraybillHeaderVO headvo = vo.getHVO();
  // if (!(POEnumBillStatus.FREE.toInteger().equals(headvo.getFbillstatus()) ||
  // POEnumBillStatus.APPROVE
  // .toInteger().equals(headvo.getFbillstatus()))) {
  // ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
  // .getNCLangRes().getStrByID("4004000_0", "04004000-0155")/*
  // * @res
  // * "所修改的单据非自由态或审批态，不可执行变更。"
  // */);
  // }
  // if (POEnumBillStatus.FREE.toInteger().equals(headvo.getFbillstatus())) {
  // updateList.add(vo);
  // updateOriList.add(headMap.get(headvo.getPk_praybill()));
  // }
  // if (POEnumBillStatus.APPROVE.toInteger().equals(headvo.getFbillstatus())) {
  // int verision = headvo.getNversion().intValue() + 1;
  // headvo.setNversion(Integer.valueOf(verision));// 版本号
  // reviseList.add(vo);
  // reviseOriList.add(headMap.get(headvo.getPk_praybill()));
  // }
  // }
  // // 更新单据
  // PraybillContext contxt = new PraybillContext();
  // if (updateList.size() > 0) {
  // new PraybillUpdateAction().update(updateList.toArray(new PraybillVO[0]));
  // }
  // if (reviseList.size() > 0) {
  // new PraybillRAction().revise(updateList.toArray(new PraybillVO[0]),
  // contxt);
  // }
  // }

}
