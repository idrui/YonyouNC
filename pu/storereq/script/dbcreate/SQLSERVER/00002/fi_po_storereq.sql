/* indexcode: i_pu_422x_b_src */
create  index i_pu_422x_b_src on po_storereq_b (csourceid)
go

/* indexcode: i_pu_422x_b_dtorg */
create  index i_pu_422x_b_dtorg on po_storereq_b (dreqdate,
pk_org)
go

/* indexcode: i_pu_422x_b_fk */
create  index i_pu_422x_b_fk on po_storereq_b (pk_storereq)
go

/* indexcode: i_pu_422x_b_srctc2 */
create  index i_pu_422x_b_srctc2 on po_storereq_b (csourceid2,
csourcetypecode2)
go

/* indexcode: i_pu_422x_dtorg */
create  index i_pu_422x_dtorg on po_storereq (dbilldate,
pk_org)
go

/* indexcode: i_pu_422x_code */
create  index i_pu_422x_code on po_storereq (vbillcode)
go

