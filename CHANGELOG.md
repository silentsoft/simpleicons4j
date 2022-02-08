# 1.2.0 (8 Feb 2022)

## Enhancements
- Supports [simple-icons v6.9.0](https://github.com/simple-icons/simple-icons/releases/tag/6.9.0)

# 1.1.0 (30 Jan 2022)

## Enhancements
- Supports [simple-icons v6.8.0](https://github.com/simple-icons/simple-icons/releases/tag/6.8.0)

# 1.0.0 (30 Jan 2022)

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

## Class Naming Exceptions
- [`.NET`](https://simpleicons.org/?q=.NET) is available as `DotnetIcon`.
- [`/e/`](https://simpleicons.org/?q=%2Fe%2F) is available as `EIcon`.
- [`1001Tracklists`](https://simpleicons.org/?q=1001Tracklists) is available as `OnethousandonetracklistsIcon`.
- [`1Password`](https://simpleicons.org/?q=1Password) is available as `OnepasswordIcon`.
- [`3M`](https://simpleicons.org/?q=3M) is available as `ThreemIcon`.
- [`42`](https://simpleicons.org/?q=42) is available as `FourtytwoIcon`.
- [`4chan`](https://simpleicons.org/?q=4chan) is available as `FourchanIcon`.
- [`4D`](https://simpleicons.org/?q=4D) is available as `FourdIcon`.
- [`500px`](https://simpleicons.org/?q=500px) is available as `FivehundredpxIcon`.