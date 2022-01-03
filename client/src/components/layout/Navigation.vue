<template>
	<nav
		class="navbar navbar-light navbar-expand-lg mb-5"
		style="background-color: #e3f2fd"
	>
		<div class="container">
			<router-link to="/dashboard" class="navbar-brand">Zoined</router-link>
			<button
				class="navbar-toggler"
				type="button"
				data-bs-toggle="collapse"
				data-bs-target="#navbarTogglerDemo"
				aria-controls="navbarTogglerDemo"
				aria-expanded="false"
				aria-label="Toggle navigation"
			>
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarTogglerDemo">
				<ul class="navbar-nav mb-2 mb-lg-0 ms-auto">
					<li class="nav-item">
						<router-link to="/upload" class="nav-link">Upload File</router-link>
					</li>
					<li class="nav-item">
						<router-link to="/dashboard" class="nav-link"
							>Dashboard</router-link
						>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<router-view />
</template>

<script>
export default {
	name: 'Navigation',
};
</script>
