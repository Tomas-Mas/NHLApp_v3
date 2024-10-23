function fetchMenu() {
	fetch(MENUBAR_URL + getSeasonFromUrl())
		.then(response => response.text())
		.then(data => {
			document.querySelector('[data-page-header]').innerHTML = data;
			setEventsForMenubar();
		})
		.catch(err => console.log('menubar fetching has errors: \n' + err));
}

function setEventsForMenubar() {
	//NHL logo in title clicked
	document.querySelector('[data-title-container]').addEventListener('click', (e) => {
		if(e.target.matches('img'))
			window.location.href = MAIN_PAGE_URL + activeSeason();
	});
			
	//NHL div with logo activated by keyboard while in focus (enter, spacebar)
	document.querySelector('[data-title-container] div').addEventListener('keypress', (e) => {
		if(e.keyCode === 13 || e.keyCode === 32)
			window.location.href = MAIN_PAGE_URL + activeSeason();
	});
	
	//season selector changed (refresh page with new season)
	document.querySelector('[data-season-selector]').addEventListener('change', (e) => {
		window.location.href = getUrlWithNewSeason(e);
	});
	
	//main navigation container clicked
	document.querySelector('[data-menu-nav-container]').addEventListener('click', (e) => {
		if(!e.target.matches('button'))
			return;
			
		let destination = e.target.textContent;
		if(destination === 'Home') {
			window.location.href = MAIN_PAGE_URL + activeSeason();
		}
	});
}

//TODO so far only works for home page
function getSeasonFromUrl() {
	let url = window.location.href;
	let season = url.substr(url.indexOf('/home/') + 6, 8);
	return season;
}

//TODO so far only works for home page
function getUrlWithNewSeason(e) {
	let selector = e.target;
	let newSeason = selector[selector.selectedIndex].text;
	let url = window.location.href;
	return url.substr(0, url.indexOf('/home/') + 6) + newSeason;
}

//Gets season from season selector element
function activeSeason() {
	let selectorEl = document.querySelector('[data-season-selector]');
	let index = selectorEl.selectedIndex
	let season = selectorEl[index].text;
	return season;
}