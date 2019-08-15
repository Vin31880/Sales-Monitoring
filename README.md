Sales Monitoring App

Sales monitoring system allows user to store, track and analyze sales data for all available sites.
Please find below high-level architecture for the same.


Let’s dive deeper now,
1.	Sender Service Client
Its UI interface (developed using angular 7) which will allow user 
a.	to login to Sales Monitoring system
b.	initiate sales data parsing by calling pushSalesData api.
c.	Show table with all pushed sales data by calling getAllSalesData api.

2.	Micro-service Ecosystem
I have followed micro-service architecture for this solution. It has following components
a.	Gateway Service: 
It is an API Gateway which is single entry point for all the API call from outside micro-service ecosystem.

b.	Registry Service: 
Its registry service which will keep track of all services.

c.	Config Server:
It allows us to externalize configuration.

d.	Security Service: 
It’s one of the most important service of this architecture. It allows us to secure our api. It’s implementation of oAuth Server which follows password grant flow.

e.	Receiver Service:
It exposes pushSalesData and getAllSalesData api to our receiver service through security service. It also send data to sales-analysis-service via feign clients.

f.	Sales Analysis Service:
It allows us to analyze the sales data received from receiver-service.
