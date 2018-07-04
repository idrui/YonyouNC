package nc.bs.ws.pu.m20;

import java.sql.SQLException;
import java.util.List;

import javax.resource.cci.ResultSet;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.itf.bd.material.assign.IMaterialAssignService;
import nc.jdbc.framework.SQLParameter;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.material.MaterialVersionVO;
import nc.vo.ecpubapp.pattern.exception.ExceptionUtils;
import nc.vo.pu.m20.ws.entity.MsgCGAG000001;
import nc.vo.pu.m20.ws.entity.MsgCGAG000001Head;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>������Ҫʵ�ֹ��ܣ�</p>
 *
 * <li>�ʸֲɹ��ƻ���Ϣ�е�������Ϣ���</li>
 * <li>������ϱ��벻���ڣ��������ɹ����������ϵ��� </li>
 * <li>������ϱ�����ڣ�������ͺš�������λ���������ϵ����Ƿ�һ�£���һ�������ϰ汾�����������ϵ��� </li>
 * 
 * @author lyw
 * @version 6.5
 * @time 2017��3��16�� ����6:06:10
 */
public class MaterialValidate {

	static BaseDAO baseDao = new BaseDAO();
	
	/**
	 * �Ƚ��ʸֵ����ϵ�����NC���ϵ����ȶ�
	 * �����롢����ͺš�������λ�Ƿ���ͬ                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
	 * @param code
	 * @param spec
	 * @param type
	 * @param unitid
	 * @return
	 */
	public static String checkuniformity(String code,String spec,String type,String unitid) {
		String sqlstr = "select pk_material from bd_material where code = ? and materialspec = ? and materialtype = ? "
				+ "and pk_measdoc = ? and dr = 0 and enablestate =2";
		SQLParameter sp = new SQLParameter();
		sp.addParam(code);
		sp.addParam(spec);
		sp.addParam(type);
		sp.addParam(unitid);
		String pkstr = null;
		try {
			 pkstr = (String) baseDao.executeQuery(sqlstr, sp, new ResultSetProcessor() {
				@Override
				public Object handleResultSet(java.sql.ResultSet rs)
						throws SQLException {
					// TODO �Զ����ɵķ������
					String ll = null;
					if (rs.next()) {
						ll = rs.getString(1);
					}
					return ll;
				}
			});
		} catch (DAOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			ExceptionUtils.wrappBusinessException("�����ͬ���ϣ�ִ��ʧ��!");
		}
		
		return pkstr;
	}

	/**
	 * �������ϱ����ȡ��������
	 * @param materialcode
	 * @return
	 */
	public static String getPKByName(String materialcode) {
		// TODO �Զ����ɵķ������
		String sqlstr = "select pk_material from bd_material where code = '" + materialcode + "'";
		String pkstr = null;
		try {
			pkstr = (String) baseDao.executeQuery(sqlstr, new ResultSetProcessor(){
				@Override
				public Object handleResultSet(java.sql.ResultSet rs)
						throws SQLException {
					// TODO �Զ����ɵķ������
					String ll = null;
					if (rs.next()) {
						ll = rs.getString(1);
					};
					return ll;
				}});
		} catch (DAOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			ExceptionUtils.wrappBusinessException("����PK��ѯ��ִ��ʧ��!");
		}
		return pkstr;
	}
	/**
	 * �������ϵ���
	 * @param pk_group
	 * @param pk_org
	 * @param pk_user
	 * @param materialcode
	 * @param materialname
	 * @param pk_measdoc
	 * @param materialspec
	 * @param materialtype
	 * @param marbaseclass
	 * @param graphid
	 * @param ddatetime 
	 * @param ddate 
	 * @param version 
	 * @return
	 */
	public static String generateMaterialDoc(String pk_group, String pk_org,
			String pk_user, String materialcode, String materialname,
			String pk_measdoc, String materialspec, String materialtype,
			String marbaseclass, String graphid, UFDate ddate, UFDateTime ddatetime, Integer version) {
		// TODO �Զ����ɵķ������
		//��汾����
		MaterialVO vo = new MaterialVO();
		//�������ϵ���
		MaterialVersionVO vvo = new MaterialVersionVO();
		String pk_material = null;
		vo.setCode(materialcode);
		vo.setCreationtime(ddatetime);
		vo.setCreator(pk_user);
		vo.setGraphid(graphid);
		vo.setMaterialspec(materialspec);
		vo.setMaterialtype(materialtype);
		vo.setName(materialname);
		vo.setPk_marbasclass(marbaseclass);
		vo.setPk_measdoc(pk_measdoc);
		vo.setPk_group(pk_group);
		vo.setPk_org(pk_group);
		vo.setVersion(version);
		//����
		vo.setEnablestate(2);
		//�ֲ�ʽ 2-�¼��ϱ�
		vo.setDataoriginflag(2);
		//����ݲ�
		vo.setIntolerance(new UFDouble(0));
		//�Ƿ�����
		vo.setLatest(UFBoolean.TRUE);
		//����ر����ݲ%��
		vo.setOutcloselowerlimit(new UFDouble(0));
		//�����ݲ%��
		vo.setOuttolerance(new UFDouble(0));
		//��Ʒ��
		vo.setProductfamily(UFBoolean.FALSE);
		//�������°汾
		vvo.setCode(materialcode);
		vvo.setCreationtime(ddatetime);
		vvo.setCreator(pk_user);
		vvo.setGraphid(graphid);
		vvo.setMaterialspec(materialspec);
		vvo.setMaterialtype(materialtype);
		vvo.setName(materialname);
		vvo.setPk_marbasclass(marbaseclass);
		vvo.setPk_measdoc(pk_measdoc);
		vvo.setPk_group(pk_group);
		vvo.setPk_org(pk_org);
		vvo.setVersion(version);
		//����
		vvo.setEnablestate(2);
		//�ֲ�ʽ 2-�¼��ϱ�
		vvo.setDataoriginflag(2);
		//����ݲ�
		vvo.setIntolerance(new UFDouble(0));
		//����ر����ݲ%��
		vvo.setOutcloselowerlimit(new UFDouble(0));
		//�����ݲ%��
		vvo.setOuttolerance(new UFDouble(0));
		//��Ʒ��
		vvo.setProductfamily(UFBoolean.FALSE);
		try {
			//���ɼ��ż����ϵ���
			pk_material = baseDao.insertVO(vo);
			try {
				getAssignService().assignMaterialForPf(pk_material, pk_org, null);
			} catch (BusinessException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			vvo.setPk_material(pk_material);
			baseDao.insertVO(vvo);
		} catch (DAOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return pk_material;
	}
	/**
	 * @return
	 */
	private static IMaterialAssignService getAssignService() {
		// TODO �Զ����ɵķ������
		IMaterialAssignService assignService = NCLocator.getInstance().lookup(IMaterialAssignService.class);
		return assignService;
	}

	/**
	 * �������ϱ��룬��ȡ��Ӧ�ĵ�ǰ�������ϰ汾
	 * @param pk_material
	 * @return
	 */
	public static Integer getVersion(String materialcode) {
		// TODO �Զ����ɵķ������
		String sqlstr = "select max(version) from bd_material where code = '" + materialcode + "'";
		Integer version = null;
		try {
			version = (Integer) baseDao.executeQuery(sqlstr, new ResultSetProcessor(){
				@Override
				public Object handleResultSet(java.sql.ResultSet rs)
						throws SQLException {
					// TODO �Զ����ɵķ������
					Integer ll = null;
					if (rs.next()) {
						ll = rs.getInt(1);
					};
					return ll;
				}});
		} catch (DAOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			ExceptionUtils.wrappBusinessException("���ϰ汾PK��ѯ��ִ��ʧ��!");
		}
		return version;
	}
	/**
	 * �������ϱ��룬ɾ��ԭ�а汾���� for bd_material_v
	 * @param materialcode
	 */
	public static void deleteMaterial(String materialcode) {
		// TODO �Զ����ɵķ������
		String wherestr = " code = '" + materialcode + "'";
		try {
			baseDao.deleteByClause(MaterialVersionVO.class, wherestr);
		} catch (DAOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}

	}
	

}
