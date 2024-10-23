document.addEventListener('DOMContentLoaded', function() {
    fetchMenu();
	fetchMainContent();
	setEventsForMainContent();
});

function fetchMainContent() {
	fetch(MAIN_PAGE_GAMES_TBL_URL + getSeasonFromUrl())
		.then(response => response.text())
		.then(data => {
			document.querySelector('[data-game-list-section]').innerHTML = data;
		})
		.catch(err => console.log(err));
		
	fetch(MAIN_PAGE_SIDEBAR_STATS_DEFAULT_SCOPES + getSeasonFromUrl())
		.then(response => response.text())
		.then(data => {
			document.querySelector('[data-stats-section]').innerHTML = data;
		})
		.catch(err => console.log(err));
}

function setEventsForMainContent() {
	setMainContentTableEvents();
	setSidebarEvents();
}

function setMainContentTableEvents() {
	let tableElement = document.querySelector('[data-game-list-section]');
	
	tableElement.addEventListener('click', (e) => {
		//game button clicked
		if(e.target.hasAttribute('data-game-btn')) {
			gamePageButtonClicked(e.target);
			return;
		}
		
		//player name clicked
		if(e.target.hasAttribute('data-clickable-player')) {
			playerClicked(e.target);
			return;
		}
		
		//expandable header row clicked
		let closestTr = e.target.closest('tr');
		let closestTable = e.target.closest('table');
		if(closestTr != null && closestTr.hasAttribute('data-expandable-row-header'))
			mainTableHeaderClicked(closestTr);
		else if(closestTable != null && closestTable.hasAttribute('data-expandable-table'))
			mainTableHeaderClicked(closestTable.closest('tr'));
	});
	
	tableElement.addEventListener('keypress', (e) => {
		if(e.keyCode != 13)
			return;
		
		if(e.target.hasAttribute('data-game-btn'))
			return;
			
		if(e.target.hasAttribute('data-clickable-player')) {
			playerClicked(e.target);
			return;
		}
		
		let closestTr = e.target.closest('tr');
		let closestTable = e.target.closest('table');
		if(closestTr != null && closestTr.hasAttribute('data-expandable-row-header'))
			mainTableHeaderClicked(closestTr);
		else if(closestTable != null && closestTable.hasAttribute('data-expandable-table'))
			mainTableHeaderClicked(closestTable.closest('tr'));
	});
}

function setSidebarEvents() {
	let sidebarElement = document.querySelector('[data-stats-section]');
	
	sidebarElement.addEventListener('click', e => {
		let clickedElement = e.target;
		if(clickedElement.hasAttribute('data-clickable-team-abr'))
			teamNameClicked(clickedElement);
		else if(clickedElement.hasAttribute('data-radio-scope'))
			sidebarRadioClicked(clickedElement);
		else if(clickedElement.closest('div') && clickedElement.closest('div').hasAttribute('data-playoff-bracket-header'))
			playoffBracketHeaderClicked(clickedElement.closest('div'));
		else if(clickedElement.closest('tr') && clickedElement.closest('tr').hasAttribute('data-game-link'))
			playoffGameClicked(clickedElement.closest('tr'));
	});
	
	sidebarElement.addEventListener('keypress', e => {
		if(e.keyCode != 13)
			return;
		
		let pressedElement = e.target;
		if(pressedElement.hasAttribute('data-clickable-team-abr')) {
			teamNameClicked(pressedElement);
			return;
		}
			
		if(pressedElement.hasAttribute('data-subnav-label')) {
			let radio = document.getElementById(pressedElement.getAttribute('for'));
			if(!radio.checked) 
				sidebarRadioClicked(radio);
			return;
		}
		
		let bracketHeader = pressedElement.closest('div');
		if(bracketHeader && bracketHeader.hasAttribute('data-playoff-bracket-header')) {
			playoffBracketHeaderClicked(bracketHeader);
			return;
		}
		
		let gameRow = pressedElement.closest('tr');
		if(gameRow && gameRow.hasAttribute('data-game-link')) {
			playoffGameClicked(gameRow);
			return;
		}
	});
}

function gamePageButtonClicked(btn) {
	gameId = btn.getAttribute('data-game-btn');
	alert(gameId);
	//TODO game page
}

function playerClicked(element) {
	playerId = element.getAttribute('data-clickable-player');
	alert(playerId);
	//TODO player page
}

function teamNameClicked(element) {
	teamAbr = element.getAttribute('data-clickable-team-abr');
	alert(teamAbr);
	//TODO team page
}

function playoffGameClicked(element) {
	gameId = element.getAttribute('data-game-link');
	alert(gameId);
	//TODO game page
}

function playoffBracketHeaderClicked(header) {
	let headerState = header.getAttribute('data-playoff-bracket-header');
	if(headerState === 'hidden') {
		//allow only one bracket to be expanded at the same time
		document.querySelectorAll('[data-playoff-bracket-header="expanded"]').forEach(expandedHeader => {
			expandedHeader.setAttribute('data-playoff-bracket-header', 'hidden');
		});
		header.setAttribute('data-playoff-bracket-header', 'expanded');
	} else if(headerState === 'expanded') {
		header.setAttribute('data-playoff-bracket-header', 'hidden');
	} else {
		header.setAttribute('data-playoff-bracket-header', 'hidden');
	}
}

function sidebarRadioClicked(element) {
	let scopeValue = element.getAttribute('data-radio-value');
	let scopeType = element.getAttribute('data-radio-scope');
	
	let url = '';
	if(scopeType === 'season')
		url = MAIN_PAGE_SIDEBAR_STATS_BY_SEASON_SCOPE;
	else if(scopeType === 'regulation')
		url = MAIN_PAGE_SIDEBAR_STATS_BY_REGULATION_SCOPE;
		
	if(url === '') {
		console.log('something went wrong with sidebar navigation');
		return;
	}
	
	fetch(url + activeSeason() + '/' + scopeValue)
		.then(response => response.text())
		.then(data => document.querySelector('[data-stats-section]').innerHTML = data)
		.catch(err => console.log(err));
}

function mainTableHeaderClicked(headerRow) {
	var rowId = headerRow.getAttribute('data-expandable-header-id');
	var dataRow = document.querySelector('[data-expandable-content-id="' + rowId + '"]');
	
	if(dataRow.getElementsByTagName('td').length == 0) {
		fetch(GAME_KEY_EVENTS_TD_URL + rowId)
			.then(result => result.text())
			.then(data => dataRow.innerHTML = data)
			.catch(err => console.log(err));
	}
	
	if(dataRow.style.display === 'none') {
		dataRow.style.display = 'block';
	} else {
		dataRow.style.display = 'none';
	}
}