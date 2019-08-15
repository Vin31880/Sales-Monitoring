import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {SalesEntryRequest} from '../model/sales-entry-request';
import {Observable} from 'rxjs';
import {DailySalesEntry} from '../model/daily-sales-entry';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SalesDataService {

  constructor(private http: HttpClient) {
  }

  pushSalesData(salesEntryRequest: SalesEntryRequest): Observable<any> {
    return this.http.post<any>(environment.baseReceiverURL, salesEntryRequest);
  }

  getAllSalesEntry(): Observable<DailySalesEntry[]> {
    return this.http.get<DailySalesEntry[]>(environment.baseReceiverURL);
  }

}
