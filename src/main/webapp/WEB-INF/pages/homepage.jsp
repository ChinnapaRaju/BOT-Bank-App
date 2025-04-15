<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" />
<style>
@import
	url("https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500&display=swap")
	;

:root {
	--primary: #eeeeee;
	--secondary: #227c70;
	--green: #82cd47;
	--secondary-light: rgba(91, 190, 177, 0.2);
	--secondary-light-2: rgb(127, 183, 126, 0.1);
	--white: #fff;
	--black: #393e46;
	--shadow: 0px 2px 8px 0px var(--secondary-light);
}

* {
	margin: 0;
	padding: 0;
	list-style-type: none;
	box-sizing: border-box;
	font-family: "Poppins", sans-serif;
}

body {
	height: 100vh;
	width: 100%;
	background-color: (var(--primary));
}

.navbar {
	display: flex;
	align-items: center;
	height: 70px;
	background-color: brown;
	padding: 0 8%;
	box-shadow: var(--shadow);
}

.navbar-logo {
	cursor: pointer;
	color: white;
}

.navbar-list {
	width: 100%;
	text-align: right;
	padding-right: 2rem;
}

.navbar-list li {
	display: inline-block;
	margin: 0 1rem;
}

.navbar-list li a {
	font-size: 1rem;
	font-weight: 500;
	color: var(--white);
	text-decoration: none;
}

.navbar ul li a:hover {
	color: #f0a500;
}

.navbar ul li a:hover::after {
	width: 100%;
}

.profile-dropdown {
	position: relative;
	width: fit-content;
}

.profile-dropdown-btn {
	display: flex;
	align-items: center;
	justify-content: space-between;
	padding-right: 1rem;
	font-size: 0.9rem;
	font-weight: 500;
	width: 150px;
	border-radius: 50px;
	color: var(--white);
	/* background-color: white;
  box-shadow: var(--shadow); */
	cursor: pointer;
	border: 1px solid var(--secondary);
	transition: box-shadow 0.2s ease-in, background-color 0.2s ease-in,
		border 0.3s;
}

.profile-dropdown-btn:hover {
	background-color: var(--secondary-light-2);
	box-shadow: var(--shadow);
}

.profile-img {
	position: relative;
	width: 3rem;
	height: 3rem;
	border-radius: 50%;
	background: url(img/prof.jpg);
	background-size: cover;
}

.profile-img i {
	position: absolute;
	right: 0;
	bottom: 0.3rem;
	font-size: 0.5rem;
	color: var(--green);
}

.profile-dropdown-btn span {
	margin: 0 0.5rem;
	margin-right: 0;
}

.profile-dropdown-list {
	position: absolute;
	top: 68px;
	width: 220px;
	right: 0;
	background-color: var(--white);
	border-radius: 10px;
	max-height: 0;
	overflow: hidden;
	box-shadow: var(--shadow);
	transition: max-height 0.5s;
}

.profile-dropdown-list hr {
	border: 0.5px solid var(--green);
}

.profile-dropdown-list.active {
	max-height: 500px;
}

.profile-dropdown-list-item {
	padding: 0.5rem 0rem 0.5rem 1rem;
	transition: background-color 0.2s ease-in, padding-left 0.2s;
}

.profile-dropdown-list-item a {
	display: flex;
	align-items: center;
	text-decoration: none;
	font-size: 0.9rem;
	font-weight: 500;
	color: var(--black);
}

.profile-dropdown-list-item a i {
	margin-right: 0.8rem;
	font-size: 1.1rem;
	width: 2.3rem;
	height: 2.3rem;
	background-color: var(--secondary);
	color: var(--white);
	line-height: 2.3rem;
	text-align: center;
	margin-right: 1rem;
	border-radius: 50%;
	transition: margin-right 0.3s;
}

.profile-dropdown-list-item:hover {
	padding-left: 1.5rem;
	background-color: var(--secondary-light);
}

/*---------------------------*/
.story {
	padding: 3% 0;
	width: 80%;
	min-height: 100vh;
	background-color: #fff;
	text-align: center;
}

.row {
	display: flex;
	justify-content: center;
	flex-wrap: wrap;
	margin: 2% 0;
}

.img {
	width: 60%;
}

.row img {
	width: 400px;;
	border-radius: 15px;
}

.boxes {
	width: 35%;
	align-items: center;
	text-align: left;
}

.heading {
	color: rgb(232, 105, 14);
	font-size: 40px;
	font-size: 30px;
	text-align: center;
}

.sub-title {
	font-size: 30px;
	font-weight: 500;
	color: blue;
}

.para h3 {
	font-size: 20px;
	color: rgb(212, 203, 191);
	text-align: center;
}

.boxes h2 {
	font-size: 25px;
}

.boxes p {
	font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
	font-size: medium;
	color: #9c8c7a;
}

.img {
	width: 20%;
	height: 20%;
}

.btn {
	padding: 10px 37px;
	background-color: #9c8c7a;
	color: white;
	border: none;
	font-size: 18px;
	cursor: pointer;
}

.btn:hover {
	background-color: grey;
	color: black;
}

.btn1 {
	border-radius: 15px;
	letter-spacing: 1px;
	margin-top: 2%;
}
</style>
<title>Profile dropdown</title>
</head>
<body>
	<form>
		<nav class="navbar">
			<div class="navbar-logo">BOT</div>

			<ul class="navbar-list">
				<li><a href="/accounts">Accounts</a></li>
				<li><a href="#">Loan</a></li>
				<li><a href="#">Wealth&Insurance</a></li>
				<li><a href="#">Card</a></li>
				<li><a href="#">ContactUS</a></li>
			</ul>

			<div class="profile-dropdown">
				<div onclick="toggle()" class="profile-dropdown-btn">
					<div class="profile-img">
						<i class="fa-solid fa-circle"></i>
					</div>

					<span>Profile <i class="fa-solid fa-angle-down"></i>
					</span>
				</div>

				<ul class="profile-dropdown-list">
					<li class="profile-dropdown-list-item"><a href="#"> <i
							class="fa-regular fa-user"></i> Deposit
					</a></li>

					<li class="profile-dropdown-list-item"><a href="#"> <i
							class="fa-regular fa-envelope"></i> Withdraw
					</a></li>

					<li class="profile-dropdown-list-item"><a
						href="transactionfile"> <i class="fa-solid fa-chart-line"></i>
							Transcation History
					</a></li>

					<li class="profile-dropdown-list-item"><a href="#"> <i
							class="fa-solid fa-sliders"></i> Settings
					</a></li>

					<li class="profile-dropdown-list-item"><a href="#"> <i
							class="fa-regular fa-circle-question"></i> Help & Support
					</a></li>
					<hr />

					<li class="profile-dropdown-list-item"><a href="welcomepage">
							<i class="fa-solid fa-arrow-right-from-bracket"></i> Log out
					</a></li>
				</ul>
			</div>
		</nav>
	</form>
	<!-------------content------------>


	<div id="story">
		<br>

		<h2 class="heading">
			<i><b>BOT Internet Bank</b></i>
		</h2>
		<br>
		<div class="row">
			<div class="boxes">
				<h1 class="sub-title">Your financial journey starts here!</h1>
				<br>
				<P>Welcome to internet banking straight to your doorAll branches
					of State Bank of India are Internet Banking enabled. If you already
					have an account with us, ask your branch to give you Internet
					Banking.</P>
				<br>
				<button class=" btn btn1">help</button>
			</div>
			<div class="img">
				<img src="img/ledi.jpg" alt="">
			</div>
		</div>
	</div>



	<script>
     profileDropdownList = document.querySelector(".profile-dropdown-list");
    let btn = document.querySelector(".profile-dropdown-btn");
    
    let classList = profileDropdownList.classList;
    
    const toggle = () => classList.toggle("active");
    
    window.addEventListener("click", function (e) {
      if (!btn.contains(e.target)) classList.remove("active");
    });
    </script>
</body>
</html>