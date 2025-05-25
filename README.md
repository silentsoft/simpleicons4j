# SimpleIcons4J

[![Maven Central](https://img.shields.io/maven-central/v/org.silentsoft/simpleicons4j)](https://search.maven.org/artifact/org.silentsoft/simpleicons4j)
[![Build Status](https://app.travis-ci.com/silentsoft/simpleicons4j.svg?branch=main)](https://app.travis-ci.com/silentsoft/simpleicons4j)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=silentsoft_simpleicons4j&metric=alert_status)](https://sonarcloud.io/dashboard?id=silentsoft_simpleicons4j)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=silentsoft_simpleicons4j&metric=coverage)](https://sonarcloud.io/dashboard?id=silentsoft_simpleicons4j)
[![Hits](https://hits.sh/github.com/silentsoft/simpleicons4j.svg)](https://hits.sh/github.com/silentsoft/simpleicons4j/)

`SimpleIcons4J` is a Java implementation of the [simple-icons](https://www.npmjs.com/package/simple-icons) JavaScript library and is inspired by [simpleicons.org](https://simpleicons.org).

## Usage
```java
Icon icon = SimpleIcons.get("simpleicons");
```

or creating instance explicitly like:

```java
Icon icon = new SimpleiconsIcon();
```

## Object Structure
```
Icon{
    title="Simple Icons",
    slug="simpleicons",
    hex="111111",
    source="https://simpleicons.org/",
    svg="<svg role=\"img\" viewBox=\"0 0 24 24\" xmlns=\"http://www.w3.org/2000/svg\">...</svg>",
    path="M12 12v-1.5c-2.484 ...",
    guidelines="https://simpleicons.org/styleguide",
    license=License{
        type="...",
        url="https://example.com/"
    }
}
```

## Slugs
Supported slugs can be found [here](slugs.md).

## Testing
```shell
$ mvn clean test-compile exec:java test
```

## Packaging
```shell
$ mvn clean test-compile exec:java package
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please note we have a [CODE_OF_CONDUCT](https://github.com/silentsoft/simpleicons4j/blob/main/CODE_OF_CONDUCT.md), please follow it in all your interactions with the project.

## License
Please refer to [LICENSE](https://github.com/silentsoft/simpleicons4j/blob/main/LICENSE.txt).
