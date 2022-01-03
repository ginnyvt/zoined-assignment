<template>
	<div class="container">
		<!-- <div></div> -->
		<div
			v-if="loading"
			class="d-flex flex-column justify-content-center align-items-center mh-100"
		>
			<Loader />
		</div>
		<div v-else-if="fetchedStoreData.length === 0">
			<p>No Data.</p>
		</div>
		<div v-else-if="fetchedProductData.length === 0">
			<p>No Data.</p>
		</div>
		<div v-else>
			<!-- Total sales by store card -->
			<div class="card mb-5">
				<div class="card-header mb-4">Total Sales By Store</div>
				<div class="card-body">
					<ul class="nav nav-pills mb-3" id="store-tabs" role="tablist">
						<li
							v-for="(byStoreData, _, index) in byStoreData"
							:key="byStoreData.year"
							class="nav-item"
							role="presentation"
						>
							<button
								:class="['nav-link', index === 0 ? 'active' : '']"
								:id="`store-${byStoreData.year}-btn`"
								data-bs-toggle="pill"
								:data-bs-target="`#store-${byStoreData.year}-content`"
								type="button"
								role="tab"
								aria-controls="pills-home"
								aria-selected="true"
							>
								{{ byStoreData.year }}
							</button>
						</li>
					</ul>

					<!-- Tab contents -->
					<div class="tab-content mb-3" id="stores-tab-content">
						<div
							v-for="(byStoreData, _, index) in byStoreData"
							:key="byStoreData.year"
							:class="[
								'tab-pane',
								'fade',
								index === 0 ? ['active', 'show'] : [],
							]"
							:id="`store-${byStoreData.year}-content`"
							role="tabpanel"
							aria-labelledby="pills-home-tab"
						>
							<div>
								<vue3-chart-js v-bind="{ ...byStoreData }" />
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Total sales by product card -->
			<div class="card">
				<div class="card-header">Total Sales By Product</div>
				<div class="card-body mt-3">
					<ul class="nav nav-pills mb-3" id="product-tabs" role="tablist">
						<li
							v-for="(products, period, index) in byProductData"
							:key="period"
							class="nav-item"
							role="presentation"
						>
							<button
								:class="['nav-link', index === 0 ? 'active' : '']"
								:id="`product-${period}-btn`"
								data-bs-toggle="pill"
								:data-bs-target="`#product-${period}-content`"
								type="button"
								role="tab"
								aria-controls="pills-home"
								aria-selected="true"
							>
								{{ period }}
							</button>
						</li>
					</ul>

					<!-- Tab contents -->
					<div class="tab-content" id="product-tab-content">
						<div
							v-for="(value, period, index) in byStoreData"
							:key="period"
							:class="[
								'tab-pane',
								'fade',
								index === 0 ? ['active', 'show'] : [],
							]"
							:id="`product-${period}-content`"
							role="tabpanel"
							aria-labelledby="pills-home-tab"
						>
							<table class="table table-bordered table-striped mt-3">
								<!-- Table header -->
								<thead>
									<tr>
										<th scope="col">#</th>
										<th scope="col">Product</th>
										<th scope="col">Amount</th>
									</tr>
								</thead>
								<tbody>
									<!-- table row -->
									<tr
										v-for="(product, index) in byProductData[period]"
										:key="index"
									>
										<th scope="row">{{ ++index }}</th>
										<td>{{ product.name }}</td>
										<td>{{ product.amount }}</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import Loader from '../layout/Loader.vue';
import Vue3ChartJs from '@j-t-mcc/vue3-chartjs';
import { groupBy } from '../../utils';

export default {
	name: 'Dashboard',
	components: { Loader, Vue3ChartJs },

	data() {
		return {
			loading: false,
			fetchedStoreData: [],
			fetchedProductData: [],
			byStoreData: {},
			byProductData: {},
		};
	},

	methods: {
		async getTotalSalesByStore() {
			this.loading = true;
			try {
				const res = await fetch('http://localhost:9000/views/salesByStore');
				const data = await res.json();
				console.log(data);
				this.fetchedStoreData = data.salesReport;

				// Format data requirred by ChartJs
				const chartData = {};
				const storeDataByYear = groupBy(data.salesReport, 'period');

				for (const year in storeDataByYear) {
					const stores = [];
					const values = [];
					storeDataByYear[year].forEach((item) => {
						stores.push(item.name);
						values.push(item.amount);
					});
					chartData[year] = {
						year: year,
						type: 'bar',
						options: {
							responsive: true,
							plugins: {
								legend: {
									position: 'bottom',
								},
							},
							scales: {
								y: {
									ticks: {
										callback: function (value) {
											return `${value}`;
										},
									},
								},
							},
						},
						data: {
							labels: stores,
							datasets: [
								{
									label: year,
									data: values,
									backgroundColor: '#5899DA',
									hoverBackgroundColor: '#5899DA',
								},
							],
						},
					};
				}
				this.loading = false;
				this.byStoreData = chartData;
			} catch (e) {
				console.log(e);
				// this.loading = false;
				alert('Something goes wrong!');
			}
		},

		async getTotalSalesByProduct() {
			this.loading = true;
			try {
				const res = await fetch('http://localhost:9000/views/salesByProduct');
				const data = await res.json();
				this.fetchedProductData = data.salesReport;
				const productDataByYears = groupBy(data.salesReport, 'period');
				this.loading = false;
				this.byProductData = productDataByYears;
			} catch (e) {
				console.log(e);

				// this.loading = false;
				alert('Something goes wrong!');
			}
		},
	},

	mounted() {
		this.getTotalSalesByStore();
		this.getTotalSalesByProduct();
	},
};
</script>
