# Use Cases - Phishing Defender

## UC-01 – Spiel starten
**Akteure:** Spieler:in  
**Ziel:** Phishing Defender vom Hauptmenü aus starten  
**Vorbedingungen:** Spielesammlung läuft, Hauptmenü ist sichtbar  
**Trigger:** Klick auf "Phishing Defender" Kachel  

**Hauptablauf:**
1. Spieler klickt auf "Phishing Defender" im Launcher
2. System lädt Spielressourcen (Sounds, E-Mail-Datenbank)
3. System zeigt Startbildschirm mit Level-Auswahl
4. Spieler sieht: Level 1, Level 2, Level 3 (gesperrte Level grau)

**Alternativen/Fehlerfälle:**
- Ressourcen fehlen → Fehlermeldung "Spiel kann nicht geladen werden"
- Zurück-Button führt zum Launcher

**Nachbedingungen:** Phishing Defender Startmenü ist aktiv, Level wählbar

---

## UC-02 – Level starten
**Akteure:** Spieler:in  
**Ziel:** Ein Spiellevel beginnen  
**Vorbedingungen:** Startmenü aktiv, Level ist freigeschaltet  
**Trigger:** Klick auf Level-Button  

**Hauptablauf:**
1. Spieler wählt "Level 1" 
2. System zeigt 3-Sekunden Countdown ("3...2...1...START!")
3. System startet Spiel:
   - Score: 0 Punkte
   - Leben: 3 Herzen
   - Erste E-Mail fällt von oben
4. Timer für E-Mail beginnt (Level 1: 5 Sekunden)

**Alternativen:**
- Level gesperrt → "Vorheriges Level zuerst abschließen!"
- ESC während Countdown → zurück zur Level-Auswahl

**Nachbedingungen:** Spiel läuft, E-Mail wartet auf Klassifizierung

---

## UC-03 – E-Mail klassifizieren
**Akteure:** Spieler:in  
**Ziel:** E-Mail als "sicher" oder "phishing" einstufen  
**Vorbedingungen:** Level läuft, E-Mail ist sichtbar  
**Trigger:** A-Taste (sicher) oder L-Taste (phishing)  

**Hauptablauf:**
1. Spieler liest E-Mail Inhalt (z.B. "Ihre Bank benötigt...")
2. Spieler drückt A (sicher) oder L (phishing)
3. System prüft Antwort:
   - **Richtig:** +10 Punkte, "RICHTIG!" Sound, grüner Effekt
   - **Falsch:** -1 Leben, "FALSCH!" Sound, roter Effekt
4. System zeigt nächste E-Mail

**Alternativen:**
- Timer läuft ab (5s) → automatisch falsch, -1 Leben
- 5 richtige in Folge → Firewall-Bonus aktiviert
- 0 Leben → Game Over

**Nachbedingungen:** Score aktualisiert, nächste E-Mail oder Level-Ende

---

## UC-04 – Firewall-Bonus aktivieren
**Akteure:** System (automatisch)  
**Ziel:** Spieler für gute Leistung belohnen  
**Vorbedingungen:** 5 richtige Antworten in Folge  
**Trigger:** Automatisch nach 5. richtiger Antwort  

**Hauptablauf:**
1. System erkennt 5 richtige Antworten in Folge
2. "FIREWALL AKTIVIERT!" Nachricht + spezieller Sound
3. Alle E-Mails fallen 50% langsamer (3 Sekunden lang)
4. Blauer Gloweffekt um Spielfeld
5. Nach 3 Sekunden: normale Geschwindigkeit

**Nachbedingungen:** Normale Spielgeschwindigkeit, Kombo zurückgesetzt

---

## UC-05 – Level abschließen / Game Over
**Akteure:** System  
**Ziel:** Level beenden und Ergebnis zeigen  
**Vorbedingungen:** Alle E-Mails klassifiziert ODER 0 Leben  
**Trigger:** Letzte E-Mail bearbeitet ODER Leben = 0  

**Hauptablauf (Erfolg):**
1. Letzte E-Mail wurde klassifiziert
2. "LEVEL GESCHAFFT!" Nachricht + Erfolg-Sound
3. Ergebnis-Screen zeigt:
   - Endpunkte: XXX
   - Genauigkeit: XX% (richtige/gesamt)
   - "Nächstes Level freischalten?" (falls verfügbar)
4. Buttons: "Nächstes Level", "Level wiederholen", "Hauptmenü"

**Alternativen (Game Over):**
1. Leben = 0
2. "GAME OVER" Nachricht + trauriger Sound
3. Ergebnis wie oben, aber "Level wiederholen" statt "Nächstes Level"

**Nachbedingungen:** 
- Bei Erfolg: nächstes Level freigeschaltet
- Highscore gespeichert (optional)

---

## Technische Regeln
- **Level 1:** 10 E-Mails, 5s pro E-Mail, 2 Fehler erlaubt
- **Level 2:** 15 E-Mails, 3s pro E-Mail, 2 Fehler erlaubt  
- **Level 3:** 20 E-Mails, 2s pro E-Mail, 1 Fehler erlaubt
- **Steuerung:** A = Sicher, L = Phishing, SPACE = Pause, ESC = Menü
- **Punkte:** +10 richtig, +20 mit Firewall-Bonus
- **Assets:** Unter 10 MB, kurze WAV-Sounds