export interface DailySalesEntry {
  dailySalesEntryId: number;
  brand: string;
  category: string;
  salesDate: Date;
  salesValue: number;
  lastModifiedDate: null;
  receiptProviderCode: string;
  mallCode: string;
  retailer: Retailer;
}

export interface Retailer {
  id: number;
  retailerProvidedId: number;
  retailerName: string;
  retailerLocation: string;
  retailerLicenseNumber: number;
}
