/* tablename: ���Ļ��ܲ��� */
create table po_vmifi (pk_stockps_b char(20) not null 
/*���Ļ��ܲ�������ʶ*/,
pk_stockps char(20) null 
/*���Ļ��ܲ��񸨱�ʶ*/,
pk_group varchar2(20) null 
/*����*/,
pk_org varchar2(20) null 
/*��˾*/,
pk_org_v varchar2(20) null 
/*��˾�汾*/,
pk_storeorg_v varchar2(20) null 
/*�����֯�汾*/,
pk_storeorg varchar2(20) null 
/*�����֯*/,
pk_financeorg_v varchar2(20) null 
/*���������֯�汾*/,
pk_financeorg varchar2(20) null 
/*���������֯*/,
pk_costregion varchar2(20) null 
/*�ɱ���*/,
pk_srcmaterial varchar2(20) null 
/*�������°汾*/,
pk_material varchar2(20) null 
/*���ϱ���*/,
pk_batchcode varchar2(20) null 
/*���ε���*/,
vbatchcode varchar2(40) null 
/*���κ�*/,
dbizdate char(19) null 
/*ҵ������*/,
castunitid varchar2(20) null 
/*��λ*/,
ninnum number(28,8) null 
/*����������*/,
ninassistnum number(28,8) null 
/*��������*/,
cprojectid varchar2(20) null 
/*��Ŀ*/,
vchangerate varchar2(60) null 
/*������*/,
crowno varchar2(20) null 
/*�к�*/,
cproductorid varchar2(20) null 
/*��������*/,
cstateid varchar2(20) null 
/*���״̬*/,
cunitid varchar2(20) null 
/*����λ*/,
pk_supplier varchar2(20) null 
/*��Ӧ��*/,
ccurrencyid varchar2(20) null 
/*��λ��*/,
nnetprice number(28,8) null 
/*��������˰����*/,
ntaxnetprice number(28,8) null 
/*�����Һ�˰����*/,
vfree1 varchar2(101) null 
/*���ɸ�������1*/,
vfree2 varchar2(101) null 
/*���ɸ�������2*/,
vfree3 varchar2(101) null 
/*���ɸ�������3*/,
vfree4 varchar2(101) null 
/*���ɸ�������4*/,
vfree5 varchar2(101) null 
/*���ɸ�������5*/,
vfree6 varchar2(101) null 
/*���ɸ�������6*/,
vfree7 varchar2(101) null 
/*���ɸ�������7*/,
vfree8 varchar2(101) null 
/*���ɸ�������8*/,
vfree9 varchar2(101) null 
/*���ɸ�������9*/,
vfree10 varchar2(101) null 
/*���ɸ�������10*/,
pk_costappsn varchar2(20) null 
/*���ɱ���Ӧ����*/,
dtocostapdate varchar2(19) null 
/*�ݹ�����*/,
nestnum number(28,8) default 0 null 
/*�ݹ�������*/,
nestprice number(28,8) null 
/*�ݹ�����*/,
nesttaxrate number(28,8) null 
/*˰��*/,
nesttaxprice number(28,8) null 
/*�ݹ���˰����*/,
nestoprice number(28,8) null 
/*����˰����*/,
nestotaxprice number(28,8) null 
/*����˰����*/,
nesttaxmny number(28,8) null 
/*����˰��*/,
nestmny number(28,8) null 
/*������˰���*/,
nesttotalmny number(28,8) null 
/*���Ҽ�˰�ϼ�*/,
pk_estcurrency varchar2(20) null 
/*����*/,
nestexhgrate number(28,8) null 
/*�۱�����*/,
nestomny number(28,8) null 
/*��˰���*/,
nestototalmny number(28,8) null 
/*��˰�ϼ�*/,
festtaxtype integer default 1 null 
/*��˰���*/,
ftoiaflag integer default 0 null 
/*���ɱ���־*/,
ftoapflag integer default 0 null 
/*��Ӧ����־*/,
bsettlefinish char(1) default 'N' null 
/*�Ƿ�������*/,
naccumsettlenum number(28,8) null 
/*�ۼƽ�������*/,
naccestcoststtlnum number(28,8) null 
/*�ݹ������ۼƽ�������*/,
naccestcostwashmny number(28,8) null 
/*�ۼƻس��ݹ��ɱ����*/,
nacctocostadjstmny number(28,8) null 
/*�ۼƵ���ȷ�ϳɱ����*/,
nacctoapadjstotmny number(28,8) null 
/*�ۼƵ���ȷ��Ӧ��ԭ�Ҽ�˰�ϼ�*/,
naccpreeststtlmny number(28,8) null 
/*�ݹ�ǰ�ۼƽ�����*/,
naccgoodssettlemny number(28,8) null 
/*�ۼƻ��������*/,
naccsettlemny number(28,8) null 
/*�ۼƽ�����*/,
naccfeesettlemny number(28,8) null 
/*�ۼƷ��ý�����*/,
vbillcode varchar2(40) null 
/*���ݺ�*/,
dbilldate char(19) null 
/*��������*/,
pk_stordoc varchar2(20) null 
/*�ֿ�*/,
billmaker varchar2(20) null 
/*�Ƶ���*/,
modifier varchar2(20) null 
/*����޸���*/,
modifiedtime char(19) null 
/*����޸�ʱ��*/,
creationtime char(19) null 
/*����ʱ��*/,
creator varchar2(20) null 
/*������*/,
baffectcost char(1) null 
/*Ӱ��ɱ���־*/,
bnormpur char(1) null 
/*�Ƿ���ͨ�ɹ�*/,
vtrantypecode varchar2(20) null 
/*�������ͱ���*/,
pk_busitype varchar2(20) null 
/*ҵ������*/,
vconsumebillcode varchar2(40) null 
/*���ĵ��ݺ�*/,
cconsumehid varchar2(20) null 
/*���ĵ��ݱ�ͷ*/,
approver varchar2(20) null 
/*������*/,
taudittime char(19) null 
/*����ʱ��*/,
csumruleid varchar2(20) null 
/*���Ļ��ܹ���ID*/,
vmimatchpurinrule varchar2(10) null 
/*����ƥ��ɹ����Ĺ���*/,
ctrantypeid varchar2(20) null 
/*��������*/,
casscustid varchar2(20) null 
/*�ͻ�*/,
csendcountryid varchar2(20) null 
/*��������/����*/,
crececountryid varchar2(20) null 
/*�ջ�����/����*/,
ctaxcountryid varchar2(20) null 
/*��˰����/����*/,
fbuysellflag integer null 
/*��������*/,
btriatradeflag char(1) null 
/*����ó��*/,
cesttaxcodeid varchar2(20) null 
/*˰��*/,
nestcaltaxmny number(28,8) null 
/*��˰���*/,
nestnosubtaxrate number(28,8) null 
/*���ɵֿ�˰��*/,
nestnosubtax number(28,8) null 
/*���ɵֿ�˰��*/,
nestcalcostmny number(28,8) null 
/*�Ƴɱ����*/,
bopptaxflag char(1) null 
/*������˰*/,
nestcalcostprice number(28,8) null 
/*�ǳɱ�����*/,
cffileid varchar2(20) null 
/*������*/,
 constraint pk_po_vmifi primary key (pk_stockps_b),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: ���Ļ��ܷ����ݹ���̯��ϸ */
create table po_vmifi_fd (pk_stockps_fd char(20) not null 
/*�ݹ����÷�̯��ϸ*/,
pk_billtype varchar2(20) null 
/*��̯���ݵ�������*/,
pk_distbybill varchar2(20) null 
/*��̯���ݵ���*/,
pk_distbybill_b varchar2(20) null 
/*��̯���ݵ�����*/,
ndistbynum number(28,8) null 
/*��̯��������*/,
ndistbymny number(28,8) null 
/*��̯���ݽ��*/,
ndistmny number(28,8) null 
/*��̯���*/,
pk_iasrc_b varchar2(20) null 
/*���ɱ���ʶ*/,
pk_stockps_fee char(20) not null 
/*���Ļ��ܷ����ݹ���ϸ_����*/,
 constraint pk_po_vmifi_fd primary key (pk_stockps_fd),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: ���Ļ��ܷ����ݹ���ϸ */
create table po_vmifi_fee (pk_stockps_fee char(20) not null 
/*���Ļ��ܷ����ݹ���ϸ*/,
pk_stockps char(20) null 
/*���Ļ��ܲ���ͷ*/,
pk_group varchar2(20) null 
/*����*/,
pk_financeorg varchar2(20) null 
/*������֯*/,
pk_supplier varchar2(20) null 
/*��Ӧ������*/,
pk_costfactor varchar2(20) null 
/*�ɱ�Ҫ��*/,
pk_feematerial varchar2(20) null 
/*������*/,
pk_srcfeematerial varchar2(20) null 
/*�ݹ���������*/,
pk_estpsn varchar2(20) null 
/*�ݹ���*/,
destdate char(19) null 
/*�ݹ�����*/,
nestexchgrate number(28,8) null 
/*�۱�����*/,
btoia char(1) default 'N' null 
/*���ɱ���־*/,
btoap char(1) default 'N' null 
/*��Ӧ����־*/,
nestmny number(28,8) null 
/*������˰���*/,
nesttaxmny number(28,8) null 
/*����˰��*/,
nesttaxrate number(28,8) null 
/*˰��*/,
nesttotalmny number(28,8) null 
/*���Ҽ�˰�ϼ�*/,
pk_estcurrency varchar2(20) null 
/*����*/,
pk_localcurrency varchar2(20) null 
/*����*/,
nestomny number(28,8) null 
/*ԭ����˰���*/,
nestototalmny number(28,8) null 
/*ԭ�Ҽ�˰�ϼ�*/,
pk_firstsettle varchar2(20) null 
/*�ݹ��������ϵ�һ�ν���ķ��÷�Ʊ���ڽ��㵥*/,
pk_firstsettle_b varchar2(20) null 
/*�ݹ��������ϵ�һ�ν���ķ��÷�Ʊ���ڽ��㵥��*/,
pk_stockps_b char(20) not null 
/*���Ļ��ܲ���_����*/,
csendcountryid varchar2(20) null 
/*��������/����*/,
crececountryid varchar2(20) null 
/*�ջ�����/����*/,
ctaxcountryid varchar2(20) null 
/*��˰����/����*/,
fbuysellflag integer null 
/*��������*/,
btriatradeflag char(1) null 
/*����ó��*/,
ctaxcodeid varchar2(20) null 
/*˰��*/,
ftaxtypeflag integer null 
/*��˰���*/,
ncaltaxmny number(28,8) null 
/*��˰���*/,
nnosubtaxrate number(28,8) null 
/*���ɵֿ�˰��*/,
nnosubtax number(28,8) null 
/*���ɵֿ�˰��*/,
ncalcostmny number(28,8) null 
/*�Ƴɱ����*/,
bopptaxflag char(1) null 
/*������˰*/,
 constraint pk_po_vmifi_fee primary key (pk_stockps_fee),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

