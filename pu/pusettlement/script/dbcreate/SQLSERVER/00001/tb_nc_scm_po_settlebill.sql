/* tablename: ���㵥��ϸ */
create table po_settlebill_b (
pk_settlebill_b nchar(20) not null 
/*������ϸ*/,
crowno nvarchar(20) null 
/*�к�*/,
pk_group nvarchar(20) null 
/*��������*/,
pk_org nvarchar(20) null 
/*������֯*/,
pk_org_v nvarchar(20) null 
/*������֯�汾*/,
frowtype int null 
/*������*/,
vinvoicetrantype nvarchar(20) null 
/*��Ʊ��������*/,
pk_invoice nvarchar(20) null 
/*��Ʊ*/,
pk_invoice_b nvarchar(20) null 
/*��Ʊ��*/,
vinvoicecode nvarchar(40) null 
/*��Ʊ��*/,
vstockbilltype nvarchar(20) null 
/*��浥������*/,
vstocktrantype nvarchar(20) null 
/*��ⵥ��������*/,
pk_stock nvarchar(20) null 
/*��浥��*/,
pk_stock_b nvarchar(20) null 
/*��浥����*/,
vstockcode nvarchar(40) null 
/*��ⵥ��/���Ļ���*/,
pk_stockorder nvarchar(20) null 
/*��浥�ݵĶ���ͷ*/,
pk_stockorder_b nvarchar(20) null 
/*��浥�ݵĶ�����*/,
pk_invoiceorder nvarchar(20) null 
/*��Ʊ�Ķ���ͷ*/,
pk_invoiceorder_b nvarchar(20) null 
/*��Ʊ�Ķ�����*/,
pk_dtransaleid nvarchar(20) null 
/*��Ӧ��ֱ�����۶���*/,
pk_dtransalebid nvarchar(20) null 
/*��Ӧ��ֱ�����۶�����*/,
pk_rushstock nvarchar(20) null 
/*�Գ�Ŀ�浥��*/,
pk_rushstock_b nvarchar(20) null 
/*�Գ�Ŀ�浥����*/,
pk_rushinvoice nvarchar(20) null 
/*�Գ�ķ�Ʊ*/,
pk_rushinvoice_b nvarchar(20) null 
/*�Գ�ķ�Ʊ��*/,
pk_srcmaterial nvarchar(20) null 
/*����*/,
pk_material nvarchar(20) null 
/*���ϰ汾*/,
cunitid nvarchar(20) null 
/*����λ*/,
nsettlenum decimal(28,8) null 
/*��������*/,
nprice decimal(28,8) null 
/*����*/,
nmoney decimal(28,8) null 
/*���*/,
ngoodsprice decimal(28,8) null 
/*������㵥��*/,
ngoodsmoney decimal(28,8) null 
/*���������*/,
bwashest nchar(1) null 
/*�Ƿ�����ݹ���־*/,
nclashestmoney decimal(28,8) null 
/*�����ݹ����*/,
noppoconfmmoney decimal(28,8) null 
/*��Ӧ��ȷ�ϳɱ�*/,
noppoconfmapmny decimal(28,8) null 
/*��Ӧ��ȷ��Ӧ��ԭ�Ҽ�˰�ϼ�*/,
nreasonalwastnum decimal(28,8) null 
/*�����������*/,
nreasonalwastprice decimal(28,8) null 
/*������ĵ���*/,
nreasonalwastmny decimal(28,8) null 
/*������Ľ��*/,
pk_arrstockorg nvarchar(20) null 
/*�ջ������֯*/,
pk_arrstockorg_v nvarchar(20) null 
/*�ջ������֯�汾*/,
pk_costregion nvarchar(20) null 
/*�ɱ���*/,
ncostfactor1 decimal(28,8) null 
/*���ҳɱ�Ҫ��1*/,
ncostfactor2 decimal(28,8) null 
/*���ҳɱ�Ҫ��2*/,
ncostfactor3 decimal(28,8) null 
/*���ҳɱ�Ҫ��3*/,
ncostfactor4 decimal(28,8) null 
/*���ҳɱ�Ҫ��4*/,
ncostfactor5 decimal(28,8) null 
/*���ҳɱ�Ҫ��5*/,
ncostfactor6 decimal(28,8) null 
/*���ҳɱ�Ҫ��6*/,
ncostfactor7 decimal(28,8) null 
/*���ҳɱ�Ҫ��7*/,
ncostfactor8 decimal(28,8) null 
/*���ҳɱ�Ҫ��8*/,
ndiscount decimal(28,8) null 
/*�ۿ�*/,
pk_supplier nvarchar(20) null 
/*��Ӧ��*/,
pk_dept nvarchar(20) null 
/*�ɹ�����ԭʼ��Ϣ*/,
pk_dept_v nvarchar(20) null 
/*�ɹ�����*/,
pk_psndoc nvarchar(20) null 
/*ҵ��Ա*/,
pk_purchasein nvarchar(20) null 
/*�ɹ����ͷID*/,
pk_purchasein_b nvarchar(20) null 
/*�ɹ������ID*/,
pk_subcontract nvarchar(20) null 
/*ί�����ͷID*/,
pk_subcontract_b nvarchar(20) null 
/*ί�������ID*/,
pk_voiconsume nvarchar(20) null 
/*���Ļ��ܱ�ͷID*/,
pk_voiconsume_b nvarchar(20) null 
/*���Ļ��ܱ���ID*/,
pk_initialest nvarchar(20) null 
/*�ڳ��ݹ���ͷID*/,
pk_initialest_b nvarchar(20) null 
/*�ڳ��ݹ�����ID*/,
pk_transin nvarchar(20) null 
/*�������ͷID*/,
pk_transin_b nvarchar(20) null 
/*���������ID*/,
pk_generalin nvarchar(20) null 
/*�������ͷID*/,
pk_generalin_b nvarchar(20) null 
/*���������ID*/,
pk_settlebill nchar(20) not null 
/*���㵥ͷ_����*/,
nadjustmny decimal(28,8) null 
/*����������*/,
stockbilldate nchar(19) null 
/*�������*/,
invoicebilldate nchar(19) null 
/*��Ʊ����*/,
vitctcode nvarchar(40) null 
/*���ں�ͬ��*/,
 constraint pk_po_settlebill_b primary key (pk_settlebill_b),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ���㵥ͷ */
create table po_settlebill (
pk_settlebill nchar(20) not null 
/*���㵥*/,
pk_group nvarchar(20) null 
/*��������*/,
pk_org nvarchar(20) null 
/*������֯*/,
pk_org_v nvarchar(20) null 
/*������֯�汾*/,
ccurrencyid nvarchar(20) null 
/*���ұ���*/,
bvirtualsettle nchar(1) null default 'N' 
/*�Ƿ����ⷢƱ�Ľ���*/,
vbillcode nvarchar(40) null 
/*���㵥��*/,
dbilldate nchar(19) null 
/*��������*/,
btoia nchar(1) null default 'N' 
/*�Ѵ����*/,
iprintcount int null default 0 
/*��ӡ����*/,
billmaker nvarchar(20) null 
/*�Ƶ���*/,
dmakedate nchar(19) null 
/*�Ƶ�����*/,
creationtime nchar(19) null 
/*����ʱ��*/,
creator nvarchar(20) null 
/*������*/,
fsettletype int null 
/*���㵥������*/,
 constraint pk_po_settlebill primary key (pk_settlebill),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: ������÷�̯��ϸ */
create table po_settle_feeallot (
pk_settle_feeallot nchar(20) not null 
/*���÷�̯��ϸ*/,
pk_settlebill nchar(20) null 
/*���㵥ͷ*/,
pk_srcmaterial nvarchar(20) null 
/*��������*/,
pk_material nvarchar(20) null 
/*�������ϰ汾*/,
vallotbilltype nvarchar(4) null 
/*���÷�̯���ݵĵ�������*/,
pk_allotbillid nvarchar(20) null 
/*��̯�ĵ���ID*/,
pk_allotbillbid nvarchar(20) null 
/*��̯�ĵ�����ID*/,
nallotmoney decimal(28,8) null 
/*���÷�̯���*/,
bestfirstsettle nchar(1) null 
/*�Ƿ��ݹ��������ϵĵ�һ�ν���*/,
ntimesafterfirst int null 
/*��һ�ν���ĺ����ۼƷ��ý������*/,
pk_oppofee_b nchar(20) null 
/*��Ӧ�ķ������ڵĽ�����ID*/,
pk_supplier nvarchar(20) null 
/*��Ӧ��*/,
pk_settlebill_b nchar(20) not null 
/*���㵥��ϸ_����*/,
nallotbillnum decimal(28,8) null 
/*��̯����������*/,
nallotbillmny decimal(28,8) null 
/*��̯�����ݽ��*/,
 constraint pk_settle_feeallot primary key (pk_settle_feeallot),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

