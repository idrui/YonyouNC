package nc.pubimpl.pu.m20.mmdp.altc;

import nc.pubitf.pu.m20.mmdp.altc.IUpdateParyOrderService;

/**
 * �������� �������ť����
 * ҵ���߼� ���ݵ��������ͱ����������빺��
 * 
 * 2015��7��24�� wangweir ����V65�汾����Ҫ�˽ӿڣ���������û�нӿڴ���˴���ע�� Begin
 * end
 * 
 * @since 6.36
 * @version 2015-2-9 ����9:35:51
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
  // // ��ȡ���б���ı�������
  // for (PraybillVO updatevo : orivos) {
  // headMap.put(updatevo.getHVO().getPk_praybill(), updatevo);
  // PraybillVO newupvo = (PraybillVO) updatevo.clone();
  // updateList.add(newupvo);
  // PraybillItemVO[] items = newupvo.getBVO();
  // for (PraybillItemVO item : items) {
  // // �����ޱ��
  // if (null == itemMap.get(item.getPk_praybill_b())) {
  // continue;
  // }
  // if (!bodyList.contains(item)) {
  // bodyList.add(item);
  // }
  // }
  // }
  // // �������������
  // this.processBodyData(bodyList, itemMap);
  //
  // PraybillVO[] updatevos = updateList.toArray(new PraybillVO[0]);
  // // �����������������ۡ�����
  // this.calculate(updatevos);
  // // ���µ��ݣ�����̬��ά�����ݣ�����̬���޶�����
  // this.updateOrder(updatevos, headMap);
  // }
  //
  // /**
  // * ��������
  // */
  // private void calculate(PraybillVO[] updateVos) {
  // RelationCalculate calculate = new RelationCalculate();
  // for (PraybillVO updatevo : updateVos) {
  // // �����������������ۡ�����
  // calculate.calculate(updatevo, PraybillItemVO.NNUM);
  // }
  // }
  //
  // /**
  // * ���������������������������
  // */
  // private void processBodyData(List<PraybillItemVO> bodyList,
  // Map<String, ChangeDetail[]> itemMap) {
  // for (PraybillItemVO body : bodyList) {
  // body.setStatus(VOStatus.UPDATED);
  // ChangeDetail[] changeItems = itemMap.get(body.getPk_praybill_b());
  // for (ChangeDetail changeitem : changeItems) {
  // String filedname = changeitem.getFieldPath();
  // // ֻ�������������ڱ��
  // if (filedname.equals("pk_praybill_b." + PraybillItemVO.NNUM)) {
  // UFDouble nnum = ValueUtils.getUFDouble(changeitem.getValue());
  // UFDouble orinnum =
  // ValueUtils.getUFDouble(changeitem.getOriginValue());
  // // ���ǰ��ֵ�������в�һ��
  // if (!orinnum.equals(body.getNnum())) {
  // ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
  // .getNCLangRes().getStrByID("4004000_0", "04004000-0154")/*
  // * @res
  // * "���޸ĵ������Ѿ����ڱ仯������ִ�б����"
  // */);
  // }
  // body.setNnum(nnum);
  // }
  // if (filedname.equals("pk_praybill_b." + PraybillItemVO.DREQDATE)) {
  // UFDate dplandate = ValueUtils.getUFDate(changeitem.getValue());
  // UFDate oridate = ValueUtils.getUFDate(changeitem.getOriginValue());
  // // ���ڣ����ǰ��ֵ�������в�һ��
  // if (!oridate.equals(body.getDreqdate())) {
  // ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
  // .getNCLangRes().getStrByID("4004000_0", "04004000-0154")/*
  // * @res
  // * "���޸ĵ������Ѿ����ڱ仯������ִ�б����"
  // */);
  // }
  // body.setDreqdate(dplandate);
  // }
  // }
  // }
  // }
  //
  // /**
  // * ���µ��ݣ�����̬��ά�����ݣ�����̬���޶�����
  // */
  // private void updateOrder(PraybillVO[] updatevos,
  // Map<String, PraybillVO> headMap) {
  // List<PraybillVO> updateList = new ArrayList<PraybillVO>();// ����̬��ά������
  // List<PraybillVO> updateOriList = new ArrayList<PraybillVO>();
  // List<PraybillVO> reviseList = new ArrayList<PraybillVO>();// ����̬���޸Ķ�����
  // List<PraybillVO> reviseOriList = new ArrayList<PraybillVO>();
  // for (PraybillVO vo : updatevos) {
  // PraybillHeaderVO headvo = vo.getHVO();
  // if (!(POEnumBillStatus.FREE.toInteger().equals(headvo.getFbillstatus()) ||
  // POEnumBillStatus.APPROVE
  // .toInteger().equals(headvo.getFbillstatus()))) {
  // ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
  // .getNCLangRes().getStrByID("4004000_0", "04004000-0155")/*
  // * @res
  // * "���޸ĵĵ��ݷ�����̬������̬������ִ�б����"
  // */);
  // }
  // if (POEnumBillStatus.FREE.toInteger().equals(headvo.getFbillstatus())) {
  // updateList.add(vo);
  // updateOriList.add(headMap.get(headvo.getPk_praybill()));
  // }
  // if (POEnumBillStatus.APPROVE.toInteger().equals(headvo.getFbillstatus())) {
  // int verision = headvo.getNversion().intValue() + 1;
  // headvo.setNversion(Integer.valueOf(verision));// �汾��
  // reviseList.add(vo);
  // reviseOriList.add(headMap.get(headvo.getPk_praybill()));
  // }
  // }
  // // ���µ���
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
