# Data Types

## Null

`null` is the default type for variables that have not been assigned a value.

```java
var x; // x has the value null
```

The Java equivalent is `Null`.

## Boolean

`boolean` are either be `true` or `false`

```java
var isRaining = true;
var isSnowing = false;
```

The Java equivalent is `Boolean`.

## Int

The `int` type is a number.

```java
var hoursOfSleep = 0;
```

The Java equivalent is `Integer`.

## Float

The `float` type is a floating point number.

```java
var gpa = 1.0;
```

The Java equivalent is `Double`.

## String

The `string` type is a list of characters.

```java
var speech = "I'm tired!";
```

The Java equivalent is `String`.

## Resource

The `resource` type is a custom data type specific to Testing.

```java
var scriptID = namespace:path;
```

The Java equivalent is `Identifier`.

## List

**Indexing lists is currently not supported**

The `list` type is a series of values.

```java
var hrsOfSleepInPastWeek = [1,4,6,5,7,3,2];
```

The Java equivalent is `ArrayList<Object>`.

## Dictionary

**Indexing dictionaries is currently not supported**

The `dictionary` type is a map of values.

```js
var dict = {
    "a": 1,
    "b": 2,
    "c": 3
};
```

The Java equivalent is `Map<?, ?>`.