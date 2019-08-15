export interface SalesEntryRequest {
  retailerId: number;
  retailerName: string;
  retailerLocation: string;
  brand: string;
  category: string;
  shiftDate: Date;
  shiftSalesValue: number;
  dailySalesSummaryId: number;
  receiptProviderCode: string;
  mallCode: string;
  retailerLicenseNumber: number;
}
