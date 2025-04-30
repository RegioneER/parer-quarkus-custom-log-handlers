# Quarkus custom log handlers

Fonte template redazione documento:  https://www.makeareadme.com/.


# Descrizione

Questo progetto nasce dall'idea di realizzare una libreria per applicazioni cloud basate su tecnologia [Quarkus](https://quarkus.io/) grazie al quale è possibile introdurre dei custom log handlers con implementazioni specifiche al fine di risolvere determinate problematiche nella gestione dell'output dei log applicativi.

# Installazione

Come già specificato nel paragrafo precedente [Descrizione](# Descrizione) si tratta di un progetto di tipo "libreria", quindi un modulo applicativo utilizzato attraverso la definzione della dipendenza Maven secondo lo standard previsto (https://maven.apache.org/): 

```xml
<dependency>
    <groupId>${project.groupId}</groupId>
    <artifactId>quarkus-custom-log-handlers</artifactId>
    <version>${versione}</version>
</dependency>
```

# Utilizzo

Di seguito alcune indicazioni sull'utilizzo della libreria in ambito di applicazioni Quarkus-based.

## Indicizzazione libreria

Necessario introdurre il plugin maven Jandex: 

```xml
<plugin>
    <groupId>org.jboss.jandex</groupId>
    <artifactId>jandex-maven-plugin</artifactId>
    <version>${jandex-maven-plugin.version}</version>
    <executions>
      <execution>
           <id>make-index</id>
           <goals>
                 <goal>jandex</goal>
          </goals>
      </execution>
    </executions>
</plugin>
```

che permette di indicizzare librerie contenenti componenti "compliant" agli standard Quarkus.
Tale indicizzazione è possibile attraverso la seguente configurazione (su application.propries o application.yaml):

```yaml
quarkus:
   index-dependency:
    quarkuslogger:
      group-id: "it.eng.parer"
      artifact-id: "quarkus-custom-log-handlers"
```

## Single line stack trace

Al fine di rispondere ad una particolare esigenza, legata all'indicizzatore di log su piattaforma [Graylog](https://graylog.org/) e alla necessità di leggibilità dei log senza perdere alcuna informazione e mantenendo i log in formato testo "standard" (non JSON) si introduce un particolare handler a tale scopo.

L'handler **StackTraceSingleLineMsgConsoleHandler** cattura i log in cui è presente un Thrown ed effettua un "cropping" del multi-line output in una singola riga all'interno del campo "message" (standard) del log prodotto.


L'implementazione dei custom log handlers al suo interno nasce dall'idea proposta dal seguente post : https://stackoverflow.com/a/73832653/20111624.

### Configurazione

Di seguito la configurazione per l'attivazione dell'handler:

```properties
parer.quarkus.config.singleline-message.enabled=true
```

```yaml
parer:
    quarkus:
        config:
            singleline-message:
                enabled: true
```

# Supporto

Mantainer del progetto è [Engineering Ingegneria Informatica S.p.A.](https://www.eng.it/).

# Contributi

Se interessati a crontribuire alla crescita del progetto potete scrivere all'indirizzo email <a href="mailto:areasviluppoparer@regione.emilia-romagna.it">areasviluppoparer@regione.emilia-romagna.it</a>.

# Credits

Progetto di proprietà di [Regione Emilia-Romagna](https://www.regione.emilia-romagna.it/) sviluppato a cura di [Engineering Ingegneria Informatica S.p.A.](https://www.eng.it/).

# Licenza

Questo progetto è rilasciato sotto licenza GNU Affero General Public License v3.0 or later ([LICENSE.txt](LICENSE.txt)).

# Appendice

- Quarkus Jandex : https://quarkus.io/guides/cdi-reference#how-to-generate-a-jandex-index
