import {Component, OnInit} from '@angular/core';
import {SalesDataService} from '../../service/sales-data.service';
// @ts-ignore
import res from '../../../assets/sample-sales-data.json';
import {SalesEntryRequest} from '../../model/sales-entry-request';
import {DailySalesEntry} from '../../model/daily-sales-entry';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  salesEntryRequestList: SalesEntryRequest[] = [];
  dailySalesEntryList: DailySalesEntry[] = [];

  constructor(private sendSalesDataService: SalesDataService) {
  }

  ngOnInit() {
    console.log(res[0].brand);
    this.salesEntryRequestList = res;
    // this.pushSalesData();
  }

  private pushSalesData() {
    if (this.salesEntryRequestList && this.salesEntryRequestList.length > 0) {
      this.salesEntryRequestList.forEach(
        (item) => {
          this.sendSalesDataService.pushSalesData(item).subscribe();
        }
      );
    }
  }

  getAccessTokenValue() {
    return localStorage.getItem('token');
  }

  getAllSalesEntry() {
    this.sendSalesDataService.getAllSalesEntry().subscribe(
      (data: DailySalesEntry[]) => {
        this.dailySalesEntryList = data;
      }
    );
  }
}
