# Place

## Commands

* `init`
  * Gibt die Karte zurück
  * Wird vom Server an den Client gesendet, sobald das WebSocket verbunden wird
  * Beispiel: `{"command": "init", "canvas": [["#342ABF", "#9629DE"], [...], ...]}`
* `update`
  * Gibt dem Client an, welcher Pixel sich auf welche Farbe geändert hat
  * Beispiel: `{"command": "update", "x": 7, "y": 20, "color": "#BC942AF"}`
* `set`
  * Schickt dem Server eine Koordinate und eine Farbe und den Key aus der Session
  * Beispiel: `{"command": "set", "x": 7, "y": 20, "color": "#BC942AF", "key": "5251d829377e9590737d859d04bf3e0e17091e5cd62626c92e7af82d9efc602f"}`
* `cooldown`
  * Schickt dem Client die dauer, die er warten muss, bis der nächste Pixel platziert werden darf (Antwort auf `set`)
  * Beispiel: `{"command": "cooldown", "seconds": 60}`