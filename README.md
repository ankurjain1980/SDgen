# SDgen
A random data generator to produce realistic data files for multiple file types (e.g. csv, log, json)

## Installation
If you're using Maven, just add the following to your `pom.xml`
```xml
<dependency>
    <groupId>au.com.anthonybruno</groupId>
    <artifactId>SDGen</artifactId>
    <version>0.1.0</version>
</dependency>
```

Alternatively, grab the [latest jar]() and chuck it on your class path.

## Usage
There are two main ways to use SDGen to generate random data. The first way is
to use an annotated plain old Java object like so:

```java
CsvSettings csvSettings = new CsvSettings();
csvSettings.setRows(2);
new Gen().use(Person.class).asCsv(csvSettings).toFile("output.csv");
```


Where the Person class looks like this:

```java
public class Person {

   @Field(generator=NameGenerator.class) 
   private final String name; 
   
   @Field(min=18, max=70)
   private final int age;
   
   public Person(String name, int age) {
        this.name = name; 
        this.age = age;
   }
   
}
```

`output.csv` would look something like this:

```java
name, age
Bob, 40
Ashley, 22
```

Here is the same example using a fluent builder:

```java
CsvSettings csvSettings = new CsvSettings();
csvSettings.setRows(2);
new Gen()
    .addField("Name", new NameGenerator())
    .addField("Age", new IntGenerator(18, 70)
    .asCsv(csvSettings)
    .toFile("output.csv");
    
    
```

## Acknowledgements
