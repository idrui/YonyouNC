/* tablename: �����������뵥��ϸ */
create table po_storereq_b (
pk_storereq_b nchar(20) not null 
/*�����������뵥��ϸ*/,
pk_group nvarchar(20) null 
/*����*/,
pk_org nvarchar(20) null 
/*�����֯���°汾*/,
pk_org_v nvarchar(20) null 
/*�����֯*/,
crowno nvarchar(20) null 
/*�к�*/,
pk_material nvarchar(20) null 
/*���ϰ汾��Ϣ*/,
pk_srcmaterial nvarchar(20) null 
/*������Ϣ*/,
cunitid nvarchar(20) null 
/*����λ*/,
nnum decimal(28,8) null 
/*������*/,
castunitid nvarchar(20) null 
/*��λ*/,
nastnum decimal(28,8) null 
/*����*/,
vchangerate nvarchar(60) null 
/*������*/,
ntaxprice decimal(28,8) null 
/*�����Һ�˰����*/,
ntaxmny decimal(28,8) null 
/*���Ҽ�˰�ϼ�*/,
pk_reqstordoc nvarchar(20) null 
/*����ֿ�*/,
dreqdate nvarchar(19) null 
/*��������*/,
pk_apppsn nvarchar(20) null 
/*������*/,
pk_appdept_v nvarchar(20) null 
/*���벿��*/,
pk_appdept nvarchar(20) null 
/*���벿�����°汾*/,
pk_receiveaddress nvarchar(20) null 
/*�ջ���ַ*/,
cdevareaid nvarchar(20) null 
/*�ջ�����*/,
cdevaddrid nvarchar(20) null 
/*�ջ��ص�*/,
csourcetypecode nvarchar(20) null 
/*��Դ��������*/,
csourceid nvarchar(20) null 
/*��Դ����*/,
csourcebid nvarchar(20) null 
/*��Դ������ϸ*/,
vsourcecode nvarchar(40) null 
/*��Դ���ݺ�*/,
vsourcetrantype nvarchar(20) null 
/*��Դ��������*/,
vsourcerowno nvarchar(20) null 
/*��Դ�����к�*/,
cfirsttypecode nvarchar(20) null 
/*Դͷ��������*/,
cfirstid nvarchar(20) null 
/*Դͷ����*/,
cfirstbid nvarchar(20) null 
/*Դͷ������ϸ*/,
vfirstcode nvarchar(40) null 
/*Դͷ���ݺ�*/,
vfirsttrantype nvarchar(20) null 
/*Դͷ��������*/,
vfirstrowno nvarchar(20) null 
/*Դͷ�����к�*/,
naccuoutnum decimal(28,8) null 
/*�ۼƳ���������*/,
naccuoutreqnum decimal(28,8) null 
/*�ۼ��������������*/,
bclose nchar(1) null default 'N' 
/*�Ƿ�ر�*/,
pk_batchcode nvarchar(20) null 
/*���κ�����*/,
vbatchcode nvarchar(40) null 
/*���κ�*/,
cvendorid nvarchar(20) null 
/*��Ӧ��*/,
cproductorid nvarchar(20) null 
/*��������*/,
cprojectid nvarchar(20) null 
/*��Ŀ*/,
cprojecttaskid nvarchar(20) null 
/*��Ŀ����*/,
ccostelementid nvarchar(20) null 
/*�ɱ�Ҫ��*/,
vfree1 nvarchar(101) null 
/*���ɸ�������1*/,
vfree2 nvarchar(101) null 
/*���ɸ�������2*/,
vfree3 nvarchar(101) null 
/*���ɸ�������3*/,
vfree4 nvarchar(101) null 
/*���ɸ�������4*/,
vfree5 nvarchar(101) null 
/*���ɸ�������5*/,
vfree6 nvarchar(101) null 
/*���ɸ�������6*/,
vfree7 nvarchar(101) null 
/*���ɸ�������7*/,
vfree8 nvarchar(101) null 
/*���ɸ�������8*/,
vfree9 nvarchar(101) null 
/*���ɸ�������9*/,
vfree10 nvarchar(101) null 
/*���ɸ�������10*/,
vbdef1 nvarchar(101) null 
/*�Զ�����1*/,
vbdef2 nvarchar(101) null 
/*�Զ�����2*/,
vbdef3 nvarchar(101) null 
/*�Զ�����3*/,
vbdef4 nvarchar(101) null 
/*�Զ�����4*/,
vbdef5 nvarchar(101) null 
/*�Զ�����5*/,
vbdef6 nvarchar(101) null 
/*�Զ�����6*/,
vbdef7 nvarchar(101) null 
/*�Զ�����7*/,
vbdef8 nvarchar(101) null 
/*�Զ�����8*/,
vbdef9 nvarchar(101) null 
/*�Զ�����9*/,
vbdef10 nvarchar(101) null 
/*�Զ�����10*/,
vbdef11 nvarchar(101) null 
/*�Զ�����11*/,
vbdef12 nvarchar(101) null 
/*�Զ�����12*/,
vbdef13 nvarchar(101) null 
/*�Զ�����13*/,
vbdef14 nvarchar(101) null 
/*�Զ�����14*/,
vbdef15 nvarchar(101) null 
/*�Զ�����15*/,
vbdef16 nvarchar(101) null 
/*�Զ�����16*/,
vbdef17 nvarchar(101) null 
/*�Զ�����17*/,
vbdef18 nvarchar(101) null 
/*�Զ�����18*/,
vbdef19 nvarchar(101) null 
/*�Զ�����19*/,
vbdef20 nvarchar(101) null 
/*�Զ�����20*/,
vbmemo nvarchar(181) null 
/*��ע*/,
dbilldate nchar(19) null 
/*��������*/,
ccurrencyid nvarchar(20) null 
/*���ұ���*/,
pk_storereq nchar(20) not null 
/*�����������뵥_����*/,
naccumbuyreqnum decimal(28,8) null 
/*�ۼ��빺������*/,
pk_reqstoorg_v nvarchar(20) null 
/*ԭʼ��������֯*/,
pk_reqstoorg nvarchar(20) null 
/*ԭʼ��������֯���°汾*/,
pk_nextbalanceorg_v nvarchar(20) null 
/*�´�ƽ������֯*/,
pk_nextbalanceorg nvarchar(20) null 
/*�´�ƽ������֯���°汾*/,
bendgather nchar(1) null 
/*��ƽ��*/,
naccustornum decimal(28,8) null 
/*�������������*/,
nnetnum decimal(28,8) null 
/*ת������������*/,
csourceid2 nvarchar(20) null 
/*���ε���*/,
csourcebid2 nvarchar(20) null 
/*���ε�����ϸ*/,
vsourcecode2 nvarchar(40) null 
/*���ε��ݺ�*/,
vsourcerowno2 nvarchar(20) null 
/*���ε����к�*/,
csourcetypecode2 nvarchar(20) null 
/*���ε�������*/,
vsourcetrantype2 nvarchar(20) null 
/*���ε��ݽ�������*/,
cfirstid2 nvarchar(20) null 
/*��������*/,
cfirstbid2 nvarchar(20) null 
/*����������ϸ*/,
vfirstcode2 nvarchar(40) null 
/*����������*/,
vfirstrowno2 nvarchar(20) null 
/*���������к�*/,
cfirsttypecode2 nvarchar(20) null 
/*������������*/,
vfirsttrantype2 nvarchar(20) null 
/*�������ݽ�������*/,
tgathertime nchar(19) null 
/*����ʱ��*/,
cgatherpsnid nvarchar(20) null 
/*������*/,
cgatherid nvarchar(20) null 
/*����ID*/,
naccumminusnum decimal(28,8) null 
/*����ƽ��ת�빺������*/,
cbs nvarchar(20) null 
/*CBS*/,
 constraint pk_po_storereq_b primary key (pk_storereq_b),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: �����������뵥 */
create table po_storereq (
pk_storereq nchar(20) not null 
/*�����������뵥*/,
pk_group nvarchar(20) null 
/*����*/,
pk_org nvarchar(20) null 
/*�����֯���°汾*/,
pk_org_v nvarchar(20) null 
/*�����֯*/,
vbillcode nvarchar(40) null 
/*���뵥��*/,
dbilldate nchar(19) null 
/*��������*/,
pk_appdepth nvarchar(20) null 
/*���벿�����°汾*/,
pk_appdepth_v nvarchar(20) null 
/*���벿��*/,
pk_apppsnh nvarchar(20) null 
/*������*/,
vtrantypecode nvarchar(20) null 
/*�������ͱ������*/,
fbillstatus int null default 0 
/*����״̬*/,
burgency nchar(1) null default 'N' 
/*����*/,
ntotalastnum decimal(28,8) null 
/*������*/,
ntotalorigmny decimal(28,8) null 
/*��˰�ϼ�*/,
pk_project nvarchar(20) null 
/*��Ŀ*/,
creator nvarchar(20) null 
/*������*/,
creationtime nchar(19) null 
/*����ʱ��*/,
billmaker nvarchar(20) null 
/*�Ƶ���*/,
dmakedate nchar(19) null 
/*�Ƶ�����*/,
approver nvarchar(20) null 
/*������*/,
taudittime nvarchar(19) null 
/*��������*/,
modifier nvarchar(20) null 
/*����޸���*/,
modifiedtime nvarchar(19) null 
/*����޸�ʱ��*/,
iprintcount int null default 0 
/*��ӡ����*/,
vdef1 nvarchar(101) null 
/*�Զ�����1*/,
vdef2 nvarchar(101) null 
/*�Զ�����2*/,
vdef3 nvarchar(101) null 
/*�Զ�����3*/,
vdef4 nvarchar(101) null 
/*�Զ�����4*/,
vdef5 nvarchar(101) null 
/*�Զ�����5*/,
vdef6 nvarchar(101) null 
/*�Զ�����6*/,
vdef7 nvarchar(101) null 
/*�Զ�����7*/,
vdef8 nvarchar(101) null 
/*�Զ�����8*/,
vdef9 nvarchar(101) null 
/*�Զ�����9*/,
vdef10 nvarchar(101) null 
/*�Զ�����10*/,
vdef11 nvarchar(101) null 
/*�Զ�����11*/,
vdef12 nvarchar(101) null 
/*�Զ�����12*/,
vdef13 nvarchar(101) null 
/*�Զ�����13*/,
vdef14 nvarchar(101) null 
/*�Զ�����14*/,
vdef15 nvarchar(101) null 
/*�Զ�����15*/,
vdef16 nvarchar(101) null 
/*�Զ�����16*/,
vdef17 nvarchar(101) null 
/*�Զ�����17*/,
vdef18 nvarchar(101) null 
/*�Զ�����18*/,
vdef19 nvarchar(101) null 
/*�Զ�����19*/,
vdef20 nvarchar(101) null 
/*�Զ�����20*/,
vmemo nvarchar(181) null 
/*��ע*/,
freqtypeflag int null 
/*��������*/,
ctrantypeid nvarchar(20) null 
/*����������������*/,
 constraint pk_po_storereq primary key (pk_storereq),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

