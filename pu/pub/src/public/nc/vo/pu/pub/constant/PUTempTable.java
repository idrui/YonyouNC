package nc.vo.pu.pub.constant;

/**
 * �ɹ������ʱ��ö��<br>
 * Ϊͳһ�����������ʱ�����뽫ʹ�õ�����ʱ����Ƭע��
 * 
 * @since 6.0
 * @version 2011-4-20 ����10:43:07
 * @author zhaoyha
 */
public enum PUTempTable {
  /* ================================ �����������뵥 ================================ */
  /** ά�޼ƻ��ر�ʱʱɾ�������빺�������ڲ�ѯ�빺��ʱƴcsourceid ��in sql */
  tmp_po_422X_01,
  /** ά�޼ƻ��ر�ʱʱɾ�������빺�������ڲ�ѯ�빺��ʱƴcsourcebid ��in sql */
  tmp_po_422X_02,
  /** �����������뵥��д�ۼ����������� */
  tmp_po_422X_03,
  /** ���ݱ�����Դbid��ѯ�����������뵥��ʱ�� */
  tmp_po_422X_04,
  /** makeGetSqlByHeadId*/
  tmp_po_422x_05,
  /** makeGetSqlByItemIds*/
  tmp_po_422x_06,
  /* ================================ �빺�� ================================ */
  /** �ر��빺���У��޸Ĳɹ��ƻ�Ԥռ�������ʱ���빺������ID��ʱ�� */
  tmp_po_20_01,

  /** �����ƻ�����ת�빺����β��� **/
  tmp_po_20_02,

  /** ����������д�빺�����������������ֶ� **/
  tmp_po_20_03,

  /** DelOldVersionRule - deleteOld **/
  tmp_po_20_04,

  /** QueryFor4b36BP - makeGetSqlByItemIds **/
  tmp_po_20_05,

  /** ReWrite20ForECBP - upDel **/
  tmp_po_20_06,

  /** QueryPrayAction - getSqlB **/
  tmp_po_20_07,

  /** QueryPrayBillImpl - queryViewByItemPK **/
  tmp_po_20_08,

  /** Delete20For30Action - getPraybillKeysSql **/
  tmp_po_20_09,

  /** QueryFor4b36BP - makeGetSqlByHeadId **/
  tmp_po_20_10,

  /** PrayNoOrderAlertԤ������ƴװ��֯in sql����ʱ�� */
  tmp_po_20_11,

  /** PrayAudittedNotCreateOrderAlartԤ������ƴװ��֯in sql����ʱ�� */
  tmp_po_20_12,

  /** PrayAudittedNotCreateOrderAlartԤ������ƴװ��֯in sql����ʱ��2 */
  tmp_po_20_13,

  /** ����������д�빺�����������������ֶ�2 **/
  tmp_po_20_14,
  
  /** ��ɢ����ECA��ѯ�빺����Ϣʱ���빺���ӿ�ʵ�������½��Ĳ�����ʱ�� **/
  tmp_po_20_15,
  
  /** Delete20For4A08Action - queryViewQuerySql ƴ��Դid�� in sql����ʱ�� **/
  tmp_po_20_16,
  
  /** Delete20For4A08Action - queryViewQuerySql ƴ��Դbid�� in sql����ʱ�� **/
  tmp_po_20_17,

  /* ================================ �ɹ����� ================================ */
  /** �ɹ�����ɾ����ɾ���޶���ʷ��ʱ�� **/
  tmp_po_21_01,

  /** �ɹ��ƻ����Ƽ�飬��ȡ�빺����ͼVOʱ���õ�����ʱ�� */
  tmp_po_21_02,

  /** ������޶���ʷ��ɾ���޶���ʷʱ��ʹ�õ���ʱ�� */
  tmp_po_21_03,

  /** OrderQueryFor4CBP - queryOrderVOCoopFromSO */
  tmp_po_21_04,

  /** OrderQueryFor4CBP - queryOrderVOCoopToSO */
  tmp_po_21_05,

  /** �ɹ�ѯ��-������Ч��������ʱ��ʹ�õ���ʱ�� */
  tmp_po_21_06,

  /** ѯ���¼�ʱʹ�õ���ʱ�� */
  tmp_po_21_07,

  /** �ɹ��ƻ���飬��ȡ�빺����ͼVOʱʹ�õ���ʱ�� */
  tmp_po_21_08,

  /** OrderWBStoreArrvTolerRule - getCodeOverTolerWhenNoRef */
  tmp_po_21_09,

  /** OrderWBStoreArrvTolerRule - getCodeOverTolerWhenRef */
  tmp_po_21_10,

  /** ������ѯ����������ID����ʱ�� */
  tmp_po_21_11,

  /** �����޶�����ʱ,ɾ��;״̬��������ʱ��ʹ�õ���ʱ�� */
  tmp_po_21_12,

  /** �����޶�����ʱ,����;״̬��������ʱ��ʹ�õ���ʱ�� */
  tmp_po_21_13,

  /** �޶�Ԥ����ƹ���ʱ����ȡ�빺����ͼVOʱʹ�õ���ʱ�� */
  tmp_po_21_14,

  /** �޶������У����������м��ʱ����ʱ�� */
  tmp_po_21_15,

  /** ��ѯ�ɹ�������;״̬VOʱʹ�õ���ʱ�� */
  tmp_po_21_16,

  /** ���ݶ�������id��ѯ��������ƻ�idʱ��ʹ�õ���ʱ�� */
  tmp_po_21_17,

  /** ���ݺ�ͬ����PK����ѯ�������Ķ���������PKʱ��ʹ�õ���ʱ�� */
  tmp_po_21_18,

  /** �Ƿ���ڹ���ĳһ��ͬ�Ķ���ʱ��ʹ�õ���ʱ�� */
  tmp_po_21_19,

  /** ��ѯ����״̬Ϊ���ͨ���ġ���Ӧ��ά������ҷ����ĵ����ƻ�ʱ��ʹ�õ���ʱ�� */
  tmp_po_21_20,

  /** Ϊ��Ӧ�̲�ѯ�����ƻ�ʱ��ʹ�õ���ʱ�� */
  tmp_po_21_21,

  /** ���������ѯ����ʵ�����У�����ID��ʱ�� */
  tmp_po_21_22,

  /** ���������ѯ����ʵ�����У�����ID��ʱ�� */
  tmp_po_21_23,

  /** ���������ѯ����ʵ�����У���ѯ�����ƻ�ʱ������ID��ʱ�� */
  tmp_po_21_24,

  /** Ϊ��������ṩ�Ĳ�ѯ�ӿڵ��Ӳ�ѯ�е���ʱ�� */
  tmp_po_21_25,

  /** �ɹ���������汨���ṩ�ķ���ʱʹ�õ���ʱ�� */
  tmp_po_21_26,

  /** ȡ�ù�Ӧ����ʱ�䷶Χ�ڵķ����������ʱ��ʹ�õ���ʱ�� */
  tmp_po_21_27,

  /** ����ƻ���ѯ */
  tmp_po_21_28,

  /** PayPlanNotAllDelRule - process */
  tmp_po_21_29,

  /** TranTypeChkRule - getHaveReceivePlanSet */
  tmp_po_21_30,

  /** OrderQueryExecForMMBP - queryExecForM52 */
  tmp_po_21_31,

  /** QueryMapListBidAsKey - queryMapListBidAsKey */
  tmp_po_21_32,

  /** OnwayStatusWriteBackRule - queryOnwayStatusVO */
  tmp_po_21_33,

  /** OrderWriteBackForScmf - udpateItemCenPuRuleB */
  tmp_po_21_34,

  /** ApDataAction - getInvoiceBIDsFromOrder */
  tmp_po_21_35,

  /** ApDataAction - getOrderPayMny */
  tmp_po_21_36,

  /** ConfirmUpdateBp - getOnwayVOs */
  tmp_po_21_37,

  /** OnwayDAO - getAccumNum */
  tmp_po_21_38,

  /** OnwayDAO - getNextStatusOnwayVO */
  tmp_po_21_39,

  /** OnwayUpdateBpMy - getOnwayVOs */
  tmp_po_21_40,

  /** OutputDeleteBp - getStatusOnwayVOs */
  tmp_po_21_41,

  /** OutputUpdateBp - getExistVO */
  tmp_po_21_42,

  /** OrderEditRecordQueryImpl - queryOrderPrice */
  tmp_po_21_43,

  /** OrderQueryFor45Impl - createQueryScheme */
  tmp_po_21_44,

  /** BillIdQueryFor36D1Impl - getBillIdsWhenCnt */
  tmp_po_21_45,

  /** BillIdQueryFor36D1Impl - getBillIdsWhenOrder */
  tmp_po_21_46,

  /** PoRPRowNoModel - getWherePart */
  tmp_po_21_47,

  /** OrderArrAlertԤ������ƴװ��֯in sql����ʱ�� */
  tmp_po_21_48,

  /** OrderArrAlertԤ������ƴװ��֯in sql����ʱ��2 */
  tmp_po_21_49,

  /** NoTradeVendAlertԤ������ƴװ��֯in sql����ʱ�� */
  tmp_po_21_50,

  /** NoTradeVendAlertԤ������ƴװ��֯in sql����ʱ��2 */
  tmp_po_21_51,

  /** PoRPNotArrvAlertԤ������ƴװ��֯in sql����ʱ�� */
  tmp_po_21_52,

  /** PayPlanMatureAlertԤ������ƴװ��֯in sql����ʱ�� */
  tmp_po_21_53,

  /** PayPlanMatureAlertԤ������ƴװ��֯in sql����ʱ��2 */
  tmp_po_21_54,

  /** PoCloseBillAlertԤ������ƴװ��֯in sql����ʱ�� */
  tmp_po_21_55,

  /** Ѱ���¼�ʱ��������ʱ�� */
  tmp_po_21_56,

  /** Ѱ���¼�ʱ��������֯��ʱ�� */
  tmp_po_21_57,

  /** Ѱ���¼�ʱ���ɹ���֯��ʱ�� */
  tmp_po_21_58,

  /** Ѱ���¼�ʱ����Ӧ����ʱ�� */
  tmp_po_21_59,
  /** �ɹ������ر�Ԥ������������ʱ�� */
  tmp_po_21_60,

  /** �ɹ�������ѯ�ۿ۵�������ɹ���֯ */
  tmp_po_21_61,
  /** �ɹ�������ѯ�ۿ۵�������Ӧ�� */
  tmp_po_21_62,
  /** �ɹ�������ѯ�ۿ۵��������ۿ۹��� */
  tmp_po_21_63,
  /** �����Ӳɹ��ṩ�ĸ���pk��ѯ����ʱ�õ�����ʱ�� */
  tmp_po_21_64,

  tmp_po_21_65,
  /** ��ɢ����ECA��ѯ�ɹ�����ʱ���ɹ������ӿ�ʵ�������½��Ĳ�����ʱ�� */
  tmp_po_21_66,

  /* ================================ ������ ================================ */
  /** ��������ȡ�����������ʱ�� */
  tmp_po_23_01,

  /** ���ݼ����������ݲ��飬�Ӳ�ѯ�е���ʱ�� */
  tmp_po_23_02,

  /** ��������д�������е����Ƿ����ʼ����ı�־��֮ǰ�ʼ�������ʱ�õ���ʱ�� */
  tmp_po_23_03,

  /** ��������ѯ���������������ʱ�õ�����ʱ�� */
  tmp_po_23_04,

  /** ����Ƿ��ظ�����ʱ����ѯ��Ӧ�ļ�������ϸ�õ�����ʱ�� */
  tmp_po_23_05,

  /** ���ݱ�ͷ������ѯ������������ʱ�� */
  tmp_po_23_06,

  /** ���ݶ���������ѯ�ö������˻�����ϸ��Ϣ��ʱ�� */
  tmp_po_23_07,

  /** Ϊ���������ṩ�Ĳ�ѯ�ӿ��У����ϲ�ѯ��ʱ�� */
  tmp_po_23_08,

  /** ���ݶ���������ѯ�ö����ĵ�������ϸ��Ϣ��ʱ�� */
  tmp_po_23_09,

  /** �ж϶����Ƿ��е�����ʱ�� */
  tmp_po_23_10,

  /** �ж϶��������ƻ��Ƿ��е�����ʱ�� */
  tmp_po_23_11,

  /** ���ݲɹ���������id��ѯ��������ʱ�� */
  tmp_po_23_12,

  /** ����������� */
  tmp_po_23_13,

  /** ���ݶ�������ID��ѯ�������˻���������ʱ�õ�����ʱ�� */
  tmp_po_23_15,

  /** WriteC005WhenResultFeedbackRule - queryOtherBbVO */
  tmp_po_23_16,

  /** Writeback23ForC003BP - writebackWhenUnApprove */
  tmp_po_23_17,

  /** ArrivePubQueryImpl - queryAggVOIncludeBBVOByBids */
  tmp_po_23_18,

  /* ================================ �۸���㵥 ================================ */
  /** ��ѯ�������Ƿ��Ѿ����۸���㵥����ʱ������ID����ʱ�� */
  tmp_po_24_01,

  /* ================================ �ɹ���Ʊ ================================ */
  /** ���ݸ���Э�飬��ѯ�������㣨��Ч������ʱ��ƴ�Ӳɹ���Ʊ����ID IN SQL����ʱ�� */
  tmp_po_25_01,

  /** ���ݸ���Э�飬��ѯ�������㣨��Ч������ʱ��ƴ�Ӳɹ������ID IN SQL����ʱ�� */
  tmp_po_25_02,

  /** ��ѯ���﷢Ʊ�����ķ��÷�Ʊ��ʱ�� */
  tmp_po_25_03,

  /** ��ѯ���﷢Ʊ����������״̬���÷�Ʊ��ʱ�� */
  tmp_po_25_04,

  /** ���ݶ������ҷ�Ʊ����id��Դͷ���ݺ���ʱ�� */
  tmp_po_25_05,

  /** �ɹ���ƱΪ�����ѯBPʱ��������ʱ�� */
  tmp_po_25_06,

  /** Ϊ���������ṩ��Ʊ��ѯ�У���ͷID��ʱ�� */
  tmp_po_25_07,

  /** Ϊ���������ṩ��Ʊ��ѯ�У�����ID��ʱ�� */
  tmp_po_25_08,

  /** Ϊ���������ṩ��Ʊ��ѯ�У�������ͷID��ʱ�� */
  tmp_po_25_09,

  /** �����������Ʊ��ѯ�е��������֯����ʱ�� */
  tmp_po_25_10,

  /** ���ݷ�Ʊ��id��ѯ������ʱ����Ʊ��ϸID��ʱ�� */
  tmp_po_25_11,

  /** ���ݶ�������ID��ѯ��Ʊʱ����������ID��ʱ�� */
  tmp_po_25_12,

  /** ��ѯ��Ʊ��߼�������ʱ�� */
  tmp_po_25_13,

  /** ���ݶ�������ID��ѯ��Ʊ����������Ʊԭ�ҽ��ʱ�õ�����ʱ�� */
  tmp_po_25_14,

  /* ================================ �ɹ����㵥 ================================ */
  /** ��ѯ�����ݹ���ϸ��ʹ�õ���ʱ�� */
  tmp_po_27_01,

  /** ��ѯ��Ӧ�ķ��÷�̯��ϸʹ�õ���ʱ�� */
  tmp_po_27_02,

  /** �������ⷢƱVO��ѯ���㵥VOʱ��ʹ�õ���ʱ�� */
  tmp_po_27_03,

  /** ����Ƿ���ڽ���δ���ɱ�ʱ��ʹ�õ���ʱ�� */
  tmp_po_27_04,

  /** ������ⵥ/VMI��ID��ѯ�������Ľ��㵥�Ļ��������飬�ݹ���ȷ�ϳɱ�ǰ�Ľ���ʱʹ�õ���ʱ�� */
  tmp_po_27_05,

  /** ���㵥ɾ��/ȡ�������ʱ���ʱ�����㵥bid��ʱ�� */
  tmp_po_27_06,

  /** �����鵥�Ѿ����������ý����̯��ʱ��ʹ�õ���ʱ�� */
  tmp_po_27_07,

  /** ��ѯ��������ⵥ�ͷ��÷�Ʊ��Ϣ,�ж���ⵥ���й���Щ��������ݹ�Ӧ��ʱ��ʹ�õ���ʱ�� */
  tmp_po_27_08,

  /** �������ϲ�ѯ�ɹ����ʱ����ʱ�� */
  tmp_po_27_09,

  /** ��ѯ���ý���Ľ����ϵʱʹ�õ���ʱ�� */
  tmp_po_27_10,

  /** ��ѯ���﷢Ʊ����ͺ�����Ʊ�Գ�Ľ����ϵʱʹ�õ���ʱ�� */
  tmp_po_27_11,

  /** ��ѯ���½����ʱ��ʹ�õ���ʱ�� */
  tmp_po_27_12,

  /** ���ݶ�������������ѯ�ۼƽ�����Ϣʱ��ʹ�õ���ʱ�� */
  tmp_po_27_13,

  /** ���ݶ���������ѯ�ۼƽ��������ͽ����Ϣʱ��ʹ�õ���ʱ�� */
  tmp_po_27_14,

  /* ================================ ������ ================================ */
  /** ���������id��ѯ���ж�Ӧ������ʱ��ʹ�õ���ʱ�� */
  tmp_po_4201_1,

  /* ================================ ���Ļ��ܲ��� ================================ */
  /** �������Ϲ������Ļ��ܵĲ�����Ϣʱ��ʹ�õ���ʱ�� */
  tmp_po_4202_1,

  /* ================================ ί������� ================================ */
  /** ����Ƿ���й����ý���ʱ��ʹ�õ���ʱ�� */
  tmp_po_4203_1,

  /** ���ݲɹ�����ID��ѯ������Ĳɹ���ʱ��ʹ�õ���ʱ�� */
  tmp_po_4203_2,

  /** ����ί����BID��ѯ�������ί����ʱ��ʹ�õ���ʱ�� */
  tmp_po_4203_3,

  /** ����ί�ⶩ��ID��ѯ�������ί����ʱ��ʹ�õ���ʱ�� */
  tmp_po_4203_4,
  /** ���������id��ѯ���ж�Ӧ������ʱ��ʹ�õ���ʱ�� */
  tmp_po_4203_5,

  /** ������ⵥ�ж�����������id��ѯ��������codeʱ��ʹ����ʱ�� */
  tmp_po_4203_6,

  /* ================================ �ڳ��ݹ��� ================================ */
  /** ���ݿ�浥����ID��ѯ���Զ������VOʱ��ʹ�õ���ʱ�� */
  tmp_po_4t_01,

  /** ���ݶ�����ID��ѯ���Զ������VOʱ��ʹ�õ���ʱ�� */
  tmp_po_4t_02,

  /* ================================ �ݹ� ================================ */
  /** ����ɾ�������ݹ���¼����ʱ�� */
  tmp_po_est_01,

  /** �ݹ�VO��ѯ����ʱ��ʹ�õ���ʱ�� */
  tmp_po_est_02,

  /** ȡ�������ݹ���I9����ʱ��ʹ�õ���ʱ�� */
  tmp_po_est_03,

  /** �Զ��ݹ���ȡ���ݹ�����ʱ����ѯ�Ƿ����ݹ����ã�ʹ�õ���ʱ�� */
  tmp_po_est_04,

  /* ================================ �ɹ��� ================================ */
  /** �ɹ��ڼƻ������ϡ��ɹ���֯��ʱ�� */
  tmp_po_pos_01,

  /** �ɹ��ڼƻ��ڿ����֯��ʱ�� */
  tmp_po_pos_02,

  /** �ɹ��ڼƻ������ϻ���������ʱ�� */
  tmp_po_pos_03,

  /** CheckUniqueRule - checkMaterialB */
  tmp_po_pos_04,

  /** QueryPlanMaterialAction - queryPlanByMaterial */
  tmp_po_pos_05,

  /** QueryPositionImpl - getPlanMaterialSqlByPkPlan */
  tmp_po_pos_06,

  /** CheckUniqueRule - getOtherUseClass */
  tmp_po_pos_07,

  /** QueryPlanMaterialAction - queryPlanByClass */
  tmp_po_pos_08,
  
  /** CheckUniqueRule - checkPk_classInOtherMaterial */
  tmp_po_pos_09,

  /* ================================ ��λ���� ================================ */
  /** �����������ͻ�ȡ��λ������ʱ�� */
  tmp_po_pr_01,

  /** ��ȡ��λSQL��ʱ�� */
  tmp_po_pr_02,

  /* ================================ ������� ================================ */
  /** ί����ⵥ47�ṩ���ɹ��ķ��������У��ж����ε����Ƿ��Ѿ����ɹ�ί�������ʱ�� */
  tmp_po_pub_01,

  /** ���س��ݲ���Ƶ����ϱ���ʱ�õ�����ʱ�� */
  tmp_po_pub_02,

  /** �ɹ���β�����ܲ�ѯ�ֵ�VOʱ��ԴBID��ʱ�� **/
  tmp_po_pub_03,

  /** �ɹ���β�����ܲ�ѯ�ֵ�VOʱ��ԴHID��ʱ�� **/
  tmp_po_pub_04,

  /* ================================ ���� ================================ */
  /** ����ִ����ϸ��ѯBP���õ�����ʱ��1 */
  tmp_po_rpt_01,

  /** ����ִ����ϸ��ѯBP���õ�����ʱ��2 */
  tmp_po_rpt_02,

  /** OrderReceivePlanRptBP - rpList */
  tmp_po_rpt_03,

  /** PayExecRptBP - getPayMny */
  tmp_po_rpt_04,

  /** PayExecRptBP - getQuerySql */
  tmp_po_rpt_05,
  /** PayExecRptBP - setCcurrencyid */
  tmp_po_rpt_06,

  /** �빺����ϸ��ѯ�У��Ӳɹ�������ѯ�빺������ID��ʱ�� */
  tmp_po_rpt_07,

  /** �빺����ϸ��ѯ�У������۶�����ѯ�빺������ID��ʱ�� */
  tmp_po_rpt_08,

  /** �빺���Ӳɹ�����ȡ������ʱ�� */
  tmp_po_rpt_09,

  /** �빺�������۶���ȡ������ʱ�� */
  tmp_po_rpt_10,

  /** ����ִ�л��ܣ�ȡ�빺��ִ������ʱ�� */
  tmp_po_rpt_11,

  /** PurchaseinAsPrayBillBatchLinkQuery - createBidSql */
  tmp_po_rpt_12,
  /** PurchaseinBatchLinkQuery - createBidSql */
  tmp_po_rpt_13;
}