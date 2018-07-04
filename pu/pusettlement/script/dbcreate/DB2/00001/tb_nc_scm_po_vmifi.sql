/* tablename: ���Ļ��ܲ��� */
create table po_vmifi (pk_stockps_b char(20) not null 
/*���Ļ��ܲ�������ʶ*/,
pk_stockps char(20) null 
/*���Ļ��ܲ��񸨱�ʶ*/,
pk_group varchar(20) null 
/*����*/,
pk_org varchar(20) null 
/*��˾*/,
pk_org_v varchar(20) null 
/*��˾�汾*/,
pk_storeorg_v varchar(20) null 
/*�����֯�汾*/,
pk_storeorg varchar(20) null 
/*�����֯*/,
pk_financeorg_v varchar(20) null 
/*���������֯�汾*/,
pk_financeorg varchar(20) null 
/*���������֯*/,
pk_costregion varchar(20) null 
/*�ɱ���*/,
pk_srcmaterial varchar(20) null 
/*�������°汾*/,
pk_material varchar(20) null 
/*���ϱ���*/,
pk_batchcode varchar(20) null 
/*���ε���*/,
vbatchcode varchar(40) null 
/*���κ�*/,
dbizdate char(19) null 
/*ҵ������*/,
castunitid varchar(20) null 
/*��λ*/,
ninnum decimal(28,8) null 
/*����������*/,
ninassistnum decimal(28,8) null 
/*��������*/,
cprojectid varchar(20) null 
/*��Ŀ*/,
vchangerate varchar(60) null 
/*������*/,
crowno varchar(20) null 
/*�к�*/,
cproductorid varchar(20) null 
/*��������*/,
cstateid varchar(20) null 
/*���״̬*/,
cunitid varchar(20) null 
/*����λ*/,
pk_supplier varchar(20) null 
/*��Ӧ��*/,
ccurrencyid varchar(20) null 
/*��λ��*/,
nnetprice decimal(28,8) null 
/*��������˰����*/,
ntaxnetprice decimal(28,8) null 
/*�����Һ�˰����*/,
vfree1 varchar(101) null 
/*���ɸ�������1*/,
vfree2 varchar(101) null 
/*���ɸ�������2*/,
vfree3 varchar(101) null 
/*���ɸ�������3*/,
vfree4 varchar(101) null 
/*���ɸ�������4*/,
vfree5 varchar(101) null 
/*���ɸ�������5*/,
vfree6 varchar(101) null 
/*���ɸ�������6*/,
vfree7 varchar(101) null 
/*���ɸ�������7*/,
vfree8 varchar(101) null 
/*���ɸ�������8*/,
vfree9 varchar(101) null 
/*���ɸ�������9*/,
vfree10 varchar(101) null 
/*���ɸ�������10*/,
pk_costappsn varchar(20) null 
/*���ɱ���Ӧ����*/,
dtocostapdate varchar(19) null 
/*�ݹ�����*/,
nestnum decimal(28,8) null default 0 
/*�ݹ�������*/,
nestprice decimal(28,8) null 
/*�ݹ�����*/,
nesttaxrate decimal(28,8) null 
/*˰��*/,
nesttaxprice decimal(28,8) null 
/*�ݹ���˰����*/,
nestoprice decimal(28,8) null 
/*����˰����*/,
nestotaxprice decimal(28,8) null 
/*����˰����*/,
nesttaxmny decimal(28,8) null 
/*����˰��*/,
nestmny decimal(28,8) null 
/*������˰���*/,
nesttotalmny decimal(28,8) null 
/*���Ҽ�˰�ϼ�*/,
pk_estcurrency varchar(20) null 
/*����*/,
nestexhgrate decimal(28,8) null 
/*�۱�����*/,
nestomny decimal(28,8) null 
/*��˰���*/,
nestototalmny decimal(28,8) null 
/*��˰�ϼ�*/,
festtaxtype integer null default 1 
/*��˰���*/,
ftoiaflag integer null default 0 
/*���ɱ���־*/,
ftoapflag integer null default 0 
/*��Ӧ����־*/,
bsettlefinish char(1) null default 'N' 
/*�Ƿ�������*/,
naccumsettlenum decimal(28,8) null 
/*�ۼƽ�������*/,
naccestcoststtlnum decimal(28,8) null 
/*�ݹ������ۼƽ�������*/,
naccestcostwashmny decimal(28,8) null 
/*�ۼƻس��ݹ��ɱ����*/,
nacctocostadjstmny decimal(28,8) null 
/*�ۼƵ���ȷ�ϳɱ����*/,
nacctoapadjstotmny decimal(28,8) null 
/*�ۼƵ���ȷ��Ӧ��ԭ�Ҽ�˰�ϼ�*/,
naccpreeststtlmny decimal(28,8) null 
/*�ݹ�ǰ�ۼƽ�����*/,
naccgoodssettlemny decimal(28,8) null 
/*�ۼƻ��������*/,
naccsettlemny decimal(28,8) null 
/*�ۼƽ�����*/,
naccfeesettlemny decimal(28,8) null 
/*�ۼƷ��ý�����*/,
vbillcode varchar(40) null 
/*���ݺ�*/,
dbilldate char(19) null 
/*��������*/,
pk_stordoc varchar(20) null 
/*�ֿ�*/,
billmaker varchar(20) null 
/*�Ƶ���*/,
modifier varchar(20) null 
/*����޸���*/,
modifiedtime char(19) null 
/*����޸�ʱ��*/,
creationtime char(19) null 
/*����ʱ��*/,
creator varchar(20) null 
/*������*/,
baffectcost char(1) null 
/*Ӱ��ɱ���־*/,
bnormpur char(1) null 
/*�Ƿ���ͨ�ɹ�*/,
vtrantypecode varchar(20) null 
/*�������ͱ���*/,
pk_busitype varchar(20) null 
/*ҵ������*/,
vconsumebillcode varchar(40) null 
/*���ĵ��ݺ�*/,
cconsumehid varchar(20) null 
/*���ĵ��ݱ�ͷ*/,
approver varchar(20) null 
/*������*/,
taudittime char(19) null 
/*����ʱ��*/,
csumruleid varchar(20) null 
/*���Ļ��ܹ���ID*/,
vmimatchpurinrule varchar(10) null 
/*����ƥ��ɹ����Ĺ���*/,
ctrantypeid varchar(20) null 
/*��������*/,
casscustid varchar(20) null 
/*�ͻ�*/,
csendcountryid varchar(20) null 
/*��������/����*/,
crececountryid varchar(20) null 
/*�ջ�����/����*/,
ctaxcountryid varchar(20) null 
/*��˰����/����*/,
fbuysellflag integer null 
/*��������*/,
btriatradeflag char(1) null 
/*����ó��*/,
cesttaxcodeid varchar(20) null 
/*˰��*/,
nestcaltaxmny decimal(28,8) null 
/*��˰���*/,
nestnosubtaxrate decimal(28,8) null 
/*���ɵֿ�˰��*/,
nestnosubtax decimal(28,8) null 
/*���ɵֿ�˰��*/,
nestcalcostmny decimal(28,8) null 
/*�Ƴɱ����*/,
bopptaxflag char(1) null 
/*������˰*/,
nestcalcostprice decimal(28,8) null 
/*�ǳɱ�����*/,
cffileid varchar(20) null 
/*������*/,
 constraint pk_po_vmifi primary key (pk_stockps_b),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ���Ļ��ܷ����ݹ���̯��ϸ */
create table po_vmifi_fd (pk_stockps_fd char(20) not null 
/*�ݹ����÷�̯��ϸ*/,
pk_billtype varchar(20) null 
/*��̯���ݵ�������*/,
pk_distbybill varchar(20) null 
/*��̯���ݵ���*/,
pk_distbybill_b varchar(20) null 
/*��̯���ݵ�����*/,
ndistbynum decimal(28,8) null 
/*��̯��������*/,
ndistbymny decimal(28,8) null 
/*��̯���ݽ��*/,
ndistmny decimal(28,8) null 
/*��̯���*/,
pk_iasrc_b varchar(20) null 
/*���ɱ���ʶ*/,
pk_stockps_fee char(20) not null 
/*���Ļ��ܷ����ݹ���ϸ_����*/,
 constraint pk_po_vmifi_fd primary key (pk_stockps_fd),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ���Ļ��ܷ����ݹ���ϸ */
create table po_vmifi_fee (pk_stockps_fee char(20) not null 
/*���Ļ��ܷ����ݹ���ϸ*/,
pk_stockps char(20) null 
/*���Ļ��ܲ���ͷ*/,
pk_group varchar(20) null 
/*����*/,
pk_financeorg varchar(20) null 
/*������֯*/,
pk_supplier varchar(20) null 
/*��Ӧ������*/,
pk_costfactor varchar(20) null 
/*�ɱ�Ҫ��*/,
pk_feematerial varchar(20) null 
/*������*/,
pk_srcfeematerial varchar(20) null 
/*�ݹ���������*/,
pk_estpsn varchar(20) null 
/*�ݹ���*/,
destdate char(19) null 
/*�ݹ�����*/,
nestexchgrate decimal(28,8) null 
/*�۱�����*/,
btoia char(1) null default 'N' 
/*���ɱ���־*/,
btoap char(1) null default 'N' 
/*��Ӧ����־*/,
nestmny decimal(28,8) null 
/*������˰���*/,
nesttaxmny decimal(28,8) null 
/*����˰��*/,
nesttaxrate decimal(28,8) null 
/*˰��*/,
nesttotalmny decimal(28,8) null 
/*���Ҽ�˰�ϼ�*/,
pk_estcurrency varchar(20) null 
/*����*/,
pk_localcurrency varchar(20) null 
/*����*/,
nestomny decimal(28,8) null 
/*ԭ����˰���*/,
nestototalmny decimal(28,8) null 
/*ԭ�Ҽ�˰�ϼ�*/,
pk_firstsettle varchar(20) null 
/*�ݹ��������ϵ�һ�ν���ķ��÷�Ʊ���ڽ��㵥*/,
pk_firstsettle_b varchar(20) null 
/*�ݹ��������ϵ�һ�ν���ķ��÷�Ʊ���ڽ��㵥��*/,
pk_stockps_b char(20) not null 
/*���Ļ��ܲ���_����*/,
csendcountryid varchar(20) null 
/*��������/����*/,
crececountryid varchar(20) null 
/*�ջ�����/����*/,
ctaxcountryid varchar(20) null 
/*��˰����/����*/,
fbuysellflag integer null 
/*��������*/,
btriatradeflag char(1) null 
/*����ó��*/,
ctaxcodeid varchar(20) null 
/*˰��*/,
ftaxtypeflag integer null 
/*��˰���*/,
ncaltaxmny decimal(28,8) null 
/*��˰���*/,
nnosubtaxrate decimal(28,8) null 
/*���ɵֿ�˰��*/,
nnosubtax decimal(28,8) null 
/*���ɵֿ�˰��*/,
ncalcostmny decimal(28,8) null 
/*�Ƴɱ����*/,
bopptaxflag char(1) null 
/*������˰*/,
 constraint pk_po_vmifi_fee primary key (pk_stockps_fee),
 ts char(19) null,
dr smallint null default 0
)
;

