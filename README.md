
# Place

## Commands

### `init`

Der Client fragt hiermit die vollständige Karte an.

#### Beispiel

```json
{
  "command": "init",
  "key": "5251d829377e9590737d859d04bf3e0e17091e5cd62626c92e7af82d9efc602f",
}
```

### `paint`

Gibt dem Client die aktuelle, vollständige Karte (Antwort auf `init`).

#### Beispiel

```json
{
  "command": "paint",
    "data": {
    "cooldown": 123,
    "pixels": [[]]
  }
}
```

### `update`

Gibt dem Client an, welcher Pixel sich auf welche Farbe geändert hat.

#### Beispiel

```json
{
  "command": "update",
  "data": {
    "color": "#79ABF3",
    "position": {
      "x": 80,
      "y": 25
    }
  }
}
```

### `set`

Schickt dem Server eine Koordinate und eine Farbe und den Key aus der Session.

#### Beispiel

```json
{
  "command": "set",
  "data": {
      "color": "#5832BF",
      "position": {
        "x": 58,
        "y": 93
      }
  }
}
```

### `cooldown`

Schickt dem Client die dauer, die er warten muss, bis der nächste Pixel platziert werden darf (Antwort auf `set`).

#### Beispiel

```json
{
  "command": "cooldown",
  "data": {
    "seconds": 60
  }
}
```
## Fehler

### `Unbekannter Command`

Wird geworfen mit `code` 666, falls der übertragen Command unbekannt ist.

```json
{
 "code": 666,
 "error": "Command XY nicht gefunden"
}
```
