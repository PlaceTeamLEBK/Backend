# Place

## Commands

### `init`

Der Client fragt hiermit die vollständige Karte an.

#### Beispiel

```json
{
  "command": "init",
  "key": "5251d829377e9590737d859d04bf3e0e17091e5cd62626c92e7af82d9efc602f",
  "timeStamp": 1675328548
}
```

Mögliche Varianten
```json
{
  "command": "init",
  "timeStamp": 1675328548
}
```

### `update`

Gibt dem Client an, welcher Pixel sich auf welche Farbe geändert hat.

#### Beispiel

```json
{
  "command": "update",
  "timeStamp": 1675328548,
  "data": {
    "pixels": [
      {
        "color": "#79ABF3",
        "position": {
          "x": 80,
          "y": 25
        }
      },
      {
        "color": "#943724",
        "position": {
          "x": 53,
          "y": 43
        }
      },
      {
        "color": "#8537AB",
        "position": {
          "x": 205,
          "y": 24
        }
      }
    ]
  }
}
```

### `set`

Schickt dem Server eine Koordinate und eine Farbe und den Key aus der Session

#### Beispiel

```json
{
  "command": "set",
  "key": "5251d829377e9590737d859d04bf3e0e17091e5cd62626c92e7af82d9efc602f",
  "timeStamp": 1675328548,
  "data": {
    "pixel": {
      "color": "#5832BF",
      "position": {
        "x": 58,
        "y": 93
      }
    }
  }
}
```

### `cooldown`

Schickt dem Client die dauer, die er warten muss, bis der nächste Pixel platziert werden darf (Antwort auf `set`)

#### Beispiel

```json
{
  "command": "set",
  "timeStamp": 1675328548,
  "data": {
    "seconds": 60
  }
}
```
