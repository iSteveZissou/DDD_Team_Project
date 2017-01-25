/*gameplayData  (gameID, totDraws, winner, totRounds, p1RoundsWon, p2 RoundsWon, p3 RoundsWon, p4 RoundsWon, p5 RoundsWon)*/

CREATE TABLE gameplayData (gameID INTEGER PRIMARY KEY, totDraws INTEGER, winner VARCHAR(17), totRounds INTEGER);

CREATE TABLE roundsWon (gameID INTEGER CONSTRAINT fk_id REFERENCES gameplayData(gameID), p1RoundsWon INTEGER, p2RoundsWon INTEGER, p3RoundsWon INTEGER, p4RoundsWon INTEGER, p5RoundsWon INTEGER);

// INSERT INTO gameplayData VALUES (1, 2, 'Player One', 7); 
// INSERT INTO gameplayData VALUES (2, 3, 'Player Three', 9);

// INSERT INTO roundsWon VALUES (10001, 4, 0, 2, 1, 0);
// INSERT INTO roundsWon VALUES (10002, 0, 0, 4, 2, 3);
