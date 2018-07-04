/* indexcode: i_po_invotran_1 */
create unique index i_po_invotran_1 on po_invctrantype (ctrantypeid asc)
;

/* indexcode: i_po_ordertran_1 */
create unique index i_po_ordertran_1 on po_potrantype (ctrantypeid asc)
;

/* indexcode: i_po_buyrtran_1 */
create unique index i_po_buyrtran_1 on po_buyrtrantype (ctrantypeid asc)
;

