<template>
	<div>
		<div class="container">
			<div v-if="loading">
				<Loader />
			</div>
			<form @submit.prevent="onSubmit" v-else>
				<div class="form-group">
					<input type="file" @change="onFileUpload" />
					<button class="btn btn-primary btn-block">Upload</button>
				</div>
			</form>
		</div>
	</div>
</template>

<script>
import Loader from '../layout/Loader.vue';
import axios from 'axios';

export default {
	name: 'UploadFile',
	components: { Loader },
	data() {
		return { loading: false, file: null };
	},
	methods: {
		onFileUpload(e) {
			this.file = e.target.files[0];
		},
		onSubmit() {
			const formData = new FormData();
			formData.append('file', this.file, this.file.name);
			this.loading = true;
			axios
				.post('http://localhost:9000/upload/companyA', formData, {
					timeout: '25000s',
				})
				.then((res) => {
					this.loading = false;
					alert('File uploaded successfully!');
				})
				.catch((e) => {
					console.log(e);
					alert('Process time out.');
					this.loading = false;
				});
		},
	},
};
</script>
