<img src="src/docs/quarkus.png" width="300">
<br/><br/>

# Quarkus custom log handlers 

Questo progetto nasce dall'idea di realizzare una libreria per applicazioni cloud basate su tecnologia [Quarkus](https://quarkus.io/) grazie al quale è possibile introdurre dei custom log handlers con implementazion specifiche al fine di risolvere determinate problematiche nella gestione dell'output dei log.

## Single line stack trace

Al fine di rispondere ad una particolare esigenza, legata all'indicizzatore di log Graylog e alla necessità di leggibilità dei log senza però perdere alcuna informazione e mantenendo i log in formato testo "standard" (non JSON) si introduce un particolare handler a tale scopo.
L'handler **StackTraceSingleLineMsgConsoleHandler** cattura i log in cui è presente un Thrown ed effettua un "cropping" del multi-line output in una singola riga all'interno del campo "message" (standard) del log prodotto.


L'implementazione dei custom log handlers al suo interno nasce dall'idea proposta dal seguente post : https://stackoverflow.com/a/73832653/20111624.

