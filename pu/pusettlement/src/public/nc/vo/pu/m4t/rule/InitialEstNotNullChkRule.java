
package nc.vo.pu.m4t.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description <ul>
 *              <li>�ǿռ��
 *              <li>��ͷ�ǿ�:����֯,��������,�ɹ����ţ� �ڳ��ݹ�����, �����֯, �ɱ���,���֣����ұ��֣���������
 *              <li>����ǿ�:�к�,����֯,��������,Ӧ����֯,����VID,����OID,��������λ,��λ
 *              </ul>
 * @scene
 * 
 * @param ��
 * 
 * @since 6.3
 * @version 2010-9-8 ����10:02:19
 * @author wuxla
 */

public class InitialEstNotNullChkRule implements IRule<InitialEstVO> {

	static class ItemChkInfo {

		private String itemCode;

		private String itemName;

		ItemChkInfo(String itemCode, String itemName) {
			this.itemCode = itemCode;
			this.itemName = itemName;
		}

		public String getItemCode() {
			return this.itemCode;
		}

		public String getItemName() {
			return this.itemName;
		}

		public void setItemCode(String itemCode) {
			this.itemCode = itemCode;
		}

		public void setItemName(String itemName) {
			this.itemName = itemName;
		}
	}

	
	@Override
	public void process(InitialEstVO[] vos) {
		if (ArrayUtils.isEmpty(vos)) {
			return;
		}

		ItemChkInfo[] headInfos = this.getHeadInfos();
		ItemChkInfo[] itemInfos = this.getItemInfos();

		for (InitialEstVO vo : vos) {
			this.checkEmpty(vo, headInfos, itemInfos);
		}
	}

	/**
	 * ��������������������
	 * <p>
	 * <b>����˵��</b>
	 * 
	 * @param vo
	 * @param itemInfos
	 * @param sb
	 *            <p>
	 * @since 6.0
	 * @author wuxla
	 * @time 2010-9-8 ����03:33:54
	 */
	private void checkBodyItems(InitialEstVO vo, ItemChkInfo[] itemInfos,
			StringBuilder sb) {
		InitialEstItemVO[] itemVOs = vo.getItems();
		if (ArrayUtils.isEmpty(itemVOs)) {
			return;
		}

		for (InitialEstItemVO itemVO : itemVOs) {
			StringBuilder itemBuilder = new StringBuilder();
			for (int i = 0; i < itemInfos.length; ++i) {
				if (itemVO.getAttributeValue(itemInfos[i].getItemCode()) != null) {
					continue;
				}

				itemBuilder.append(itemInfos[i].getItemName()).append(",");
			}

			if (itemBuilder.length() > 0) {
				itemBuilder.deleteCharAt(itemBuilder.length() - 1);
				sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
						"4004060_0",
						"04004060-0230",
						null,
						new String[] { itemVO.getCrowno(),
								itemBuilder.toString() })/*
														 * �����{0}�е������ֶβ�����Ϊ�գ�{1}\
														 * n
														 */);
			}
		}
	}

	/**
	 * �����������������ǿ���
	 * <p>
	 * <b>����˵��</b>
	 * 
	 * @param vo
	 * @param headInfos
	 * @param itemInfos
	 *            <p>
	 * @since 6.0
	 * @author wuxla
	 * @time 2010-9-8 ����06:52:14
	 */
	private void checkEmpty(InitialEstVO vo, ItemChkInfo[] headInfos,
			ItemChkInfo[] itemInfos) {
		StringBuilder sb = new StringBuilder();
		this.checkHeadItems(vo, headInfos, sb);
		this.checkBodyItems(vo, itemInfos, sb);

		if (sb.length() > 0) {
			ExceptionUtils.wrappBusinessException(sb.toString());
		}
	}

	/**
	 * ������������������ͷ
	 * <p>
	 * <b>����˵��</b>
	 * 
	 * @param vo
	 * @param headInfos
	 * @param sb
	 *            <p>
	 * @since 6.0
	 * @author wuxla
	 * @time 2010-9-8 ����06:51:59
	 */
	private void checkHeadItems(InitialEstVO vo, ItemChkInfo[] headInfos,
			StringBuilder sb) {
		StringBuilder headBuilder = new StringBuilder();
		InitialEstHeaderVO headerVO = vo.getHeader();
		for (int i = 0; i < headInfos.length; ++i) {
			if (headerVO.getAttributeValue(headInfos[i].getItemCode()) != null) {
				continue;
			}

			headBuilder.append(headInfos[i].getItemName()).append(",");
		}

		if (headBuilder.length() > 0) {
			headBuilder.deleteCharAt(headBuilder.length() - 1);
			sb.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
					"4004060_0", "04004060-0174")/* @res "��ͷ�����ֶβ�����Ϊ�գ�" */
					+ headBuilder.toString() + "\n");
		}
	}

	/**
	 * ����������������ͷ�ǿ���:����֯,��������,�ɹ����ţ� �ڳ��ݹ�����, �����֯, �ɱ���,���֣����ұ��֣���������
	 * <p>
	 * <b>����˵��</b>
	 * 
	 * @return <p>
	 * @since 6.0
	 * @author wuxla
	 * @time 2010-9-8 ����06:51:48
	 */
	private ItemChkInfo[] getHeadInfos() {
		ItemChkInfo[] headInfos = new ItemChkInfo[13];
		headInfos[0] = new ItemChkInfo(InitialEstHeaderVO.PK_ORG,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
						"4004060_0", "04004060-0175")/*
													 * @res "������֯�汾"
													 */);
		headInfos[1] = new ItemChkInfo(InitialEstHeaderVO.PK_ORG_V,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
						"4004060_0", "04004060-0176")/* @res "������֯" */);
		headInfos[2] = new ItemChkInfo(InitialEstHeaderVO.PK_GROUP,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
						"UC001-0000072")/* @res "����" */);
		headInfos[3] = new ItemChkInfo(InitialEstHeaderVO.PK_DEPT,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
						"UC000-0004099")/* @res "�ɹ�����" */);
		headInfos[4] = new ItemChkInfo(InitialEstHeaderVO.PK_DEPT_V,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
						"UC000-0004099")/* @res "�ɹ�����" */);
		headInfos[5] = new ItemChkInfo(InitialEstHeaderVO.VTRANTYPECODE,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
						"4004060_0", "04004060-0177")/* @res "�ڳ��ݹ�����" */);
		headInfos[6] = new ItemChkInfo(InitialEstHeaderVO.PK_STOCKORG,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
						"4004060_0", "04004060-0178")/* @res "�����֯�汾" */);
		headInfos[7] = new ItemChkInfo(InitialEstHeaderVO.PK_STOCKORG_V,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
						"UC000-0001825")/* @res "�����֯" */);
		headInfos[8] = new ItemChkInfo(InitialEstHeaderVO.PK_COSTREGION,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
						"4004060_0", "04004060-0179")/* @res "�ɱ���" */);
		headInfos[9] = new ItemChkInfo(InitialEstHeaderVO.CORIGCURRENCYID,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
						"UC000-0001755")/* @res "����" */);
		headInfos[10] = new ItemChkInfo(InitialEstHeaderVO.CCURRENCYID,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
						"4004060_0", "04004060-0180")/* @res "��λ��" */);
		headInfos[11] = new ItemChkInfo(InitialEstHeaderVO.DBILLDATE,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
						"UC000-0000799")/* @res "��������" */);
		headInfos[12] = new ItemChkInfo(InitialEstHeaderVO.PK_SUPPLIER,
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
						"UC000-0000275")/* @res "��Ӧ��" */);
		// ���Ԫ��ע������鳤��
		return headInfos;
	}

	/**
	 * ������������������ǿ���:�к�,����֯,��������,Ӧ����֯,����VID,����OID,��������λ,��λ
	 * <p>
	 * <b>����˵��</b>
	 * 
	 * @return <p>
	 * @since 6.0
	 * @author wuxla
	 * @time 2010-9-8 ����06:51:36
	 */
	private ItemChkInfo[] getItemInfos() {
		ItemChkInfo[] itemInfos = new ItemChkInfo[] {
				new ItemChkInfo(InitialEstItemVO.CROWNO,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"common", "UC000-0003389")/* @res "�к�" */),
				new ItemChkInfo(InitialEstItemVO.PK_ORG,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"4004060_0", "04004060-0175")/*
															 * @res "������֯�汾"
															 */),
				new ItemChkInfo(InitialEstItemVO.PK_ORG_V,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"4004060_0", "04004060-0176")/* @res "������֯" */),
				new ItemChkInfo(InitialEstItemVO.PK_GROUP,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"common", "UC001-0000072")/* @res "����" */),
				new ItemChkInfo(InitialEstItemVO.PK_APFINANCEORG,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"4004060_0", "04004060-0181")/* @res "Ӧ����֯�汾" */),
				new ItemChkInfo(InitialEstItemVO.PK_APFINANCEORG_V,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"4004060_0", "04004060-0182")/* @res "Ӧ����֯" */),
				new ItemChkInfo(InitialEstItemVO.PK_MATERIAL,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"common", "UC000-0002911")/* @res "���ϱ���" */),
				new ItemChkInfo(InitialEstItemVO.PK_SRCMATERIAL,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"4004060_0", "04004060-0183")/* @res "���ϰ汾" */),
				new ItemChkInfo(InitialEstItemVO.CASTUNITID,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"4004060_0", "04004060-0184")/* @res "��λ" */),
				new ItemChkInfo(InitialEstItemVO.CUNITID,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"4004060_0", "04004060-0185")/*
															 * @res "����λ"
															 */),
				new ItemChkInfo(InitialEstItemVO.NNUM,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"4004060_0", "04004060-0247")/*
															 * @res "������"
															 */),
				new ItemChkInfo(InitialEstItemVO.NASTNUM,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"4004060_0", "04004060-0246")/*
															 * @res "����"
															 */),
				new ItemChkInfo(InitialEstItemVO.VCHANGERATE,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"4004060_0", "04004060-0248")/* @res "������" */),
				new ItemChkInfo(InitialEstItemVO.CRECECOUNTRYID,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"common", "04004000-0005")/* @res "�ջ�����/����" */),
				new ItemChkInfo(InitialEstItemVO.CSENDCOUNTRYID,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"common", "04004000-0006")/* @res "��������/����" */),
				new ItemChkInfo(InitialEstItemVO.CTAXCOUNTRYID,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"common", "04004000-0007")/* @res "��˰����/����" */),
				new ItemChkInfo(InitialEstItemVO.FBUYSELLFLAG,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"common", "04004000-0008")/* @res "��������" */),

				new ItemChkInfo(InitialEstItemVO.CTAXCODEID,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"common", "04004000-0009")/* @res "˰��" */),
				new ItemChkInfo(InitialEstItemVO.CSENDCOUNTRYID,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"common", "04004000-0010")/* @res "��˰�����" */),
				new ItemChkInfo(InitialEstItemVO.VCHANGERATE,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"common", "04004000-0011")/* @res "������" */) };
		return itemInfos;
	}

}
