package nc.bs.ws.pu.m20;

import java.sql.SQLException;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.vo.bd.material.measdoc.MeasdocVO;
import nc.vo.ecpubapp.pattern.exception.ExceptionUtils;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;

/**
 * <p>������Ҫʵ�ֹ��ܣ�</p>
 *
 * <li>ʵ�ָ��ݼ�����λ����ת��PK������޶�ӦPK��ֵ��������������λ��������</li>
 * 
 * @author lyw
 * @version 6.5
 * @time 2017��3��17�� ����9:07:43
 */
public class MeasDocValidate {
	static BaseDAO baseDao = new BaseDAO();
	public static String measDocPKByName (String measname) {
		String sqlstr = "select pk_measdoc from bd_measdoc where name = '" + measname + "'";
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
			ExceptionUtils.wrappBusinessException("������λPK��ѯ��ִ��ʧ��!");
		}
		return pkstr;
	}
	/**
	 * ���ɼ�����λ����
	 * @param pk_group
	 * @param pk_org
	 * @param pk_user
	 * @param castunit
	 * @param ddate
	 * @param ddatetime
	 */
	public static String generateMeasDoc(String pk_group, String pk_org,
			String pk_user, String castunit, UFDate ddate, UFDateTime ddatetime) {
		// TODO �Զ����ɵķ������
		MeasdocVO  vo = new MeasdocVO();
		vo.setCode(castunit);
		vo.setCreationtime(ddatetime);
		vo.setCreator(pk_user);
		vo.setPk_group(pk_group);
		vo.setPk_org(pk_org);
		vo.setName(castunit);
		String pk_measdoc =null;
		try {
			pk_measdoc = baseDao.insertVO(vo);
		} catch (DAOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return pk_measdoc;
	}
	
}

